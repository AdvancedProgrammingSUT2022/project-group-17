package sut.civilization.View.NonGraphical;

import sut.civilization.Controller.LoginController;
import sut.civilization.Enums.LoginCommands;
import sut.civilization.Model.Classes.Game;
import sut.civilization.Model.Classes.User;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;

public class LoginMenu extends Menu {
    private final LoginController loginController = new LoginController();

    @Override
    public void run(Scanner scanner) {
        String input = scanner.nextLine();
        Matcher commandMatcher;

        if ((commandMatcher = LoginCommands.getMatcher(input, LoginCommands.CREATE_USER)).matches()) {
            System.out.println(loginController.createUser(commandMatcher));

        } else if (LoginCommands.getMatcher(input, LoginCommands.LIST_OF_USERS).matches()) {
            ArrayList<User> users = Game.instance.getUsers();
            if (users.size() == 0) System.out.println("we have no users right now");
            for (int i = 0; i < users.size(); i++) {
                System.out.println(i + 1 + "-\t" + users.get(i));
            }

        } else if ((commandMatcher = LoginCommands.getMatcher(input, LoginCommands.LOGIN_USER)).matches()) {
            System.out.println(loginController.loginUser(commandMatcher));


        } else if (LoginCommands.getMatcher(input, LoginCommands.EXIT).matches()) {
            setMenuName("EXIT");

        } else {
            System.out.println("invalid command!");

        }
    }
}
