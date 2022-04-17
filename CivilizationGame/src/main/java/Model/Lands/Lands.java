package Model.Lands;

import Model.Improvements.Improvement;

public enum Lands {
    ;

    Land land;
    Lands(Improvement improvement, int movementCost, int cost, boolean isBuyable, boolean isWalkable){
        this.land = new Land(improvement, movementCost, cost, isBuyable, isWalkable);
    }
}
