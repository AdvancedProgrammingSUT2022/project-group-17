package Enums.GameEnums;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum GameCommands {
    cheatIncreaseGold("increase --gold (?<amount>\\d+)"),
    cheatIncreaseTurn("increase --turn (?<amount>\\d+)"),
    cheatIncreaseFood("increase --food (?<amount>\\d+)"),
    cheatIncreaseProduction("increase --production (?<amount>\\d+)"),
    SELECT_COMBAT_UNIT("select combat unit on -i (?<i>\\d+) -j (?<j>\\d+)") ,
    SELECT_CIVILIZED_UNIT("select civilized unit on -i (?<i>\\d+) -j (?<j>\\d+)"),
    CHEAT_PUT_UNIT("put unit on -i (?<i>\\d+) -j (?<j>\\d+) --unitName(?<unitName>\\S+)"),
    MENU_EXIT("menu exit");

    private final String regex;

    GameCommands(String regex) {
        this.regex = regex;
    }

    public static Matcher getMatcher(String input, GameCommands gameCommands) {
        return Pattern.compile(gameCommands.regex).matcher(input);
    }
}
