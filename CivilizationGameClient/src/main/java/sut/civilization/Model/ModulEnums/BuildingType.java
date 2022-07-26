package sut.civilization.Model.ModulEnums;

public enum BuildingType {
    BARRACKS("Barracks", 80, 1, 10, TechnologyType.BRONZE_WORKING),
    GRANARY("Granary", 100, 1, 10, TechnologyType.POTTERY),
    LIBRARY("Library", 80, 1, 10, TechnologyType.WRITING),
    MONUMENT("Monument", 60, 1, 10, null),
    WALLS("Walls", 100, 1, 10, TechnologyType.MASONRY),
    WATER_MILL("Water Mill", 120, 2, 10, TechnologyType.THE_WHEEL),
    ARMORY("Armory", 130, 3, 10, TechnologyType.IRON_WORKING),
    BURIAL_TOMB("Burial Tomb", 120, 0, 10, TechnologyType.PHILOSOPHY),
    CIRCUS("Circus", 150, 3, 10, TechnologyType.HORSEBACK_RIDING),
    COLOSSEUM("Colosseum", 150, 3, 10, TechnologyType.CONSTRUCTION),
    COURTHOUSE("Courthouse", 200, 5, 10, TechnologyType.MATHEMATICS),
    STABLE("Stable", 100, 1, 10, TechnologyType.HORSEBACK_RIDING),
    TEMPLE("Temple", 120, 2, 10, TechnologyType.PHILOSOPHY),
    CASTLE("Castle", 200, 3, 10, TechnologyType.CHIVALRY),
    FORGE("Forge", 150, 2, 10, TechnologyType.METAL_CASTING),
    GARDEN("Garden", 120, 2, 10, TechnologyType.THEOLOGY),
    MARKET("Market", 120, 0, 10, TechnologyType.CURRENCY),
    MINT("Mint", 120, 0, 10, TechnologyType.CURRENCY),
    MONASTERY("Monastery", 120, 2, 10, TechnologyType.THEOLOGY),
    UNIVERSITY("University", 200, 3, 10, TechnologyType.EDUCATION),
    WORKSHOP("Workshop", 100, 2, 10, TechnologyType.METAL_CASTING),
    BANK("Bank", 220, 0, 10, TechnologyType.BANKING),
    MILITARY_ACADEMY("Military Academy", 350, 3, 10, TechnologyType.MILITARY_SCIENCE),
    MUSEUM("Museum", 350, 3, 10, TechnologyType.ARCHAEOLOGY),
    OPERA_HOUSE("Opera House", 220, 3, 10, TechnologyType.ACOUSTICS),
    PUBLIC_SCHOOL("Public School", 350, 3, 10, TechnologyType.SCIENTIFIC_THEORY),
    SATRAPS_COURT("Satrap's Court", 220, 0, 10, TechnologyType.BANKING),
    THEATER("Theater", 300, 5, 10, TechnologyType.PRINTING_PRESS),
    WINDMILL("Windmill", 180, 2, 10, TechnologyType.ECONOMICS),
    ARSENAL("Arsenal", 350, 3, 10, TechnologyType.RAILROAD),
    BROADCAST_TOWER("Broadcast Tower", 600, 3, 10, null),
    FACTORY("Factory", 300, 3, 10, TechnologyType.STEAM_POWER),
    HOSPITAL("Hospital", 400, 2, 10, TechnologyType.BIOLOGY),
    MILITARY_BASE("Military Base", 450, 4, 10, TechnologyType.TELEGRAPH),
    STOCK_EXCHANGE("Stock Exchange", 650, 0, 10, TechnologyType.ELECTRICITY),
    ;


    public String name;
    public int cost;
    public int maintenance;
    public int initialTurns;
    public TechnologyType technologyType;
    public String imageAddress;

    BuildingType(String name, int cost, int maintenance, int initialTurns, TechnologyType technologyType) {
        this.name = name;
        this.cost = cost;
        this.maintenance = maintenance;
        this.initialTurns = initialTurns;
        this.technologyType = technologyType;
        this.imageAddress = "/sut/civilization/Images/buildings/" + name + ".png";
    }
}
