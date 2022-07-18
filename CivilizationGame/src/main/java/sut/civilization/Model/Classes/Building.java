package sut.civilization.Model.Classes;

import sut.civilization.Model.ModulEnums.BuildingType;

public class Building {

    protected int turns;
    protected BuildingType buildingType;
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
