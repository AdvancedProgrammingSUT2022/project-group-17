package sut.civilization.Model.ModulEnums;

import javafx.scene.paint.Color;

public enum CurrencyType {
    FOOD("Food", Color.LIGHTGREEN),
    PRODUCTION("Production", Color.ORANGE),
    GOLD("Gold" , Color.YELLOW),
    SCIENCE("Science", Color.AQUA),
    HAPPINESS("Happiness", Color.PALEGREEN),

    ;
    public String name;
    public String imageAddress;
    public Color color;

    CurrencyType(String name, Color color) {
        this.name = name;
        this.imageAddress = "/sut/civilization/Images/currencies/" + name + ".png";
        this.color = color;
    }
}
