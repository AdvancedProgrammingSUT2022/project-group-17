package Model.Units;
import Model.Nations.Nation;
import Model.Units.Enums.RangedCombatUnitType;

public class RangedCombatUnit extends CombatUnit {
    protected int rangedStrength;
    protected RangedCombatUnitType rangedCombatUnitType;

    public RangedCombatUnit(RangedCombatUnitType rangedCombatUnitType,Nation ownerNation) {
        super(rangedCombatUnitType.name, rangedCombatUnitType.cost, rangedCombatUnitType.combatStrength, rangedCombatUnitType.MP,
                rangedCombatUnitType.resourceType, rangedCombatUnitType.technologyType, rangedCombatUnitType.turns,
                rangedCombatUnitType.hp,ownerNation);
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
