package sut.civilization.Enums.GameEnums;

public enum WorkerWorks {
    REMOVE_JUNGLE(7),
    REMOVE_FOREST(4),
    REMOVE_MARSH(6),
    REMOVE_ROUTE(3),
    REPAIR(3);

    final public int turns;

    WorkerWorks(int turns) {
        this.turns = turns;
    }
}
