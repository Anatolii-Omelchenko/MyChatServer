package prog.academy.mychatserver;

import com.google.gson.*;
import java.util.Date;

public class Message {

    public static final String DATE_FORMAT = "yyyy-MM-dd HH-mm-ss";

    private Date date = new Date();
    private String from;
    private String to;
    private String text;

    public Message(String from, String text) {
        this.from = from;
        this.text = text;
    }

    public String toJSON() {
        Gson gson = new GsonBuilder().setDateFormat(DATE_FORMAT).create();
        return gson.toJson(this);
    }

    public static Message fromJSON(String s) {
        Gson gson = new GsonBuilder().setDateFormat(DATE_FORMAT).create();
        return gson.fromJson(s, Message.class);
    }

    @Override
    public String toString() {
        return new StringBuilder().append("[").append(date)
                .append(", From: ").append(from).append(", To: ")
                .append(to).append("] ").append(text).toString();
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
