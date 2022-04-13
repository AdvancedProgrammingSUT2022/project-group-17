package Model;

import Model.Resources.Resource;

public class Currency extends Resource {
    private int growthRate;

    public Currency(String name, int balance, int growthRate){
        super(name, balance);
        this.growthRate = growthRate;
    }

    public int getGrowthRate() {
        return growthRate;
    }

    public void setGrowthRate(int growthRate) {
        this.growthRate = growthRate;
    }

    public void addGrowthRate (int amount){
        this.balance += this.growthRate;
    }

}
