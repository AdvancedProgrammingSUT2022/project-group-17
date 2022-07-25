package sut.civilization.Controller;

import sut.civilization.Model.Classes.Game;
import sut.civilization.Model.Classes.Pair;
import sut.civilization.Model.Classes.User;

import java.util.ArrayList;
import java.util.regex.Matcher;

public class ProfileController extends Controller {

    public String changeNickname(User user,Matcher matcher) {
        for (User oldUser : Game.instance.getUsers()) {
            if (oldUser.getNickname().equals(matcher.group("nickName")))
                return ("user with nickname " + matcher.group("nickName") + " already exists.");
        }

        user.setNickname(matcher.group("nickName"));
        return ("nick name successfully changed");
    }

    public String changeNickname(Pair<String,String> newNickname,String oldNickName){
        if (!newNickname.x.equals(newNickname.y)) return "two nicknames are not identical!";

        if (!Game.instance.getLoggedInUser().getNickname().equals(oldNickName)) return "old nickname is not right! see it idiot it is right there!";

        for (User user : Game.instance.getUsers()) {
            if (user.getNickname().equals(newNickname.x)){
                return "this Nickname already exists!";
            }
        }

        Game.instance.getLoggedInUser().setNickname(newNickname.x);
        return "Nickname successfully changed.";

    }
    public String changePassword(User user,Matcher matcher) {
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

        if (!oldPassword.equals(user.getPassword()))
            return ("password is incorrect");
        if (oldPassword.equals(newPassword))
            return ("please enter a new password");

        user.setPassword(newPassword);
        return ("password changed successfully");
    }

    public String changePassword(Pair<String,String> newPassword, String oldPassword) {
        if (!newPassword.x.equals(newPassword.y)) return "newPasswords are not identical!";

        if (!oldPassword.equals(Game.instance.getLoggedInUser().getPassword())) return "Password is not correct!";

        if (oldPassword.equals(newPassword.x)) return "please enter a new Password!";

        Game.instance.getLoggedInUser().setPassword(newPassword.x);
        return "password changed successfully.";
    }

    public String removeAccount(User user,Matcher matcher) {
        if (!matcher.group("password").equals(user.getPassword()))
            return ("password is incorrect!");

        ArrayList<User> users = Game.instance.getUsers();
        for (int i = 0; i < users.size(); i++) {
            User oldUser = users.get(i);
            if (oldUser.getUsername().equals(user.getUsername())) {
                users.remove(i);
                break;
            }
        }

        Game.instance.setLoggedInUser(null);
        return ("user successfully removed");
    }

    public String updateAvatarLocation(String location) {
        Game.instance.getLoggedInUser().setAvatarLocation(location);
        return "changed successfully.";
    }
}
