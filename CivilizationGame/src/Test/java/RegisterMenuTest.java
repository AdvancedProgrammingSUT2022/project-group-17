import Controller.LoginController;
import Model.Game;
import Model.Users.User;
import org.junit.Assert;
import org.junit.Test;
import Enums.LoginCommands;

import java.util.ArrayList;
import java.util.regex.Matcher;

public class RegisterMenuTest {
    LoginController controller = new LoginController();
    ArrayList<User> users;
    Matcher commandMatcher;


    {
        users = Game.readUserListFromDatabase();
        Game.setUsers(users);
    }

    @Test
    public void wrongUsername() {
        commandMatcher = LoginCommands.getMatcher("user login -u Ali11 -p Ali123", LoginCommands.loginUser);
        if (commandMatcher.matches())
            Assert.assertEquals("user doesn't exists!", controller.loginUser(commandMatcher));
    }

    @Test
    public void wrongUserCommand() {
        commandMatcher = LoginCommands.getMatcher("user login -u Ali -u Ali123", LoginCommands.loginUser);
        if (commandMatcher.matches())
            Assert.assertEquals("can't enter username field more than once", controller.loginUser(commandMatcher));
    }

    @Test
    public void wrongPasswordCommand() {
        commandMatcher = LoginCommands.getMatcher("user login -p Ali -p Ali123", LoginCommands.loginUser);
        if (commandMatcher.matches())
            Assert.assertEquals("can't enter password field more than once", controller.loginUser(commandMatcher));
    }

    @Test
    public void swipingFlags() {
        commandMatcher = LoginCommands.getMatcher("user login -p Ali1234 -u Ali", LoginCommands.loginUser);
        if (commandMatcher.matches())
            Assert.assertEquals("welcome " + Game.getUserByName("Ali").getNickname() +"!", controller.loginUser(commandMatcher));
        else
            Assert.assertEquals("5", "10");
    }
}
