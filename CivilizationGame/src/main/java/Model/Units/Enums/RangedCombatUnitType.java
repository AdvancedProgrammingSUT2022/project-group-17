package Model.Units.Enums;

import Model.Resources.Enums.ResourceType;
import Model.Resources.Resource;
import Model.Technologies.Technology;
import Model.Technologies.TechnologyType;
import Model.Units.RangedCombatUnit;

public enum RangedCombatUnitType {
    ARCHER("Archer", 70, 4, 6, 2, 2, null,
            TechnologyType.Archery, 0, 10, false),
    CHARIOT_ARCHER("Chariot Archer", 60, 3, 6, 2, 4,
            ResourceType.Horse, TechnologyType.TheWheel, 0, 10, false),
    CATAPULT("Catapult", 100, 4, 14, 2, 2,
            ResourceType.Iron, TechnologyType.Mathematics, 0, 10, true),
    TREBUCHET("Trebuchet", 170, 6, 20, 2, 2,
            ResourceType.Iron, TechnologyType.Physics, 0, 10, true),
    CANON("Canon", 250, 10, 26, 2, 2,
            null, TechnologyType.Chemistry, 0, 10, true),
    ARTILLERY("Artillery", 420, 16, 32, 3, 2, null,
            TechnologyType.Dynamite, 0, 10, true);

    public String name;
    public int cost;
    public int combatStrength;
    public int rangedStrength;
    public int range;
    public int MP;
    public ResourceType resourceType;
    public TechnologyType technologyType;
    public int turns;
    public int hp;
    public boolean isSiege;

    RangedCombatUnitType(String name, int cost, int combatStrength, int rangedStrength, int range, int MP,
                         ResourceType resourceType, TechnologyType technologyType, int turns, int hp, boolean isSiege) {
        this.name = name;
        this.cost = cost;
        this.combatStrength = combatStrength;
        this.rangedStrength = rangedStrength;
        this.range = range;
        this.MP = MP;
        this.resourceType = resourceType;
        this.technologyType = technologyType;
        this.turns = turns;
        this.hp = hp;
        this.isSiege = isSiege;
    }
}
