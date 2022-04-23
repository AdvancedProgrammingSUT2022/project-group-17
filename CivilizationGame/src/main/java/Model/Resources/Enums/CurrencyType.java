package Model.Resources.Enums;

import Model.Resources.Currency;

public enum CurrencyType {
    Gold("Gold"),
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
