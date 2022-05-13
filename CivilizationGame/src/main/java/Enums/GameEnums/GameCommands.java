package Enums.GameEnums;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum GameCommands {
    SHOW_MAP("show map"),
    MENU_EXIT("menu exit"),
    NEXT_TURN("next turn"),
    SELECT_COMBAT_UNIT("select combat unit on -x (?<x>\\d+) -y (?<y>\\d+)"),
    SELECT_CIVILIZED_UNIT("select civilized unit on -x (?<x>\\d+) -y (?<y>\\d+)"),
    SHOW_RESEARCH("show research"),
    SHOW_UNITS("show units"),
    SHOW_CITIES("show cities"),
    SHOW_DIPLOMACIES("show diplomacies"),
    SHOW_DEMOGRAPHICS("show demographics"),
    SHOW_MILITARIES("show militaries"),
    SHOW_ECONOMICS("show economics");

    //TODO fill enums

    private final String regex;

    GameCommands(String regex) {
        this.regex = regex;
    }

    public static Matcher getMatcher(String input, GameCommands gameCommands) {
        return Pattern.compile(gameCommands.regex).matcher(input);
    }
}
