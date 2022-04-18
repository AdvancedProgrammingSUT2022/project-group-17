package Model.Units;

import Model.Resources.Resource;

public class Unit {
    protected String name;
    protected int hp;
    protected int movement;
    protected int cost;
    protected int turns;
    protected int XP;
    //Fixme
    protected static boolean isTechnologicallyAvailable = false;
    //TODO how to know if the user has the specific resource
    protected Resource resource;

    public Unit(String name, int hp, int movement, int cost, Resource resource, int turns) {
        this.name = name;
        this.hp = hp;
        this.movement = movement;
        this.cost = cost;
        this.resource = resource;
        this.XP = 0;
        this.turns = turns;
    }

    public String getName() {
        return name;
    }

    public int getHp() {
        return hp;
    }

    public int getMovement() {
        return movement;
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

    public void changeXP(int amount){
        this.XP -= amount;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public void setTurns(int turns) {
        this.turns = turns;
    }
}
