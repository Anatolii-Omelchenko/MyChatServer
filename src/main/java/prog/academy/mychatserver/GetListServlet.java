package prog.academy.mychatserver;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

@WebServlet(name = "GetListServlet", value = "/get")
public class GetListServlet extends HttpServlet {

    private MessageList msgList = MessageList.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String fromStr = request.getParameter("from");
        String login = request.getParameter("login");
        int from = 0;

        try {
            from = Integer.parseInt(fromStr);
            if (from < 0) {
                from = 0;
            }
        } catch (Exception ex) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        response.setContentType("application/json");

        String json = msgList.toJSON(from, login);
        if(json != null){
            OutputStream os = response.getOutputStream();
            byte[] buff = json.getBytes(StandardCharsets.UTF_8);
            os.write(buff);

//            PrintWriter pw = response.getWriter();
//            pw.print(json);
        }
    }


}
