package Model.Lands;

import Model.Improvements.Improvement;

public enum Lands {
    ;

    Land land;
    Lands(Improvement improvement, int movementCost, int cost, boolean isBuyable, boolean isWalkable, int goldGrowth, int productionGrowth, int foodGrowth){
        this.land = new Land(improvement, movementCost, cost, isBuyable, isWalkable, goldGrowth, productionGrowth, foodGrowth);
    }
}
