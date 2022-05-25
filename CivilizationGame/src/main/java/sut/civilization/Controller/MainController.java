package sut.civilization.Controller;

import javafx.stage.Stage;
import sut.civilization.Controller.GameControllers.GameController;
import sut.civilization.Enums.Menus;
import sut.civilization.Model.Classes.Game;
import sut.civilization.Model.Classes.User;
import sut.civilization.View.NonGraphical.Menu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.regex.Matcher;

public class MainController extends Controller{

    public String logoutUser() {
        menuChange("login menu");
        Game.changeScene(Menus.LOGIN_MENU);
        Game.getLoggedInUser().setLastTimeOnline(new Date(System.currentTimeMillis()));
        Game.getLoggedInUser().setOnline(false);
        Game.setLoggedInUser(null);
        return "logged out successful";
    }

    public String playGame(Matcher matcher) {
        ArrayList<String> tokens = new ArrayList<>(Arrays.asList(matcher.group(1).split("\\s*-p\\d+\\s*")));
        tokens.remove(0);
        ArrayList<User> players = new ArrayList<>();
        for (String token : tokens) {
            if (getUserByName(token) == null) return "at least one user name doesn't exist";
            else players.add(getUserByName(token));
        }

        Game.setPlayersInGame(players);
        GameController.setCurrentTurnUser(Game.getPlayersInGame().get(0));
        Menu.setMenuName("GameMenu");
        return "game successfully started!";
    }

    public String menuChange(String menuName){
        if (!menuName.equals("login menu") && !menuName.equals("profile menu")){
            return ("invalid menu name\navailable menu names : \"login menu\" \"profile menu\"");
        }

        if (menuName.equals("login menu"))
            Menu.setMenuName("LoginMenu");
        else Menu.setMenuName("ProfileMenu");

        return ("menu changed successfully");
    }

    public void exitGame(){
        logoutUser();
        Game.saveUserListToDatabase();
        ((Stage) Game.getCurrentScene().getWindow()).close();
    }
}
