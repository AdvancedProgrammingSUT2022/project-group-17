package Model.Units;
import Model.Pair;
import Model.Resources.Enums.ResourceType;
import Model.Nations.Nation;
import Model.Technologies.TechnologyType;
import Model.Units.Enums.UnitStatus;

public class Unit {
    protected Nation ownerNation;
    protected String name;
    protected Pair location;
    protected int hp;
    protected int MP;
    protected int cost;
    protected int turns;
    protected int XP;
    protected boolean isWaitingForCommand;
    protected TechnologyType technologyType;
    protected ResourceType resourceType;
    protected UnitStatus unitStatus;

    public Unit(String name, int cost, int MP, ResourceType resourceType, TechnologyType technologyType,
                int turns, int hp, Nation ownerNation, Pair location, UnitStatus unitStatus) {
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

    public Pair getLocation() {
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

    public boolean isWaitingForCommand() {
        return isWaitingForCommand;
    }

    public int getMP() {
        return MP;
    }

    public UnitStatus getUnitStatus() {
        return unitStatus;
    }

    public void setLocation(Pair location) {
        this.location = location;
    }

    public void changeXP(int amount) {
        this.XP -= amount;
    }

    public void decreaseMP(int amount) {
        this.MP -= amount;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public void setTurns(int turns) {
        this.turns = turns;
    }

    public void setWaitingForCommand(boolean waitingForCommand) {
        isWaitingForCommand = waitingForCommand;
    }
}
