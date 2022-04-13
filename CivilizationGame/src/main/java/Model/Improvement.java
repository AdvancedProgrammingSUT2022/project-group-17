package Model;

public class Improvement {
    protected String name;
    protected static boolean isTechnologicallyAvailable;

    public Improvement(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static boolean isIsTechnologicallyAvailable() {
        return isTechnologicallyAvailable;
    }

    public void applyEffect(){}
}
