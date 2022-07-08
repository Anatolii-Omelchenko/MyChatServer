package prog.academy.mychatserver;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.LinkedList;
import java.util.List;

public class MessageList {
    private static final MessageList msgList = new MessageList();

    private final Gson gson;
    private final List<Message> list = new LinkedList<>();

    private MessageList() {
        gson = new GsonBuilder().setDateFormat(Message.DATE_FORMAT).create();
    }

    public static MessageList getInstance(){
        return msgList;
    }

    public synchronized void add(Message m){
        list.add(m);
    }

    public synchronized String toJSON(int n, String to){
        if(n >= list.size()){
            return null;
        }
        return gson.toJson(new JsonMessages(list, n, to));
    }
}
