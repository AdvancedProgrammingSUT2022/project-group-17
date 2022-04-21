package Model;

import Enums.Consts;
import Model.Lands.Land;
import Model.Users.User;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Game {

    private static ArrayList<User> users = readUserListFromDatabase();
    private static User currentUser;
    private static int turn = 0;
    private static ArrayList<String> allTechnologies = new ArrayList<>();
    public static Land[][] map = new Land[Consts.MAP_SIZE.amount][Consts.MAP_SIZE.amount];

    public static User getUserByName(String username){
        for (User user : users) {
            if (user.getUsername().equals(username)){
                return user;
            }
        }
        return null;
    }

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

    private static ArrayList<User> readUserListFromDatabase(){
        String json = null;
        try {
            json = new String(Files.readAllBytes(Paths.get("src/main/databases/Users.json")));
        } catch (IOException e) {
            System.out.println("unable to read from database");
        }
        users = new Gson().fromJson(json, new TypeToken<ArrayList<User>>(){}.getType());
        if (users == null) return new ArrayList<>();
        return users;
    }

    public static boolean saveUserListToDatabase(){
        try {
            FileWriter fileWriter = new FileWriter("src/main/databases/Users.json");
            fileWriter.write(new Gson().toJson(Game.getUsers()));
            fileWriter.close();
        } catch (IOException exception) {
            return false;
        }
        return true;
    }
}
