package Enums.GameEnums;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum GameCommands {
    SELECT_COMBAT_UNIT("select combat unit on -i (?<i>\\d+) -j (?<j>\\d+)") ,
    SELECT_CIVILIZED_UNIT("select civilized unit on -i (?<i>\\d+) -j (?<j>\\d+)"); //TODO fill enums
    private String regex;

    GameCommands(String regex) {
        this.regex = regex;
    }

    public static Matcher getMatcher(String input, GameCommands gameCommands) {
        return Pattern.compile(input).matcher(gameCommands.regex);
    }
}
