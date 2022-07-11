package prog.academy.mychatserver;

import jakarta.servlet.http.HttpServletRequest;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class Utils {

    public static byte[] requestBodyToArray(HttpServletRequest request) throws IOException {
        InputStream is = request.getInputStream();
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] buff = new byte[1024];
        int r;

        do {
            r = is.read(buff);
            if (r > 0) {
                bos.write(buff, 0, r);
            }
        } while (r != -1);

        return bos.toByteArray();
    }
}
