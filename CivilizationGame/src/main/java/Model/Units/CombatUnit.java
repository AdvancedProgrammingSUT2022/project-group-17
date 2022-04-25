package Model.Units;
import Model.Resources.Enums.ResourceType;
import Model.Nations.Nation;
import Model.Resources.Resource;
import Model.Technologies.Technology;
import Model.Technologies.TechnologyType;

public class CombatUnit extends Unit {
    protected int combatStrength;
    protected int ZOC;

    public CombatUnit(String name, int cost, int combatStrength, int MP, ResourceType resourceType,
                      TechnologyType technologyType, int turns, int hp,Nation ownerNation) {
        super(name, cost, MP, resourceType, technologyType, turns, hp,ownerNation);
        this.combatStrength = combatStrength;
    }
}
