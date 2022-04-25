package Model.Units;

import Model.Nations.Nation;
import Model.Resources.Resource;

public class CivilizedUnit extends Unit {

    public CivilizedUnit(Nation ownerNation, String name, int hp, int movement, int cost, Resource resource, int turns) {
        super(ownerNation,name, hp, movement, cost, resource, turns);
    }

}
