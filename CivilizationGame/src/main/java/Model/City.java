package Model;

import Model.Buildings.Building;
import Model.Improvements.Improvement;
import Model.Lands.Land;
import Model.Nations.Nation;
import Model.Units.CloseCombatUnit;
import Model.Units.Unit;

import java.util.ArrayList;

public class City {
    protected Nation ownerNation;
    protected ArrayList<Land> lands = new ArrayList<>();
    protected int citizens;
    protected int HP;
    protected int combatStrength;
    protected int rangedStrength;
    protected int level;
    protected ArrayList<Building> buildings = new ArrayList<>();
    protected ArrayList<Improvement> improvements = new ArrayList<>();
    protected Building inProgressBuilding;
    protected Unit inProgressUnit;
    protected CloseCombatUnit garrison;

    public City(Nation ownerNation){
        this.citizens = 3;
        this.HP = 100;
        this.combatStrength = 20;
        this.rangedStrength = 10;
        this.level = 1;
        this.ownerNation = ownerNation;
    }

    public Nation getOwnerNation() {
        return ownerNation;
    }

    public void setOwnerNation(Nation ownerNation) {
        this.ownerNation = ownerNation;
    }

    public ArrayList<Land> getLands() {
        return lands;
    }

    public int getCitizens() {
        return citizens;
    }

    public ArrayList<Building> getBuildings() {
        return buildings;
    }

    public int getHP() {
        return HP;
    }

    public int getCombatStrength() {
        return combatStrength;
    }

    public int getRangedStrength() {
        return rangedStrength;
    }

    public CloseCombatUnit getGarrison() {
        return garrison;
    }

    public int getLevel() {
        return level;
    }

    public Building getInProgressBuilding() {
        return inProgressBuilding;
    }

    public ArrayList<Improvement> getImprovements() {
        return improvements;
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

    public Unit getInProgressUnit() {
        return inProgressUnit;
    }

    public void setInProgressBuilding(Building inProgressBuilding) {
        this.inProgressBuilding = inProgressBuilding;
    }

    public void setInProgressUnit(Unit inProgressUnit) {
        this.inProgressUnit = inProgressUnit;
    }

}
