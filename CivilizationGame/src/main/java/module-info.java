module sut.civilization {
    requires javafx.fxml;
    requires javafx.base;
    requires javafx.controls;
    requires javafx.graphics;
    requires com.google.gson;

    opens sut.civilization.Model.Classes;
    exports sut.civilization.Model.Classes;

    opens sut.civilization.View.Graphical;
    exports sut.civilization.View.Graphical;

    opens sut.civilization.View.NonGraphical;
    exports sut.civilization.View.NonGraphical;

    opens sut.civilization.Model.ModulEnums;
    exports sut.civilization.Model.ModulEnums;

    opens sut.civilization.Enums.GameEnums;
    exports sut.civilization.Enums.GameEnums;

    opens sut.civilization.Enums;
    exports sut.civilization.Enums;

    opens sut.civilization.fxml;

    opens sut.civilization.Images;

    opens sut.civilization.Images.Avatars;
    opens sut.civilization.Images.BackGround;
    opens sut.civilization.Images.Icons;
    opens sut.civilization.Images.tiles;
    opens sut.civilization.Images.resources;

    opens sut.civilization;
    exports sut.civilization;
}