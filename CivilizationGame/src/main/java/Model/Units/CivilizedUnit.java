package Model.Units;

import Model.Resources.Enums.ResourceType;
import Model.Resources.Resource;
import Model.Technologies.Technology;
import Model.Technologies.TechnologyType;

public class CivilizedUnit extends Unit {

    public CivilizedUnit(String name, int cost, int MP, ResourceType resourceType, TechnologyType technologyType,
                         int turns, int hp) {
        super(name, cost, MP, resourceType, technologyType, turns, hp);
    }

}
