package sut.civilization.Model.Classes;

import sut.civilization.Model.ModulEnums.CurrencyType;

public class Currency {
    protected int balance;
    protected int growthRate = 0;
    protected CurrencyType currencyType;

    public Currency(CurrencyType currencyType){
        this.currencyType = currencyType;
        this.balance = 0;
    }

    public void setBalance(int balance) {
        this.balance = balance;
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

    public void addGrowthRate(int growthRate){
        this.growthRate += growthRate;
    }

    public void addBalance(int balance) {
        this.balance += balance;
    }

    public void addGrowthRateToBalance(){
        this.balance += this.growthRate;
    }
}
