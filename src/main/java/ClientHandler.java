import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class ClientHandler extends Thread {

    String directoryPath;
    final Socket clientSocket;
    InputStream inputStream;
    OutputStream outputStream;

    // Status
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
    String contentEncoding = "";
    final String contentText = "Content-Type: text/plain" + CRLF;
    final String contentJson = "Content-Type: application/json" + CRLF;
    final String contentOctet = "Content-Type: application/octet-stream" + CRLF;

    public ClientHandler(Socket s, String directoryPath) {
        this.clientSocket = s;
        this.directoryPath = directoryPath;
    }

    @Override
    public void run() {
        try {
            InputStream inputStream = clientSocket.getInputStream();
            OutputStream outputStream = clientSocket.getOutputStream();

            Scanner input = new Scanner(inputStream, StandardCharsets.UTF_8);
            input.useDelimiter(CRLF);

            String start = input.nextLine();
            String method = start.split(" ")[0];
            String path = start.split(" ")[1];
            String version = start.split(" ")[2];

            System.out.println("firstLine :: " + method + " " + path + " " + version);
            System.out.println("client requesting connection to resource :: " + path);

            // Parse headers
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
                    case "content-encoding":
                        contentEncoding = headerValue;
                        break;
                    default:
                        break;
                }
            }

            String response = "";
            String contentBody = "";
            if (path.equals("/")) {
                response = httpOkResponse + CRLF;
                outputStream.write(response.getBytes(StandardCharsets.UTF_8));
                System.out.println("server response :: 200 OK");

            } else if (path.startsWith("/echo/")) {
                String echo = path.replaceFirst("/echo/", "");
                response = httpOkResponse + contentText + contentLength + echo.length() + CRLF + CRLF + echo;
                outputStream.write(response.getBytes(StandardCharsets.UTF_8));
                System.out.println("server response :: 200 OK");

            } else if (path.equals("/user-agent")) {
                contentBody = userAgent;
                response = httpOkResponse + contentText + contentLength + contentBody.length() + CRLF + CRLF + contentBody;
                outputStream.write(response.getBytes(StandardCharsets.UTF_8));
                System.out.println("server response :: 200 OK");

            } else if (path.startsWith("/files")) {
                String filePath = path.replaceFirst("/files/", "");
                File file = new File(directoryPath + filePath);

                if (file.exists()) {
                    contentBody = new String(Files.readAllBytes(Paths.get(file.toURI())));
                    response = httpOkResponse + contentOctet + contentLength + contentBody.length() + CRLF + CRLF + contentBody;
                    outputStream.write(response.getBytes(StandardCharsets.UTF_8));
                    System.out.println("server response :: 200 OK");
                } else {
                    response = http404Response + CRLF;
                    outputStream.write(response.getBytes(StandardCharsets.UTF_8));
                    System.out.println("server response :: 404 Not Found");
                }

            } else {
                response = http404Response + CRLF;
                outputStream.write(response.getBytes(StandardCharsets.UTF_8));
                System.out.println("server response :: 404 Not Found");
            }

            this.clientSocket.close();
        } catch (IOException e) {
            System.out.println("IOException: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
