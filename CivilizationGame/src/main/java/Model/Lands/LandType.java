package Model.Lands;

import Model.LandFeatures.LandFeatureType;
import Model.Resources.Enums.ResourceType;

public enum LandType {
    Desert("Desert", 0, 0, 0, -33, 1,
            new LandFeatureType[]{LandFeatureType.Oasis, LandFeatureType.Watershed},
            new ResourceType[]{ResourceType.Iron, ResourceType.Gold, ResourceType.Silver, ResourceType.Jewel,
                    ResourceType.Marble, ResourceType.Cotton, ResourceType.Fumigation, ResourceType.Sheep}, true),
    GrassLand("GrassLand", 2, 0, 0, -33, 1,
            new LandFeatureType[]{LandFeatureType.Jungle, LandFeatureType.Swamp},
            new ResourceType[]{ResourceType.Iron, ResourceType.Horse, ResourceType.Coal, ResourceType.Cow,
                    ResourceType.Gold, ResourceType.Jewel, ResourceType.Marble, ResourceType.Cotton,
                    ResourceType.Sheep}, true),
    Hill("Hill", 0, 2, 0, 25, 2,
            new LandFeatureType[]{LandFeatureType.Jungle, LandFeatureType.LushJungle},
            new ResourceType[]{ResourceType.Iron, ResourceType.Coal, ResourceType.Deer, ResourceType.Gold,
                    ResourceType.Silver, ResourceType.Jewel, ResourceType.Marble, ResourceType.Sheep}, true),
    Mountain("Mountain", 0, 0, 0, 25, 1000, null, null, false),
    Ocean("Ocean", 0, 0, 0, 25, 1000, null, null, false),
    Plain("Plain", 1, 1, 0, -33, 1,
            new LandFeatureType[]{LandFeatureType.Jungle, LandFeatureType.LushJungle},
            new ResourceType[]{ResourceType.Iron, ResourceType.Horse, ResourceType.Coal, ResourceType.Wheat,
                    ResourceType.Gold, ResourceType.Jewel, ResourceType.Marble, ResourceType.Tusk, ResourceType.Cotton,
                    ResourceType.Fumigation, ResourceType.Sheep}, true),
    Snow("Snow", 0, 0, 0, -33, 1, null,
            new ResourceType[]{ResourceType.Iron}, true),
    Tundra("Tundra", 1, 0, 0, -33, 1,
            new LandFeatureType[]{LandFeatureType.Jungle}, new ResourceType[]{ResourceType.Iron, ResourceType.Horse,
            ResourceType.Deer, ResourceType.Silver, ResourceType.Silver, ResourceType.Jewel, ResourceType.Marble,
            ResourceType.Fur}, true),



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
