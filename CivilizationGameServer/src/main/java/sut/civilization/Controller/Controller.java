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
}
