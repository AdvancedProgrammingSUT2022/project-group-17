package Enums.GameEnums;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum WorkerCommands {
    BUILD_ROAD("worker build road on -x (?<x>\\d+) -y (?<y>\\d+)"),
    BUILD_RAILROAD("worker build railroad on -x (?<x>\\d+) -y (?<y>\\d+)"),
    BUILD_FARM("worker build farm on -x (?<x>\\d+) -y (?<y>\\d+)"),
    BUILD_MINE("worker build mine on -x (?<x>\\d+) -y (?<y>\\d+)"),
    BUILD_TRADING_POST("worker build trading post on -x (?<x>\\d+) -y (?<y>\\d+)"),
    BUILD_LUMBERMILL("worker build lumbermill on -x (?<x>\\d+) -y (?<y>\\d+)"),
    BUILD_PASTURE("worker build pasture on -x (?<x>\\d+) -y (?<y>\\d+)"),
    BUILD_CAMP("worker build camp on -x (?<x>\\d+) -y (?<y>\\d+)"),
    BUILD_PLANTATION("worker build plantation on -x (?<x>\\d+) -y (?<y>\\d+)"),
    BUILD_QUARRY("worker build quarry on -x (?<x>\\d+) -y (?<y>\\d+)"),
    REMOVE_JUNGLE("worker remove jungle from -x (?<x>\\d+) -y (?<y>\\d+)"),
    REMOVE_ROUTE("worker remove route from -x (?<x>\\d+) -y (?<y>\\d+)");
    private final String regex;

    WorkerCommands(String regex) {
        this.regex = regex;
    }

    public static Matcher getMatcher(String input, WorkerCommands workerCommands) {
        return Pattern.compile(workerCommands.regex).matcher(input);
    }
}
