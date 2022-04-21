package Model.Improvements;

public class Improvement {
    protected static boolean isTechnologicallyAvailable;
    protected boolean hasCitizen;
    protected int turns;
    protected ImprovementType improvementType;

    public Improvement(ImprovementType improvementType) {
        this.improvementType = improvementType;
        this.turns = improvementType.initialTurns;
    }


    public ImprovementType getImprovementType() {
        return improvementType;
    }

    public int getTurns() {
        return turns;
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
