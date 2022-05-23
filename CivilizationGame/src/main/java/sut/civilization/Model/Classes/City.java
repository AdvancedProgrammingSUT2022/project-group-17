package sut.civilization.Model.Classes;

import sut.civilization.Model.ModulEnums.CivilizedUnitType;
import sut.civilization.Model.ModulEnums.CloseCombatUnitType;
import sut.civilization.Model.ModulEnums.RangedCombatUnitType;

import java.util.ArrayList;

public class City {
    protected String name;
    protected Nation ownerNation;
    protected ArrayList<Land> lands = new ArrayList<>();
    protected int citizens = 10;
    protected int employees = 0;
    protected int HP;
    protected int combatStrength;
    protected int rangedStrength;
    protected int level;
    protected ArrayList<Building> buildings = new ArrayList<>();
    protected ArrayList<Improvement> improvements = new ArrayList<>();
    protected Building inProgressBuilding;
    protected boolean hasAnInProgressUnit = false;
    protected CivilizedUnitType inProgressCivilizedUnit = null;
    protected CloseCombatUnitType inProgressCloseCombatUnit = null;
    protected RangedCombatUnitType inProgressRangedCombatUnit = null;
    protected int nextUnitTurns;
    protected Land mainLand;
    protected CloseCombatUnit garrison;
    protected int coinGrowth;
    protected int foodGrowth;
    protected int productionGrowth;
    protected int happinessGrowth;
    protected int scienceGrowth;

    public City(Nation ownerNation, String name){
        this.citizens = 3;
        this.HP = 100;
        this.combatStrength = 20;
        this.rangedStrength = 10;
        this.level = 1;
        this.ownerNation = ownerNation;
        this.name = name;
    }


    public String getName() {
        return name;
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

    public int getCoinGrowth() {
        return coinGrowth;
    }

    public int getFoodGrowth() {
        return foodGrowth;
    }

    public int getProductionGrowth() {
        return productionGrowth;
    }

    public int getHappinessGrowth() {
        return happinessGrowth;
    }

    public int getScienceGrowth() {
        return scienceGrowth;
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

    public void setInProgressBuilding(Building inProgressBuilding) {
        this.inProgressBuilding = inProgressBuilding;
    }

    public int getEmployees() {
        return employees;
    }

    public void setEmployees(int employees) {
        this.employees = employees;
    }

    public void setCitizens(int citizens) {
        this.citizens = citizens;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }

    public void setCombatStrength(int combatStrength) {
        this.combatStrength = combatStrength;
    }

    public void setRangedStrength(int rangedStrength) {
        this.rangedStrength = rangedStrength;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setCoinGrowth(int coinGrowth) {
        this.coinGrowth = coinGrowth;
    }

    public void setFoodGrowth(int foodGrowth) {
        this.foodGrowth = foodGrowth;
    }

    public void setProductionGrowth(int productionGrowth) {
        this.productionGrowth = productionGrowth;
    }

    public void setHappinessGrowth(int happinessGrowth) {
        this.happinessGrowth = happinessGrowth;
    }

    public void setScienceGrowth(int scienceGrowth) {
        this.scienceGrowth = scienceGrowth;
    }

    public CivilizedUnitType getInProgressCivilizedUnit() {
        return inProgressCivilizedUnit;
    }

    public CloseCombatUnitType getInProgressCloseCombatUnit() {
        return inProgressCloseCombatUnit;
    }

    public RangedCombatUnitType getInProgressRangedCombatUnit() {
        return inProgressRangedCombatUnit;
    }

    public int getNextUnitTurns() {
        return nextUnitTurns;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLands(ArrayList<Land> lands) {
        this.lands = lands;
    }

    public void setBuildings(ArrayList<Building> buildings) {
        this.buildings = buildings;
    }

    public void setImprovements(ArrayList<Improvement> improvements) {
        this.improvements = improvements;
    }

    public void setInProgressCivilizedUnit(CivilizedUnitType inProgressCivilizedUnit) {
        this.inProgressCivilizedUnit = inProgressCivilizedUnit;
    }

    public void setInProgressCloseCombatUnit(CloseCombatUnitType inProgressCloseCombatUnit) {
        this.inProgressCloseCombatUnit = inProgressCloseCombatUnit;
    }

    public void setInProgressRangedCombatUnit(RangedCombatUnitType inProgressRangedCombatUnit) {
        this.inProgressRangedCombatUnit = inProgressRangedCombatUnit;
    }

    public void setNextUnitTurns(int nextUnitTurns) {
        this.nextUnitTurns = nextUnitTurns;
    }

    public void setGarrison(CloseCombatUnit garrison) {
        this.garrison = garrison;
    }

    public void setHasAnInProgressUnit(boolean hasAnInProgressUnit) {
        this.hasAnInProgressUnit = hasAnInProgressUnit;
    }

    public boolean hasAnInProgressUnit() {
        return hasAnInProgressUnit;
    }

    public Land getMainLand() {
        return mainLand;
    }

    public void setMainLand(Land mainLand) {
        this.mainLand = mainLand;
    }
}
