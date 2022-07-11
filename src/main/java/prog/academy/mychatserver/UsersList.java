package prog.academy.mychatserver;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.*;

public class UsersList {

    private static final UsersList usersList = new UsersList();

    private Gson gson;
    private Map<String, User> users = new HashMap<>();

    private UsersList() {
        gson = new GsonBuilder().create();
    }

    public static UsersList getInstance() {
        return usersList;
    }

    public synchronized void add(String login, User user) {
        users.put(login, user);
    }

    public synchronized void checkOnline() {
        long inactivity = 600000; //10 minutes

        for (Map.Entry<String, User> entry : users.entrySet()) {
            User user = entry.getValue();
            if (user.getStatus().equals("offline")) {
                continue;
            } else {
                if (inactivity < (new Date().getTime() - user.getLastActivity().getTime())) {
                    user.setStatus("waiting");
                }
            }
        }
    }

    public synchronized String toJSON() {
        return gson.toJson(new JsonUsers(users));
    }

    public Map<String, User> getUsers() {
        return users;
    }

}
