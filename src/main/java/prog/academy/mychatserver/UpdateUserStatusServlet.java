package prog.academy.mychatserver;

import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet(name = "UpdateUserStatusServlet", value = "/update")
public class UpdateUserStatusServlet extends HttpServlet {

    UsersList usersList = UsersList.getInstance();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        byte[] buff = Utils.requestBodyToArray(request);
        String login = new String(buff, StandardCharsets.UTF_8);

        usersList.add(login, new User(login, "online"));

    }

}
