package sut.civilization.Enums.GameEnums;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum CityCommands {
    BUILD_CITY("build city on -x (?<x>\\d+) -y (?<y>\\d+) named (?<name>\\S+)"),
    BUY_LAND("buy land on -x (?<x>\\d+) -y (?<y>\\d+)"),
    SEND_CITIZEN("send citizen to -x (?<x>\\d+) -y (?<y>\\d+)"),
    RETRIEVE_CITIZEN("retrieve citizen from -x (?<x>\\d+) -y (?<y>\\d+)"),
    SHOW_BANNER("show banner"),
    CITY_ATTACK("attack unit on -x (?<x>\\d+) -y (?<y>\\d+)"),
    ;

    private final String regex;

    CityCommands(String regex){
        this.regex = regex;
    }


    public static Matcher getMatcher(String input, CityCommands cityCommands) {
        return Pattern.compile(cityCommands.regex).matcher(input);
    }

}
