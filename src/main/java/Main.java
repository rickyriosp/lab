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
    final String httpOkResponse = "HTTP/1.1 200 OK" + CRLF;
    final String http404Response = "HTTP/1.1 404 Not Found" + CRLF;

    // Headers
    String host = "";
    String userAgent = "";
    String accept = "";
    String acceptLanguage = "";
    String acceptEncoding = "";
    String referer = "";
    String connection = "";
    String contentType = "Content-Type: ";
    String contentLength = "Content-Length: ";
    final String contentText = "Content-Type: text/plain" + CRLF;
    final String contentJson = "Content-Type: application/json" + CRLF;


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

      while (input.hasNextLine()) {
        String header = input.nextLine();
        if (header.isBlank() || header.isEmpty()) break;

        String headerName = header.split(":")[0].trim();
        String headerValue = header.split(":")[1].trim();
        switch (headerName.toLowerCase()) {
          case "host":
            host = headerValue;
            break;
          case "user-agent":
            userAgent = headerValue;
            break;
          case "accept":
            accept = headerValue;
            break;
          case "accept-language":
            acceptLanguage = headerValue;
            break;
          case "accept-encoding":
            acceptEncoding = headerValue;
          case "content-type":
            contentType = headerValue;
            break;
          default:
            break;
        }
      }

      String response = "";
      if (path.equals("/")) {
        response = httpOkResponse + CRLF;
        clientOutputStream.write(response.getBytes(StandardCharsets.UTF_8));
        System.out.println("server response :: 200 OK");

      } else if (path.startsWith("/echo/")) {
        String echo = path.replaceFirst("/echo/", "");
        response = httpOkResponse + contentText + contentLength + echo.length() + CRLF + CRLF + echo;
        clientOutputStream.write(response.getBytes(StandardCharsets.UTF_8));
        System.out.println("server response :: 200 OK");

      } else if (path.equals("/user-agent")) {
        response = httpOkResponse + contentText + contentLength + userAgent.length() + CRLF + CRLF + userAgent;
        clientOutputStream.write(response.getBytes(StandardCharsets.UTF_8));
        System.out.println("server response :: 200 OK");
        
      } else {
        response = http404Response + CRLF;
        clientOutputStream.write(response.getBytes(StandardCharsets.UTF_8));
        System.out.println("server response :: 404 Not Found");
      }

      clientOutputStream.flush();
      clientOutputStream.close();
    } catch (IOException e) {
      System.out.println("IOException: " + e.getMessage());
    }
  }
}
