package Model.Lands;

import Model.Buildings.Building;
import Model.Units.Unit;

public class Land {

    protected Building building;
    protected int movementCost;
    protected int cost;
    //TODO
    protected Unit[] units = new Unit[2]; //units[0] => jangi  units[1] => gheire jangi
    protected boolean isBuyable;
    protected boolean isWalkable;

    public Land(Building building, int movementCost, int cost, Unit[] units, boolean isBuyable, boolean isWalkable) {
        this.building = building;
        this.movementCost = movementCost;
        this.cost = cost;
        this.units = units;
        this.isBuyable = isBuyable;
        this.isWalkable = isWalkable;
    }

    public Building getBuilding() {
        return building;
    }

    public int getMovementCost() {
        return movementCost;
    }

    public int getCost() {
        return cost;
    }

    public Unit[] getUnits() {
        return units;
    }

    public boolean isBuyable() {
        return isBuyable;
    }

    public boolean isWalkable() {
        return isWalkable;
    }
}
