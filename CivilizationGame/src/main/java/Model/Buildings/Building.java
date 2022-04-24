package Model.Buildings;

public class Building {

    protected int turns;
    protected BuildingType buildingType;
    //FixMe does making a parents field static impact the child's field in any unwanted way
    protected static boolean isTechnologicallyAvailable = false;

    public Building(BuildingType buildingType){
        this.turns = buildingType.initialTurns;
        this.buildingType = buildingType;
    }

    public BuildingType getBuildingType() {
        return buildingType;
    }

    public int getTurns() {
        return turns;
    }

    public static boolean isIsTechnologicallyAvailable() {
        return isTechnologicallyAvailable;
    }

    public static boolean isAvailable() {
        return isTechnologicallyAvailable;
    }

    public static void setIsAvailable(boolean isAvailable) {
        Building.isTechnologicallyAvailable = isAvailable;
    }
}
