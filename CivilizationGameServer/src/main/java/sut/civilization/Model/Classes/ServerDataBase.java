package sut.civilization.Model.Classes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ServerDataBase {
    private static ServerDataBase instance;
    private final HashMap<String, ArrayList<Request>> addFriendRequestTree;
    private ServerDataBase(){
        this.addFriendRequestTree = new HashMap<>();
    }

    public static ServerDataBase getInstance() {
        if (instance == null){
            instance = Game.instance.loadServerDataBase();
            if (instance == null)
                instance = new ServerDataBase();
        }

        return instance;
    }

    public HashMap<String, ArrayList<Request>> getAddFriendRequestTree() {
        return addFriendRequestTree;
    }
    public void addFriendRequest(String userName,Request request) {
        this.addFriendRequestTree.putIfAbsent(userName,new ArrayList<>());
        this.addFriendRequestTree.get(userName).add(request);
    }

    public void setFriendRequest(String userName,ArrayList<Request> requests) {
        this.addFriendRequestTree.put(userName, requests);
    }
}
