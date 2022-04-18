package Model.Units.Enums;

import Model.Resources.Resource;
import Model.Units.RangedCombatUnit;

public enum RangedCombatUnits {
    ;//TODO fill enums
    private RangedCombatUnit rangedCombatUnit;

    RangedCombatUnits(String name, int hp, int movement, int cost, Resource resource, int combatStrength,
                      int rangedStrength, int range, boolean isSiege) {
        this.rangedCombatUnit = new RangedCombatUnit(name, hp, movement, cost, resource, combatStrength,
                rangedStrength, range, isSiege);
    }
}
