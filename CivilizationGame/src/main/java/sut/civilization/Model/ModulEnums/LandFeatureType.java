package sut.civilization.Model.ModulEnums;

public enum LandFeatureType {
    WATERSHED("Watershed", 2, 0, 0, -33, 1, new ResourceType[]{ResourceType.WHEAT, ResourceType.SUGAR}, true),
    JUNGLE("Jungle", 1, 1, 0, 25, 2, new ResourceType[]{ResourceType.DEER, ResourceType.SILK}, true),
    ICE("Ice", 0, 0, 0, 0, -1, null, false),
    FOREST("Forest", 1, -1, 0, 25, 2, new ResourceType[]{ResourceType.BANANA, ResourceType.JEWEL}, true),
    MARSH("Marsh", -1, 0, 0, -33, 2, new ResourceType[]{ResourceType.SUGAR}, true),
    OASIS("Oasis", 3, 0, 1, -33, 1, null, true),
    //fixme put sth else other than the number 1000 for movement cost of Rivers
    RIVER_TOP("RiverTop", 0, 0, 1, 0, 1000, null, true),
    RIVER_TOP_RIGHT("RiverTopRight", 0, 0, 1, 0, 1000, null, true),
    RIVER_TOP_LEFT("RiverTopLeft", 0, 0, 1, 0, 1000, null, true),
    RIVER_BOTTOM("RiverBottom", 0, 0, 1, 0, 1000, null, true),
    RIVER_BOTTOM_RIGHT("RiverBottomRight", 0, 0, 1, 0, 1000, null, true),
    RIVER_BOTTOM_LEFT("RiverBottomLeft", 0, 0, 1, 0, 1000, null, true),


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
