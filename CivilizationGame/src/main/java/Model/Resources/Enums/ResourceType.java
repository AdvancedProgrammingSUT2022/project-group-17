package Model.Resources.Enums;

import Model.Resources.Resource;

public enum ResourceType {
    Banana("Banana", )
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
