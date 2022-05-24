package sut.civilization.View.Graphical;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import sut.civilization.Enums.Menus;
import sut.civilization.Model.Classes.Game;
import sut.civilization.Model.Classes.User;

public class LoginController extends ViewController {
    sut.civilization.Controller.LoginController loginController = new sut.civilization.Controller.LoginController();
    @FXML
    private PasswordField loginPasswordField;
    @FXML
    private TextField loginUserNameField;
    @FXML
    private TextField registerUserNameField;
    @FXML
    private TextField registerNickNameField;
    @FXML
    private PasswordField registerPasswordField;

    public void registerUser(MouseEvent mouseEvent) {
        if (registerNickNameField.getText().equals("") || registerPasswordField.getText().equals("") || registerUserNameField.getText().equals(""))
            resultMessage = "you must fill all parts!";
        else
            resultMessage = loginController.createUser(new User(registerUserNameField.getText(), registerPasswordField.getText(), registerNickNameField.getText()));
        if (isMessageError(resultMessage)) {
            registerUserNameField.getStyleClass().add("Error");
            registerPasswordField.getStyleClass().add("Error");
            registerNickNameField.getStyleClass().add("Error");
        } else {
            registerUserNameField.getStyleClass().clear();
            registerPasswordField.getStyleClass().clear();
            registerNickNameField.getStyleClass().clear();
            Game.changeScene(Menus.MAIN_MENU);
        }
        showPopUp(Game.getCurrentScene().getWindow(), resultMessage);
    }

    public void enterAsGuest(MouseEvent mouseEvent) {
        resultMessage = loginController.enterAsGuest();
        Game.changeScene(Menus.MAIN_MENU);
        showPopUp(Game.getCurrentScene().getWindow(), resultMessage);
    }

    public void loginUser(MouseEvent mouseEvent) {
        if (loginUserNameField.getText().equals("") || loginPasswordField.getText().equals(""))
            resultMessage = "you must fill all parts!";
        else
            resultMessage = loginController.loginUser(new User(loginUserNameField.getText(), loginPasswordField.getText(), ""));

        if (isMessageError(resultMessage)) {
            loginPasswordField.getStyleClass().add("Error");
            loginUserNameField.getStyleClass().add("Error");
        } else {
            loginPasswordField.getStyleClass().clear();
            loginUserNameField.getStyleClass().clear();
            Game.changeScene(Menus.MAIN_MENU);
        }

        showPopUp(Game.getCurrentScene().getWindow(), resultMessage);
    }
}
