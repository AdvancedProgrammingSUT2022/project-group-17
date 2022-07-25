package sut.civilization.Enums;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum ProfileCommands {
    CHANGE_PASSWORD("profile change -((?:o|n)p \\S+) -((?:o|n)p \\S+)"),
    CHANGE_NICKNAME("profile change -n (?<nickName>\\S+)"),
    INFO_PROFILE("info profile"),
    REMOVE_ACCOUNT("remove account -p (?<password>\\S+)"),
    EXIT("menu exit");
    private final String regex;

    ProfileCommands(String regex) {
        this.regex = regex;
    }

    public static Matcher getMatcher(String input, ProfileCommands profileCommands) {
        return Pattern.compile(profileCommands.regex).matcher(input);
    }
}
