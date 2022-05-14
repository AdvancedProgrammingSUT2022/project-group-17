package Model.Units;
import Model.Pair;
import Model.Resources.Enums.ResourceType;
import Model.Nations.Nation;
import Model.Technologies.TechnologyType;
import Model.Units.Enums.UnitStatus;

public class CombatUnit extends Unit {
    protected int combatStrength;
    protected int ZOC;

    public CombatUnit(String name, int cost, int combatStrength, int MP, ResourceType resourceType,
                      TechnologyType technologyType, int turns, int hp, Nation ownerNation, Pair location) {
        super(name, cost, MP, resourceType, technologyType, turns, hp,ownerNation, location, UnitStatus.AWAKE);
        this.combatStrength = combatStrength;
    }
}
