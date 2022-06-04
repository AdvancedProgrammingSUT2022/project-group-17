package sut.civilization.View.Graphical;

import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import sut.civilization.Enums.Menus;
import sut.civilization.Model.Classes.Game;

import java.time.LocalDateTime;

public class ChatMenuController extends ViewController {
    public TextArea messageInput;
    public TextField search;
    public VBox allMessages;
    public ScrollPane messagesScrollPane;


    public void BackToMainMenu() {
        Game.changeScene(Menus.MAIN_MENU);
    }

    public void send() {
        if (!messageInput.getText().isBlank()) {
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
            sendingMessage.setMaxWidth(Region.USE_PREF_SIZE);
            VBox.setMargin(sendingMessage, new Insets(10, 0, 0, 0));

            allMessages.getChildren().add(sendingMessage);
            messageInput.setText("");
            messagesScrollPane.setVvalue(1);
        }
    }

    public void sendByEnter(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ENTER) send();
    }
}
