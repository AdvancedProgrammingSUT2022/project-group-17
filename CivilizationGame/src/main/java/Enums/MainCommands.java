package Enums;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum MainCommands {
    menuEnter("menu enter (?<menuName>.+)"),
    exit("menu exit"),
    scoreBoard("info score board"),
    logout("logout"),
    playGame("play game (--player[1-9]+ \\S+ )+(--player[1-9]+ \\S+)");

    private final String regex;

    MainCommands(String regex) {
        this.regex = regex;
    }

    public static Matcher getMatcher(String input, MainCommands mainCommands) {
        return Pattern.compile(mainCommands.regex).matcher(input);
    }
}
