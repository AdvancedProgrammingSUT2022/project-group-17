package sut.civilization.Transitions;

import javafx.animation.Transition;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import sut.civilization.Model.Classes.Game;

public class LoadingTransition extends Transition {
    private final Runnable runnable;

    private final int millisDuration;

    private ImageView loadingImage;

    private Parent parent;

    public LoadingTransition(Parent pane, Runnable runnable, int millisDuration){
        this.runnable = runnable;
        this.millisDuration = millisDuration;
        this.loadingImage = new ImageView(new Image("sut/civilization/Images/otherIcons/loadBlack.png"));
        this.loadingImage.setFitWidth(70);
        this.loadingImage.setFitHeight(70);
        this.loadingImage.setX(20);
        this.loadingImage.setY(Game.instance.getCurrentScene().getWindow().getY() + Game.instance.getCurrentScene().getWindow().getHeight() - this.loadingImage.getFitHeight() - 100);
        this.parent = pane;
        ( (Pane) pane).getChildren().add(this.loadingImage);
        this.setCycleCount(1);
        this.setCycleDuration(Duration.millis(millisDuration));

        this.parent.setDisable(true);
        if (runnable != null)
            this.setOnFinished(e -> runnable.run());
    }

    @Override
    protected void interpolate(double v) {
        loadingImage.setRotate(loadingImage.getRotate() + 5);
        if (v == 1){
            ((Pane) parent).getChildren().remove(loadingImage);
            parent.setDisable(false);
        }
    }
}
