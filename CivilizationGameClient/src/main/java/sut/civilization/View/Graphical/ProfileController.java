package sut.civilization.View.Graphical;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Popup;
import javafx.stage.Window;
import sut.civilization.Controller.ConnectionController;
import sut.civilization.Enums.Menus;
import sut.civilization.Model.Classes.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Map;

public class ProfileController extends ViewController {

    private final sut.civilization.Controller.ProfileController profileController = new sut.civilization.Controller.ProfileController();
    public ScrollPane avatarsScrollPane;
    @FXML
    private ImageView avatarImageView;
    @FXML
    private Label scoreText;
    @FXML
    private Label userNameText;
    @FXML
    private Label nickNameText;
    @FXML
    private PasswordField oldPassword;
    @FXML
    private PasswordField newPassword;
    @FXML
    private PasswordField newPasswordRepeated;
    @FXML
    private TextField oldNickname;
    @FXML
    private TextField newNickname;
    @FXML
    private TextField newNicknameRepeated;

    public void initialize() {
        HBox allAvatarsContainer = new HBox();
        allAvatarsContainer.setSpacing(10);
        allAvatarsContainer.setAlignment(Pos.CENTER);

        avatarImageView.setImage(new Image(Game.instance.getLoggedInUser().getAvatarLocation()));
        userNameText.setText("UserName : " + Game.instance.getLoggedInUser().getUsername());
        nickNameText.setText("NickName : " + Game.instance.getLoggedInUser().getNickname());
        scoreText.setText("Score : " + Game.instance.getLoggedInUser().getScore());
        allAvatarsContainer.getChildren().clear();

        for (int i = 1; i < 19; i++) {
            ImageView imageView = new ImageView(new Image("sut/civilization/Images/Avatars/(" + i + ").png"));
            imageView.setFitWidth(130);
            imageView.setFitHeight(130);
            imageView.setOnMouseClicked(this::changeAvatar);
            allAvatarsContainer.getChildren().add(imageView);
        }

        allAvatarsContainer.getStyleClass().add("Hbox");
        avatarsScrollPane.setContent(allAvatarsContainer);
    }

    public void changeUserPassword(MouseEvent mouseEvent) {
        resultMessage = profileController.changePassword(new Pair<>(newPassword.getText(), newPasswordRepeated.getText()), oldPassword.getText());
        showPopUp(Game.instance.getCurrentScene().getWindow(), resultMessage);
        newPasswordRepeated.setText("");
        newPassword.setText("");
        oldPassword.setText("");
    }

    public void changeUserNickname(MouseEvent mouseEvent) {
        resultMessage = profileController.changeNickname(new Pair<>(newNickname.getText(), newNicknameRepeated.getText()), oldNickname.getText());
        showPopUp(Game.instance.getCurrentScene().getWindow(), resultMessage);

        nickNameText.setText(Game.instance.getLoggedInUser().getNickname());

        newNickname.setText("");
        newNicknameRepeated.setText("");
        oldNickname.setText("");
    }

    public void backToMainMenu(MouseEvent mouseEvent) {
        Game.instance.changeScene(Menus.MAIN_MENU);
    }

    public void chooseAvatarFromComputer(MouseEvent mouseEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("png files", "*.png"), new FileChooser.ExtensionFilter("jpg files", "*.jpg"), new FileChooser.ExtensionFilter("jpeg files", "*.jpeg"));
        File file = fileChooser.showOpenDialog(null);
        if (file == null)
            return;
        File temp = new File("src/main/resources/sut/civilization/Images/Avatars/" + file.getName());

        try {
            Files.copy(file.toPath(), Paths.get("src/main/resources/sut/civilization/Images/Avatars/" + file.getName()));
        } catch (IOException ignored) {
        }

        Game.instance.getLoggedInUser().setAvatarLocation(String.valueOf(file.toURI()));
        avatarImageView.setImage(new Image(String.valueOf(file.toURI())));

