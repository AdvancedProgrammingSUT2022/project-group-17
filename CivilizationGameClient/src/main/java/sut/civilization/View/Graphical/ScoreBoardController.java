package sut.civilization.View.Graphical;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
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
import javafx.util.Duration;
import sut.civilization.Controller.ConnectionController;
import sut.civilization.Enums.Menus;
import sut.civilization.Model.Classes.Game;
import sut.civilization.Model.Classes.Request;
import sut.civilization.Model.Classes.Response;
import sut.civilization.Model.Classes.User;

import java.util.*;

public class ScoreBoardController extends ViewController {
    @FXML
    private VBox scoreBoardTable;
    public Timeline timelineUpdater;

    public void initialize() {
        setUp();
        timelineUpdater = new Timeline();
        timelineUpdater.setCycleCount(-1);
        timelineUpdater.getKeyFrames().add(new KeyFrame(Duration.millis(1500), actionEvent -> setUp()));
        timelineUpdater.play();
    }

    public void backToMainMenu(MouseEvent mouseEvent) {
        timelineUpdater.stop();
        Game.instance.changeScene(Menus.MAIN_MENU);
    }

    private void setUp(){
        Request request = new Request("scoreBoard", "updateList");
        Response response = ConnectionController.getInstance().sendRequestToServer(request.toJson());

        for (User user : Game.instance.getUsers()) {
            user.setOnline(false);
        }

        User temp;
        ArrayList<String> userNames = (ArrayList<String>) response.getDataToken("userNames");
        Map<String, String> avatarLocations = (Map <String,String>) response.getDataToken("avatarLocations");
        Map<String, Double> scores = (Map<String,Double>) response.getDataToken("scores");
        Map<String, Double> lastWins = (Map<String,Double>) response.getDataToken("lastWins");
        if (userNames != null) {
            for (String userName : userNames) {
                if ((temp = getUserByName(userName)) != null) {
                    temp.setOnline(true);
                }
            }
        }


        for (User user : Game.instance.getUsers()) {
            if (avatarLocations.get(user.getUsername()) != null)
                user.setAvatarLocation(avatarLocations.get(user.getUsername()));

            if (scores.get(user.getUsername()) != null){
                int kooft = (int) Math.floor(scores.get(user.getUsername()));
                user.setScore(kooft);
            }

            if (lastWins.get(user.getUsername()) != null){
                int kooft = (int) Math.floor(lastWins.get(user.getUsername()));
                user.setLastWinTime(kooft);

            }
        }


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

            String lastWin = (user.getLastWinTime() == 0 ? "N/A" : new Date(user.getLastWinTime()).toString().substring(0, new Date(user.getLastWinTime()).toString().length() - 10));

            Label information = new Label(user.getNickname() + " /  lastWin : " + lastWin + " / score : " + user.getScore());

            if (user.isOnline()) {
                hbox.getStyleClass().add("onlineUserBorder");
                information.getStyleClass().add("onlineUserText");
                numberIndicator.getStyleClass().add("onlineUserText");
                numberIndicator.getStyleClass().add("onlineUserBorder");
            } else {
                hbox.getStyleClass().remove("onlineUserBorder");
                information.getStyleClass().remove("onlineUserText");
                numberIndicator.getStyleClass().remove("onlineUserText");
                numberIndicator.getStyleClass().remove("onlineUserBorder");
            }

            avatarImage.setOnMouseClicked(actionEvent -> showUserInformationPopUp(user));

            hbox.getChildren().add(numberIndicator);
            hbox.getChildren().add(avatarImage);
            hbox.getChildren().add(information);

            scoreBoardTable.getChildren().add(hbox);
        }

    }

    private ArrayList<User> getSortedUserList() {
        Comparator<User> comparator = Comparator.comparing(User::getScore).reversed().thenComparing(User::getLastWinTime).thenComparing(User::getNickname);

        ArrayList<User> temp = new ArrayList<>(Game.instance.getUsers());
        temp.sort(comparator);

        return temp;
    }




}
