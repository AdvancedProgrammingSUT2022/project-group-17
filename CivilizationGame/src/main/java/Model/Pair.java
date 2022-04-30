package Model;

public class Pair {
    public int x;
    public int y;

    public Pair(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public boolean equals(Pair pair) {
        return this.x == pair.x && this.y == pair.y;
    }

    @Override
    public String toString() {
        return "(" + x + "," + y +")";
    }
}
