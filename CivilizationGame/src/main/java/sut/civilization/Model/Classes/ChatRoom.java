package sut.civilization.Model.Classes;

import java.lang.reflect.Member;
import java.util.ArrayList;

public class ChatRoom extends Chat {
    private String name;

    public ChatRoom(String name, ArrayList<User> users) {
        super(users);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
