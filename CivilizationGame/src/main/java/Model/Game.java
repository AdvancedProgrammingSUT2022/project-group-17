package Model;

import Controller.GameControllers.LandController;
import Model.Lands.Land;
import Model.Users.User;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;


public class Game {

    private static ArrayList<User> users = readUserListFromDatabase();
    private static User loggedInUser;
    private static int turn = 0;

    // a number between 0 and players count in order to know witch user have to play
    private static int subTurn = 0;
    private static ArrayList<String> allTechnologies = new ArrayList<>();
    private static ArrayList<User> playersInGame = new ArrayList<>();

    static {
        playersInGame.add(users.get(0));
        playersInGame.add(users.get(1));
    }

    public static Land[][] map = LandController.mapInitializer();
    public static HashMap<LandPair, Integer> dist = new HashMap<>();
    public static HashMap<LandPair, String> path = new HashMap<>();

    public static int getSubTurn() {
        return subTurn;
    }

    public static void setSubTurn(int subTurn) {
        Game.subTurn = subTurn;
    }

    public static int getTurn() {
        return turn;
    }

    public static void setTurn(int turn) {
        Game.turn = turn;
    }

    public static ArrayList<User> getPlayersInGame() {
        return playersInGame;
    }

    public static void setPlayersInGame(ArrayList<User> playersInGame) {
        Game.playersInGame = playersInGame;
    }

    public static void setUsers(ArrayList<User> users) {
        Game.users = users;
    }

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

    public static User getLoggedInUser() {
        return loggedInUser;
    }

    public static void setLoggedInUser(User user){
        Game.loggedInUser = user;
    }

    public static void addUser(User user){
        Game.users.add(user);
    }

    public static ArrayList<User> readUserListFromDatabase(){
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
