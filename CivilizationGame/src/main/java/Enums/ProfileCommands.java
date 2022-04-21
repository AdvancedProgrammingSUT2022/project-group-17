package Enums;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum ProfileCommands {
    ;//TODO fill enums
    private String regex;

    ProfileCommands(String regex) {
        this.regex = regex;
    }

    public static Matcher getMatcher(String input, ProfileCommands profileCommands) {
        return Pattern.compile(profileCommands.regex).matcher(input);
    }
}
