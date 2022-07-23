package sut.civilization.View.Graphical;

import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import sut.civilization.Controller.ConnectionController;
import sut.civilization.Controller.GameControllers.GameController;
import sut.civilization.Enums.Menus;
import sut.civilization.Model.Classes.Game;
import sut.civilization.Model.Classes.Request;

public class MainController extends ViewController{
    sut.civilization.Controller.MainController mainController = new sut.civilization.Controller.MainController();

    public void initialize(){
        setUser();
    }

    private void setUser() {
        Request setUserRequest = new Request("server","userSetter");
        setUserRequest.addToken("userName",Game.instance.getLoggedInUser().getUsername());
        ConnectionController.getInstance().sendUpdateToServer(setUserRequest.toJson());
    }
    public void startGame(MouseEvent mouseEvent) {
        Game.instance.changeScene(Menus.SELECTION_MENU);
    }

    public void enterProfileMenu(MouseEvent mouseEvent) {
        Game.instance.changeScene(Menus.PROFILE_MENU);
    }

    public void enterScoreBoardMenu(MouseEvent mouseEvent) {
        Game.instance.changeScene(Menus.SCORE_BOARD);
    }

    public void enterChatMenu(MouseEvent mouseEvent) {
        Game.instance.changeScene(Menus.CHAT_MENU);
    }

    public void exit(MouseEvent mouseEvent) {
        mainController.exitGame();
    }

    public void logout(MouseEvent mouseEvent) {
        showPopUp(Game.instance.getCurrentScene().getWindow(),mainController.logoutUser());
    }

    public void changeVolume(MouseEvent mouseEvent) {
        //TODO make this when music was settled

    }
}
