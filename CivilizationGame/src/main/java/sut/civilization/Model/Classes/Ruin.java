package sut.civilization.Model.Classes;

import java.util.Random;

public class Ruin {
    private boolean retrieved = false;
    private int goldAmount;

    public Ruin() {
        this.goldAmount = new Random().nextInt(10);
    }

    public boolean isRetrieved() {
        return retrieved;
    }

    public int getGoldAmount() {
        return goldAmount;
    }

    public void retrieve(){
        this.retrieved = true;
    }

}
