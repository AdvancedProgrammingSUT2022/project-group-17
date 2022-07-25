package sut.civilization.Model.Classes;
import sut.civilization.Enums.GameEnums.WorkerWorks;
import sut.civilization.Model.ModulEnums.ImprovementType;
import sut.civilization.Model.ModulEnums.CivilizedUnitType;

public class CivilizedUnit extends Unit {

    protected CivilizedUnitType civilizedUnitType;
    protected int turnsLeft;
    protected ImprovementType improvementType = null;
    protected WorkerWorks workerWorks = null;


    public CivilizedUnit(CivilizedUnitType civilizedUnitType, Nation ownerNation, Pair<Integer,Integer> location) {
        super(civilizedUnitType.name, civilizedUnitType.cost, civilizedUnitType.MP, civilizedUnitType.resourceType,
                civilizedUnitType.technologyType, civilizedUnitType.turns, civilizedUnitType.hp,ownerNation, location);
        this.civilizedUnitType = civilizedUnitType;
    }

    public CivilizedUnitType getCivilizedUnitType() {
        return civilizedUnitType;
    }

    public int getTurnsLeft() {
        return turnsLeft;
    }

    public ImprovementType getImprovementType() {
        return improvementType;
    }

    public WorkerWorks getWorkerWorks() {
        return workerWorks;
    }

    public void setTurnsLeft(int turnsLeft) {
        this.turnsLeft = turnsLeft;
    }

    public void decreaseTurnsLeft(int amount) {
        this.turnsLeft -= amount;
    }

    public void setImprovementType(ImprovementType improvementType) {
        this.improvementType = improvementType;
    }

    public void setWorkerWorks(WorkerWorks workerWorks) {
        this.workerWorks = workerWorks;
    }
}
