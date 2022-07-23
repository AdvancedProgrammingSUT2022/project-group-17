package sut.civilization.Controller;

import sut.civilization.Model.Classes.Game;
import sut.civilization.Model.Classes.Request;
import sut.civilization.Model.Classes.Response;
import sut.civilization.Model.Classes.User;
import sut.civilization.View.NonGraphical.Menu;

import java.util.regex.Matcher;

public class LoginController extends Controller {

    public String createUser(Matcher matcher) {
        User newUser = new User(null, null, null);
        for (int i = 1; i < 4; i++) {
            if (matcher.group(i).charAt(0) == 'u') {
                if (newUser.getUsername() != null)
                    return ("can't enter username more than once!");

                newUser.setUsername(matcher.group(i).substring(2));

            } else if (matcher.group(i).charAt(0) == 'p') {
                if (newUser.getPassword() != null)
                    return ("can't enter password more than once!");

                newUser.setPassword(matcher.group(i).substring(2));

            } else if (matcher.group(i).charAt(0) == 'n') {
                if (newUser.getNickname() != null)
                    return ("can't enter nickname more than once!");

                newUser.setNickname(matcher.group(i).substring(2));
            }
        }

        for (User user : Game.instance.getUsers()) {
            if (user.getUsername().equals(newUser.getUsername()))
                return ("this username already exists");
            if (user.getNickname().equals(newUser.getNickname()))
                return ("this nickname already exists");
        }

        Game.instance.addUser(newUser);
//        if (!Game.instance.saveUserListToDatabase()) return ("unable to save users to database");

        return ("user successfully created!");
    }

    public String createUser(User newUser) {
        Request request = new Request("login","createUser");
        request.addToken("userName",newUser.getUsername());
        request.addToken("password",newUser.getPassword());
        request.addToken("nickName",newUser.getNickname());

        Response response = ConnectionController.getInstance().sendRequestToServer(request.toJson());
        if (response.getStatusCode() == 200)
            Game.instance.getUsers().add(newUser);

        return response.getMessage();
    }

    public String loginUser(Matcher matcher) {
//        if (Game.instance.getLoggedInUser() != null) return ("please logout in main menu first!");

        String userName = null;
        String password = null;
        for (int i = 1; i < 3; i++) {
            if (matcher.group(i).charAt(0) == 'u') {
                if (userName != null)
                    return ("can't enter username field more than once");

                userName = matcher.group(i).substring(2);
            } else if (matcher.group(i).charAt(0) == 'p') {
                if (password != null)
                    return ("can't enter password field more than once");

                password = matcher.group(i).substring(2);
            }
        }
        if ((userName == null) || (password == null))
            return ("you must enter both of username and password fields");

        User user = getUserByName(userName);

        if (user == null) {
            return ("user doesn't exists!");
        }

        if (!password.equals(user.getPassword())) {
            return ("incorrect password!");
        }

        Game.instance.setLoggedInUser(user);
        Menu.setMenuName("MainMenu");
        return ("welcome " + user.getNickname() + "!");
    }

    public String loginUser(User userinfo) {
        Request request = new Request("login","loginUser");
        request.addToken("userName",userinfo.getUsername());
        request.addToken("password",userinfo.getPassword());

        Response response = ConnectionController.getInstance().sendRequestToServer(request.toJson());
        if (response.getStatusCode() == 200) {
            Game.instance.setLoggedInUser(getUserByName(userinfo.getUsername()));
            this.getUserByName(userinfo.getUsername()).setOnline(true);
        }

        return response.getMessage();
    }

    public String enterAsGuest() {
        Game.instance.setLoggedInUser(new User("", "", ""));
        return "entered as guest.";
    }

}
