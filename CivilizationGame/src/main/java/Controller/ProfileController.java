package Controller;

import Model.Game;
import Model.Users.User;

import java.util.ArrayList;
import java.util.regex.Matcher;

public class ProfileController extends Controller {
    public String changeNickname(Matcher matcher) {
        for (User user : Game.getUsers()) {
            if (user.getNickname().equals(matcher.group("nickName")))
                return ("user with nickname " + matcher.group("nickName") + " already exists.");
        }

        Game.getLoggedInUser().setNickname(matcher.group("nickName"));
        return ("nick name successfully changed");
    }

    public String changePassword(Matcher matcher) {
        String oldPassword = null;
        String newPassword = null;
        for (int i = 1; i < 3; i++) {
            if (matcher.group(i).charAt(0) == 'n'){
                newPassword = matcher.group(i).substring(3);

            } else {
                oldPassword = matcher.group(i).substring(3);

            }
        }
        if (oldPassword == null || newPassword == null) return ("you must fill both of new password and old password fields");

        if (!oldPassword.equals(Game.getLoggedInUser().getPassword()))
            return ("password is incorrect");
        if (oldPassword.equals(newPassword))
            return ("please enter a new password");

        Game.getLoggedInUser().setPassword(newPassword);
        return ("password changed successfully");
    }

    public String removeAccount(Matcher matcher) {
        if (!matcher.group("password").equals(Game.getLoggedInUser().getPassword()))
            return ("password is incorrect!");

        ArrayList<User> users = Game.getUsers();
        for (int i = 0; i < users.size(); i++) {
            User user = users.get(i);
            if (user.getUsername().equals(Game.getLoggedInUser().getUsername())) {
                users.remove(i);
                break;
            }
        }

        Game.setLoggedInUser(null);
        return ("user successfully removed");
    }
}
