package sut.civilization;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sut.civilization.Enums.Menus;
import sut.civilization.Model.Classes.Game;

public class Civilization extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Game.setCurrentScene(new Scene(Game.loadScene(Menus.LOGIN_MENU)));
        stage.setScene(Game.getCurrentScene());
        stage.setTitle("Civilization V");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
