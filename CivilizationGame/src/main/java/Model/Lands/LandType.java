package Model.Lands;

import Model.LandFeatures.LandFeatureType;
import Model.Resources.Enums.ResourceType;

public enum LandType {
    Desert("Desert", 0, 0, 0, -33, 1, null, null, true),
    GrassLand("GrassLand", 2, 0, 0, -33, 1, null, null, true),
    Hill("Hill", 0, 2, 0, 25, 2, null, null, true),
    Mountain("Mountain", 0, 0, 0, 25, 1000, null, null, false),
    Ocean("Ocean", 0, 0, 0, 25, 1000, null, null, false),
    Plain("Plain", 1, 1, 0, -33, 1, null, null, true),
    Snow("Snow", 0, 0, 0, -33, 1, null, null, true),
    Tundra("Tundra", 1, 0, 0, -33, 1, null, null, true),
    


    ;

    public String name;
    public int foodGrowth;
    public int productionGrowth;
    public int coinGrowth;
    public int combatChange;
    public int movementCost;
    public LandFeatureType[] landFeatureTypes;
    public ResourceType[] resourceTypes;
    public boolean isWalkable;

    LandType(String name, int foodGrowth, int productionGrowth, int coinGrowth, int combatChange, int movementCost,
             LandFeatureType[] landFeatureTypes, ResourceType[] resourceTypes, boolean isWalkable) {
        this.name = name;
        this.foodGrowth = foodGrowth;
        this.productionGrowth = productionGrowth;
        this.coinGrowth = coinGrowth;
        this.combatChange = combatChange;
        this.movementCost = movementCost;
        this.landFeatureTypes = landFeatureTypes;
        this.resourceTypes = resourceTypes;
        this.isWalkable = isWalkable;
    }
}
