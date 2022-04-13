package Model;

import Model.Buildings.Building;
import Model.Lands.Land;
import Model.Units.Unit;

import java.util.ArrayList;

public class City {

    protected ArrayList<Land> lands = new ArrayList<>();
    protected int citizens;
    protected ArrayList<Building> buildings = new ArrayList<>();
    protected ArrayList<Improvement> improvements = new ArrayList<>();
    protected Building inProgressBuilding;
    protected Unit inProgressUnit;

    public ArrayList<Land> getLands() {
        return lands;
    }

    public int getCitizens() {
        return citizens;
    }

    public ArrayList<Building> getBuildings() {
        return buildings;
    }

    public void addBuilding(Building building){
        buildings.add(building);
    }

    public void addLand(Land land){
        lands.add(land);
    }

    public void addCitizens(int amount){
        this.citizens += amount;
    }

    public Building getInProgressBuilding() {
        return inProgressBuilding;
    }

    public Unit getInProgressUnit() {
        return inProgressUnit;
    }

    public void setInProgressBuilding(Building inProgressBuilding) {
        this.inProgressBuilding = inProgressBuilding;
    }

    public void setInProgressUnit(Unit inProgressUnit) {
        this.inProgressUnit = inProgressUnit;
    }

    public ArrayList<Improvement> getImprovements() {
        return improvements;
    }
}
