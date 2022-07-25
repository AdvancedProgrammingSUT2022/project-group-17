package sut.civilization.Enums;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum MainCommands {
    MAIN_COMMANDS("menu enter (?<menuName>.+)"),
    exit("menu exit"),
    logout("logout"),
    playGame("play game ((?:-p[0-9]+ \\S+(?: )?){2,})");

    private final String regex;

    MainCommands(String regex) {
        this.regex = regex;
    }

    public static Matcher getMatcher(String input, MainCommands mainCommands) {
        return Pattern.compile(mainCommands.regex).matcher(input);
    }
}
