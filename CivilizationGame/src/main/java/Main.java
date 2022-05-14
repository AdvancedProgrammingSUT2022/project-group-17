import Controller.GameControllers.LandController;
import Model.Game;
import Model.Pair;
import View.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        HashMap<String, Integer> menuNumbers = new HashMap<>();
        Menu[] menus = new Menu[4];
        menuInitializer(menus,menuNumbers);

        String currentMenu = "LoginMenu";
        Scanner scanner = new Scanner(System.in);
        LandController.printMap(Game.map);
        while (!currentMenu.equals("EXIT")) {
            menus[menuNumbers.get(currentMenu)].run(scanner);
            currentMenu = Menu.getMenuName();
        }
        Game.saveUserListToDatabase();
    }

    private static void menuInitializer(Menu[] menus, HashMap<String, Integer> menuNumbers){
        menus[0] = new LoginMenu();
        menus[1] = new MainMenu();
        menus[2] = new GameMenu();
        menus[3] = new ProfileMenu();
        menuNumbers.put("LoginMenu", 0);
        menuNumbers.put("MainMenu", 1);
        menuNumbers.put("GameMenu", 2);
        menuNumbers.put("ProfileMenu", 3);
    }


}
