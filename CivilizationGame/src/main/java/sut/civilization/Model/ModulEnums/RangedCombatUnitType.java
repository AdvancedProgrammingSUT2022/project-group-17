package sut.civilization.Model.ModulEnums;

public enum RangedCombatUnitType {
    ARCHER("Archer", 70, 4, 6, 10, 2, null,
            TechnologyType.ARCHERY, 10, false),
    CHARIOT_ARCHER("Chariot Archer", 60, 3, 6, 2, 4,
            ResourceType.HORSE, TechnologyType.THE_WHEEL, 10, false),
    CROSSBOWMAN("Crossbowman", 120, 6, 12, 2,2, null,
            TechnologyType.MACHINERY, 10, false),
    CATAPULT("Catapult", 100, 4, 14, 2, 2,
            ResourceType.IRON, TechnologyType.MATHEMATICS, 10, true),
    TREBUCHET("Trebuchet", 170, 6, 20, 2, 2,
            ResourceType.IRON, TechnologyType.PHYSICS, 10, true),
    CANNON("Cannon", 250, 10, 26, 2, 2,
            null, TechnologyType.CHEMISTRY, 10, true),
    ARTILLERY("Artillery", 420, 16, 32, 3, 2, null,
            TechnologyType.DYNAMITE, 10, true);

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
    public final String imageAddress;
    public UnitActions[] actions;

    RangedCombatUnitType(String name, int cost, int combatStrength, int rangedStrength, int range, int MP,
                         ResourceType resourceType, TechnologyType technologyType, int hp, boolean isSiege) {
        this.name = name;
        this.cost = cost;
        this.combatStrength = combatStrength;
        this.rangedStrength = rangedStrength;
        this.range = range;
        this.MP = MP;
        this.resourceType = resourceType;
        this.technologyType = technologyType;
        this.hp = hp;
        this.turns = 3;
        this.isSiege = isSiege;
        this.imageAddress = "/sut/civilization/Images/units/" + name + ".png";
        this.actions = new UnitActions[]{UnitActions.MOVE, UnitActions.RANGED_ATTACK, UnitActions.FORTIFY,
                UnitActions.ALERT, UnitActions.FORTIFY_UNTIL_HEAL, UnitActions.SLEEP, UnitActions.WAKE, UnitActions.DELETE};
    }
}
