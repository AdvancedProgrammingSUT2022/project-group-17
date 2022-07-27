package sut.civilization.Model.Classes;

import java.util.ArrayList;
import java.util.Objects;

public class Chat {
    private ArrayList<User> users = new ArrayList<>();
    private ArrayList<Message> messages = new ArrayList<>();

    private boolean isGroup = false;

    public Chat(ArrayList<User> users) {
        this.users = users;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }

    public void setMessages(ArrayList<Message> messages) {
        this.messages = messages;
    }

    public boolean isGroup() {
        return isGroup;
    }

    public void setGroup(boolean group) {
        isGroup = group;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public ArrayList<Message> getMessages() {
        return messages;
    }

    public void addMessage(Message message) {
        messages.add(message);
    }

    public void removeMessage(Message message) {
        messages.remove(message);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Chat chat = (Chat) o;

        for (User user : this.users) {
            if (!chat.users.contains(user))
                return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Chat{" +
                "users=" + users +
                ", messages=" + messages +
                '}';
    }

    public boolean haveUsers(ArrayList<User> users) {
        for (User user : users) {
            if (!this.users.contains(user))
                return false;
        }
        return true;
    }
}
