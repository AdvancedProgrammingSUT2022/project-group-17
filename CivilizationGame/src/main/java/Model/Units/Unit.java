package Model.Units;

import Model.Resources.Resource;

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
    //TODO how to know if the user has the specific resource
    protected Resource resource;

    public Unit(String name, int hp, int movement, int cost, Resource resource, int turns) {
        this.name = name;
        this.hp = hp;
        this.MP = movement;
        this.cost = cost;
        this.resource = resource;
        this.XP = 0;
        this.turns = turns;
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

    public Resource getResource() {
        return resource;
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

    public void changeXP(int amount){
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
}
