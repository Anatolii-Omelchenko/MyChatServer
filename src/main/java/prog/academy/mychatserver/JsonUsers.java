package prog.academy.mychatserver;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class JsonUsers {
    private final List<User> list = new ArrayList<>();

    public JsonUsers(Map<String, User> sourceList) {

        for (Map.Entry<String, User> entry : sourceList.entrySet()) {
            User user = entry.getValue();
            list.add(user);
        }

    }
}
