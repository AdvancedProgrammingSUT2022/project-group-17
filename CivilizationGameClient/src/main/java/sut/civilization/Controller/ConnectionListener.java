package sut.civilization.Controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.thoughtworks.xstream.XStream;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Popup;
import sut.civilization.Controller.GameControllers.CityController;
import sut.civilization.Controller.GameControllers.GameController;
import sut.civilization.Controller.GameControllers.LandController;
import sut.civilization.Controller.GameControllers.TechnologyController;
import sut.civilization.Enums.Menus;
import sut.civilization.Model.Classes.*;
import sut.civilization.Model.ModulEnums.NationType;
import sut.civilization.View.Graphical.GamePlayController;
import sut.civilization.View.Graphical.ViewController;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

public class ConnectionListener extends Thread {
    private final Pair<DataInputStream, DataOutputStream> serverHandleStream;

    public ConnectionListener(Socket serverSideSocket) {
        try {
            this.serverHandleStream = new Pair<>(new DataInputStream(serverSideSocket.getInputStream()),new DataOutputStream(serverSideSocket.getOutputStream()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        this.setDaemon(true);
    }
    @Override
    public void run() {
        while (true){
            try {
                handleUpdate(Response.fromJson(listenForUpdateFromServer()));
            } catch (IOException e) {
                System.out.println("connection with server is lost ! closing app ...");
                System.exit(0);
                break;
            }
        }
    }

    private String listenForUpdateFromServer() throws IOException{
        int length;
        length = serverHandleStream.x.readInt();

        byte[] message = new byte[0];

        if (length > 0){
            message = new byte[length];
            serverHandleStream.x.readFully(message,0,message.length);
        }
        return new String(message);
    }

    private void handleUpdate(Response updateResponse) {

        if (updateResponse == null) {
            return;
        }
        if (updateResponse.getMessage().equals("setMap")) {
            XStream xStream = new XStream();
            Game.instance.setPlayersInGame((ArrayList<User>) xStream.fromXML((String) updateResponse.getDataToken("players")));
            int temp = (int) Math.floor((Double) updateResponse.getDataToken("subTurn"));
            Game.instance.setSubTurn(temp);
            GameController.setCurrentTurnUser(Game.instance.getPlayersInGame().get(temp));
            Game.instance.map = (Land[][]) xStream.fromXML((String) updateResponse.getDataToken("map"));

            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    Game.instance.changeScene(Menus.GAME_MENU);
                    Game.instance.getCurrentScene().getRoot().setDisable(!Game.instance.getPlayersInGame().get(Game.instance.getSubTurn()).getUsername().equals(Game.instance.getLoggedInUser().getUsername()));
                }
            });
        }

        if (updateResponse.getMessage().equals("startGameRequest")) {
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    ConnectionListener.this.showGameRequestPopUp((ArrayList<String>) new XStream().fromXML((String) updateResponse.getDataToken("users")), updateResponse);
                }
            });
        }

        if (updateResponse.getMessage().equals("clientUpdateDataBase")){
            XStream xStream = new XStream();

            Game.instance.setPlayersInGame((ArrayList<User>) xStream.fromXML((String) updateResponse.getDataToken("players")));
            Game.instance.setSubTurn(Integer.parseInt((String) updateResponse.getDataToken("subTurn")) % Game.instance.getPlayersInGame().size());
            GameController.setCurrentTurnUser(Game.instance.getPlayersInGame().get(Game.instance.getSubTurn()));
            Game.instance.map = (Land[][]) xStream.fromXML((String) updateResponse.getDataToken("map"));

            System.out.println("sub turn : " + Game.instance.getSubTurn());
            System.out.println("players in game : " + Game.instance.getPlayersInGame());
            System.out.println("current turn user " + GameController.getCurrentTurnUser());

            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    Game.instance.getCurrentScene().getRoot().setDisable(!Game.instance.getPlayersInGame().get(Game.instance.getSubTurn()).getUsername().equals(Game.instance.getLoggedInUser().getUsername()));
                    GamePlayController.getInstance().updateWholeMap();
                    GamePlayController.getInstance().hideUnitPopup();
                    GamePlayController.getInstance().updateWholeMap();
                    GamePlayController.getInstance().updateTechnologyBox();
                    GamePlayController.getInstance().updateCurrencyBar();
                }
            });
        }

        if (updateResponse.getMessage().startsWith("endGame")) {
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    Game.instance.changeScene(Menus.MAIN_MENU);
                    new ViewController().showPopUp(Game.instance.getCurrentScene().getWindow(),updateResponse.getMessage());
                }
            });
        }
    }

    private void showGameRequestPopUp(ArrayList<String> users,Response updateResponse) {
        Popup popup = new Popup();

        Label label = new Label("new game request from " + users + "; do you accept ?");

        VBox mainVbox = new VBox();
        mainVbox.setId("wideContent");
        mainVbox.getStylesheets().add("sut/civilization/StyleSheet/popUp.css");

        ChoiceBox<String> nationChooser = new ChoiceBox<>();

        for (NationType value : NationType.values()) {
            nationChooser.getItems().add(value.name);
        }

        Button accept = new Button("accept");
        accept.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (nationChooser.getValue() != null){
                    Request request = new Request("gameMenu", "startGameAnswer");
                    request.addToken("owner",(String) updateResponse.getDataToken("owner"));
                    request.addToken("answer", "yes");
                    request.addToken("nation",nationChooser.getValue());
                    ConnectionController.getInstance().sendUpdateToServer(request.toJson());
                    popup.hide();
                }

                else {
                    new ViewController().showPopUp(Game.instance.getCurrentScene().getWindow(),"please choose a nation !");
                }

            }
        });

        Button reject = new Button("reject");
        reject.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Request request = new Request("gameMenu", "startGameAnswer");
                request.addToken("owner",(String) updateResponse.getDataToken("owner"));
                request.addToken("answer", "no");
                request.addToken("nation",nationChooser.getValue());
                ConnectionController.getInstance().sendUpdateToServer(request.toJson());
                popup.hide();
            }
        });


        HBox hBox = new HBox();
        hBox.getChildren().add(accept);
        hBox.getChildren().add(reject);
        hBox.setSpacing(10);
        hBox.setAlignment(Pos.CENTER);

        mainVbox.getChildren().add(label);
        mainVbox.getChildren().add(nationChooser);
        mainVbox.getChildren().add(hBox);

        popup.getContent().add(mainVbox);
        popup.setAutoHide(false);
        popup.show(Game.instance.getCurrentScene().getWindow());
    }
}

