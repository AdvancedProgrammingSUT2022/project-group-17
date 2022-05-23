package sut.civilization.Model.ModulEnums;

public enum BuildingType {
    BARRACKS("Barracks", 80, 1, 10),
    GRANARY("Granary", 100, 1, 10),
    LIBRARY("Library", 80, 1, 10),
    MONUMENT("Monument", 60, 1, 10),
    WALLS("Walls", 100, 1, 10),
    WATER_MILL("Water Mill", 120, 2, 10),
    ARMORY("Armory", 130, 3, 10),
    BURIAL_TOMB("Burial Tomb", 120, 0, 10),
    CIRCUS("Circus", 150, 3, 10),
    COLOSSEUM("Colosseum", 150, 3, 10),
    COURTHOUSE("Courthouse", 200, 5, 10),
    STABLE("Stable", 100, 1, 10),
    TEMPLE("Temple", 120, 2, 10),
    CASTLE("Castle", 200, 3, 10),
    FORGE("Forge", 150, 2, 10),
    GARDEN("Garden", 120, 2, 10),
    MARKET("Market", 120, 0, 10),
    MINT("Mint", 120, 0, 10),
    MONASTERY("Monastery", 120, 2, 10),
    UNIVERSITY("University", 200, 3, 10),
    WORKSHOP("Workshop", 100, 2, 10),
    BANK("Bank", 220, 0, 10),
    MILITARY_ACADEMY("Military Academy", 350, 3, 10),
    MUSEUM("Museum", 350, 3, 10),
    OPERA_HOUSE("Opera House", 220, 3, 10),
    PUBLIC_SCHOOL("Public School", 350, 3, 10),
    SATRAPS_COURT("Satrap’s Court", 220, 0, 10),
    THEATER("Theater", 300, 5, 10),
    WINDMILL("Windmill", 180, 2, 10),
    ARSENAL("Arsenal", 350, 3, 10),
    BROADCAST_TOWER("Broadcast Tower", 600, 3, 10),
    FACTORY("Factory", 300, 3, 10),
    HOSPITAL("Hospital", 400, 2, 10),
    MILITARY_BASE("Military Base", 450, 4, 10),
    STOCK_EXCHANGE("Stock Exchange", 650, 0, 10),
    ;


    public String name;
    public int cost;
    public int maintenance;
    public int initialTurns;

    BuildingType(String name, int cost, int maintenance, int initialTurns) {
        this.name = name;
        this.cost = cost;
        this.maintenance = maintenance;
        this.initialTurns = initialTurns;
    }
}
