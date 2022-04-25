package Enums;

import Model.Pair;

public enum Consts {

    MAP_SIZE(new Pair(10,10));

    public final Pair amount;

    Consts(Pair amount){
        this.amount = amount;
    }
}
