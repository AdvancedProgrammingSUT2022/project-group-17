package Model.Resources.Enums;

public enum ResourceType {
    BANANA("Banana", CurrencyType.FOOD, 1),
    COW("Cow", CurrencyType.FOOD, 1),
    DEER("Deer", CurrencyType.FOOD, 1),
    SHEEP("Sheep", CurrencyType.FOOD, 2),
    WHEAT("Wheat", CurrencyType.FOOD, 1),

    COAL("Coal", CurrencyType.PRODUCTION, 1),
    HORSE("Horse", CurrencyType.PRODUCTION, 1),
    IRON("Iron", CurrencyType.PRODUCTION, 1),

    COTTON("Cotton", CurrencyType.COIN, 2),
    FUR("Fur", CurrencyType.COIN, 2),
    JEWEL("Jewel", CurrencyType.COIN, 3),
    GOLD("Gold", CurrencyType.COIN, 2),
    FUMIGATION("Fumigation", CurrencyType.COIN, 2),
    TUSK("Tusk", CurrencyType.COIN, 2),
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
