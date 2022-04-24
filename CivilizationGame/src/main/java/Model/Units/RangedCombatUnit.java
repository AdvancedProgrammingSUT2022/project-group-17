package Model.Units;

import Model.Resources.Enums.ResourceType;
import Model.Resources.Resource;
import Model.Technologies.Technology;
import Model.Technologies.TechnologyType;

public class RangedCombatUnit extends CombatUnit {
    protected int rangedStrength;
    protected int range;
    protected boolean isSiege;

    public RangedCombatUnit(String name, int cost, int combatStrength, int rangedStrength, int range, int MP,
                            ResourceType resourceType, TechnologyType technologyType, int turns, int hp,
                            boolean isSiege) {
        super(name, cost, combatStrength, MP, resourceType, technologyType, turns, hp);
        this.rangedStrength = rangedStrength;
        this.range = range;
        this.isSiege = isSiege;
    }

    public int getCombatStrength() {
        return combatStrength;
    }

    public int getRangedStrength() {
        return rangedStrength;
    }

    public int getRange() {
        return range;
    }

    public boolean isSiege() {
        return isSiege;
    }
}
