package sut.civilization.Model.Classes;

import sut.civilization.Controller.GameControllers.CityController;
import sut.civilization.Model.ModulEnums.BuildingType;

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
    protected ArrayList<BuildingType> buildings = new ArrayList<>();
    protected ArrayList<Improvement> improvements = new ArrayList<>();
    protected boolean hasAnInProgressProduct = false;
    protected Building inProgressBuilding = null;
    protected CivilizedUnit inProgressCivilizedUnit = null;
    protected CloseCombatUnit inProgressCloseCombatUnit = null;
    protected RangedCombatUnit inProgressRangedCombatUnit = null;
    protected int nextProductTurns;
    protected Land mainLand;
    protected CloseCombatUnit garrison;
    protected int coinGrowth;
    protected int foodGrowth;
    protected int productionGrowth;
    protected int happinessGrowth;
    protected int scienceGrowth;
    protected ArrayList<Land> affordableLands = new ArrayList<>();

    public City(Nation ownerNation, String name, Land mainLand){
        this.citizens = 3;
        this.HP = 100;
        this.combatStrength = 20;
        this.rangedStrength = 10;
        this.level = 1;
        this.ownerNation = ownerNation;
        this.name = name;
        this.mainLand = mainLand;
        
        CityController.updateAffordableLands();
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

    public ArrayList<BuildingType> getBuildings() {
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

    public void addBuilding(BuildingType building){
        buildings.add(building);
    }

    public void addLand(Land land){
        lands.add(land);
    }

    public void addCitizens(int amount){
        this.citizens += amount;
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

    public CivilizedUnit getInProgressCivilizedUnit() {
        return inProgressCivilizedUnit;
    }

    public CloseCombatUnit getInProgressCloseCombatUnit() {
        return inProgressCloseCombatUnit;
    }

    public RangedCombatUnit getInProgressRangedCombatUnit() {
        return inProgressRangedCombatUnit;
    }

    public int getNextProductTurns() {
        return nextProductTurns;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLands(ArrayList<Land> lands) {
        this.lands = lands;
    }

    public void setImprovements(ArrayList<Improvement> improvements) {
        this.improvements = improvements;
    }

    public void setInProgressCivilizedUnit(CivilizedUnit inProgressCivilizedUnit) {
        this.inProgressCloseCombatUnit = null;
        this.inProgressRangedCombatUnit = null;
        this.inProgressBuilding = null;
        this.inProgressCivilizedUnit = inProgressCivilizedUnit;
    }

    public void setInProgressBuilding(Building inProgressBuilding) {
        this.inProgressCivilizedUnit = null;
        this.inProgressRangedCombatUnit = null;
        this.inProgressCloseCombatUnit = null;
        this.inProgressBuilding = inProgressBuilding;
    }

    public void setInProgressCloseCombatUnit(CloseCombatUnit inProgressCloseCombatUnit) {
        this.inProgressCivilizedUnit = null;
        this.inProgressBuilding = null;
        this.inProgressRangedCombatUnit = null;
        this.inProgressCloseCombatUnit = inProgressCloseCombatUnit;
    }

    public void setInProgressRangedCombatUnit(RangedCombatUnit inProgressRangedCombatUnit) {
        this.inProgressCivilizedUnit = null;
        this.inProgressCloseCombatUnit = null;
        this.inProgressBuilding = null;
        this.inProgressRangedCombatUnit = inProgressRangedCombatUnit;
    }

    public void setNextProductTurns(int nextProductTurns) {
        this.nextProductTurns = nextProductTurns;
    }

    public void setGarrison(CloseCombatUnit garrison) {
        this.garrison = garrison;
    }

    public void setHasAnInProgressProduct(boolean hasAnInProgressProduct) {
        this.hasAnInProgressProduct = hasAnInProgressProduct;
    }

    public boolean hasAnInProgressProduct() {
        return hasAnInProgressProduct;
    }

    public Land getMainLand() {
        return mainLand;
    }

    public void setMainLand(Land mainLand) {
        this.mainLand = mainLand;
    }

    public ArrayList<Land> getAffordableLands() {
        return affordableLands;
    }

    public void addAffordableLand(Land land){
        affordableLands.add(land);
    }

    public void clearAffordableLands(){
        affordableLands.clear();
    }

    @Override
    public String toString() {
        return "City{" +
                "lands=" + lands +
                '}';
    }
}
