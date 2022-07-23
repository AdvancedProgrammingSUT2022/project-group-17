package sut.civilization;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.stage.WindowEvent;
import sut.civilization.Controller.ConnectionController;
import sut.civilization.Enums.Menus;
import sut.civilization.Model.Classes.Game;
import sut.civilization.Model.Classes.User;

import java.util.Objects;

public class Civilization extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        ConnectionController.getInstance().initConnection();

        Game.instance.setCurrentScene(new Scene(Game.instance.loadScene(Menus.LOGIN_MENU)));

        stage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResource("Images/Icons/programIcon.png")).toExternalForm()));
        stage.setScene(Game.instance.getCurrentScene());
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent windowEvent) {
                if (Game.instance.getLoggedInUser() != null){
                    Game.instance.getLoggedInUser().setNation(null);
                    Game.instance.getLoggedInUser().setOnline(false);
                }
                Game.instance.saveUserListToDatabase();
                stage.close();
                ConnectionController.getInstance().closeConnection();
                System.exit(0);

            }
        });
        stage.setTitle("Civilization V");
        stage.show();


    }

    public static void main(String[] args) {
        launch(args);
    }
}
