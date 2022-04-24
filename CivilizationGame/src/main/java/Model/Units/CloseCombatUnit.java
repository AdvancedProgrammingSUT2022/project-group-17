package Model.Units;

import Model.Resources.Enums.ResourceType;
import Model.Resources.Resource;
import Model.Technologies.Technology;
import Model.Technologies.TechnologyType;
import Model.Units.Enums.CloseCombatUnitType;

public class CloseCombatUnit extends CombatUnit {

    private CloseCombatUnitType closeCombatUnitType;

    public CloseCombatUnit(CloseCombatUnitType closeCombatUnitType) {
        super(closeCombatUnitType.name, closeCombatUnitType.cost, closeCombatUnitType.combatStrength, closeCombatUnitType.MP,
                closeCombatUnitType.resourceType, closeCombatUnitType.technologyType, closeCombatUnitType.turns,
                closeCombatUnitType.hp);
        this.closeCombatUnitType = closeCombatUnitType;
    }

    public int getCombatStrength() {
        return combatStrength;
    }

    public CloseCombatUnitType getCloseCombatUnitType() {
        return closeCombatUnitType;
    }
}
