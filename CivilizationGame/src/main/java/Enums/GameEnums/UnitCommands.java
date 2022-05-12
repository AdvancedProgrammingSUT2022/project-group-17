package Enums.GameEnums;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum UnitCommands {
    UNIT_MOVE_TO("unit move to -x (?<x>\\d+) -y (?<y>\\d+)"); //TODO fill enums
    private final String regex;

    UnitCommands(String regex) {
        this.regex = regex;
    }

    public static Matcher getMatcher(String input, UnitCommands unitCommands) {
        return Pattern.compile(unitCommands.regex).matcher(input);
    }
}
