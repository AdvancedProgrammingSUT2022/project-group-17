package Model.Resources.Enums;

import Model.Resources.Currency;

public enum Currencies {
    ; //TODO fill enums
    private Currency currency;

    Currencies(String name, int balance, int growthRate) {
        this.currency = new Currency(name, balance, growthRate);
    }
}
