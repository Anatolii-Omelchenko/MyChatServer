package prog.academy.mychatserver;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

@WebServlet(name = "LogOutServlet", value = "/logout")
public class LogOutServlet extends HttpServlet {

    UsersList usersList = UsersList.getInstance();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        byte[] buff = requestBodyToArray(request);
        String login = new String(buff, StandardCharsets.UTF_8);

        usersList.getUsers().put(login, new User(login,"offline"));
    }

    private byte[] requestBodyToArray(HttpServletRequest request) throws IOException {
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