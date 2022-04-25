package Model.Units;

import Model.Nations.Nation;
import Model.Resources.Resource;

public class CloseCombatUnit extends CombatUnit{

    public CloseCombatUnit(Nation ownerNation, String name, int hp, int movement, int cost, Resource resource, int combatStrength, int turns) {
        super(ownerNation,name, hp, movement, cost, resource, turns, combatStrength);
    }

    public int getCombatStrength() {
        return combatStrength;
    }
}
