package sut.civilization;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.shape.Polygon;
import javafx.stage.Stage;
import sut.civilization.Enums.Menus;
import sut.civilization.Model.Classes.Game;

import java.util.Objects;

public class Civilization extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Game.instance.setCurrentScene(new Scene(Game.instance.loadScene(Menus.LOGIN_MENU)));
        stage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResource("Images/Icons/programIcon.png")).toExternalForm()));
        stage.setScene(Game.instance.getCurrentScene());
        stage.setTitle("Civilization V");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
