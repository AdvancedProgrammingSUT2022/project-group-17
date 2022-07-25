package sut.civilization.Model.ModulEnums;

import javafx.scene.image.Image;
import sut.civilization.Civilization;

public enum UnitActions {
    MOVE(new Image(Civilization.class.getResourceAsStream("/sut/civilization/Images/unitActions/Move.png"))),
    DELETE(new Image(Civilization.class.getResourceAsStream("/sut/civilization/Images/unitActions/Delete.png"))),
    WAKE(new Image(Civilization.class.getResourceAsStream("/sut/civilization/Images/unitActions/Wake.png"))),
    SLEEP(new Image(Civilization.class.getResourceAsStream("/sut/civilization/Images/unitActions/Sleep.png"))),
    REPAIR(new Image(Civilization.class.getResourceAsStream("/sut/civilization/Images/unitActions/Repair.png"))),
    ATTACK(new Image(Civilization.class.getResourceAsStream("/sut/civilization/Images/unitActions/Attack.png"))),
    RANGED_ATTACK(new Image(Civilization.class.getResourceAsStream("/sut/civilization/Images/unitActions/Ranged Attack.png"))),
    FORTIFY_UNTIL_HEAL(new Image(Civilization.class.getResourceAsStream("/sut/civilization/Images/unitActions/Heal.png"))),
    FOUND_CITY(new Image(Civilization.class.getResourceAsStream("/sut/civilization/Images/unitActions/Found City.png"))),
    FORTIFY(new Image(Civilization.class.getResourceAsStream("/sut/civilization/Images/unitActions/Fortify.png"))),
    BUILD_IMPROVEMENT(new Image(Civilization.class.getResourceAsStream("/sut/civilization/Images/unitActions/Build Improvement.png"))),
    REMOVE_FEATURE(new Image(Civilization.class.getResourceAsStream("/sut/civilization/Images/unitActions/Remove Feature.png"))),
    ALERT(new Image(Civilization.class.getResourceAsStream("/sut/civilization/Images/unitActions/Alert.png")))
    ;

    public Image image;

    UnitActions(Image image) {
        this.image = image;
    }

}
