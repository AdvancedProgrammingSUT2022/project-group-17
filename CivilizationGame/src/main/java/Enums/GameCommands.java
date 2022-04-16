package Enums;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum GameCommands {
    ;//TODO fill enums
    private String regex;

    GameCommands(String regex) {
        this.regex = regex;
    }

    public static Matcher getMatcher(String input, GameCommands gameCommands) {
        return Pattern.compile(input).matcher(gameCommands.regex);
    }
}
