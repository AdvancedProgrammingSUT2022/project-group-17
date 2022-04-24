package View;

<<<<<<< HEAD
import Controller.LoginController;
import Enums.LoginCommands;
import Model.Game;
import Model.Users.User;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;

public class LoginMenu extends Menu {
    private final LoginController loginController = new LoginController();
=======
public class LoginMenu extends Menu{
    private LoginController loginController = new LoginController();
>>>>>>> Logic/unitMovement

    @Override
    public void run(Scanner scanner) {
        String input = scanner.nextLine();
        Matcher commandMatcher;

        if ((commandMatcher = LoginCommands.getMatcher(input, LoginCommands.createUser)).matches()) {
            System.out.println(loginController.createUser(commandMatcher));

        } else if (LoginCommands.getMatcher(input, LoginCommands.listOfUsers).matches()) {
            ArrayList<User> users = Game.getUsers();
            if (users.size() == 0) System.out.println("we have no users right now");
            for (int i = 0; i < users.size(); i++) {
                System.out.println(i + 1 + "-\t" + users.get(i));
            }

        } else if ((commandMatcher = LoginCommands.getMatcher(input, LoginCommands.loginUser)).matches()) {
            System.out.println(loginController.loginUser(commandMatcher));


        } else if (LoginCommands.getMatcher(input, LoginCommands.exit).matches()) {
            Menu.setMenuName("EXIT");

        } else {
            System.out.println("invalid command!");

        }
    }
}
