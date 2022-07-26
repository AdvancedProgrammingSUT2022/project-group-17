package sut.civilization.View.Graphical;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.thoughtworks.xstream.XStream;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import sut.civilization.Civilization;
import sut.civilization.Controller.ConnectionController;
import sut.civilization.Controller.GameControllers.CityController;
import sut.civilization.Controller.GameControllers.LandController;
import sut.civilization.Controller.GameControllers.TechnologyController;
import sut.civilization.Enums.Menus;
import sut.civilization.Model.Classes.*;
import sut.civilization.Model.ModulEnums.NationType;
import sut.civilization.Transitions.LoadingTransition;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class GameMenuController {
    private final sut.civilization.Controller.GameControllers.GameController gameController = new sut.civilization.Controller.GameControllers.GameController();
    public Label chooseNation1;
    public ChoiceBox<String> nations1;
    public ScrollPane scrollPane;
    public ArrayList<String> opponents = new ArrayList<>();

    public HashMap<String, ChoiceBox<String>> selectedNations = new HashMap<>();


    public void mainMenu(MouseEvent mouseEvent) {
        Game.instance.changeScene(Menus.MAIN_MENU);
    }

    public void startGame(MouseEvent mouseEvent) {
        if (canGameStart()){
            ArrayList<String> userNames = new ArrayList<>(opponents);
            userNames.add(0,Game.instance.getLoggedInUser().getUsername());

            Request request = new Request("gameMenu","startGameRequest");
            request.addToken("users",new XStream().toXML(userNames));
            request.addToken("owner",Game.instance.getLoggedInUser().getUsername());
            request.addToken("nation", Objects.requireNonNull(NationType.getNationTypeByName(nations1.getValue())).name);

            final Response[] response = new Response[1];
            response[0] = new Response(404,"not accepted!");
            new Thread(new Runnable() {
                @Override
                public void run() {
                    response[0] = ConnectionController.getInstance().sendRequestToServer(request.toJson());
                    System.out.println(response[0]);
                }
            }).start();

            LoadingTransition loadingTransition = new LoadingTransition(Game.instance.getCurrentScene().getRoot(), new Runnable() {
                @Override
                public void run() {

                    if (response[0].getStatusCode() != 200){
                        new ViewController().showPopUp(Game.instance.getCurrentScene().getWindow(),response[0].getMessage());
                        return;
                    }
                    while (Game.instance.map == null) {
                        System.out.println("map is null");
                    }

                }
            }, 15000);
            loadingTransition.play();
        }
    }

    public void loadGame(MouseEvent mouseEvent) {

    }

    public boolean canGameStart(){
        return opponents.size() != 0 && nations1.getValue() != null;
    }

    public void initialize(){
        int j = 0;
        for (NationType nationType : NationType.values()) {
            nations1.getItems().add(nationType.name);
        }

        HBox hBox = new HBox();
        VBox usernames = new VBox();
        VBox opponentsNations = new VBox();

        usernames.setStyle("-fx-background: transparent");

        opponentsNations.setSpacing(5);
        hBox.setSpacing(5);
        usernames.setSpacing(5);

        usernames.setPrefWidth(200);
        usernames.setPrefHeight(50);
        Request request = new Request("game","getOnlineUsers");
        Response response = ConnectionController.getInstance().sendRequestToServer(request.toJson());
        ArrayList<User> onlineUsers = (ArrayList<User>) new XStream().fromXML((String) response.getDataToken("users"));

        for (User user : onlineUsers) {
            if (user.getUsername().equals(Game.instance.getLoggedInUser().getUsername()))
                continue;

            Button button = new Button(user.getUsername());
            button.setPrefHeight(50);
            button.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    if (!opponents.contains(button.getText())) {
                        button.setStyle("-fx-background-color: red");
                        opponents.add(button.getText());
                    } else {
                        button.setStyle("-fx-background-color: pink");
                        opponents.remove(button.getText());
                    }
                }
            });
            usernames.getChildren().add(button);
        }

        hBox.getChildren().add(usernames);
        hBox.setAlignment(Pos.CENTER);
        hBox.setId("content");
        scrollPane.setContent(hBox);
    }
}
