package sut.civilization.View.Graphical;

import javafx.scene.control.Label;
import javafx.stage.Popup;
import javafx.stage.Window;

public class ViewController {
    String resultMessage;

    protected void showPopUp(Window window, String resultMessage) {
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
}
