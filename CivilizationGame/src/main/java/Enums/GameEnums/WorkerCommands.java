package Enums.GameEnums;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum WorkerCommands {
    BUILD_ROAD("worker build road"),
    BUILD_RAILROAD("worker build railroad"),
    BUILD_FARM("worker build farm"),
    BUILD_MINE("worker build mine"),
    BUILD_TRADING_POST("worker build trading post"),
    BUILD_LUMBER_MILL("worker build lumberMill"),
    BUILD_PASTURE("worker build pasture"),
    BUILD_CAMP("worker build camp"),
    BUILD_PLANTATION("worker build plantation"),
    BUILD_QUARRY("worker build quarry"),
    REMOVE_JUNGLE("worker remove jungle"),
    REMOVE_ROUTE("worker remove route");
    private final String regex;

    WorkerCommands(String regex) {
        this.regex = regex;
    }

    public static Matcher getMatcher(String input, WorkerCommands workerCommands) {
        return Pattern.compile(workerCommands.regex).matcher(input);
    }
}
