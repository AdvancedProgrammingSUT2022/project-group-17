package Model.Units.Enums;

import Model.Nations.Nation;
import Model.Resources.Resource;
import Model.Units.CivilizedUnit;

public enum CivilizedUnitType {
    ;//TODO fill enums

    private CivilizedUnit civilizedUnit;

    CivilizedUnitType(Nation ownerNation, String name, int hp, int movement, int cost, Resource resource, int turns) {
        this.civilizedUnit = new CivilizedUnit(ownerNation,name, hp, movement, cost, resource, turns);
    }
}
