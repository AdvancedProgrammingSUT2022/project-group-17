package sut.civilization.Enums.GameEnums;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum TechnologyCommands {
    ADD_TECHNOLOGY("add technology (?<type>\\S+)"),
    ;

    private final String regex;

    TechnologyCommands(String regex){
        this.regex = regex;
    }

    public static Matcher getMatcher(String input, TechnologyCommands technologyCommands) {
        return Pattern.compile(technologyCommands.regex).matcher(input);
    }
}
