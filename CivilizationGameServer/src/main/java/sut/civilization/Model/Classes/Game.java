package sut.civilization.Model.Classes;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import sut.civilization.Civilization;
import sut.civilization.Enums.Menus;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.Arrays;


public class Game {
    public transient static Game instance = new Game();

    public Land[][] map;
    public int[][] dist = new int[110][110];
    public String[][] path = new String[110][110];
    private ArrayList<User> users = readUserListFromDatabase();
    private transient FXMLLoader fxmlLoader;
    private transient Scene currentScene;
    private User loggedInUser;
    private int turn = 0;
    // a number between 0 and players count in order to know witch user have to play
    private int subTurn = 0;
    private ArrayList<User> playersInGame = new ArrayList<>();

    public Scene getCurrentScene() {
        return currentScene;
    }

    public void setCurrentScene(Scene currentScene) {
        Game.instance.currentScene = currentScene;
    }

    public int getSubTurn() {
        return subTurn;
    }

    public void setSubTurn(int subTurn) {
        Game.instance.subTurn = subTurn;
    }

    public int getTurn() {
        return turn;
    }

    public void setTurn(int turn) {
        Game.instance.turn = turn;
    }

    public ArrayList<User> getPlayersInGame() {
        return playersInGame;
    }

    public void setPlayersInGame(ArrayList<User> playersInGame) {
        Game.instance.playersInGame = playersInGame;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<User> users) {
        Game.instance.users = users;
    }

    public User getLoggedInUser() {
        return loggedInUser;
    }

    public void setLoggedInUser(User user) {
        Game.instance.loggedInUser = user;
    }

    public void setMap(Land[][] map) {
        Game.instance.map = map;
    }

    public void addUser(User user) {
        Game.instance.users.add(user);
    }

    public ArrayList<User> readUserListFromDatabase() {
        String json = null;
        try {
            json = new String(Files.readAllBytes(Paths.get("src/main/resources/sut/civilization/DataBase/Users.json")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        users = new Gson().fromJson(json, new TypeToken<ArrayList<User>>() {
        }.getType());
        if (users == null) return new ArrayList<>();

        for (User user : users) {
            ArrayList<String> avatarLocationTokens = new ArrayList<>(Arrays.asList(user.getAvatarLocation().split("/")));
            user.setAvatarLocation("sut/civilization/Images/Avatars/" + avatarLocationTokens.get(avatarLocationTokens.size() - 1));
        }
        return users;
    }

    public ServerDataBase loadServerDataBase() {
        String json = null;
        try {
            json = new String(Files.readAllBytes(Paths.get("src/main/resources/sut/civilization/DataBase/ServerDatabase.json")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Gson().fromJson(json,ServerDataBase.class);
    }

    public void saveUserDatabase() {
        try {
            FileWriter fileWriter = new FileWriter("src/main/resources/sut/civilization/DataBase/Users.json");
            fileWriter.write(new Gson().toJson(Game.instance.getUsers()));
            fileWriter.close();
            if (ServerDataBase.getInstance() != null){
                FileWriter secondWriter = new FileWriter("src/main/resources/sut/civilization/DataBase/ServerDatabase.json");
                secondWriter.write(new Gson().toJson(ServerDataBase.getInstance()));
                secondWriter.close();
            }
        } catch (IOException exception) {
            System.out.println("cannot write user list to dataBase mate :(\nthe stack trace is in below :");
            exception.printStackTrace();
        }
    }

    public Parent loadScene(Menus menu) {
        fxmlLoader = new FXMLLoader();

        switch (menu) {
            case SELECTION_MENU:
                fxmlLoader.setLocation(Civilization.class.getResource("fxml/GameMenu.fxml"));
                break;
            case GAME_MENU:
                fxmlLoader.setLocation(Civilization.class.getResource("fxml/Game.fxml"));
                break;
            case CHAT_MENU:
                fxmlLoader.setLocation(Civilization.class.getResource("fxml/ChatMenu.fxml"));
                break;
            case SCORE_BOARD:
                fxmlLoader.setLocation(Civilization.class.getResource("fxml/ScoreBoard.fxml"));
                break;
            case MAIN_MENU:
                fxmlLoader.setLocation(Civilization.class.getResource("fxml/MainMenu.fxml"));
                break;
            case LOGIN_MENU:
                fxmlLoader.setLocation(Civilization.class.getResource("fxml/LoginMenu.fxml"));
                break;
            case PROFILE_MENU:
                fxmlLoader.setLocation(Civilization.class.getResource("fxml/ProfileMenu.fxml"));
                break;
        }
        try {
            return fxmlLoader.load();
        } catch (IOException e) {
            System.err.println("cannot load fxml file for " + menu + " menu");

            throw new RuntimeException(e);
        }
    }

    public void changeScene(Menus menu) {
        currentScene.setRoot(loadScene(menu));
    }

    public FXMLLoader getFxmlLoader() {
        return fxmlLoader;
    }

    public void setFxmlLoader(FXMLLoader fxmlLoader) {
        Game.instance.fxmlLoader = fxmlLoader;
    }


}
