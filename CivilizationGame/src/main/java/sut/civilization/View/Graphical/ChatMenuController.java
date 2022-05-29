package sut.civilization.View.Graphical;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import sut.civilization.Enums.Menus;
import sut.civilization.Model.Classes.Game;

import java.time.LocalDateTime;

public class ChatMenuController extends ViewController {
    public TextField messageInput;
    public TextField search;
    public VBox allMessages;


    public void BackToMainMenu() {
        Game.changeScene(Menus.MAIN_MENU);
    }

    public void send() {
        VBox sendingMessage = new VBox();
        Label name = new Label(Game.getLoggedInUser().getUsername());
        name.getStyleClass().add("name");
        Label messageText = new Label(messageInput.getText());
        messageText.getStyleClass().add("message");
        Label time = new Label(LocalDateTime.now().getHour() + ":" + LocalDateTime.now().getMinute());
        time.getStyleClass().add("time");
        sendingMessage.getChildren().add(name);
        sendingMessage.getChildren().add(messageText);
        sendingMessage.getChildren().add(time);
        sendingMessage.getStyleClass().add("messageBox");
        VBox.setMargin(sendingMessage, new Insets(10,0,0,0));

        allMessages.getChildren().add(sendingMessage);
        messageInput.setText("");
    }

}
