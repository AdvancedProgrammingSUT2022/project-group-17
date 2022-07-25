package sut.civilization.Model.Classes;

import sut.civilization.Model.ModulEnums.ImprovementType;

public class Improvement {
    protected static boolean isTechnologicallyAvailable;
    protected boolean hasCitizen;
    protected int turns;
    protected ImprovementType improvementType;
    protected boolean isBroken = false;

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

    public boolean isBroken() {
        return isBroken;
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

    public void setBroken(boolean broken) {
        isBroken = broken;
    }
}
