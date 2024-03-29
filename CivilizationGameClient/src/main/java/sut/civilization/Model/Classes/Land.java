package sut.civilization.Model.Classes;

import sut.civilization.Model.ModulEnums.LandType;

import java.util.ArrayList;
import java.util.Objects;

public class Land {

    protected Improvement improvement;
    protected Improvement route;
    protected Ruin ruin;
    protected LandFeature landFeature;
    protected CombatUnit ZOC;

    protected int cost;
    protected City ownerCity = null;
    protected boolean isCityCenter;
    protected CivilizedUnit civilizedUnit = null;
    protected CombatUnit combatUnit = null;
    protected boolean isBuyable;

    protected boolean isAPartOfPath = false;
    protected LandType landType;
    protected int visibility = 2;
    // 0 -> fog of war
    // 1 -> shadow (unknown)
    // 2 -> visible (shown)
    protected boolean[] hasRiver = new boolean[6];
    protected Resource resource;

    protected ArrayList<Nation> seerNations = new ArrayList<>();

    //fixme check this
    protected int foodGrowth = 0;
    protected int productionGrowth = 0;
    protected int coinGrowth = 0;
    //TODO assign land's movement cost it's landType movement cost when initializing map
    protected int movementCost;
    protected boolean hasCitizen;
    protected int i;
    protected int j;


    public Land(LandType landType, int cost, int i, int j) {
        this.landType = landType;
        this.foodGrowth = landType.foodGrowth;
        this.cost = cost;
        this.landFeature = null;
        this.improvement = null;
        this.i = i;
        this.j = j;
        this.ruin = null;
        this.isBuyable = true;
        this.visibility = 0;
    }

    public void addGrowthToLandOwner() {
        if (this.ownerCity != null && this.hasCitizen()) {
            Nation landOwnerNation = this.ownerCity.getOwnerNation();

            landOwnerNation.getCoin().addGrowthRate(this.coinGrowth);
            landOwnerNation.getProduction().addGrowthRate(this.productionGrowth);
            landOwnerNation.getFood().addGrowthRate(this.foodGrowth);

            landOwnerNation.getCoin().addGrowthRate(this.landFeature.getLandFeatureType().goldGrowth);
            landOwnerNation.getProduction().addGrowthRate(this.landFeature.getLandFeatureType().productionGrowth);
            landOwnerNation.getFood().addGrowthRate(this.landFeature.getLandFeatureType().foodGrowth);
        }
    }

    public boolean isACityMainLand() {
        for (User user : Game.instance.getPlayersInGame()) {
            for (City city : user.getNation().getCities()) {
                if (city.getMainLand().equals(this)) {
                    return true;
                }
            }
        }
        return false;
    }

    public CivilizedUnit getCivilizedUnit() {
        for (User user : Game.instance.getPlayersInGame()) {
            for (Unit unit : user.getNation().getUnits()) {
                if (unit.getLocation().x.equals(this.i) && unit.getLocation().y.equals(this.j) && unit instanceof CivilizedUnit) {
                    return (CivilizedUnit) unit;
                }
            }
        }

        return null;
    }

    public void setCivilizedUnit(CivilizedUnit civilizedUnit) {
        this.civilizedUnit = civilizedUnit;
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

    public void setImprovement(Improvement improvement) {
        this.improvement = improvement;
    }

    public Improvement getRoute() {
        return route;
    }

    public void setRoute(Improvement route) {
        this.route = route;
    }

    public boolean isCityCenter() {
        return isCityCenter;
    }

    public void setCityCenter(boolean cityCenter) {
        isCityCenter = cityCenter;
    }

    public void setRiver(int index, boolean value) {
        this.hasRiver[index] = value;
    }

    public boolean isBuyable() {
        return isBuyable;
    }

    public LandFeature getLandFeature() {
        return landFeature;
    }

    public void setLandFeature(LandFeature landFeature) {
        this.landFeature = landFeature;
    }

    public City getOwnerCity() {
        for (User user : Game.instance.getPlayersInGame()) {
            for (City city : user.getNation().getCities()) {
                if (city.mainLand.getJ() == this.getJ() && city.mainLand.getI() == this.getI())
                    return city;
            }
        }
        return ownerCity;
    }

    public void setOwnerCity(City ownerCity) {
        this.ownerCity = ownerCity;
    }

    public CombatUnit getCombatUnit() {
        for (User user : Game.instance.getPlayersInGame()) {
            for (Unit unit : user.getNation().getUnits()) {
                if (unit.getLocation().x.equals(this.i) && unit.getLocation().y.equals(this.j) && unit instanceof CombatUnit) {
                    return (CombatUnit) unit;
                }
            }
        }

        return null;
    }

    public void setCombatUnit(CombatUnit combatUnit) {
        this.combatUnit = combatUnit;
    }

    public boolean isAPartOfPath() {
        return isAPartOfPath;
    }

    public void setAPartOfPath(boolean APartOfPath) {
        isAPartOfPath = APartOfPath;
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

    public void setCitizen(boolean hasCitizen) {
        this.hasCitizen = hasCitizen;
    }

    public boolean hasCitizen() {
        return hasCitizen;
    }

    public CombatUnit getZOC() {
        return ZOC;
    }

    public void setZOC(CombatUnit ZOC) {
        this.ZOC = ZOC;
    }


    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public int getJ() {
        return j;
    }

    public void setJ(int j) {
        this.j = j;
    }

    public Ruin getRuin() {
        return ruin;
    }

    public void setRuin(Ruin ruin) {
        this.ruin = ruin;
    }

    public ArrayList<Nation> getSeerNations() {
        return seerNations;
    }

    public void addSeerNation(Nation seerNation) {
        if (!this.seerNations.contains(seerNation))
            this.seerNations.add(seerNation);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Land land = (Land) o;
        return i == land.i && j == land.j;
    }

    @Override
    public String toString() {
        return "Land{= (" + i + "," + j + ")," +
        "landType= " + landType + " }";
    }
}
