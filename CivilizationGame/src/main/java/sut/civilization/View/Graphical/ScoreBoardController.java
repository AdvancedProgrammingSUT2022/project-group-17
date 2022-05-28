package sut.civilization.View.Graphical;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import sut.civilization.Civilization;
import sut.civilization.Enums.Menus;
import sut.civilization.Model.Classes.Game;
import sut.civilization.Model.Classes.User;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.Objects;

public class ScoreBoardController extends ViewController {
    @FXML
    private VBox scoreBoardTable;

    public void initialize() {
        scoreBoardTable.getChildren().clear();

        ArrayList<User> users = getSortedUserList();
        for (int i = 0; i < (int) Math.min(9, users.size()); i++) {
            User user = users.get(i);

            HBox hbox = new HBox();
            hbox.setAlignment(Pos.CENTER);
            hbox.setSpacing(10);
            hbox.getStyleClass().add("informationContainer");
            hbox.getStylesheets().add(scoreBoardTable.getStylesheets().get(0));


            Label numberIndicator = new Label(String.valueOf(i + 1));
            numberIndicator.getStyleClass().add("numberIndicator");

            ImageView avatarImage = new ImageView(new Image(user.getAvatarLocation()));
            avatarImage.getStyleClass().add("imageView");
            avatarImage.setFitWidth(50);
            avatarImage.setFitHeight(50);

            String lastWin = (user.getLastWinTime() == 0 ? "N/A" : new Date(user.getLastWinTime()).toString().substring(0,new Date(user.getLastWinTime()).toString().length()-10));

            Label information = new Label(user.getNickname() + " /  lastWin : " + lastWin + " / score : " + user.getScore());
            if (user.equals(Game.getLoggedInUser())) {
                hbox.getStyleClass().add("onlineUserBorder");
                information.getStyleClass().add("onlineUserText");
                numberIndicator.getStyleClass().add("onlineUserText");
                numberIndicator.getStyleClass().add("onlineUserBorder");
            }

            hbox.getChildren().add(numberIndicator);
            hbox.getChildren().add(avatarImage);
            hbox.getChildren().add(information);

            scoreBoardTable.getChildren().add(hbox);

        }
    }

    public void backToMainMenu(MouseEvent mouseEvent) {
        Game.changeScene(Menus.MAIN_MENU);
    }

    private ArrayList<User> getSortedUserList() {
        Comparator<User> comparator = Comparator.comparing(User::getScore).reversed().thenComparing(User::getLastWinTime).thenComparing(User::getNickname);

        ArrayList<User> temp = new ArrayList<>(Game.getUsers());
        temp.sort(comparator);

        return temp;
    }
}
