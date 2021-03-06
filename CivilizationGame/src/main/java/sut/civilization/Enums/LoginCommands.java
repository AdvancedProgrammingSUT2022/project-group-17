package sut.civilization.Enums;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum LoginCommands {
    CREATE_USER("user create -((?:u|n|p) \\S+) -((?:u|n|p) \\S+) -((?:u|n|p) \\S+)"),
    LOGIN_USER("user login -((?:u|p) \\S+) -((?:u|p) \\S+)"),
    LIST_OF_USERS("info registered users"),
    EXIT("menu exit"),
    //just in case we need such thing
    // strong password must contain (upperCase & lowerCase) alphabet and number and at least 8 char
    strongPassword("(?=.*[0-9].*)(?=.*[a-z].*)(?=.*[A-Z].*).{8,}");

    private final String regex;

    LoginCommands(String regex) {
        this.regex = regex;
    }

    public static Matcher getMatcher(String input, LoginCommands loginCommands) {
        return Pattern.compile(loginCommands.regex).matcher(input);
    }
}
