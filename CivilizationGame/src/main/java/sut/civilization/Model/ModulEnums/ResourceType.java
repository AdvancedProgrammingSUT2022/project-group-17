package sut.civilization.Model.ModulEnums;

public enum ResourceType {
    BANANA("Bananas", CurrencyType.FOOD, 1),
    COW("Cattle", CurrencyType.FOOD, 1),
    DEER("Deer", CurrencyType.FOOD, 1),
    SHEEP("Sheep", CurrencyType.FOOD, 2),
    WHEAT("Wheat", CurrencyType.FOOD, 1),
    COAL("Coal", CurrencyType.PRODUCTION, 1),
    HORSE("Horses", CurrencyType.PRODUCTION, 1),
    IRON("Iron", CurrencyType.PRODUCTION, 1),
    COTTON("Cotton", CurrencyType.COIN, 2),
    FUR("Furs", CurrencyType.COIN, 2),
    JEWEL("Jewelry", CurrencyType.COIN, 3),
    GOLD("Gold Ore", CurrencyType.COIN, 2),
    FUMIGATION("Incense", CurrencyType.COIN, 2),
    TUSK("Ivory", CurrencyType.COIN, 2),
    MARBLE("Marble", CurrencyType.COIN, 2),
    SILK("Silk", CurrencyType.COIN, 2),
    SILVER("Silver", CurrencyType.COIN, 2),
    SUGAR("Sugar", CurrencyType.COIN, 2),



    ;
    public String name;
    public CurrencyType currency;
    public int amount;

    ResourceType(String name, CurrencyType currency, int amount) {
        this.name = name;
        this.currency = currency;
        this.amount = amount;
    }
}
