package Model.Lands;

import Model.City;
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
    //TODO fog of war
    protected City ownerCity = null;
    protected CivilizedUnit civilizedUnit = null;
    protected CloseCombatUnit closeCombatUnit = null;
    protected RangedCombatUnit rangedCombatUnit = null;
    protected boolean isBuyable;
    protected boolean isWalkable;
    protected int goldGrowth;
    protected int productionGrowth;
    protected int foodGrowth;

    public Land(Improvement improvement, int movementCost, int cost, boolean isBuyable, boolean isWalkable, int goldGrowth, int productionGrowth, int foodGrowth) {
        this.improvement = improvement;
        this.movementCost = movementCost;
        this.cost = cost;
        this.isBuyable = isBuyable;
        this.isWalkable = isWalkable;
        landFeature = null;
        this.goldGrowth = goldGrowth;
        this.productionGrowth = productionGrowth;
        this.foodGrowth = foodGrowth;
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

    public void setOwnerCity(City ownerCity) {
        this.ownerCity = ownerCity;
    }
}
