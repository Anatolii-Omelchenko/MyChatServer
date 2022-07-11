package prog.academy.mychatserver;

import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "CheckOnlineServlet", value = "/check")
public class CheckOnlineServlet extends HttpServlet {

    private UsersList usersList = UsersList.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        response.setContentType("application/json");
        usersList.checkOnline();

        String json = usersList.toJSON();

        if(json != null){
            PrintWriter printWriter = response.getWriter();
            printWriter.print(json);
        }

    }

}
