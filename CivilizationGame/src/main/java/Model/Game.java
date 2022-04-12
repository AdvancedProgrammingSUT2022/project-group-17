package Model;

import Model.Users.User;

import java.util.ArrayList;

public class Game {

    private static ArrayList<User> users = new ArrayList<>();
    private static User currentUser;

    public static ArrayList<User> getUsers() {
        return users;
    }

    public static User getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(User user){
        Game.currentUser = user;
    }

    public static void addUser(User user){
        Game.users.add(user);
    }


}
