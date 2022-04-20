package Model.Improvements;

public class Improvement {
//    protected String name;
    protected static boolean isTechnologicallyAvailable;
    protected boolean hasCitizen;
//    protected int turns;
    public ImprovementType improvementType;

    public Improvement(ImprovementType improvementType) {
        this.improvementType = improvementType;
    }

//    public String getName() {
//        return name;
//    }

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
