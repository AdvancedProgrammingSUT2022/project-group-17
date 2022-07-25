package sut.civilization.View.Graphical;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import sut.civilization.Civilization;
import sut.civilization.Enums.Menus;
import sut.civilization.Model.Classes.Game;
import sut.civilization.Model.Classes.Pair;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

public class ProfileController extends ViewController{

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
    private final sut.civilization.Controller.ProfileController profileController = new sut.civilization.Controller.ProfileController();

    public void initialize(){
        HBox allAvatarsContainer = new HBox();
        allAvatarsContainer.setSpacing(10);
        allAvatarsContainer.setAlignment(Pos.CENTER);

        avatarImageView.setImage(new Image(Game.instance.getLoggedInUser().getAvatarLocation()));
        userNameText.setText("UserName : " + Game.instance.getLoggedInUser().getUsername());
        nickNameText.setText("NickName : " + Game.instance.getLoggedInUser().getNickname());
        scoreText.setText("Score : " + Game.instance.getLoggedInUser().getScore());
        allAvatarsContainer.getChildren().clear();

        for (int i = 1; i < 19; i++) {
            ImageView imageView = new ImageView(new Image("sut/civilization/Images/Avatars/ (" + i + ").png"));
            imageView.setFitWidth(130);
            imageView.setFitHeight(130);
            imageView.setOnMouseClicked(this::changeAvatar);
            allAvatarsContainer.getChildren().add(imageView);
        }

        allAvatarsContainer.getStyleClass().add("Hbox");
        avatarsScrollPane.setContent(allAvatarsContainer);
    }

    public void changeUserPassword(MouseEvent mouseEvent) {
        resultMessage = profileController.changePassword(new Pair<>(newPassword.getText(),newPasswordRepeated.getText()),oldPassword.getText());
        showPopUp(Game.instance.getCurrentScene().getWindow(),resultMessage);
        newPasswordRepeated.setText("");
        newPassword.setText("");
        oldPassword.setText("");
    }

    public void changeUserNickname(MouseEvent mouseEvent) {
        resultMessage = profileController.changeNickname(new Pair<>(newNickname.getText(),newNicknameRepeated.getText()),oldNickname.getText());
        showPopUp(Game.instance.getCurrentScene().getWindow(),resultMessage);
        newNickname.setText("");
        newNicknameRepeated.setText("");
        oldNickname.setText("");
    }

    public void backToMainMenu(MouseEvent mouseEvent) {
        Game.instance.changeScene(Menus.MAIN_MENU);
    }

    public void chooseAvatarFromComputer(MouseEvent mouseEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("png files","*.png"),new FileChooser.ExtensionFilter("jpg files","*.jpg"),new FileChooser.ExtensionFilter("jpeg files","*.jpeg"));
        File file = fileChooser.showOpenDialog(null);
        if (file == null)
            return;
        File temp = new File("src/main/resources/sut/civilization/Images/Avatars/"+ file.getName());

        try {
            Files.copy(file.toPath(), Paths.get("src/main/resources/sut/civilization/Images/Avatars/" + file.getName()));
        } catch (IOException ignored) {
        }

        Game.instance.getLoggedInUser().setAvatarLocation(String.valueOf(file.toURI()));
        avatarImageView.setImage(new Image(String.valueOf(file.toURI())));
    }

    public void changeAvatar(MouseEvent mouseEvent) {
        ImageView imageView = (ImageView) mouseEvent.getSource();
        avatarImageView.setImage(imageView.getImage());
        Game.instance.getLoggedInUser().setAvatarLocation(imageView.getImage().getUrl());
    }
}
