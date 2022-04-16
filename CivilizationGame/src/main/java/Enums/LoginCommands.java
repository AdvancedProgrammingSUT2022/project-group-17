package Enums;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum LoginCommands {
    ;//TODO fill enums
    private String regex;

    LoginCommands(String regex) {
        this.regex = regex;
    }

    public static Matcher getMatcher(String input, LoginCommands loginCommands) {
        return Pattern.compile(input).matcher(loginCommands.regex);
    }
}
