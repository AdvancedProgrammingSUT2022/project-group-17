package sut.civilization.View.Graphical;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Popup;
import javafx.stage.Window;
import sut.civilization.Controller.ConnectionController;
import sut.civilization.Model.Classes.Game;
import sut.civilization.Model.Classes.Request;
import sut.civilization.Model.Classes.Response;
import sut.civilization.Model.Classes.User;

public class ViewController {
    String resultMessage;

    public void showPopUp(Window window, String resultMessage) {
        Popup popup = new Popup();
        Label label = new Label(resultMessage);


        if (isMessageError(resultMessage)) {
            label.setStyle("-fx-min-width: 300;" +
                    "    -fx-min-height: 40;" +
                    "    -fx-text-alignment: center;" +
                    "    -fx-alignment: center;" +
                    "    -fx-border-radius: 5;" +
                    "    -fx-background-radius: 5;" +
                    "    -fx-border-color: #ff0000;" +
                    "    -fx-background-color: #ffffff;" +
                    "    -fx-border-width: 1;" +
                    "    -fx-font-family: \"Segoe UI Black\"");

        } else {
            label.setStyle("-fx-min-width: 300;" +
                    "    -fx-min-height: 40;" +
                    "    -fx-text-alignment: center;" +
                    "    -fx-alignment: center;" +
                    "    -fx-border-radius: 5;" +
                    "    -fx-background-radius: 5;" +
                    "    -fx-border-color: #59ff00;" +
                    "    -fx-background-color: #ffffff;" +
                    "    -fx-border-width: 1;" +
                    "    -fx-font-family:\"Segoe UI Black\"");

        }
        popup.setX(window.getX() + 10);
        popup.setY(window.getY() + window.getHeight() - 52);
        popup.setAutoHide(true);
        popup.getContent().add(label);
        popup.show(window);
    }

    protected boolean isMessageError(String message){
        return message.charAt(message.length() - 1) == '!';
    }

    protected User getUserByName(String name){
        for (User user : Game.instance.getUsers()) {
            if (user.getUsername().equals(name))
                return user;
        }
        return null;
    }

    protected void showUserInformationPopUp(User user) {
        Popup popup = new Popup();
        popup.setAutoHide(true);
        popup.setAutoFix(true);
        // auto fix ?

        VBox vBox = new VBox();
        vBox.setId("content");
        vBox.setAlignment(Pos.CENTER);
        vBox.getStylesheets().add("sut/civilization/StyleSheet/popUp.css");
        vBox.setSpacing(20);

        ImageView avatar = new ImageView(new Image(user.getAvatarLocation()));
        Label userName = new Label("UserName : " + user.getUsername());
        Label nickName = new Label("nickName : " +user.getNickname());
        Label score = new Label("Score : " + user.getScore());

        Button sendRequest = new Button();
        sendRequest.setId("sendRequestButton");


        sendRequest.setText("Send Friend Request");

        sendRequest.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Request request = new Request("social","addFriendRequest");
                request.addToken("to",user.getUsername());
                request.addToken("from",Game.instance.getLoggedInUser().getUsername());

                Response response = ConnectionController.getInstance().sendRequestToServer(request.toJson());
                showPopUp(Game.instance.getCurrentScene().getWindow(),response.getMessage());
            }
        });

        Button closeButton = new Button();
        closeButton.setId("closeButton");
        closeButton.setText("close");
        closeButton.setOnMouseClicked(mouseEvent -> popup.hide());

        HBox hbox = new HBox();
        hbox.setAlignment(Pos.CENTER);
        hbox.setSpacing(10);

        hbox.getChildren().add(closeButton);
        hbox.getChildren().add(sendRequest);


        vBox.getChildren().add(avatar);
        vBox.getChildren().add(userName);
        vBox.getChildren().add(nickName);
        vBox.getChildren().add(score);
        vBox.getChildren().add(hbox);
        popup.getContent().add(vBox);

        Window window = Game.instance.getCurrentScene().getWindow();

        popup.setAnchorY(window.getY() + window.getHeight()/2 - 225);
        popup.setAnchorX(window.getX() + window.getWidth()/2 - 220);

        popup.setWidth(250);
        popup.setHeight(250);

        popup.show(window);

    }

    public User getPlayer(String name){
        if (Game.instance.getPlayersInGame() == null)
            return null;

        for (User user : Game.instance.getPlayersInGame()) {
            if (user.getUsername().equals(name))
                return user;
        }
        return null;
    }
}
