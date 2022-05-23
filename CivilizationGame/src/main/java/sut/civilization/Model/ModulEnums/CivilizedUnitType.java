package sut.civilization.Model.ModulEnums;

public enum CivilizedUnitType {
    SETTLER("Settler", 89, 2, null, null, 0, 10),
    WORKER("Worker", 70, 2, null, null, 0, 10);

    public final String name;
    public final int cost;
    public final int MP;
    public final ResourceType resourceType;
    public final TechnologyType technologyType;
    public final int turns;
    public final int hp;
    CivilizedUnitType(String name, int cost, int MP, ResourceType resourceType, TechnologyType technologyType,
                      int turns, int hp) {
        this.name = name;
        this.cost = cost;
        this.MP = MP;
        this.resourceType = resourceType;
        this.technologyType = technologyType;
        this.turns = turns;
        this.hp = hp;
    }
}