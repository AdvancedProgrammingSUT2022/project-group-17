package Enums.GameEnums;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum CityCommands {
    SELECT_CITY("select city on -x (?<x>\\d+) -y (?<y>\\d+)"),
    BUILD_CITY("build city on -x (?<x>\\d+) -y (?<y>\\d+)"),
    BUY_LAND("buy land on -x (?<x>\\d+) -y (?<y>\\d+)"),
    ;

    String regex;

    CityCommands(String regex){
        this.regex = regex;
    }

    public static Matcher getMatcher(String input, CityCommands cityCommands) {
        return Pattern.compile(cityCommands.regex).matcher(input);
    }
}
