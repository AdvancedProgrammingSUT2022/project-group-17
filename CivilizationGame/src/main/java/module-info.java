module sut.civilization {
    requires javafx.fxml;
    requires javafx.base;
    requires javafx.controls;
    requires javafx.graphics;
    requires com.google.gson;

    opens sut.civilization.Model.Classes;
    exports sut.civilization.Model.Classes;

    opens sut.civilization.Model.ModulEnums;
    exports sut.civilization.Model.ModulEnums;

    opens sut.civilization.Enums.GameEnums;
    exports sut.civilization.Enums.GameEnums;

    opens sut.civilization;
    exports sut.civilization;
}