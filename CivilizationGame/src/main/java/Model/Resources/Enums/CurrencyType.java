package Model.Resources.Enums;

import Model.Resources.Currency;

public enum CurrencyType {
    Coin("Coin"),
    Food("Food"),
    Production("Production"),
    Science("Science"),
    Happiness("Happiness"),

    ;
    public String name;

    CurrencyType(String name) {
        this.name = name;
    }
}
