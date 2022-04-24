package Model.Resources.Enums;

import Model.Resources.Resource;

public enum ResourceType {
    Banana("Banana", CurrencyType.Food, 1),
    Cow("Cow", CurrencyType.Food, 1),
    Deer("Deer", CurrencyType.Food, 1),
    Sheep("Sheep", CurrencyType.Food, 2),
    Wheat("Wheat", CurrencyType.Food, 1),

    Coal("Coal", CurrencyType.Production, 1),
    Horse("Horse", CurrencyType.Production, 1),
    Iron("Iron", CurrencyType.Production, 1),

    Cotton("Cotton", CurrencyType.Coin, 2),
    Fur("Fur", CurrencyType.Coin, 2),
    Jewel("Jewel", CurrencyType.Coin, 3),
    Gold("Gold", CurrencyType.Coin, 2),
    Fumigation("Fumigation", CurrencyType.Coin, 2),
    Tusk("Tusk", CurrencyType.Coin, 2),
    Marble("Marble", CurrencyType.Coin, 2),
    Silk("Silk", CurrencyType.Coin, 2),
    Silver("Silver", CurrencyType.Coin, 2),
    Sugar("Sugar", CurrencyType.Coin, 2),



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
