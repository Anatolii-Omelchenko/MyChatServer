package prog.academy.mychatserver;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UsersList {

    private static final UsersList usersList = new UsersList();

    private Gson gson;
    private List<User> users = new ArrayList<>();

    private UsersList() {
        gson = new GsonBuilder().create();
    }

    public static UsersList getInstance() {
        return usersList;
    }

    public synchronized void add(User user) {
        users.add(user);
    }

    public synchronized void checkOnline() {
        Date currentDate = new Date();
        long inactivity = 60000;

        for (User user : users) {
            if (!user.getStatus().equals("offline")) {
                if (inactivity < (user.getLastActivity().getTime() - currentDate.getTime())) {
                    user.setStatus("waiting");
                }
            }
        }
    }

    public synchronized String toJSON(){
       return gson.toJson(users);
    }
}