        Request request = new Request("profile", "updateAvatarLocation");
        request.addToken("userName", Game.instance.getLoggedInUser().getUsername());
        request.addToken("location", Game.instance.getLoggedInUser().getAvatarLocation());
        ConnectionController.getInstance().sendUpdateToServer(request.toJson());
    }

    public void changeAvatar(MouseEvent mouseEvent) {
        ImageView imageView = (ImageView) mouseEvent.getSource();
        avatarImageView.setImage(imageView.getImage());
        Game.instance.getLoggedInUser().setAvatarLocation(imageView.getImage().getUrl());
        Request request = new Request("profile", "updateAvatarLocation");
        request.addToken("userName", Game.instance.getLoggedInUser().getUsername());
        request.addToken("location", Game.instance.getLoggedInUser().getAvatarLocation());
        ConnectionController.getInstance().sendUpdateToServer(request.toJson());
    }

    public void showFriendRequests(MouseEvent mouseEvent) {
        Popup popup = new Popup();
        popup.setAutoHide(false);
        popup.setAutoFix(true);
        // auto fix ?

        VBox mainVBox = new VBox();
        mainVBox.setId("wideContent");
        mainVBox.getStylesheets().add("sut/civilization/StyleSheet/popUp.css");
        mainVBox.setAlignment(Pos.CENTER);

        VBox subVbox = new VBox();
        subVbox.setSpacing(5);
        subVbox.setMinWidth(1000);
        subVbox.setAlignment(Pos.CENTER);

        ScrollPane scrollPane = new ScrollPane(subVbox);
        scrollPane.setPrefViewportHeight(350);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);


        mainVBox.getChildren().add(scrollPane);

        Request sentRequest = new Request("social", "sendFriendRequests");
        sentRequest.addToken("userName", Game.instance.getLoggedInUser().getUsername());
        Response response = ConnectionController.getInstance().sendRequestToServer(sentRequest.toJson());
        ArrayList<Request> requests = new Gson().fromJson((String) response.getDataToken("requests"),new TypeToken<ArrayList<Request>>(){}.getType());
        ServerDataBase.getInstance().setFriendRequest(Game.instance.getLoggedInUser().getUsername(), requests);

        if (requests != null) {
            for (Request request : requests) {
                String from = request.getToken("from");
                if (getUserByName(from) == null)
                    continue;

                HBox hBox = new HBox();
                hBox.setId("subContent");

                Label text = new Label("friend request from : " + from);
                ImageView check = new ImageView(new Image("sut/civilization/Images/otherIcons/Checkmark.png"));
                ImageView cross = new ImageView(new Image("sut/civilization/Images/otherIcons/Close.png"));
                ImageView imageView = new ImageView(new Image(getUserByName(from).getAvatarLocation()));
                imageView.setOnMouseClicked(e -> super.showUserInformationPopUp(getUserByName(from)));

                imageView.setCursor(Cursor.HAND);

                check.setFitHeight(50);
                check.setFitWidth(50);
                cross.setFitHeight(50);
                cross.setFitWidth(50);
                cross.setCursor(Cursor.HAND);
                check.setCursor(Cursor.HAND);

                Separator separator = new Separator();
                separator.setOrientation(Orientation.HORIZONTAL);
                separator.setMaxWidth(800);

                check.setOnMouseClicked(e -> this.answerRequest("yes", from, separator, hBox, subVbox));
                cross.setOnMouseClicked(e -> this.answerRequest("no", from, separator, hBox, subVbox));

                hBox.getChildren().add(imageView);
                hBox.getChildren().add(text);
                hBox.getChildren().add(check);
                hBox.getChildren().add(cross);

                subVbox.getChildren().add(hBox);
                subVbox.getChildren().add(separator);

            }
        }

        Button sendRequest = new Button();
        sendRequest.setId("sendRequestButton");
        sendRequest.setText("Reject All");
        sendRequest.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {

            }
        });

        Button closeButton = new Button();
        closeButton.setId("closeButton");
        closeButton.setText("close");
        closeButton.setOnMouseClicked(mouseEvent1 -> popup.hide());


        HBox hbox = new HBox();
        hbox.setAlignment(Pos.CENTER);
        hbox.setSpacing(10);
        hbox.getChildren().add(closeButton);
        hbox.getChildren().add(sendRequest);
        mainVBox.getChildren().add(hbox);
        popup.getContent().add(mainVBox);

        Window window = Game.instance.getCurrentScene().getWindow();

        popup.setAnchorY(window.getY() + window.getHeight() / 2 - 250);
        popup.setAnchorX(window.getX() + window.getWidth() / 2 - 500);

        popup.show(window);

    }

    private void answerRequest(String answer, String key, Separator separator, HBox hBox, VBox vBox) {
        vBox.getChildren().remove(hBox);
        vBox.getChildren().remove(separator);
        Request request = new Request("social", "answerFriendRequest");
        request.addToken("userName", Game.instance.getLoggedInUser().getUsername());
        request.addToken("target", key);
        request.addToken("answer", answer);
        Response response = ConnectionController.getInstance().sendRequestToServer(request.toJson());
        showPopUp(Game.instance.getCurrentScene().getWindow(), response.getMessage());
    }

    public void showWaitingRequests(MouseEvent mouseEvent) {
        Request sentRequest = new Request("social", "sendWaitingRequests");
        sentRequest.addToken("userName", Game.instance.getLoggedInUser().getUsername());
        Response response = ConnectionController.getInstance().sendRequestToServer(sentRequest.toJson());
        ArrayList<Request> requests = new Gson().fromJson((String) response.getDataToken("requests"),new TypeToken<ArrayList<Request>>(){}.getType());

        Popup popup = new Popup();
        popup.setAutoHide(false);
        popup.setAutoFix(true);

        VBox mainVBox = new VBox();
        mainVBox.setId("wideContent");
        mainVBox.getStylesheets().add("sut/civilization/StyleSheet/popUp.css");
        mainVBox.setAlignment(Pos.CENTER);

        VBox subVbox = new VBox();
        subVbox.setSpacing(5);
        subVbox.setMinWidth(1000);
        subVbox.setAlignment(Pos.CENTER);

        ScrollPane scrollPane = new ScrollPane(subVbox);
        scrollPane.setPrefViewportHeight(350);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        mainVBox.getChildren().add(scrollPane);

        if (requests != null) {
            for (Request request : requests) {
                String to = request.getToken("to");
                if (getUserByName(to) == null)
                    continue;

                HBox hBox = new HBox();
                hBox.setId("subContent");

                Label text = new Label("friend request to : " + to);
                ImageView imageView = new ImageView(new Image(getUserByName(to).getAvatarLocation()));
                imageView.setOnMouseClicked(e -> super.showUserInformationPopUp(getUserByName(to)));
                imageView.setCursor(Cursor.HAND);


                Separator separator = new Separator();
                separator.setOrientation(Orientation.HORIZONTAL);
                separator.setMaxWidth(800);

                hBox.getChildren().add(imageView);
                hBox.getChildren().add(text);

                subVbox.getChildren().add(hBox);
                subVbox.getChildren().add(separator);

            }
        }

        Button closeButton = new Button();
        closeButton.setId("closeButton");
        closeButton.setText("close");
        closeButton.setOnMouseClicked(mouseEvent1 -> popup.hide());


        HBox hbox = new HBox();
        hbox.setAlignment(Pos.CENTER);
        hbox.setSpacing(10);
        hbox.getChildren().add(closeButton);
        mainVBox.getChildren().add(hbox);
        popup.getContent().add(mainVBox);

        Window window = Game.instance.getCurrentScene().getWindow();

        popup.setAnchorY(window.getY() + window.getHeight() / 2 - 250);
        popup.setAnchorX(window.getX() + window.getWidth() / 2 - 500);

        popup.show(window);

    }
}
