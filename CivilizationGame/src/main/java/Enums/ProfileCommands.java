package Enums;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum ProfileCommands {
    changePassword("profile change -((?:o|n)p \\S+) -((?:o|n)p \\S+)"),
    changeNickname("profile change -n (?<nickName>\\S+)"),
    infoProfile("info profile"),
    removeAccount("remove account -p (?<password>\\S+)"),
    exit("menu exit");
    private final String regex;

    ProfileCommands(String regex) {
        this.regex = regex;
    }

    public static Matcher getMatcher(String input, ProfileCommands profileCommands) {
        return Pattern.compile(profileCommands.regex).matcher(input);
    }
}
