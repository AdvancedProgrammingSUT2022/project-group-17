package Enums.GameEnums;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum UnitCommands {
    UNIT_MOVE_TO("unit move to -((?:i|j) \\d+) -((?:i|j) \\d+)"); //TODO fill enums
    private String regex;

    UnitCommands(String regex) {
        this.regex = regex;
    }

    public static Matcher getMatcher(String input, UnitCommands unitCommands) {
        return Pattern.compile(input).matcher(unitCommands.regex);
    }
}
