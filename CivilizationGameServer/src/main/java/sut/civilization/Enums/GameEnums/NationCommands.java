package sut.civilization.Enums.GameEnums;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum NationCommands {

    SHOW_HAPPINESS("show happiness"),
    ;
    private final String regex;

    NationCommands(String regex){
        this.regex = regex;
    }


    public static Matcher getMatcher(String input, NationCommands nationCommands) {
        return Pattern.compile(nationCommands.regex).matcher(input);
    }
}
