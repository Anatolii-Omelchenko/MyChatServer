package prog.academy.mychatserver;

import com.google.gson.JsonElement;

import java.util.ArrayList;
import java.util.List;

public class JsonMessages {

    private final List<Message> list = new ArrayList<>();

    public JsonMessages(List<Message> sourceList, int fromIndex, String to) {
        for (int i = fromIndex; i < sourceList.size(); i++) {
            Message m = sourceList.get(i);
            if (m.getTo().equals(to) || m.getTo().equals("All") || m.getFrom().equals(to)) {
                list.add(sourceList.get(i));
            }
        }
    }
}
