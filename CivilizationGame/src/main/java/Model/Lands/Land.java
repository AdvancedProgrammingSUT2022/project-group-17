package Model.Lands;

import Model.City;
import Model.Improvements.Improvement;
import Model.LandFeatures.LandFeature;
import Model.Resources.Resource;
import Model.Units.CivilizedUnit;
import Model.Units.CombatUnit;

public class Land {

    protected Improvement improvement;
    protected Improvement route;
    protected LandFeature landFeature;
    protected int cost;
    protected City ownerCity = null;
    protected boolean isCityCenter;
    protected CivilizedUnit civilizedUnit = null;
    protected CombatUnit combatUnit = null;
    protected boolean isBuyable;
    protected boolean isAPartOfPath = false;
    protected LandType landType;
    protected int visibility = 0;
    // 0 -> fog of war
    // 1 -> shadow (unknown)
    // 2 -> visible (shown)
    protected boolean[] hasRiver = new boolean[6];
    protected Resource resource;
    //fixme check this
    protected int foodGrowth = 0;
    protected int productionGrowth = 0;
    protected int coinGrowth = 0;
    //TODO assign land's movement cost it's landType movement cost when initializing map
    protected int movementCost;


    public Land(LandType landType, int cost) {
        this.landType = landType;
        this.cost = cost;
        this.landFeature = null;
        this.improvement = null;
        this.isBuyable = true;
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

    public Improvement getRoute() {
        return route;
    }

    public boolean isCityCenter() {
        return isCityCenter;
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

    public void setRoute(Improvement route) {
        this.route = route;
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

    public void setMovementCost(int movementCost) {
        this.movementCost = movementCost;
    }
}
