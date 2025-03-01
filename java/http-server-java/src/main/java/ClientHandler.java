import java.io.*;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.StringTokenizer;
import java.util.zip.GZIPOutputStream;

public class ClientHandler extends Thread {

    String directoryPath;
    final Socket clientSocket;

    // Status
    final String CRLF = "\r\n";
    final String httpOkResponse = "HTTP/1.1 200 OK" + CRLF;
    final String http201Response = "HTTP/1.1 201 Created" + CRLF;
    final String http404Response = "HTTP/1.1 404 Not Found" + CRLF;

    // Headers
    String host = "";
    String userAgent = "";
    String accept = "";
    String acceptLanguage = "";
    String acceptEncoding = "";
    String referer = "";
    String connection = "";
    String contentType = "";
    String contentLength = "";
    String contentEncoding = "";
    String contentEncodingStr = "Content-Encoding: ";
    String contentTypeStr = "Content-Type: ";
    String contentLengthStr = "Content-Length: ";
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

            int bufferSize = 1024;
            char[] buffer = new char[bufferSize];
            StringBuilder input = new StringBuilder();
            InputStreamReader in = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
            for (int numRead; (numRead = in.read(buffer, 0, bufferSize)) > 0;) {
                input.append(buffer, 0, numRead);
                if (numRead < bufferSize) break;
            }

            System.out.println("Incoming Request:\n" + input.toString());

            StringTokenizer inToken = new StringTokenizer(input.toString(), CRLF);
            String start = inToken.nextToken(CRLF);
            String method = start.split(" ")[0];
            String path = start.split(" ")[1];
            String version = start.split(" ")[2];

            //System.out.println("firstLine :: " + method + " " + path + " " + version);
            System.out.println("client requesting connection to resource :: " + path);

            String[] headersAndBody = inToken.nextToken("").split(CRLF+ CRLF);
            String headers = headersAndBody[0];
            StringTokenizer headersToken = new StringTokenizer(headers, CRLF);

            // Parse headers
            while (headersToken.hasMoreTokens()) {
                String header = headersToken.nextToken(CRLF);
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
                    case "content-length":
                        contentLength = headerValue;
                        break;
                    case "content-encoding":
                        contentEncoding = headerValue;
                        break;
                    case "referer":
                        referer = headerValue;
                        break;
                    case "connection":
                        connection = headerValue;
                        break;
                    default:
                        break;
                }
            }

            // Body
            String contentBody = headersAndBody.length > 1 ? headersAndBody[1] : "";


            String response = "";
            String responseBody = "";

            // Select the first valid encoding, otherwise do not send content encoding in response
            for (String encoding : acceptEncoding.split(",")) {
                if (Encodings.getByName(encoding.trim()) != null) {
                    contentEncodingStr += encoding.trim() + CRLF;
                    break;
                }
            }
            contentEncodingStr = contentEncodingStr.endsWith(CRLF) ? contentEncodingStr : "";

            if (path.equals("/")) {
                // Root path response
                response = httpOkResponse + CRLF;
                outputStream.write(response.getBytes(StandardCharsets.UTF_8));
                System.out.println("server response :: 200 OK");

            } else if (path.startsWith("/echo/")) {
                // Echo path response
                responseBody = path.replaceFirst("/echo/", "");
                contentLengthStr += responseBody.length() + CRLF;

                byte[] compressed = null;
                ByteBuffer byteBuffer;
                if (contentEncodingStr.contains("gzip")) {
                    compressed = GzipUtil.compress(responseBody, StandardCharsets.UTF_8.toString());
                    contentLengthStr = "Content-Length: " + compressed.length + CRLF;

                    response = httpOkResponse + contentText + contentLengthStr + contentEncodingStr + CRLF;
                    byteBuffer = ByteBuffer.allocate(response.length() + compressed.length);
                    byteBuffer.put(response.getBytes());
                    byteBuffer.put(compressed);
                } else {
                    response = httpOkResponse + contentText + contentLengthStr + contentEncodingStr + CRLF + responseBody;
                    byteBuffer = ByteBuffer.allocate(response.length() + responseBody.length());
                    byteBuffer.put(response.getBytes());
                }
                //String testCompression = GzipUtil.decompress(compressed, StandardCharsets.UTF_8.toString());

                outputStream.write(byteBuffer.array());
                System.out.println("server response :: 200 OK");

            } else if (path.equals("/user-agent")) {
                // User Agent path response
                responseBody = userAgent;
                contentLengthStr += responseBody.length() + CRLF;
                response = httpOkResponse + contentText + contentLengthStr + CRLF + responseBody;
                outputStream.write(response.getBytes(StandardCharsets.UTF_8));
                System.out.println("server response :: 200 OK");

            } else if (path.startsWith("/files")) {
                // Files path response
                String fileName = path.replaceFirst("/files/", "");
                File file = new File(directoryPath + fileName);

                if (method.equals("GET")) {
                    if (file.exists()) {
                        try {
                            responseBody = new String(Files.readAllBytes(Paths.get(file.toURI())));
                        } catch (IOException e) {
                            System.out.println("[ERROR]: an error occurred trying to access the file: " + file.toURI());
                        }
                        contentLengthStr += responseBody.length() + CRLF;
                        response = httpOkResponse + contentOctet + contentLengthStr + CRLF + responseBody;
                        outputStream.write(response.getBytes(StandardCharsets.UTF_8));
                        System.out.println("server response :: 200 OK");
                    } else {
                        response = http404Response + CRLF;
                        outputStream.write(response.getBytes(StandardCharsets.UTF_8));
                        System.out.println("server response :: 404 Not Found");
                    }
                } else if (method.equals("POST")) {
                    try {
                        file.createNewFile();
                        Files.write(Paths.get(file.toURI()), contentBody.getBytes());
                    } catch (IOException e) {
                        System.out.println("[ERROR]: an error occurred trying to create the file: " + file.toURI());
                    }

                    response = http201Response + CRLF;
                    outputStream.write(response.getBytes(StandardCharsets.UTF_8));
                    System.out.println("server response :: 201 Created");
                }

            } else {
                // Default 404 Not Found response for unrecognized paths
                response = http404Response + CRLF;
                outputStream.write(response.getBytes(StandardCharsets.UTF_8));
                System.out.println("server response :: 404 Not Found");
            }

            // Close the connection to the client
            this.clientSocket.close();
        } catch (IOException e) {
            System.out.println("IOException: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
