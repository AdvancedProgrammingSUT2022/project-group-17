package Model.Lands.View;

import java.util.Scanner;

public class Menu {
    protected static String menuName = "LoginMenu";

    public void run(Scanner scanner) {

    }

    public static String getMenuName() {
        return menuName;
    }

    public static void setMenuName(String menuName) {
        Menu.menuName = menuName;
    }
}
