package Model.Units.Enums;

import Model.Resources.Resource;
import Model.Units.CivilizedUnit;

public enum CivilizedUnits {
    ;//TODO fill enums

    private CivilizedUnit civilizedUnit;

    CivilizedUnits(String name, int hp, int movement, int cost, Resource resource, int turns) {
        this.civilizedUnit = new CivilizedUnit(name, hp, movement, cost, resource, turns);
    }
}
