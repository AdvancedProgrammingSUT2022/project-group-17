package Model.Units;
import Model.Improvements.ImprovementType;
import Model.Nations.Nation;
import Model.Pair;
import Model.Units.Enums.CivilizedUnitType;
import Model.Units.Enums.UnitStatus;

public class CivilizedUnit extends Unit {

    protected CivilizedUnitType civilizedUnitType;


    public CivilizedUnit(CivilizedUnitType civilizedUnitType, Nation ownerNation, Pair location) {
        super(civilizedUnitType.name, civilizedUnitType.cost, civilizedUnitType.MP, civilizedUnitType.resourceType,
                civilizedUnitType.technologyType, civilizedUnitType.turns, civilizedUnitType.hp,ownerNation, location, UnitStatus.AWAKE);
        this.civilizedUnitType = civilizedUnitType;
    }

    public CivilizedUnitType getCivilizedUnitType() {
        return civilizedUnitType;
    }
}
