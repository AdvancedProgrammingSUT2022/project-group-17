package Model;

import Enums.Consts;
import Model.Lands.Land;
import Model.Users.User;

import java.util.ArrayList;

public class Game {

    private static ArrayList<User> users = new ArrayList<>();
    private static User currentUser;
    private static int turn = 0;
    private static ArrayList<String> allTechnologies = new ArrayList<>();
    public static Land[][] map = new Land[Consts.MAP_SIZE.amount][Consts.MAP_SIZE.amount];

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
