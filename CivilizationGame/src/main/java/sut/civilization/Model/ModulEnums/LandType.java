package sut.civilization.Model.ModulEnums;

public enum LandType {
    DESERT("Desert", 0, 0, 0, -33, 1,
            new LandFeatureType[]{LandFeatureType.OASIS, LandFeatureType.WATERSHED},
            new ResourceType[]{ResourceType.IRON, ResourceType.GOLD, ResourceType.SILVER, ResourceType.JEWEL,
                    ResourceType.MARBLE, ResourceType.COTTON, ResourceType.FUMIGATION, ResourceType.SHEEP}, true),
    GRASS_LAND("GrassLand", 2, 0, 0, -33, 1,
            new LandFeatureType[]{LandFeatureType.JUNGLE, LandFeatureType.MARSH},
            new ResourceType[]{ResourceType.IRON, ResourceType.HORSE, ResourceType.COAL, ResourceType.COW,
                    ResourceType.GOLD, ResourceType.JEWEL, ResourceType.MARBLE, ResourceType.COTTON,
                    ResourceType.SHEEP}, true),
    HILL("Hill", 0, 2, 0, 25, 2,
            new LandFeatureType[]{LandFeatureType.JUNGLE, LandFeatureType.FOREST},
            new ResourceType[]{ResourceType.IRON, ResourceType.COAL, ResourceType.DEER, ResourceType.GOLD,
                    ResourceType.SILVER, ResourceType.JEWEL, ResourceType.MARBLE, ResourceType.SHEEP}, true),
    MOUNTAIN("Mountain", 0, 0, 0, 25, 1000, null, null, false),
    OCEAN("Ocean", 0, 0, 0, 25, 1000, null, null, false),
    PLAIN("Plain", 1, 1, 0, -33, 1,
            new LandFeatureType[]{LandFeatureType.JUNGLE, LandFeatureType.FOREST},
            new ResourceType[]{ResourceType.IRON, ResourceType.HORSE, ResourceType.COAL, ResourceType.WHEAT,
                    ResourceType.GOLD, ResourceType.JEWEL, ResourceType.MARBLE, ResourceType.TUSK, ResourceType.COTTON,
                    ResourceType.FUMIGATION, ResourceType.SHEEP}, true),
    SNOW("Snow", 0, 0, 0, -33, 1, null,
            new ResourceType[]{ResourceType.IRON}, true),
    TUNDRA("Tundra", 1, 0, 0, -33, 1,
            new LandFeatureType[]{LandFeatureType.JUNGLE}, new ResourceType[]{ResourceType.IRON, ResourceType.HORSE,
            ResourceType.DEER, ResourceType.SILVER, ResourceType.SILVER, ResourceType.JEWEL, ResourceType.MARBLE,
            ResourceType.FUR}, true),

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
