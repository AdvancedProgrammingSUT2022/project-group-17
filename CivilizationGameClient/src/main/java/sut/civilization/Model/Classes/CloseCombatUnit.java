package sut.civilization.Model.Classes;
import sut.civilization.Model.ModulEnums.CloseCombatUnitType;

public class CloseCombatUnit extends CombatUnit {

    private CloseCombatUnitType closeCombatUnitType;

    public CloseCombatUnit(CloseCombatUnitType closeCombatUnitType,Nation ownerNation, Pair<Integer,Integer> location) {
        super(closeCombatUnitType.name, closeCombatUnitType.cost, closeCombatUnitType.combatStrength, closeCombatUnitType.MP,
                closeCombatUnitType.resourceType, closeCombatUnitType.technologyType, closeCombatUnitType.turns,
                closeCombatUnitType.hp,ownerNation, location);
        this.closeCombatUnitType = closeCombatUnitType;
        turnsLeft = closeCombatUnitType.turns;
    }

    public int getCombatStrength() {
        return combatStrength;
    }

    public CloseCombatUnitType getCloseCombatUnitType() {
        return closeCombatUnitType;
    }
}
