package sut.civilization.View.Graphical;

import javafx.beans.value.ChangeListener;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import sut.civilization.Civilization;
import sut.civilization.Controller.GameControllers.LandController;
import sut.civilization.Model.Classes.Game;
import sut.civilization.Model.Classes.LandGraphical;
import sut.civilization.Model.Classes.Pair;

import java.util.Objects;

public class GameMenuController extends ViewController{
    @FXML
    private ScrollPane root;

    public void initialize(){
        Game.getCurrentScene().getWindow().setHeight(800);
        Game.getCurrentScene().getWindow().setWidth(1500);
        Game.getCurrentScene().getWindow().setX(10);
        Game.getCurrentScene().getWindow().setY(10);
        Pane pane = new Pane();
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 10; j++) {
                LandGraphical landGraphical = new LandGraphical(new Pair<>(i, j));
                pane.getChildren().add(landGraphical);
            }
        }
        root.setContent(pane);
    }
}
