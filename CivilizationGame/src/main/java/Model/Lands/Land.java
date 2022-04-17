package Model.Lands;

import Model.Improvements.Improvement;
import Model.LandFeatures.LandFeature;
import Model.Units.CivilizedUnit;
import Model.Units.CloseCombatUnit;
import Model.Units.RangedCombatUnit;

public class Land {

    protected Improvement improvement;
    protected LandFeature landFeature;
    protected int movementCost;
    protected int cost;
    //TODO
    protected CivilizedUnit civilizedUnit = null;
    protected CloseCombatUnit closeCombatUnit = null;
    protected RangedCombatUnit rangedCombatUnit = null;
    protected boolean isBuyable;
    protected boolean isWalkable;

    public Land(Improvement improvement, int movementCost, int cost, boolean isBuyable, boolean isWalkable) {
        this.improvement = improvement;
        this.movementCost = movementCost;
        this.cost = cost;
        this.isBuyable = isBuyable;
        this.isWalkable = isWalkable;
        landFeature = null;
    }

    public Improvement getImprovement() {
        return improvement;
    }

    public int getMovementCost() {
        return movementCost;
    }

    public int getCost() {
        return cost;
    }

    public boolean isBuyable() {
        return isBuyable;
    }

    public boolean isWalkable() {
        return isWalkable;
    }
}
