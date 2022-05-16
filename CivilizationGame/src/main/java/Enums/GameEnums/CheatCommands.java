package Enums.GameEnums;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum CheatCommands {
    PUT_UNIT("put unit on -x (?<x>\\d+) -y (?<y>\\d+)"),
    ADD_TECHNOLOGY("add technology --name (?<name>.+)"),
    DESTROY_IMPROVEMENT("destroy improvement on -x (?<x>\\d+) -y (?<y>\\d+)"),
    CHEAT_INCREASE_GOLD("increase --gold (?<amount>\\d+)"),
    CHEAT_INCREASE_TURN("increase --turn (?<amount>\\d+)"),
    CHEAT_INCREASE_FOOD("increase --food (?<amount>\\d+)"),
    CHEAT_INCREASE_PRODUCTION("increase --production (?<amount>\\d+)"),
    CHEAT_INCREASE_SCIENCE("increase --science (?<amount>\\d+)"),
    CHEAT_INCREASE_HAPPINESS("increase --happiness (?<amount>\\d+)"),
    CHEAT_WIN("win game"),
    ;

    private final String regex;

    CheatCommands(String regex) {
        this.regex = regex;
    }

    public static Matcher getMatcher(String input, CheatCommands cheatCommands) {
        return Pattern.compile(cheatCommands.regex).matcher(input);
    }
}
