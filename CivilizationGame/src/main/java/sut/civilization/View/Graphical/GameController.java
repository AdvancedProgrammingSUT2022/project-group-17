package sut.civilization.View.Graphical;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import sut.civilization.Enums.Menus;
import sut.civilization.Model.Classes.Game;

public class GameController {
    private final sut.civilization.Controller.GameControllers.GameController gameController = new sut.civilization.Controller.GameControllers.GameController();
    public Label chooseNation1;
    public TextField chooseOpponents1;
    public ChoiceBox nations1;
    public TextField chooseOpponents2;
    public Label chooseNation2;
    public ChoiceBox nations2;


    public void mainMenu(MouseEvent mouseEvent) {
        Game.changeScene(Menus.MAIN_MENU);
    }

    public void startGame(MouseEvent mouseEvent) {
        if (canGameStart()){
            int nation_one = Integer.parseInt(String.valueOf(nations1.getValue().toString().charAt(0)));
            System.out.println(nation_one);
            gameController.chooseNation(nation_one, 0);
            int nation_two = Integer.parseInt(String.valueOf(nations2.getValue().toString().charAt(0)));
            gameController.chooseNation(nation_two, 1);
            System.out.println(Game.getPlayersInGame().size());
        }
    }

    public void loadGame(MouseEvent mouseEvent) {
    }

    public boolean canGameStart(){
        int opponentsNumber1 = Integer.parseInt(chooseOpponents1.getText());
        int opponentsNumber2 = Integer.parseInt(chooseOpponents2.getText());
        if (opponentsNumber1 < 0 || opponentsNumber1 > 3)
            return false;
        if (opponentsNumber2 < 0 || opponentsNumber2 > 3)
            return false;

        return true;
    }

    public void initialize(){
        String[] nations = {"0- Indus Valley", "1- Maya", "2- Ancient Greece", "3- Persia", "4- Ancient Egypt", "5- Mesopotamian",
                "6- Rome", "7- Aztec", "8- Inca"};
        for (int i = 0; i < 9; i++) {
            Label label = new Label(nations[i]);
            nations1.getItems().add(label.getText());
            nations2.getItems().add(label.getText());
        }
    }
}
