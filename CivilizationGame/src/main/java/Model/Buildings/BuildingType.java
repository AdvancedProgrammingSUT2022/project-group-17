package Model.Buildings;

public enum BuildingType {
    Barracks("Barracks", 80, 1, 10),
    Granary("Granary", 100, 1, 10),
    Library("Library", 80, 1, 10),
    Monument("Monument", 60, 1, 10),
    Walls("Walls", 100, 1, 10),
    WaterMill("Water Mill", 120, 2, 10),
    Armory("Armory", 130, 3, 10),
    BurialTomb("Burial Tomb", 120, 0, 10),
    Circus("Circus", 150, 3, 10),
    Colosseum("Colosseum", 150, 3, 10),
    Courthouse("Courthouse", 200, 5, 10),
    Stable("Stable", 100, 1, 10),
    Temple("Temple", 120, 2, 10),
    Castle("Castle", 200, 3, 10),
    Forge("Forge", 150, 2, 10),
    Garden("Garden", 120, 2, 10),
    Market("Market", 120, 0, 10),
    Mint("Mint", 120, 0, 10),
    Monastery("Monastery", 120, 2, 10),
    University("University", 200, 3, 10),
    Workshop("Workshop", 100, 2, 10),
    Bank("Bank", 220, 0, 10),
    MilitaryAcademy("Military Academy", 350, 3, 10),
    Museum("Museum", 350, 3, 10),
    OperaHouse("Opera House", 220, 3, 10),
    PublicSchool("Public School", 350, 3, 10),
    SatrapsCourt("Satrap’s Court", 220, 0, 10),
    Theater("Theater", 300, 5, 10),
    Windmill("Windmill", 180, 2, 10),
    Arsenal("Arsenal", 350, 3, 10),
    BroadcastTower("Broadcast Tower", 600, 3, 10),
    Factory("Factory", 300, 3, 10),
    Hospital("Hospital", 400, 2, 10),
    MilitaryBase("Military Base", 450, 4, 10),
    StockExchange("Stock Exchange", 650, 0, 10),
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
