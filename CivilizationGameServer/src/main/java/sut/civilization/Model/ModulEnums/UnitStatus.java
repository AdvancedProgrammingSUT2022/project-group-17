package sut.civilization.Model.ModulEnums;

public enum UnitStatus {

    AWAKE(true),
    FORTIFY(false),
    SLEEP(false),
    ALERT(false),
    FORTIFY_UNTIL_HEAL(false),
    GARRISON(true),
    WORKING(false),
    MOVING(false),
    ATTACKING(false);

    public final boolean isWaitingForCommand;

    UnitStatus(boolean isWaitingForCommand) {
        this.isWaitingForCommand = isWaitingForCommand;
    }
}
