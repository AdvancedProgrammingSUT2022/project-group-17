package sut.civilization;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.shape.Polygon;
import javafx.stage.Stage;
import sut.civilization.Enums.Menus;
import sut.civilization.Model.Classes.Game;

public class Civilization extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Game.setCurrentScene(new Scene(Game.loadScene(Menus.LOGIN_MENU)));
        stage.setResizable(false);
        stage.setScene(Game.getCurrentScene());
        stage.setTitle("Civilization V");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
