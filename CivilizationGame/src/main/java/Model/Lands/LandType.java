package Model.Lands;

import Model.Improvements.Improvement;

public enum LandType {
    ;

    public int movementCost;
    public boolean isWalkable;
    public int goldGrowth;
    public int foodGrowth;
    public int productionGrowth;

    LandType(int movementCost, boolean isWalkable, int goldGrowth, int foodGrowth, int productionGrowth) {
        this.movementCost = movementCost;
        this.isWalkable = isWalkable;
        this.goldGrowth = goldGrowth;
        this.foodGrowth = foodGrowth;
        this.productionGrowth = productionGrowth;
    }
}
