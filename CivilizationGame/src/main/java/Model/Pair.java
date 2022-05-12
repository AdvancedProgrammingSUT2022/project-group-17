package Model;

import Enums.Consts;

public class Pair {
    public int x;
    public int y;

    public Pair(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public static boolean isValid(Pair pair) {
        if (pair == null)
            return false;

        return pair.x >= 0 && pair.y >= 0 && pair.x < Consts.MAP_SIZE.amount.x && pair.y < Consts.MAP_SIZE.amount.y;
    }

    public boolean equals(Pair pair) {
        return this.x == pair.x && this.y == pair.y;
    }

    @Override
    public String toString() {
        return "(" + x + "," + y +")";
    }
}
