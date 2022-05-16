package Enums.GameEnums;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum UnitCommands {
    CIVILIZED_UNIT_MOVE_TO("civilized unit move to -x (?<x>\\d+) -y (?<y>\\d+)"), //TODO fill enums
    COMBAT_UNIT_MOVE_TO("combat unit move to -x (?<x>\\d+) -y (?<y>\\d+)"),
    UNIT_ATTACK("unit attack city"),
    CREATE_UNIT("create a (?<name>\\S+) unit with type (?<type>[a-zA-Z ]+)"),
    PURCHASE_UNIT("purchase a (?<name>\\S+) unit with type (?<type>[a-zA-Z ]+)"),
    COMBAT_UNIT_ACTION("combat unit -a (?<action>.+)"),
    UNIT_SLEEP("unit sleep"),
    UNIT_WAKE("unit wake"),
    UNIT_DELETE("unit delete"),
    ;
    private final String regex;

    UnitCommands(String regex) {
        this.regex = regex;
    }

    public static Matcher getMatcher(String input, UnitCommands unitCommands) {
        return Pattern.compile(unitCommands.regex).matcher(input);
    }
}
