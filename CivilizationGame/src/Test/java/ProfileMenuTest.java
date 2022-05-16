import Controller.LoginController;
import Controller.ProfileController;
import Enums.LoginCommands;
import Enums.ProfileCommands;
import Model.Game;
import Model.Users.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

public class ProfileMenuTest extends Tester{
    ProfileController profileController = new ProfileController();
    LoginController loginController = new LoginController();
    String username = "Ali";
    String password = "Ali1234";
    User user = new User(username,password,"AliHastam");

    @BeforeEach
    public void setup() {
        commandMatcher = LoginCommands.getMatcher("user login -u Ali -p Ali1234",LoginCommands.LOGIN_USER);
        loginController.loginUser(commandMatcher);
    }
    @Test
    public void nicknameChangeRepetitiveWrong(){
        commandMatcher = ProfileCommands.getMatcher("profile change -n matal",ProfileCommands.CHANGE_NICKNAME);
        if (commandMatcher.matches())
            Assert.assertEquals("user with nickname matal already exists.",profileController.changeNickname(user,commandMatcher));
    }

    @Test
    public void nicknameSuccessfullyChange(){
        commandMatcher = ProfileCommands.getMatcher("profile change -n ALiNistam",ProfileCommands.CHANGE_NICKNAME);
        if (commandMatcher.matches())
            Assert.assertEquals("nick name successfully changed",profileController.changeNickname(user,commandMatcher));
    }

    @Test
    public void changePasswordTestNewPasswordEqualsOldPassword(){
        commandMatcher = ProfileCommands.getMatcher("profile change -op Ali1234 -np Ali1234",ProfileCommands.CHANGE_PASSWORD);
        if (commandMatcher.matches())
            Assert.assertEquals("please enter a new password",profileController.changePassword(user,commandMatcher));
    }

    @Test
    public void changePasswordTestWrongFlags(){
        commandMatcher = ProfileCommands.getMatcher("profile change -op Ali123 -op AliAli",ProfileCommands.CHANGE_PASSWORD);
        if (commandMatcher.matches())
            Assert.assertEquals("you must fill both of new password and old password fields",profileController.changePassword(user,commandMatcher));
    }

    @Test
    public void changePasswordTestOldPasswordWrong(){
        commandMatcher = ProfileCommands.getMatcher("profile change -op Ali123 -np AliAli",ProfileCommands.CHANGE_PASSWORD);
        if (commandMatcher.matches())
            Assert.assertEquals("password is incorrect",profileController.changePassword(user,commandMatcher));
    }

    @Test
    public void changePasswordTestSuccessful(){
        commandMatcher = ProfileCommands.getMatcher("profile change -op Ali1234 -np AliAli",ProfileCommands.CHANGE_PASSWORD);
        if (commandMatcher.matches())
            Assert.assertEquals("password changed successfully",profileController.changePassword(user,commandMatcher));
    }

    @Test
    public void changePasswordTestSuccessfulChangingFlags(){
        commandMatcher = ProfileCommands.getMatcher("profile change -np Ali1234 -op AliAli",ProfileCommands.CHANGE_PASSWORD);
        if (commandMatcher.matches())
            Assert.assertEquals("password is incorrect",profileController.changePassword(user,commandMatcher));
    }


    @Test
    public void removingAccountTestWrongPassword(){
        commandMatcher = ProfileCommands.getMatcher("remove account -p Ali123", ProfileCommands.CHANGE_PASSWORD);
        if (commandMatcher.matches())
            Assert.assertEquals("password is incorrect!",profileController.removeAccount(user,commandMatcher));
    }

    @Test
    public void removingAccountTestSuccessful(){
        commandMatcher = ProfileCommands.getMatcher("remove account -p Ali1234",ProfileCommands.CHANGE_PASSWORD);
        if (commandMatcher.matches())
            Assert.assertEquals("user successfully removed",profileController.removeAccount(user,commandMatcher));
    }
}
