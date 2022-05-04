package Enums.GameEnums;

public enum CityCommands {
    SELECT_CITY("select city on -x (?<x>\\d+) -y (?<y>\\d+)"),
    BUILD_CITY("build city on -x (?<x>\\d+) -y (?<y>\\d+)"),
    BUY_LAND("buy land on -x (?<x>\\d+) -y (?<y>\\d+)"),

    ;

    String regex;

    CityCommands(String regex){
        this.regex = regex;
    }
}
