package sut.civilization.Model.Classes;

import java.time.LocalDateTime;

public class Message {
    private User sender;
    private String messageText;
    private String time;
    private boolean isSeen;


    public Message(User sender, String messageText, String time) {
        this.sender = sender;
        this.messageText = messageText;
        this.time = time;
        this.isSeen = false;
    }

    public User getSender() {
        return sender;
    }

    public String getMessageText() {
        return messageText;
    }

    public String getTime() {
        return time;
    }

    public boolean isSeen() {
        return isSeen;
    }
}
