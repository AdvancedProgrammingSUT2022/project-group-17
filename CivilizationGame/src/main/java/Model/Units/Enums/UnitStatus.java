package Model.Units.Enums;

public enum UnitStatus {
    FORTIFY("Fortify"),
    SLEEP("Sleep"),
    ALERT("Alert"),
    FORTIFY_UNTIL_HILL("Fortify until hill"),
    GARRISON("Garrison"),
    WORKING("Working"),
    WAITING_FOR_COMMAND("Waiting for command");

    public final String name;

    UnitStatus(String name) {
        this.name = name;
    }
}
