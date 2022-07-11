package sut.civilization.View.Graphical;

import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import sut.civilization.Controller.GameControllers.GameController;
import sut.civilization.Enums.Menus;
import sut.civilization.Model.Classes.Game;

public class MainController extends ViewController{
    sut.civilization.Controller.MainController mainController = new sut.civilization.Controller.MainController();

    public void startGame(MouseEvent mouseEvent) {
        Game.changeScene(Menus.SELECTION_MENU);
    }

    public void enterProfileMenu(MouseEvent mouseEvent) {
        Game.changeScene(Menus.PROFILE_MENU);
    }

    public void enterScoreBoardMenu(MouseEvent mouseEvent) {
        Game.changeScene(Menus.SCORE_BOARD);
    }

    public void enterChatMenu(MouseEvent mouseEvent) {
        Game.changeScene(Menus.CHAT_MENU);
    }

    public void exit(MouseEvent mouseEvent) {
        mainController.exitGame();
    }

    public void logout(MouseEvent mouseEvent) {
        showPopUp(Game.getCurrentScene().getWindow(),mainController.logoutUser());
    }

    public void changeVolume(MouseEvent mouseEvent) {
        //TODO make this when music was settled

    }
}
