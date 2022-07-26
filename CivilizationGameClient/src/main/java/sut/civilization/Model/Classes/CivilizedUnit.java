package sut.civilization.Model.Classes;
import sut.civilization.Enums.GameEnums.WorkerWorks;
import sut.civilization.Model.ModulEnums.ImprovementType;
import sut.civilization.Model.ModulEnums.CivilizedUnitType;

public class CivilizedUnit extends Unit {

    protected CivilizedUnitType civilizedUnitType;
    protected ImprovementType improvementType = null;
    protected WorkerWorks workerWorks = null;


    public CivilizedUnit(CivilizedUnitType civilizedUnitType, Nation ownerNation, Pair<Integer,Integer> location) {
        super(civilizedUnitType.name, civilizedUnitType.cost, civilizedUnitType.MP, civilizedUnitType.resourceType,
                civilizedUnitType.technologyType, civilizedUnitType.hp,ownerNation, location);
        this.civilizedUnitType = civilizedUnitType;
        turnsLeft = civilizedUnitType.turns;
    }

    public CivilizedUnitType getCivilizedUnitType() {
        return civilizedUnitType;
    }

    public ImprovementType getImprovementType() {
        return improvementType;
    }

    public WorkerWorks getWorkerWorks() {
        return workerWorks;
    }

    public void setImprovementType(ImprovementType improvementType) {
        this.improvementType = improvementType;
    }

    public void setWorkerWorks(WorkerWorks workerWorks) {
        this.workerWorks = workerWorks;
    }
}
