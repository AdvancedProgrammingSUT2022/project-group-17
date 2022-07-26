package sut.civilization.Model.Classes;
import sut.civilization.Model.ModulEnums.RangedCombatUnitType;

public class RangedCombatUnit extends CombatUnit {
    protected int rangedStrength;
    protected RangedCombatUnitType rangedCombatUnitType;

    public RangedCombatUnit(RangedCombatUnitType rangedCombatUnitType,Nation ownerNation, Pair<Integer,Integer> location) {
        super(rangedCombatUnitType.name, rangedCombatUnitType.cost, rangedCombatUnitType.combatStrength, rangedCombatUnitType.MP,
                rangedCombatUnitType.resourceType, rangedCombatUnitType.technologyType, rangedCombatUnitType.turns,
                rangedCombatUnitType.hp,ownerNation, location);
        this.rangedStrength = rangedCombatUnitType.rangedStrength;
        this.rangedCombatUnitType = rangedCombatUnitType;
        turnsLeft = rangedCombatUnitType.turns;
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
