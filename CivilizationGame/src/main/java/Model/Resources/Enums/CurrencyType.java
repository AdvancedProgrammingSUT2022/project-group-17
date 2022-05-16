package Model.Resources.Enums;

public enum CurrencyType {
    COIN("Coin"),
    FOOD("Food"),
    PRODUCTION("Production"),
    SCIENCE("Science"),
    HAPPINESS("Happiness"),

    ;
    public String name;

    CurrencyType(String name) {
        this.name = name;
    }
}
