package Model.Units;
import Model.Resources.Enums.ResourceType;
import Model.Nations.Nation;
import Model.Units.Enums.CloseCombatUnitType;

public class CloseCombatUnit extends CombatUnit {

    private CloseCombatUnitType closeCombatUnitType;

    public CloseCombatUnit(CloseCombatUnitType closeCombatUnitType,Nation ownerNation, int x, int y) {
        super(closeCombatUnitType.name, closeCombatUnitType.cost, closeCombatUnitType.combatStrength, closeCombatUnitType.MP,
                closeCombatUnitType.resourceType, closeCombatUnitType.technologyType, closeCombatUnitType.turns,
                closeCombatUnitType.hp,ownerNation, x, y);
        this.closeCombatUnitType = closeCombatUnitType;

    }

    public int getCombatStrength() {
        return combatStrength;
    }

    public CloseCombatUnitType getCloseCombatUnitType() {
        return closeCombatUnitType;
    }
}
