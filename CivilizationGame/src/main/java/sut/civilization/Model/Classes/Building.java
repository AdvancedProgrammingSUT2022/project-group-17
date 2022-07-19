package sut.civilization.Model.Classes;

import sut.civilization.Model.ModulEnums.BuildingType;

public class Building {

    //TODO change this on nextTurn function
    protected int turnsLeft;
    protected BuildingType buildingType;

    public Building(BuildingType buildingType){
        this.buildingType = buildingType;
    }

    public BuildingType getBuildingType() {
        return buildingType;
    }

    public int getTurnsLeft() {
        return turnsLeft;
    }
}
