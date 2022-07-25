package sut.civilization;

import sut.civilization.Controller.GameControllers.LandController;
import sut.civilization.Model.Classes.Game;
import sut.civilization.View.NonGraphical.*;

import java.util.HashMap;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        LandController landController = new LandController();

        HashMap<String, Integer> menuNumbers = new HashMap<>();
        Menu[] menus = new Menu[4];
        menuInitializer(menus,menuNumbers);

        String currentMenu = "LoginMenu";
        Scanner scanner = new Scanner(System.in);

        Game.instance.setMap(landController.mapInitializer());
        landController.initializeDistances();
        landController.updateDistances();

        while (!currentMenu.equals("EXIT")) {
            landController.printMap(Game.instance.map);
            menus[menuNumbers.get(currentMenu)].run(scanner);
            currentMenu = Menu.getMenuName();
        }

        Game.instance.saveUserDatabase();
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
