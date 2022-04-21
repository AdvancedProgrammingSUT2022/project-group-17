package Enums;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum MainCommands {
    ;//TODO fill enums
    private String regex;

    MainCommands(String regex) {
        this.regex = regex;
    }

    public static Matcher getMatcher(String input, MainCommands mainCommands) {
        return Pattern.compile(mainCommands.regex).matcher(input);
    }
}
