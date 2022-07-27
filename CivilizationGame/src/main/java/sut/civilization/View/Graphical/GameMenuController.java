package sut.civilization.View.Graphical;

import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Popup;
import sut.civilization.Controller.GameControllers.CityController;
import sut.civilization.Controller.GameControllers.LandController;
import sut.civilization.Controller.GameControllers.TechnologyController;
import sut.civilization.Enums.Menus;
import sut.civilization.Model.Classes.Game;
import sut.civilization.Model.Classes.User;
import sut.civilization.Model.ModulEnums.NationType;

import java.util.ArrayList;
import java.util.HashMap;

public class GameMenuController {
    private final sut.civilization.Controller.GameControllers.GameController gameController = new sut.civilization.Controller.GameControllers.GameController();
    public Label chooseNation1;
    public ChoiceBox myNation;
    public ScrollPane scrollPane;

    public ArrayList<String> opponents = new ArrayList<>();
    public HashMap<String, ChoiceBox> selectedNations = new HashMap<>();


    public void mainMenu() {
        Game.instance.changeScene(Menus.MAIN_MENU);
    }

    public void startGame(MouseEvent mouseEvent) {
        if (canGameStart()){
            int nation_one = Integer.parseInt(String.valueOf(myNation.getValue().toString().charAt(0)));
            Game.instance.getPlayersInGame().add(Game.instance.getLoggedInUser());
            sut.civilization.Controller.GameControllers.GameController.setCurrentTurnUser(Game.instance.getLoggedInUser());
            gameController.chooseNation(nation_one, 0);
            int i = 1;
            for (String opponent : opponents) {
                Game.instance.getPlayersInGame().add(gameController.getUserByName(opponent));
                int nationNumber = Integer.parseInt(String.valueOf((selectedNations.get(opponent).getValue().toString().charAt(0))));
                gameController.chooseNation(nationNumber, i++);
            }
//            for (User user : Game.instance.getPlayersInGame()) {
//                System.out.println(user.getNation().getNationType().name);
//                System.out.println(user.getUsername());
//            }

            Game.instance.setMap(LandController.mapInitializer());

            LandController.initializeDistances();
            LandController.updateDistances();
            TechnologyController.updateNextAvailableTechnologies();
            CityController.updateAffordableLands();

            Game.instance.changeScene(Menus.GAME_MENU);
        }
    }

    public void loadGame(MouseEvent mouseEvent) {

    }

    public boolean canGameStart(){
//        if (nations1.getValue().equals(null)){
//            System.out.println("ridi");
//            return false;
//        }

        if (opponents.size() == 0)
            return false;
        return true;
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
            myNation.getItems().add(label.getText());
        }


        HBox hBox = new HBox();
        VBox usernames = new VBox();
        VBox opponentsNations = new VBox();
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

            ChoiceBox choiceBox = new ChoiceBox<>();
            choiceBox.setPrefHeight(50);
            for (int i = 0; i < 9; i++) {
                Label label = new Label(nations[i]);
                choiceBox.getItems().add(label.getText());
            }
            opponentsNations.getChildren().add(choiceBox);
            selectedNations.put(user.getUsername(), choiceBox);
        }

        hBox.getChildren().add(usernames);
        hBox.getChildren().add(opponentsNations);
        scrollPane.setContent(hBox);
    }
}
