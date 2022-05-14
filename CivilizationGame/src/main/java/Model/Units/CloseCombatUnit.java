package Model.Units;
import Model.Pair;
import Model.Resources.Enums.ResourceType;
import Model.Nations.Nation;
import Model.Units.Enums.CloseCombatUnitType;

public class CloseCombatUnit extends CombatUnit {

    private CloseCombatUnitType closeCombatUnitType;

    public CloseCombatUnit(CloseCombatUnitType closeCombatUnitType,Nation ownerNation, Pair location) {
        super(closeCombatUnitType.name, closeCombatUnitType.cost, closeCombatUnitType.combatStrength, closeCombatUnitType.MP,
                closeCombatUnitType.resourceType, closeCombatUnitType.technologyType, closeCombatUnitType.turns,
                closeCombatUnitType.hp,ownerNation, location);
        this.closeCombatUnitType = closeCombatUnitType;

    }

    public int getCombatStrength() {
        return combatStrength;
    }

    public CloseCombatUnitType getCloseCombatUnitType() {
        return closeCombatUnitType;
    }
}
