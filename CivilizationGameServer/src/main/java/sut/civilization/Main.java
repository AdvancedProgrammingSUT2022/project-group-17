package sut.civilization;

import com.thoughtworks.xstream.XStream;
import sut.civilization.Controller.ConnectionController;
import sut.civilization.Controller.GameControllers.LandController;
import sut.civilization.Model.Classes.Game;
import sut.civilization.Model.Classes.Land;
import sut.civilization.Model.Classes.ServerDataBase;
import sut.civilization.Model.Classes.User;
import sut.civilization.View.NonGraphical.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        for (User user : Game.instance.getUsers()) {
            user.setNation(null);
        }

        ConnectionController connectionController = new ConnectionController(7090);
        connectionController.listenForNewClient();
    }
}
