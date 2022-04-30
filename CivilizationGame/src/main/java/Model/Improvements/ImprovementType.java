package Model.Improvements;

import Model.LandFeatures.LandFeature;
import Model.LandFeatures.LandFeatureType;
import Model.Lands.Land;
import Model.Lands.LandType;
import Model.Resources.Enums.CurrencyType;
import Model.Resources.Enums.ResourceType;
import Model.Technologies.TechnologyType;

public enum ImprovementType {
    Camp("Camp", null, 0, new ResourceType[]{ResourceType.Fur, ResourceType.Tusk, ResourceType.Deer},
            TechnologyType.Trapping, new LandType[]{LandType.Tundra, LandType.Plain, LandType.Hill},
            new LandFeatureType[]{LandFeatureType.Jungle}, 10),
    Farm("Farm", CurrencyType.Food, 1, new ResourceType[]{ResourceType.Wheat}, TechnologyType.Agriculture,
            new LandType[]{LandType.Plain, LandType.Desert, LandType.GrassLand}, null, 5),
    LumberMill("Lumber Mill", CurrencyType.Production, 1, null, TechnologyType.Construction,
            null, new LandFeatureType[]{LandFeatureType.Jungle}, 5),
    Mine("Mine", CurrencyType.Production, 1, new ResourceType[]{ResourceType.Jewel, ResourceType.Gold,
            ResourceType.Silver, ResourceType.Coal, ResourceType.Iron}, TechnologyType.Mining, new LandType[]{LandType.Desert,
            LandType.Plain, LandType.GrassLand, LandType.Tundra, LandType.Snow, LandType.Hill},
            new LandFeatureType[]{LandFeatureType.Jungle, LandFeatureType.LushJungle, LandFeatureType.Swamp}, 5),
    Pasture("Pasture", null, 0, new ResourceType[]{ResourceType.Horse, ResourceType.Cow,
    ResourceType.Sheep}, TechnologyType.AnimalHusbandry, new LandType[]{LandType.Plain, LandType.Desert, LandType.GrassLand, LandType.Tundra, LandType.Hill},
            null, 5),
    PlantingAndWorking("PlantingAndWorking", null, 0, new ResourceType[]{ResourceType.Banana,
            ResourceType.Cotton, ResourceType.Fumigation, ResourceType.Silk, ResourceType.Sugar}, TechnologyType.Calendar,
            new LandType[]{LandType.Plain, LandType.Desert, LandType.GrassLand},
            new LandFeatureType[]{LandFeatureType.Jungle, LandFeatureType.LushJungle,
            LandFeatureType.Swamp, LandFeatureType.Watershed}, 5),
    Quarry("Quarry", null, 0, new ResourceType[]{ResourceType.Marble},
            TechnologyType.Masonry, new LandType[]{LandType.Plain, LandType.Desert, LandType.GrassLand, LandType.Tundra,
            LandType.Hill}, null, 5),
    TradingPost("Trading Post", CurrencyType.Coin, 1, null, TechnologyType.Trapping,
            new LandType[]{LandType.Desert, LandType.Plain, LandType.GrassLand, LandType.Tundra}, null,  5),
    Factory("Factory", CurrencyType.Production, 2, null, TechnologyType.Engineering,
            new LandType[]{LandType.Plain, LandType.Desert, LandType.GrassLand, LandType.Tundra, LandType.Snow},
            null, 5);


    public String name;
    public CurrencyType currency;
    public int amount;
    public ResourceType[] resourcesGiven;
    public TechnologyType technology;
    public LandType[] landTypes;
    public LandFeatureType[] landFeatureTypes;
    public int initialTurns;

    ImprovementType(String name, CurrencyType currency, int amount, ResourceType[] resourcesGiven, TechnologyType technology,
                    LandType[] landTypes, LandFeatureType[] landFeatureTypes, int initialTurns) {
        this.name = name;
        this.currency = currency;
        this.amount = amount;
        this.resourcesGiven = resourcesGiven;
        this.technology = technology;
        this.landTypes = landTypes;
        this.landFeatureTypes = landFeatureTypes;
        this.initialTurns = initialTurns;
    }
}
