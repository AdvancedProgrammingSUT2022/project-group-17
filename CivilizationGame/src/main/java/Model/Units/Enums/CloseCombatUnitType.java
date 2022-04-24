package Model.Units.Enums;

import Model.Resources.Enums.ResourceType;
import Model.Resources.Resource;
import Model.Technologies.Technology;
import Model.Technologies.TechnologyType;
import Model.Units.CloseCombatUnit;

public enum CloseCombatUnitType {
    SCOUT("Scout", 25, 4, 2, null, null, 0, 10),
    SPEAR_MAN("Spear Man", 50, 7, 2, null, TechnologyType.BronzeWorking,
            0, 10),
    WARRIOR("Warrior", 40, 6, 2, null, null, 0, 10),
    HORSE_MAN("Horse Man", 80, 12, 4, ResourceType.Horse, TechnologyType.HorsebackRiding,
            0, 10),
    SWORDS_MAN("Swords Man", 80, 11, 2, ResourceType.Iron, TechnologyType.IronWorking,
            0, 10),
    KNIGHT("Knight", 150, 18, 3, ResourceType.Horse, TechnologyType.Chivalry,
            0, 10),
    LONG_SWORDS_MAN("Long Swords Man", 150, 18, 3, ResourceType.Iron, TechnologyType.Steel,
            0, 10),
    PIKE_MAN("Pike Man", 100, 10, 2, null, TechnologyType.CivilService,
            0, 10),
    CAVALRY("Cavalry", 260, 25, 3, ResourceType.Horse, TechnologyType.MilitaryScience,
            0, 10),
    LANCER("Lancer", 220, 22, 4, ResourceType.Horse, TechnologyType.Metallurgy,
            0, 10),
    MUSKET_MAN("Musket Man", 120, 16, 2, null, TechnologyType.Gunpowder,
            0, 10),
    RIFLE_MAN("Rifle Man", 200, 25, 2, null, TechnologyType.Rifling,
            0, 10),
    ANTI_TANK_GUN("Anti-Tank Gun", 300, 32, 2, null,
            TechnologyType.ReplaceableParts, 0, 10),
    INFANTRY("Infantry", 300, 36, 2, null, TechnologyType.ReplaceableParts,
            0, 10),
    PANZER("Panzer", 450, 60, 5, null, TechnologyType.Combustion,
            0, 10),
    TANK("Tank", 450, 50, 4, null, TechnologyType.Combustion,
            0, 10);

    private CloseCombatUnit closeCombatUnit;

    CloseCombatUnitType(String name, int cost, int combatStrength, int MP, ResourceType resourceType, TechnologyType technologyType,
                        int turns, int hp) {
        this.closeCombatUnit = new CloseCombatUnit(name, cost, combatStrength, MP, resourceType, technologyType, turns,
                hp);
    }
}
