package Model.Units;

import Model.Resources.Resource;

public class RangedCombatUnit extends Unit{
    protected int combatStrength;
    protected int rangedStrength;
    protected int range;
    protected boolean isSiege;

    public RangedCombatUnit(String name, int hp, int movement, int cost, Resource resource, int combatStrength,
                            int rangedStrength, int range, boolean isSiege) {
        super(name, hp, movement, cost, resource, turns);
        this.combatStrength = combatStrength;
        this.rangedStrength = rangedStrength;
        this.range = range;
        this.isSiege = isSiege;
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

    public boolean isSiege() {
        return isSiege;
    }
}
