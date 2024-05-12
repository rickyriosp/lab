import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        // Get passed arguments
        String directoryPath = "";
        if (args.length > 1 && args[0].equals("--directory") && args[1] != null) {
            directoryPath = args[1].endsWith("/") ? args[1] : args[1] + "/";
        }

        // Create a TCP/IP socket
        ServerSocket serverSocket = null;
        Socket clientSocket = null;

        try {
            // Create a TCP/IP socket
            serverSocket = new ServerSocket(4221);
            serverSocket.setReuseAddress(true);
            System.out.println("Server is running on port 4221...");

            while (true) {
                // Wait for connection
                System.out.println("Waiting for a connection...");
                clientSocket = serverSocket.accept(); // Wait for connection from client.
                System.out.printf("Connection from %s has been established.\n", clientSocket.getRemoteSocketAddress());

                Thread newClient = new ClientHandler(clientSocket, directoryPath);
                newClient.start();
            }
        } catch (IOException e) {
            System.out.println("IOException: " + e.getMessage());
        } finally {
            // Clean up the server socket
            try {
                serverSocket.close();
            } catch (IOException e) {
                System.out.println("IOException: " + e.getMessage());
            }
            System.out.println("Server has been shut down.");
        }
    }
}
