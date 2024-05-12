import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class GzipUtil {

    public static String compress(String str, String inEncoding) {
        if (str == null || str.length() == 0) {
            return str;
        }

        try {
            ByteArrayOutputStream outStream = new ByteArrayOutputStream();
            GZIPOutputStream gzipOS = new GZIPOutputStream(outStream);
            gzipOS.write(str.getBytes(inEncoding));
            gzipOS.close();

            return new String(Base64.getEncoder().encode(outStream.toByteArray()));
        } catch (IOException e) {
            System.out.println("IOException: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    public static String decompress(String str, String outEncoding) {
        if (str == null || str.length() == 0) {
            return str;
        }

        try {
            byte[] bytes = Base64.getDecoder().decode(str.getBytes());
            ByteArrayOutputStream outStream = new ByteArrayOutputStream();
            ByteArrayInputStream inStream = new ByteArrayInputStream(bytes);
            GZIPInputStream gzipIS = new GZIPInputStream(inStream);

            int bufferSize = 1024;
            byte[] buffer = new byte[bufferSize];
            for (int numRead; (numRead = gzipIS.read(buffer, 0, bufferSize)) > 0;) {
                outStream.write(buffer, 0, numRead);
            }
            return outStream.toString(outEncoding);
        } catch (IOException e) {
            System.out.println("IOException: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }
}
