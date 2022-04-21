package Model.Buildings;

public enum BuildingType {
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
