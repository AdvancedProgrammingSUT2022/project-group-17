package Model.Units;

import Model.Resources.Resource;

public class RangedCombatUnit extends Unit{
    protected int combatStrength;
    protected int rangedStrength;
    protected int range;

    public RangedCombatUnit(String name, int hp, int movement, int cost, Resource resource, int combatStrength,
                            int rangedStrength, int range) {
        super(name, hp, movement, cost, resource);
        this.combatStrength = combatStrength;
        this.rangedStrength = rangedStrength;
        this.range = range;
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
}
