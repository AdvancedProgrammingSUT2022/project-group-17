package sut.civilization.View.Graphical;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import javafx.application.Platform;
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
import sut.civilization.Model.Classes.Request;
import sut.civilization.Model.Classes.Response;
import sut.civilization.Model.Classes.User;
import sut.civilization.Model.ModulEnums.NationType;
import sut.civilization.Transitions.LoadingTransition;

import java.util.ArrayList;
import java.util.HashMap;

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
            request.addToken("users",new Gson().toJson(userNames));
            request.addToken("owner",Game.instance.getLoggedInUser().getUsername());


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

                    Game.instance.getPlayersInGame().add(Game.instance.getLoggedInUser());
                    sut.civilization.Controller.GameControllers.GameController.setCurrentTurnUser(Game.instance.getLoggedInUser());
                    gameController.chooseNation(Integer.parseInt(String.valueOf(nations1.getValue().charAt(0))), 0);

                    int i = 1;
                    for (String opponent : opponents) {
                        Game.instance.getPlayersInGame().add(gameController.getUserByName(opponent));
                        gameController.chooseNation(0, i++);
                    }

                    while (Game.instance.map == null) {
                        System.out.println("map is null");
                    }
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            Game.instance.changeScene(Menus.GAME_MENU);
                        }
                    });
                }
            }, 13000);
            loadingTransition.play();

        }
    }

    public void loadGame(MouseEvent mouseEvent) {

    }

    public boolean canGameStart(){
        return opponents.size() != 0 && nations1.getValue() != null;
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

        usernames.setStyle("-fx-background: transparent");

        opponentsNations.setSpacing(5);
        hBox.setSpacing(5);
        usernames.setSpacing(5);

        usernames.setPrefWidth(200);
        usernames.setPrefHeight(50);
        Request request = new Request("game","getOnlineUsers");
        Response response = ConnectionController.getInstance().sendRequestToServer(request.toJson());
        ArrayList<User> onlineUsers = new Gson().fromJson((String) response.getDataToken("users"),new TypeToken<ArrayList<User>>(){}.getType());

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
