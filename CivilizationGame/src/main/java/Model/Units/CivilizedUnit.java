package Model.Units;
import Enums.GameEnums.WorkerWorks;
import Model.Improvements.ImprovementType;
import Model.Nations.Nation;
import Model.Pair;
import Model.Units.Enums.CivilizedUnitType;
import Model.Units.Enums.UnitStatus;

public class CivilizedUnit extends Unit {

    protected CivilizedUnitType civilizedUnitType;
    protected int turnsLeft;
    protected ImprovementType improvementType = null;
    protected WorkerWorks workerWorks = null;


    public CivilizedUnit(CivilizedUnitType civilizedUnitType, Nation ownerNation, Pair location) {
        super(civilizedUnitType.name, civilizedUnitType.cost, civilizedUnitType.MP, civilizedUnitType.resourceType,
                civilizedUnitType.technologyType, civilizedUnitType.turns, civilizedUnitType.hp,ownerNation, location, UnitStatus.AWAKE);
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
