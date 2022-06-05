package sut.civilization.Model.Classes;

import java.util.ArrayList;

public class Chat {
    private User user1;
    private User user2;
    private ArrayList<Message> messages = new ArrayList<>();

    public Chat(User user1, User user2) {
        this.user1 = user1;
        this.user2 = user2;
    }

    public User getUser1() {
        return user1;
    }
    public User getUser2() {
        return user2;
    }

    public ArrayList<Message> getMessages() {
        return messages;
    }

    public void addMessage(Message message) {
        messages.add(message);
    }
}
