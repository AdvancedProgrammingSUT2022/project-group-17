import Controller.LoginController;
import Model.Game;
import Model.Users.User;
import org.junit.Assert;
import org.junit.Test;
import Enums.LoginCommands;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

public class RegisterMenuTest extends Tester{
    LoginController loginController = new LoginController();

    @BeforeEach
    public void setup() {
        users.add(new User("Mohammad_Mahdi_Akbar", "TeaBag", "atal"));
        users.add(new User("Hamed_Saboor_Yaraghi", "HamedSY007", "matal"));
        users.add(new User("Amir_Hossein_Ravan_Nakhjavani", "AmirAmir", "tootoole"));
        users.add(new User("Ali", "Ali1234", "Ali Hastam"));
        Game.setUsers(Game.getUsers());
    }

    @AfterEach
    public void remover() {
        Game.getUsers().clear();
    }

    @Test
    public void FirstRegisteringChangingFlags() {
        commandMatcher = LoginCommands.getMatcher("user create -u Amir -n Ravan -p Amiramir", LoginCommands.createUser);
        if (commandMatcher.matches())
            Assert.assertEquals("user successfully created!", loginController.createUser(commandMatcher));
    }

    @Test
    public void SecondRegisteringChangingFlags() {
        commandMatcher = LoginCommands.getMatcher("user create -p Amiramir -n Ravan1 -u Amir1", LoginCommands.createUser);
        if (commandMatcher.matches())
            Assert.assertEquals("user successfully created!", loginController.createUser(commandMatcher));
    }

    @Test
    public void FirstRegisterWrongCommand() {
        commandMatcher = LoginCommands.getMatcher("user create -u Amir -u Ravan1 -p Amiramir", LoginCommands.createUser);
        if (commandMatcher.matches())
            Assert.assertEquals("can't enter username more than once!", loginController.createUser(commandMatcher));
    }

    @Test
    public void SecondRegisterWrongCommand() {
        commandMatcher = LoginCommands.getMatcher("user create -n Amir -n Ravan1 -p Amiramir", LoginCommands.createUser);
        if (commandMatcher.matches())
            Assert.assertEquals("can't enter nickname more than once!", loginController.createUser(commandMatcher));
    }

    @Test
    public void repetitiveUsernameUserCreate() {
        commandMatcher = LoginCommands.getMatcher("user create -u Hamed_Saboor_Yaraghi -n Ravan1 -p Amiramir", LoginCommands.createUser);
        if (commandMatcher.matches())
            Assert.assertEquals("this username already exists", loginController.createUser(commandMatcher));
    }

    @Test
    public void repetitiveNicknameUserCreate() {
        commandMatcher = LoginCommands.getMatcher("user create -u Aliii -n matal -p Amiramir", LoginCommands.createUser);
        if (commandMatcher.matches())
            Assert.assertEquals("this nickname already exists", loginController.createUser(commandMatcher));
    }

    @Test
    public void LoginWrongUsername() {
        commandMatcher = LoginCommands.getMatcher("user login -u Ali11 -p Ali123", LoginCommands.loginUser);
        if (commandMatcher.matches())
            Assert.assertEquals("user doesn't exists!", loginController.loginUser(commandMatcher));
    }

    @Test
    public void LoginUserCommandWrong() {
        commandMatcher = LoginCommands.getMatcher("user login -u Ali -u Ali123", LoginCommands.loginUser);
        if (commandMatcher.matches())
            Assert.assertEquals("can't enter username field more than once", loginController.loginUser(commandMatcher));
    }

    @Test
    public void LoginWrongPasswordCommand() {
        commandMatcher = LoginCommands.getMatcher("user login -p Ali -p Ali123", LoginCommands.loginUser);
        if (commandMatcher.matches())
            Assert.assertEquals("can't enter password field more than once", loginController.loginUser(commandMatcher));
    }

    @Test
    public void LoginSwipingFlags() {
        commandMatcher = LoginCommands.getMatcher("user login -p Ali1234 -u Ali", LoginCommands.loginUser);
        if (commandMatcher.matches())
            Assert.assertEquals("welcome " + Game.getUserByName("Ali").getNickname() + "!", loginController.loginUser(commandMatcher));
        else
            Assert.assertEquals("5", "10");
    }

}
