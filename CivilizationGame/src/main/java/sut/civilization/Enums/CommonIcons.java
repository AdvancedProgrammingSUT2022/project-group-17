package sut.civilization.Enums;

import javafx.scene.image.Image;
import sut.civilization.Civilization;

import java.util.Objects;

public enum CommonIcons {
    EX(new Image(Objects.requireNonNull(Civilization.class.getResourceAsStream(
            "/sut/civilization/Images/otherIcons/ex.png"
    ))))
    ;

    public final Image image;

    CommonIcons(Image image) {
        this.image = image;
    }
}
