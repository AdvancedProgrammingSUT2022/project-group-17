package Model.Units;

import Model.Resources.Resource;

public class Unit {
    protected String name;
    protected int hp;
    protected int combatStrength;
    protected int rangedStrength;
    protected int range;
    protected int movement;
    protected int cost;
    protected int XP;
    //Fixme
    protected static boolean isTechnologicallyAvailable = false;
    //TODO how to know if the user has the specific resource
    protected Resource resource;

    public Unit(String name, int hp, int combatStrength, int rangedStrength, int range, int movement, int cost, Resource resource) {
        this.name = name;
        this.hp = hp;
        this.combatStrength = combatStrength;
        this.rangedStrength = rangedStrength;
        this.range = range;
        this.movement = movement;
        this.cost = cost;
        this.resource = resource;
        this.XP = 0;
    }

    public String getName() {
        return name;
    }

    public int getHp() {
        return hp;
    }

    public int getCombatStrength() {
        return combatStrength;
    }

    public int getRangedStrength() {
        return rangedStrength;
    }

    public int getRange() {
        return range;
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
}
