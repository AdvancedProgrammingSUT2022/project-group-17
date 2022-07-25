package sut.civilization.Enums.GameEnums;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum WorkerCommands {
    BUILD_IMPROVEMENT("worker build (?<name>.+)"),
    REPAIR_IMPROVEMENT("worker repair improvement"),
    REMOVE_FEATURE("worker remove feature"),
    REMOVE_ROUTE("worker remove route");
    private final String regex;

    WorkerCommands(String regex) {
        this.regex = regex;
    }

    public static Matcher getMatcher(String input, WorkerCommands workerCommands) {
        return Pattern.compile(workerCommands.regex).matcher(input);
    }
}
