package sut.civilization.Model.Classes;
import sut.civilization.Model.ModulEnums.ResourceType;
import sut.civilization.Model.ModulEnums.TechnologyType;
import sut.civilization.Model.ModulEnums.UnitStatus;

public class Unit {
    protected Nation ownerNation;
    protected String name;
    protected Pair<Integer,Integer> location;
    protected int hp;
    protected int MP;
    protected int cost;
    protected int maintenance = 1;
    protected int turns;
    protected int XP;
    protected boolean isWaitingForCommand = true;
    protected TechnologyType technologyType;
    protected ResourceType resourceType;
    protected UnitStatus unitStatus;
    protected String path = "";
    protected City targetCity;
    protected Unit targetUnit;

    public Unit(String name, int cost, int MP, ResourceType resourceType, TechnologyType technologyType,
                int turns, int hp, Nation ownerNation, Pair<Integer,Integer> location) {
        this.ownerNation = ownerNation;
        this.name = name;
        this.cost = cost;
        this.MP = MP;
        this.resourceType = resourceType;
        this.technologyType = technologyType;
        this.turns = turns;
        this.hp = hp;
        this.XP = 0;

        this.location = location;
        this.unitStatus = UnitStatus.AWAKE;
    }

    public Nation getOwnerNation() {
        return ownerNation;
    }

    public void setOwnerNation(Nation ownerNation) {
        this.ownerNation = ownerNation;
    }

    public String getName() {
        return name;
    }

    public Pair<Integer,Integer> getLocation() {
        return location;
    }

    public int getHp() {
        return hp;
    }

    public int getCost() {
        return cost;
    }

    public ResourceType getResourceType() {
        return resourceType;
    }

    public int getXP() {
        return XP;
    }

    public String getPath() {
        return path;
    }

    public boolean isWaitingForCommand() {
        return isWaitingForCommand;
    }

    public int getMP() {
        return MP;
    }

    public UnitStatus getUnitStatus() {
        return unitStatus;
    }

    public void setLocation(Pair<Integer,Integer> location) {
        this.location = location;
    }

    public void changeXP(int amount) {
        this.XP -= amount;
    }

    public void decreaseMP(int amount) {
        this.MP -= amount;
    }

    public void setMP(int MP) {
        this.MP = MP;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public void addHp(int amount) {
        this.hp += amount;
    }

    public void setTurns(int turns) {
        this.turns = turns;
    }

    public void setWaitingForCommand(boolean waitingForCommand) {
        isWaitingForCommand = waitingForCommand;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setTargetCity(City targetCity) {
        this.targetCity = targetCity;
    }

    public void setTargetUnit(Unit targetUnit) {
        this.targetUnit = targetUnit;
    }

    public City getTargetCity() {
        return targetCity;
    }

    public Unit getTargetUnit() {
        return targetUnit;
    }

    public void setUnitStatus(UnitStatus unitStatus) {
        this.unitStatus = unitStatus;
    }

    public int getMaintenance() {
        return maintenance;
    }
}
