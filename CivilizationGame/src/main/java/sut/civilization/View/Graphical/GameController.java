package sut.civilization.View.Graphical;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import sut.civilization.Enums.Menus;
import sut.civilization.Model.Classes.Game;

public class GameController {
    private final sut.civilization.Controller.GameControllers.GameController gameController = new sut.civilization.Controller.GameControllers.GameController();

    @FXML
    private TextField chooseOpponents;
    @FXML
    private TextField chooseNation;

    public void mainMenu(MouseEvent mouseEvent) {
        Game.changeScene(Menus.MAIN_MENU);
    }

    public void startGame(MouseEvent mouseEvent) {
        if (canGameStart()){
            gameController.chooseNation(Integer.parseInt(chooseNation.getText()), 0);
        }
    }

    public void loadGame(MouseEvent mouseEvent) {
    }

    public boolean canGameStart(){
        int nationNumber = Integer.parseInt(chooseNation.getText());
        int opponentsNumber = Integer.parseInt(chooseOpponents.getText());
        if (nationNumber < 0 || nationNumber > 8)
            return false;
        if (opponentsNumber < 0 || opponentsNumber > 3)
            return false;
    }
}
