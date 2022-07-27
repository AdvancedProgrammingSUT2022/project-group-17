package sut.civilization.Controller;

import sut.civilization.Model.Classes.Game;
import sut.civilization.Model.Classes.User;

public class Controller {

    public User getUserByName(String username){
        for (User oldUser : Game.instance.getUsers()) {
            if (oldUser.getUsername().equals(username)){
                return oldUser;
            }
        }
        return null;
    }

    public static User getPlayer(String name){
        if (Game.instance.getPlayersInGame() == null)
            return null;

        for (User user : Game.instance.getPlayersInGame()) {
            if (user.getUsername().equals(name))
                return user;
        }
        return null;
    }
}
