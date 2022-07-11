package sut.civilization.Model.ModulEnums;

public enum ResourceType {
    BANANA("Bananas", "It's resource Stupid boy ! need no information !", CurrencyType.FOOD, 1),
    COW("Cattle","It's resource Stupid boy ! need no information !", CurrencyType.FOOD, 1),
    COAL("Coal","It's resource Stupid boy ! need no information !", CurrencyType.PRODUCTION, 1),
    COTTON("Cotton","It's resource Stupid boy ! need no information !", CurrencyType.COIN, 2),
    DEER("Deer","It's resource Stupid boy ! need no information !", CurrencyType.FOOD, 1),
    FUR("Furs","It's resource Stupid boy ! need no information !", CurrencyType.COIN, 2),
    GOLD("Gold Ore","It's resource Stupid boy ! need no information !", CurrencyType.COIN, 2),
    HORSE("Horses","It's resource Stupid boy ! need no information !", CurrencyType.PRODUCTION, 1),
    FUMIGATION("Incense","It's resource Stupid boy ! need no information !", CurrencyType.COIN, 2),
    TUSK("Ivory","It's resource Stupid boy ! need no information !", CurrencyType.COIN, 2),
    IRON("Iron","It's resource Stupid boy ! need no information !", CurrencyType.PRODUCTION, 1),
    JEWEL("Jewelry","It's resource Stupid boy ! need no information !", CurrencyType.COIN, 3),
    MARBLE("Marble","It's resource Stupid boy ! need no information !", CurrencyType.COIN, 2),
    SHEEP("Sheep","It's resource Stupid boy ! need no information !", CurrencyType.FOOD, 2),
    SILK("Silk","It's resource Stupid boy ! need no information !", CurrencyType.COIN, 2),
    SILVER("Silver","It's resource Stupid boy ! need no information !", CurrencyType.COIN, 2),
    SUGAR("Sugar","It's resource Stupid boy ! need no information !", CurrencyType.COIN, 2),
    WHEAT("Wheat","It's resource Stupid boy ! need no information !", CurrencyType.FOOD, 1),



    ;
    public final String name;
    public final CurrencyType currency;
    public final int amount;
    public final String information;

    ResourceType(String name, String information, CurrencyType currency, int amount) {
        this.name = name;
        this.currency = currency;
        this.amount = amount;
        this.information = information;
    }
}
