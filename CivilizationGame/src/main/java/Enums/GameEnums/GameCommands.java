package Enums.GameEnums;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum GameCommands {

    SELECT_COMBAT_UNIT("select combat unit on -((?:i|j) \\d+) -((?:i|j) \\d+)"),
    SELECT_CIVILIZED_UNIT("select civilized unit on -((?:i|j) \\d+) -((?:i|j) \\d+)"),
    CHEAT_INCREASE_GOLD("increase --gold (?<amount>\\d+)"),
    CHEAT_INCREASE_TURN("increase --turn (?<amount>\\d+)"),
    CHEAT_INCREASE_FOOD("increase --food (?<amount>\\d+)"),
    CHEAT_INCREASE_PRODUCTION("increase --production (?<amount>\\d+)");
    //TODO fill enums

    private final String regex;

    GameCommands(String regex) {
        this.regex = regex;
    }

    public static Matcher getMatcher(String input, GameCommands gameCommands) {
        return Pattern.compile(gameCommands.regex).matcher(input);
    }
}
