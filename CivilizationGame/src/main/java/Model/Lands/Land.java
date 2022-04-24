package Model.Lands;

import Model.City;
import Model.Improvements.Improvement;
import Model.LandFeatures.LandFeature;
import Model.Resources.Resource;
import Model.Units.CivilizedUnit;
import Model.Units.CloseCombatUnit;
import Model.Units.RangedCombatUnit;

import java.util.ArrayList;

public class Land {

    protected Improvement improvement;
    protected LandFeature landFeature;
    protected int MP;
    protected int cost;
    protected City ownerCity = null;
    protected CivilizedUnit civilizedUnit = null;
    protected CloseCombatUnit closeCombatUnit = null;
    protected RangedCombatUnit rangedCombatUnit = null;
    protected boolean isBuyable;
    protected boolean isWalkable;
    protected int goldGrowth;
    protected int productionGrowth;
    protected int foodGrowth;
    protected boolean isAPartOfPath = false;
    protected LandType landType;
    protected int visibility;
    protected ArrayList<Resource> resources;

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

    public int getVisibility() {
        return visibility;
    }

    public int getMP() {
        return MP;
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

    public boolean isAPartOfPath() {
        return isAPartOfPath;
    }

    public void setOwnerCity(City ownerCity) {
        this.ownerCity = ownerCity;
    }

    public void setAPartOfPath(boolean APartOfPath) {
        isAPartOfPath = APartOfPath;
    }
}
