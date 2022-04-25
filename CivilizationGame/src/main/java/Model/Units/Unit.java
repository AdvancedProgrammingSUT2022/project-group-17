package Model.Units;

import Model.Resources.Enums.ResourceType;
import Model.Resources.Resource;
import Model.Technologies.Technology;
import Model.Technologies.TechnologyType;

public class Unit {
    protected String name;
    protected int i;
    protected int j;
    protected int hp;
    protected int MP;
    protected int cost;
    protected int turns;
    protected int XP;
    protected boolean isWaitingForCommand;
    //Fixme
    protected static boolean isTechnologicallyAvailable = false;
    protected TechnologyType technologyType;
    //TODO how to know if the user has the specific resource
    protected ResourceType resourceType;

    public Unit(String name, int cost, int MP, ResourceType resourceType, TechnologyType technologyType,
                int turns, int hp) {
        this.name = name;
        this.cost = cost;
        this.MP = MP;
        this.resourceType = resourceType;
        this.technologyType = technologyType;
        this.turns = turns;
        this.hp = hp;
        this.XP = 0;
    }

    public String getName() {
        return name;
    }

    public int getI() {
        return i;
    }

    public int getJ() {
        return j;
    }

    public int getHp() {
        return hp;
    }

    public int getCost() {
        return cost;
    }

    public static boolean isIsTechnologicallyAvailable() {
        return isTechnologicallyAvailable;
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

    public void setI(int i) {
        this.i = i;
    }

    public void setJ(int j) {
        this.j = j;
    }

    public void changeXP(int amount) {
        this.XP -= amount;
    }

    public void changeMP(int amount) {
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
