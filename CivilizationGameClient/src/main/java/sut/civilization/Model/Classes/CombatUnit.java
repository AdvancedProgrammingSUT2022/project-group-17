package sut.civilization.Model.Classes;
import sut.civilization.Model.ModulEnums.ResourceType;
import sut.civilization.Model.ModulEnums.TechnologyType;

public class CombatUnit extends Unit {
    protected int combatStrength;

    public CombatUnit(String name, int cost, int combatStrength, int MP, ResourceType resourceType,
                      TechnologyType technologyType, int turns, int hp, Nation ownerNation, Pair<Integer,Integer> location) {
        super(name, cost, MP, resourceType, technologyType, hp,ownerNation, location);
        this.combatStrength = combatStrength;
    }

    public int getCombatStrength() {
        return combatStrength;
    }
}
