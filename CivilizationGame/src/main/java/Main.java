import Model.LandFeatures.LandFeature;
import Model.LandFeatures.LandFeatureType;
import View.*;

import java.util.HashMap;

public class Main {

    public static void main(String[] args) {
        HashMap<String, Integer> menuNumbers = new HashMap<>();
        menuNumbers.put("LoginMenu",0);
        menuNumbers.put("MainMenu",1);
        menuNumbers.put("GameMenu",2);
        menuNumbers.put("ProfileMenu",3);

        Menu[] menus = new Menu[3];
        menus[0] = new LoginMenu();
        menus[1] = new MainMenu();
        menus[2] = new GameMenu();
        menus[3] = new ProfileMenu();

        String currentMenu;
        do {
            currentMenu = Menu.getMenuName();
            menus[menuNumbers.get(currentMenu)].run();
        } while(!currentMenu.equals("EXIT"));


    }



}
