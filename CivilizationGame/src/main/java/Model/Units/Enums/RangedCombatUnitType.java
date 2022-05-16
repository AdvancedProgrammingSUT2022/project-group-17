package Model.Units.Enums;
import Model.Resources.Enums.ResourceType;
import Model.Technologies.TechnologyType;

public enum RangedCombatUnitType {
    ARCHER("Archer", 70, 4, 6, 2, 2, null,
            TechnologyType.ARCHERY, 0, 10, false),
    CHARIOT_ARCHER("Chariot Archer", 60, 3, 6, 2, 4,
            ResourceType.HORSE, TechnologyType.THE_WHEEL, 0, 10, false),
    CROSSBOWMAN("Crossbowman", 120, 6, 12, 2,2, null,
            TechnologyType.MACHINERY, 0, 10, false),
    CATAPULT("Catapult", 100, 4, 14, 2, 2,
            ResourceType.IRON, TechnologyType.MATHEMATICS, 0, 10, true),
    TREBUCHET("Trebuchet", 170, 6, 20, 2, 2,
            ResourceType.IRON, TechnologyType.PHYSICS, 0, 10, true),
    CANON("Canon", 250, 10, 26, 2, 2,
            null, TechnologyType.CHEMISTRY, 0, 10, true),
    ARTILLERY("Artillery", 420, 16, 32, 3, 2, null,
            TechnologyType.DYNAMITE, 0, 10, true);

    public final String name;
    public final int cost;
    public final int combatStrength;
    public final int rangedStrength;
    public final int range;
    public final int MP;
    public final ResourceType resourceType;
    public final TechnologyType technologyType;
    public final int turns;
    public final int hp;
    public final boolean isSiege;

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
