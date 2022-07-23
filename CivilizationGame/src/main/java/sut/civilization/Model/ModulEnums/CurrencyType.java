package sut.civilization.Model.ModulEnums;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import sut.civilization.Civilization;

import java.util.Objects;

public enum CurrencyType {
    FOOD("Food", Color.LIGHTGREEN),
    PRODUCTION("Production", Color.ORANGE),
    GOLD("Gold" , Color.YELLOW),
    SCIENCE("Science", Color.AQUA),
    HAPPINESS("Happiness", Color.PALEGREEN),

    ;
    public final String name;
    public final Image image;
    public final Color color;

    CurrencyType(String name, Color color) {
        this.name = name;
        this.image = new Image(Objects.requireNonNull(Civilization.class.getResourceAsStream("/sut/civilization/Images/currencies/" + name + ".png")));
        this.color = color;
    }
}
