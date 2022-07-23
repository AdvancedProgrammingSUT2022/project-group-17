package sut.civilization.View.Graphical;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import sut.civilization.Controller.ConnectionController;
import sut.civilization.Controller.GameControllers.LandController;
import sut.civilization.Enums.Menus;
import sut.civilization.Model.Classes.Game;
import sut.civilization.Model.Classes.User;
import sut.civilization.Model.ModulEnums.NationType;

import java.util.ArrayList;
import java.util.HashMap;

public class GameMenuController {
    private final sut.civilization.Controller.GameControllers.GameController gameController = new sut.civilization.Controller.GameControllers.GameController();
    public Label chooseNation1;
    public ChoiceBox nations1;
    public ScrollPane scrollPane;

    public ArrayList<String> opponents = new ArrayList<>();
    public HashMap<String, ChoiceBox> selectedNations = new HashMap<>();


    public void mainMenu(MouseEvent mouseEvent) {
        Game.instance.changeScene(Menus.MAIN_MENU);
    }

    public void startGame(MouseEvent mouseEvent) {
        if (canGameStart()){
            Game.instance.getPlayersInGame().add(Game.instance.getLoggedInUser());
            sut.civilization.Controller.GameControllers.GameController.setCurrentTurnUser(Game.instance.getLoggedInUser());
            gameController.chooseNation(0, 0);
            int i = 1;
            for (String opponent : opponents) {
                Game.instance.getPlayersInGame().add(gameController.getUserByName(opponent));
                gameController.chooseNation(0, i++);
            }
//            ConnectionController.getInstance().sendStartGameRequest(Game.instance.getPlayersInGame());
            while (Game.instance.map == null)
                System.out.println("map is null");
            Game.instance.changeScene(Menus.GAME_MENU);
        }
    }

    public void loadGame(MouseEvent mouseEvent) {

    }

    public boolean canGameStart(){
        return opponents.size() != 0;
    }

    public void initialize(){
        String[] nations = new String[9];
        int j = 0;
        for (NationType nationType : NationType.values()) {
            nations[j] = j + "- " + nationType.name;
            j++;
        }
        for (int i = 0; i < 9; i++) {
            Label label = new Label(nations[i]);
            nations1.getItems().add(label.getText());
        }


        HBox hBox = new HBox();
        VBox usernames = new VBox();
        VBox opponentsNations = new VBox();

        opponentsNations.setSpacing(5);
        hBox.setSpacing(5);
        usernames.setSpacing(5);

        usernames.setPrefWidth(200);
        usernames.setPrefHeight(50);
        

        for (User user : Game.instance.getUsers()) {
            Button button = new Button(user.getUsername());
            button.setPrefHeight(50);
            button.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    if (!opponents.contains(button.getText())) {
                        button.setStyle("-fx-background-color: red");
                        opponents.add(button.getText());
                    } else {
                        button.setStyle("-fx-background-color: white");
                        opponents.remove(button.getText());
                    }
                }
            });
            usernames.getChildren().add(button);
        }

        hBox.getChildren().add(usernames);
        hBox.getChildren().add(opponentsNations);
        hBox.setAlignment(Pos.CENTER);
        scrollPane.setContent(hBox);
    }
}
