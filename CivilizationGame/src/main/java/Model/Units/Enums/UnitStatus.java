package Model.Units.Enums;

public enum UnitStatus {
    FORTIFY("Fortify"),
    SLEEP("Sleep"),
    ALERT("Alert"),
    FORTIFY_UNTIL_HILL("Fortify until hill"),
    GARRISON("Garrison"),
    WORKING("Working");

    public final String name;

    UnitStatus(String name) {
        this.name = name;
    }
}
