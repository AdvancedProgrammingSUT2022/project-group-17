package Model.Units.Enums;

import Model.Resources.Resource;
import Model.Units.CloseCombatUnit;

public enum CloseCombatUnits {
    ;//TODO fill enums
    private CloseCombatUnit closeCombatUnit;

    CloseCombatUnits(String name, int hp, int movement, int cost, Resource resource, int combatStrength, int turns) {
        this.closeCombatUnit = new CloseCombatUnit(name, hp, movement, cost, resource, combatStrength, turns);

    }
}
