package Model.Units.Enums;

import Model.Nations.Nation;
import Model.Resources.Resource;
import Model.Units.CloseCombatUnit;

public enum CloseCombatUnitType {
    ;//TODO fill enums
    private CloseCombatUnit closeCombatUnit;

    CloseCombatUnitType(Nation ownerNation, String name, int hp, int movement, int cost, Resource resource, int combatStrength, int turns) {
        this.closeCombatUnit = new CloseCombatUnit(ownerNation,name, hp, movement, cost, resource, combatStrength, turns);

    }
}
