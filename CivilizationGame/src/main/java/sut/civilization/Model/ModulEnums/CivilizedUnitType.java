package sut.civilization.Model.ModulEnums;

import sut.civilization.Model.Classes.Unit;

import java.util.ArrayList;

public enum CivilizedUnitType {
    SETTLER("Settler", 89, 2, null, null, 0, 10,
            new UnitActions[]{UnitActions.MOVE, UnitActions.FOUND_CITY, UnitActions.SLEEP,
                    UnitActions.WAKE, UnitActions.DELETE}),
    WORKER("Worker", 70, 2, null, null, 0, 10,
            new UnitActions[]{UnitActions.MOVE, UnitActions.BUILD_IMPROVEMENT,
                    UnitActions.REPAIR, UnitActions.SLEEP, UnitActions.WAKE, UnitActions.DELETE});

    public final String name;
    public final int cost;
    public final int MP;
    public final ResourceType resourceType;
    public final TechnologyType technologyType;
    public final int turns;
    public final int hp;
    public final String imageAddress;
    public UnitActions[] actions;
    CivilizedUnitType(String name, int cost, int MP, ResourceType resourceType, TechnologyType technologyType,
                      int turns, int hp, UnitActions[] actions) {
        this.name = name;
        this.cost = cost;
        this.MP = MP;
        this.resourceType = resourceType;
        this.technologyType = technologyType;
        this.turns = turns;
        this.hp = hp;
        this.imageAddress = "/sut/civilization/Images/units/" + name + ".png";
        this.actions = actions;
    }
}