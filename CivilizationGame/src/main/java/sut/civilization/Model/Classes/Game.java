package sut.civilization.Model.Classes;

import javafx.scene.Scene;
import sut.civilization.Controller.GameControllers.LandController;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;


public class Game {
    private static Scene currentScene;
    private static ArrayList<User> users = readUserListFromDatabase();
    private static User loggedInUser;
    private static int turn = 0;

    // a number between 0 and players count in order to know witch user have to play
    private static int subTurn = 0;
    private static ArrayList<User> playersInGame = new ArrayList<>();

    public static Land[][] map;
    public static int[][] dist = new int[110][110];
    public static String[][] path = new String[110][110];

    public static Scene getCurrentScene() {
        return currentScene;
    }

    public static void setCurrentScene(Scene currentScene) {
        Game.currentScene = currentScene;
    }

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
    public static ArrayList<User> getUsers() {
        return users;
    }

    public static User getLoggedInUser() {
        return loggedInUser;
    }

    public static void setLoggedInUser(User user){
        Game.loggedInUser = user;
    }

    public static Land[][] getMap() {
        return map;
    }

    public static void setMap(Land[][] map) {
        Game.map = map;
    }

    public static void addUser(User user){
        Game.users.add(user);
    }

    public static ArrayList<User> readUserListFromDatabase(){
        String json = null;
        try {
            json = new String(Files.readAllBytes(Paths.get("src/main/resources/sut/civilization/DataBase/Users.json")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        users = new Gson().fromJson(json, new TypeToken<ArrayList<User>>(){}.getType());
        if (users == null) return new ArrayList<>();
        return users;
    }

    public static void saveUserListToDatabase(){
        try {
            FileWriter fileWriter = new FileWriter("src/main/resources/sut/civilization/DataBase/Users.json");
            fileWriter.write(new Gson().toJson(Game.getUsers()));
            fileWriter.close();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}
