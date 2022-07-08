package prog.academy.mychatserver;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.nio.charset.StandardCharsets;

@WebServlet(name = "AddServlet", value = "/add")
public class AddServlet extends HttpServlet {

    private MessageList msgList = MessageList.getInstance();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        byte[] buff = requestBodyToArray(request);
        String buffString = new String(buff, StandardCharsets.UTF_8);

        Message msg = Message.fromJSON(buffString);
        if (msg != null) {
            msgList.add(msg);
        } else {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    private byte[] requestBodyToArray(HttpServletRequest request) throws IOException {
        InputStream is = request.getInputStream();
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] buff = new byte[1024];
        int r;

        do{
            r = is.read(buff);
            if(r>0){
                bos.write(buff,0, r);
            }
        } while (r != -1);

        return bos.toByteArray();
    }
}
