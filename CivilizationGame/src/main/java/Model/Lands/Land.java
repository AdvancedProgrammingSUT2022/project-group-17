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
    protected int cost;
    protected City ownerCity = null;
    protected CivilizedUnit civilizedUnit = null;
    protected CloseCombatUnit closeCombatUnit = null;
    protected RangedCombatUnit rangedCombatUnit = null;
    protected boolean isBuyable;
    protected boolean isWalkable;
    protected LandType landType;

    public Land(LandType landType, int cost, boolean isBuyable, boolean isWalkable) {
        this.landType = landType;
        this.cost = cost;
        this.isBuyable = isBuyable;
        this.isWalkable = isWalkable;
        this.landFeature = null;
        this.improvement = null;
    }

    public Improvement getImprovement() {
        return improvement;
    }

    public LandFeature getLandFeature() {
        return landFeature;
    }

    public City getOwnerCity() {
        return ownerCity;
    }

    public CivilizedUnit getCivilizedUnit() {
        return civilizedUnit;
    }

    public CloseCombatUnit getCloseCombatUnit() {
        return closeCombatUnit;
    }

    public RangedCombatUnit getRangedCombatUnit() {
        return rangedCombatUnit;
    }

    public LandType getLandType() {
        return landType;
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
