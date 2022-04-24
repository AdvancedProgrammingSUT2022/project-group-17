package Model.Resources;

import Model.Resources.Enums.CurrencyType;
import Model.Resources.Enums.ResourceType;

public class Currency {
    protected int balance;
    protected int growthRate;
    protected CurrencyType currencyType;

    public Currency(CurrencyType currencyType){
        this.currencyType = currencyType;
        this.balance = 0;
    }

    public int getGrowthRate() {
        return growthRate;
    }

    public int getBalance() {
        return balance;
    }

    public CurrencyType getCurrencyType() {
        return currencyType;
    }

    public void setGrowthRate(int growthRate) {
        this.growthRate = growthRate;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }
}
