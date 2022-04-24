package Controller;

import View.Menu;

public class Controller {
    public void changeMenu(String menuName) {
        Menu.setMenuName(menuName);
        System.out.println("you are in " + menuName + " now ...");
    }
}
