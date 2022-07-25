package sut.civilization.Enums;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import sut.civilization.Civilization;

import java.util.Objects;

public enum avatarImages {
    ;

    private final Image image;

    public Image getImage() {
        return image;
    }

    avatarImages(String address){
        image = new Image(address);
    }

}
