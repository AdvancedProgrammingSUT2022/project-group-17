package Model.LandFeatures;

import Model.Resources.Enums.ResourceType;

public enum LandFeatureType {
    Watershed("Watershed", 2, 0, 0, -33, 1, new ResourceType[]{ResourceType.Wheat, ResourceType.Sugar}, true),
    Jungle("Jungle", 1, 1, 0, 25, 2, new ResourceType[]{ResourceType.Deer, ResourceType.Silk}, true),
    Ice("Ice", 0, 0, 0, 0, -1, null, false),
    Forest("Forest", 1, -1, 0, 25, 2, new ResourceType[]{ResourceType.Banana, ResourceType.Jewel}, true),
    Marsh("Marsh", -1, 0, 0, -33, 2, new ResourceType[]{ResourceType.Sugar}, true),
    Oasis("Oasis", 3, 0, 1, -33, 1, null, true),
    //fixme put sth else other than the number 1000 for movement cost of Rivers
    RiverTop("RiverTop", 0, 0, 1, 0, 1000, null, true),
    RiverTopRight("RiverTopRight", 0, 0, 1, 0, 1000, null, true),
    RiverTopLeft("RiverTopLeft", 0, 0, 1, 0, 1000, null, true),
    RiverBottom("RiverBottom", 0, 0, 1, 0, 1000, null, true),
    RiverBottomRight("RiverBottomRight", 0, 0, 1, 0, 1000, null, true),
    RiverBottomLeft("RiverBottomLeft", 0, 0, 1, 0, 1000, null, true),


    ;

    public String name;
    public int foodGrowth;
    public int productionGrowth;
    public int goldGrowth;
    public int combatChange;
    public int movementCost;
    public ResourceType[] resources;
    public boolean isWalkable;

    LandFeatureType(String name, int foodGrowth, int productionGrowth, int goldGrowth, int combatChange, int movementCost, ResourceType[] resources, boolean isWalkable) {
        this.name = name;
        this.foodGrowth = foodGrowth;
        this.productionGrowth = productionGrowth;
        this.goldGrowth = goldGrowth;
        this.combatChange = combatChange;
        this.movementCost = movementCost;
        this.resources = resources;
        this.isWalkable = isWalkable;
    }
}
