package Model.Improvements;

public class Improvement {
    protected String name;
    protected static boolean isTechnologicallyAvailable;
    protected boolean hasCitizen;
    protected int turns;
    protected Improvements improvements;

    public Improvement(String name, int turns) {
        this.name = name;
        this.turns = turns;
    }

    public String getName() {
        return name;
    }

    public static boolean isIsTechnologicallyAvailable() {
        return isTechnologicallyAvailable;
    }

    public void applyEffect(){}

    public boolean isHasCitizen() {
        return hasCitizen;
    }

    public void setHasCitizen(boolean hasCitizen) {
        this.hasCitizen = hasCitizen;
    }
}
