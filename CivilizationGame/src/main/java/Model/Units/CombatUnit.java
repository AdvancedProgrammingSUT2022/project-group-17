package Model.Units;

import Model.Nations.Nation;
import Model.Resources.Resource;

public class CombatUnit extends Unit{
    protected int combatStrength;
    protected int ZOC;

    public CombatUnit(Nation ownerNation, String name, int hp, int movement, int cost, Resource resource, int turns , int combatStrength) {
        super(ownerNation,name, hp, movement, cost, resource, turns);
        this.combatStrength = combatStrength;
    }
}
