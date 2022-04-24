package Model.Units;

import Model.Resources.Enums.ResourceType;
import Model.Resources.Resource;
import Model.Technologies.Technology;
import Model.Technologies.TechnologyType;

public class CloseCombatUnit extends CombatUnit {

    public CloseCombatUnit(String name, int cost, int combatStrength, int MP, ResourceType resourceType, TechnologyType technologyType,
                           int turns, int hp) {
        super(name, cost, combatStrength, MP, resourceType, technologyType, turns, hp);
    }

    public int getCombatStrength() {
        return combatStrength;
    }
}
