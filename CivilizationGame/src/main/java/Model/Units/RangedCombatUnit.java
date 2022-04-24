package Model.Units;

import Model.Resources.Enums.ResourceType;
import Model.Resources.Resource;
import Model.Technologies.Technology;
import Model.Technologies.TechnologyType;
import Model.Units.Enums.RangedCombatUnitType;

public class RangedCombatUnit extends CombatUnit {
    protected int rangedStrength;
    protected RangedCombatUnitType rangedCombatUnitType;

    public RangedCombatUnit(RangedCombatUnitType rangedCombatUnitType) {
        super(rangedCombatUnitType.name, rangedCombatUnitType.cost, rangedCombatUnitType.combatStrength, rangedCombatUnitType.MP,
                rangedCombatUnitType.resourceType, rangedCombatUnitType.technologyType, rangedCombatUnitType.turns,
                rangedCombatUnitType.hp);
        this.rangedStrength = rangedCombatUnitType.rangedStrength;
    }

    public int getCombatStrength() {
        return combatStrength;
    }

    public int getRangedStrength() {
        return rangedStrength;
    }

    public RangedCombatUnitType getRangedCombatUnitType() {
        return rangedCombatUnitType;
    }
}
