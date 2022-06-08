package sut.civilization.Model.Classes;

import sut.civilization.Enums.Consts;

import java.util.Objects;

public class Pair <firstClass,secondClass>{
    public firstClass x;
    public secondClass y;

    public Pair(firstClass x, secondClass y) {
        this.x = x;
        this.y = y;
    }

    public Pair() {

    }

    public static boolean isValid(Pair<Integer,Integer> pair) {
        if (pair == null)
            return false;

        return pair.x >= 0 && pair.y >= 0 && (pair.x < Consts.MAP_SIZE.amount.x) && (pair.y < Consts.MAP_SIZE.amount.y);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pair<?, ?> pair = (Pair<?, ?>) o;
        return Objects.equals(x, pair.x) && Objects.equals(y, pair.y);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "(" + x + "," + y +")";
    }
}
