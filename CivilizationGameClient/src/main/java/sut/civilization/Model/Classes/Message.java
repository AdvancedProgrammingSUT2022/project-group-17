package sut.civilization.Model.Classes;

import java.time.LocalDateTime;
import java.util.Objects;

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

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message = (Message) o;
        return Objects.equals(messageText, message.messageText);
    }

    @Override
    public int hashCode() {
        return Objects.hash(messageText);
    }
}
