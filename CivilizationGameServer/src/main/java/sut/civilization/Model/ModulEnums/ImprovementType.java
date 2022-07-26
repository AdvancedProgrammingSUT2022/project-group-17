package sut.civilization.Model.ModulEnums;

import sut.civilization.Civilization;

public enum ImprovementType {
    CAMP("Camp", null, 0, new ResourceType[]{ResourceType.FUR, ResourceType.TUSK, ResourceType.DEER},
            TechnologyType.TRAPPING, new LandType[]{LandType.TUNDRA, LandType.PLAIN, LandType.HILL},
            new LandFeatureType[]{LandFeatureType.JUNGLE}, 10),
    FARM("Farm", CurrencyType.FOOD, 1, new ResourceType[]{ResourceType.WHEAT}, TechnologyType.AGRICULTURE,
            new LandType[]{LandType.PLAIN, LandType.DESERT, LandType.GRASS_LAND}, null, 6),
    //fixme forest farm need two techs!
    FOREST_FARM("Farm", CurrencyType.FOOD, 1, new ResourceType[]{ResourceType.WHEAT}, TechnologyType.MINING,
            null, new LandFeatureType[]{LandFeatureType.FOREST}, 10),
    JUNGLE_FARM("Farm", CurrencyType.FOOD, 1, new ResourceType[]{ResourceType.WHEAT}, TechnologyType.BRONZE_WORKING,
            null, new LandFeatureType[]{LandFeatureType.JUNGLE}, 13),
    MARSH_FARM("Farm", CurrencyType.FOOD, 1, new ResourceType[]{ResourceType.WHEAT}, TechnologyType.MASONRY,
            null, new LandFeatureType[]{LandFeatureType.MARSH}, 12),
    LUMBER_MILL("Lumber Mill", CurrencyType.PRODUCTION, 1, null, TechnologyType.CONSTRUCTION,
            null, new LandFeatureType[]{LandFeatureType.JUNGLE}, 6),
    MINE("Mine", CurrencyType.PRODUCTION, 1, new ResourceType[]{ResourceType.JEWEL, ResourceType.GOLD,
            ResourceType.SILVER, ResourceType.COAL, ResourceType.IRON}, TechnologyType.MINING, new LandType[]{LandType.DESERT,
            LandType.PLAIN, LandType.GRASS_LAND, LandType.TUNDRA, LandType.SNOW, LandType.HILL},
            new LandFeatureType[]{LandFeatureType.JUNGLE, LandFeatureType.FOREST, LandFeatureType.MARSH}, 6),
    FOREST_MINE("Mine", CurrencyType.PRODUCTION, 1, new ResourceType[]{ResourceType.JEWEL, ResourceType.GOLD,
            ResourceType.SILVER, ResourceType.COAL, ResourceType.IRON}, null, null,
            new LandFeatureType[]{LandFeatureType.FOREST}, 10),
    JUNGLE_MINE("Mine", CurrencyType.PRODUCTION, 1, new ResourceType[]{ResourceType.JEWEL, ResourceType.GOLD,
            ResourceType.SILVER, ResourceType.COAL, ResourceType.IRON}, TechnologyType.BRONZE_WORKING, null,
            new LandFeatureType[]{LandFeatureType.JUNGLE}, 13),
    MARSH_MINE("Mine", CurrencyType.PRODUCTION, 1, new ResourceType[]{ResourceType.JEWEL, ResourceType.GOLD,
            ResourceType.SILVER, ResourceType.COAL, ResourceType.IRON}, TechnologyType.MASONRY, null,
            new LandFeatureType[]{LandFeatureType.MARSH}, 12),
    PASTURE("Pasture", null, 0, new ResourceType[]{ResourceType.HORSE, ResourceType.COW,
            ResourceType.SHEEP}, TechnologyType.ANIMAL_HUSBANDRY, new LandType[]{LandType.PLAIN, LandType.DESERT, LandType.GRASS_LAND, LandType.TUNDRA, LandType.HILL},
            null, 6),
    PLANTATION("Plantation", null, 0, new ResourceType[]{ResourceType.BANANA,
            ResourceType.COTTON, ResourceType.FUMIGATION, ResourceType.SILK, ResourceType.SUGAR}, TechnologyType.CALENDAR,
            new LandType[]{LandType.PLAIN, LandType.DESERT, LandType.GRASS_LAND},
            new LandFeatureType[]{LandFeatureType.JUNGLE, LandFeatureType.FOREST,
                    LandFeatureType.MARSH, LandFeatureType.WATERSHED}, 6),
    QUARRY("Quarry", null, 0, new ResourceType[]{ResourceType.MARBLE},
            TechnologyType.MASONRY, new LandType[]{LandType.PLAIN, LandType.DESERT, LandType.GRASS_LAND, LandType.TUNDRA,
            LandType.HILL}, null, 6),
    TRADING_POST("Trading Post", CurrencyType.GOLD, 1, null, TechnologyType.TRAPPING,
            new LandType[]{LandType.DESERT, LandType.PLAIN, LandType.GRASS_LAND, LandType.TUNDRA}, null, 6),
    FACTORY("Factory", CurrencyType.PRODUCTION, 2, null, TechnologyType.ENGINEERING,
            new LandType[]{LandType.PLAIN, LandType.DESERT, LandType.GRASS_LAND, LandType.TUNDRA, LandType.SNOW},
            null, 6),
    ROAD("Road", null, 0, null, TechnologyType.THE_WHEEL, null ,null, 3),
    RAILROAD("Railroad", null, 0, null, TechnologyType.RAILROAD, null, null, 3);


    public String name;
    public CurrencyType currency;
    public int amount;
    public ResourceType[] resourcesGiven;
    public TechnologyType technology;
    public LandType[] landTypes;
    public LandFeatureType[] landFeatureTypes;
    public int initialTurns;
    public String imageAddress;
    public String onTileImageAddress;

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
        this.imageAddress = "/sut/civilization/Images/improvements/" + name + ".png";
        this.onTileImageAddress = "/sut/civilization/Images/Icons/ImprovementIcons/" + name + ".png";
    }
}
