package Enums;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum GameCommands {
    cheatIncreaseGold("increase --gold (?<amount>\\d+)"),
    cheatIncreaseTurn("increase --turn (?<amount>\\d+)"),
    cheatIncreaseFood("increase --food (?<amount>\\d+)"),
    cheatIncreaseProduction("increase --production (?<amount>\\d+)");
    //TODO fill enums
    private final String regex;

    GameCommands(String regex) {
        this.regex = regex;
    }

    public static Matcher getMatcher(String input, GameCommands gameCommands) {
        return Pattern.compile(gameCommands.regex).matcher(input);
    }
}
