import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Main {
  public static void main(String[] args) {

    final String CRLF = "\r\n";
    String httpOkResponse = "HTTP/1.1 200 OK" + CRLF;
    String http404Response = "HTTP/1.1 404 Not Found" + CRLF;

    // You can use print statements as follows for debugging, they'll be visible when running tests.
    System.out.println("Logs from your program will appear here!");

    // Uncomment this block to pass the first stage
    //
    ServerSocket serverSocket = null;
    Socket clientSocket = null;

    try {
      serverSocket = new ServerSocket(4221);
      serverSocket.setReuseAddress(true);
      clientSocket = serverSocket.accept(); // Wait for connection from client.
      System.out.println("accepted new connection");

      InputStream clientInputStream  = clientSocket.getInputStream();
      OutputStream clientOutputStream = clientSocket.getOutputStream();

      Scanner input = new Scanner(clientInputStream, StandardCharsets.UTF_8);
      input.useDelimiter(CRLF);

      String start = input.nextLine();
      String method = start.split(" ")[0];
      String path = start.split(" ")[1];
      String version = start.split(" ")[2];

      System.out.println("firstLine :: " + method + " " + path + " " + version);
      System.out.println("client requesting connection to resource :: " + path);

      switch (path) {
        case "/":
          clientOutputStream.write(httpOkResponse.getBytes(StandardCharsets.UTF_8));
          break;
        default:
          clientOutputStream.write(http404Response.getBytes(StandardCharsets.UTF_8));
      }

      clientOutputStream.flush();
      clientOutputStream.close();
    } catch (IOException e) {
      System.out.println("IOException: " + e.getMessage());
    }
  }
}
