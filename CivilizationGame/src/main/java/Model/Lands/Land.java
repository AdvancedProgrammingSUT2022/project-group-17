package Model.Lands;

import Model.City;
import Model.Improvements.Improvement;
import Model.Improvements.ImprovementType;
import Model.LandFeatures.LandFeature;
import Model.Resources.Resource;
import Model.Units.CivilizedUnit;
import Model.Units.CloseCombatUnit;
import Model.Units.CombatUnit;
import Model.Units.RangedCombatUnit;

import java.util.ArrayList;

public class Land {

    protected Improvement improvement;
    //fixme
    protected ImprovementType improvementType;
    protected LandFeature landFeature;
    protected int cost;
    protected City ownerCity = null;
    protected boolean isCityCenter;
    protected CivilizedUnit civilizedUnit = null;
    protected CombatUnit combatUnit = null;
    protected boolean isBuyable;
    protected boolean isAPartOfPath = false;
    protected LandType landType;
    //FIXME Hamed you must set visibility below to 0
    protected int visibility = 2;
    // 0 -> fog of war
    // 1 -> shadow (unknown)
    // 2 -> visible (shown)
    protected boolean[] hasRiver = new boolean[6];
    protected Resource resource;
    //fixme check this
    protected int foodGrowth = 0;
    protected int productionGrowth = 0;
    protected int coinGrowth = 0;


    public Land(LandType landType, int cost) {
        this.landType = landType;
        this.cost = cost;
        this.landFeature = null;
        this.improvement = null;
    }

    public CivilizedUnit getCivilizedUnit() {
        return civilizedUnit;
    }

    public int getMP() {
        return landType.movementCost;
    }

    public int getCost() {
        return cost;
    }

    public Improvement getImprovement() {
        return improvement;
    }

    public boolean isCityCenter() {
        return isCityCenter;
    }

    public ImprovementType getImprovementType() {
        return improvementType;

    }

    public void setRiver(int index, boolean value){
        this.hasRiver[index] = value;
    }

    public boolean isBuyable() {
        return isBuyable;
    }

    public void setImprovement(Improvement improvement) {
        this.improvement = improvement;
    }

    public void setImprovementType(ImprovementType improvementType) {
        this.improvementType = improvementType;
    }

    public LandFeature getLandFeature() {
        return landFeature;
    }

    public void setLandFeature(LandFeature landFeature) {
        this.landFeature = landFeature;
    }

    public City getOwnerCity() {
        return ownerCity;
    }

    public void setOwnerCity(City ownerCity) {
        this.ownerCity = ownerCity;
    }

    public void setCivilizedUnit(CivilizedUnit civilizedUnit) {
        this.civilizedUnit = civilizedUnit;
    }

    public CombatUnit getCombatUnit() {
        return combatUnit;
    }

    public void setCombatUnit(CombatUnit combatUnit) {
        this.combatUnit = combatUnit;
    }

    public boolean isAPartOfPath() {
        return isAPartOfPath;
    }

    public LandType getLandType() {
        return landType;
    }

    public void setLandType(LandType landType) {
        this.landType = landType;
    }

    public int getVisibility() {
        return visibility;
    }

    public void setVisibility(int visibility) {
        this.visibility = visibility;
    }

    public boolean[] getHasRiver() {
        return hasRiver;
    }

    public void setHasRiver(boolean[] hasRiver) {
        this.hasRiver = hasRiver;
    }

    public Resource getResource() {
        return resource;
    }

    public void setResource(Resource resource) {
        this.resource = resource;
    }

    public void setAPartOfPath(boolean APartOfPath) {
        isAPartOfPath = APartOfPath;
    }

    public void setCityCenter(boolean cityCenter) {
        isCityCenter = cityCenter;
    }

    public void addFoodGrowth(int amount) {
        this.foodGrowth += amount;
    }

    public void addProductionGrowth(int amount) {
        this.productionGrowth += amount;
    }

    public void addCoinGrowth(int amount) {
        this.coinGrowth += amount;
    }
}
