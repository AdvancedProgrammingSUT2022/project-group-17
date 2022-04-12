package Model.Lands;

import Model.Buildings.Building;
import Model.Units.Unit;

public class Land {

    protected Building building;
    protected int movementCost;
    protected int cost;
    //TODO
    protected Unit[] units = new Unit[2]; //units[0] => jangi  units[1] => gheire jangi
    protected boolean isAvailable;
    protected int X;
    protected int Y;

    public Land(Building building, int movementCost, int cost, Unit[] units, boolean isAvailable, int x, int y) {
        this.building = building;
        this.movementCost = movementCost;
        this.cost = cost;
        this.units = units;
        this.isAvailable = isAvailable;
        X = x;
        Y = y;
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

    public boolean isAvailable() {
        return isAvailable;
    }

    public int getX() {
        return X;
    }

    public int getY() {
        return Y;
    }
}
