package prog.academy.mychatserver;

import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet(name = "AddServlet", value = "/add")
public class AddServlet extends HttpServlet {

    private MessageList msgList = MessageList.getInstance();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        byte[] buff = Utils.requestBodyToArray(request);
        String buffString = new String(buff, StandardCharsets.UTF_8);

        Message msg = Message.fromJSON(buffString);
        if (msg != null) {
            msgList.add(msg);
        } else {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }


}
