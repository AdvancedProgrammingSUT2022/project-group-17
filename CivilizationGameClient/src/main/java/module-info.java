open module sut.civilization {
    requires javafx.fxml;
    requires javafx.base;
    requires javafx.controls;
    requires javafx.graphics;
    requires com.google.gson;

    exports sut.civilization.Model.Classes;

    exports sut.civilization.View.Graphical;

    exports sut.civilization.View.NonGraphical;

    exports sut.civilization.Model.ModulEnums;

    exports sut.civilization.Enums.GameEnums;

    exports sut.civilization.Enums;

    exports sut.civilization;
}