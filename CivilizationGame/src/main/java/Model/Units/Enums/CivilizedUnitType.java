package Model.Units.Enums;

import Model.Resources.Enums.ResourceType;
import Model.Resources.Resource;
import Model.Technologies.Technology;
import Model.Technologies.TechnologyType;
import Model.Units.CivilizedUnit;

public enum CivilizedUnitType {
    SETTLER("Settler", 89, 2, null, null, 0, 10),
    WORKER("Worker", 70, 2, null, null, 0, 10);

    public String name;
    public int cost;
    public int MP;
    public ResourceType resourceType;
    public TechnologyType technologyType;
    public int turns;
    public int hp;

    CivilizedUnitType(String name, int cost, int MP, ResourceType resourceType, TechnologyType technologyType, int turns, int hp) {
        this.name = name;
        this.cost = cost;
        this.MP = MP;
        this.resourceType = resourceType;
        this.technologyType = technologyType;
        this.turns = turns;
        this.hp = hp;
    }
}

/*
    SETTLER("Settler", 89, 2, null, null),
    WORKER("Worker", 70, 2, null, null),

    ARCHER("Archer", 70, 4, 6, 2, 2, null, TechnologyType.Archery),
    CHARIOT_ARCHER("Chariot Archer", 60, 3, 6, 2, 4, ResourceType.Horse, TechnologyType.Wheel),
    CATAPULT("Catapult", 100, 4, 14, 2, 2, ResourceType.Iron, TechnologyType.Mathematics),
    TREBUCHET("Trebuchet", 170, 6, 20, 2, 2, ResourceType.Iron, TechnologyType.Physics),
    CANON("Canon", 250, 10, 26, 2, 2, null, TechnologyType.Chemistry),
    ARTILLERY("Artillery", 420, 16, 32, 3, 2, null, TechnologyType.Dynamite),

    SCOUT("Scout", 25, 4, 2, null, null),
    SPEARMAN("Spearman", 50, 7, 2, null, TechnologyType.BronzeWorking),
    WARRIOR("Warrior", 40, 6, 2, null, null),
    HORSEMAN("Horseman", 80, 12, 4, ResourceType.Horse, TechnologyType.HorsebackRiding),
    SWORDSMAN("Swordsman", 80, 11, 2, ResourceType.Iron, TechnologyType.IronWorking),
    KNIGHT("Knight", 150, 18, 3, ResourceType.Horse, TechnologyType.Chivalry),
 */