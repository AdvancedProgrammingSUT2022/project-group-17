package Model.Units;
import Model.Nations.Nation;
import Model.Units.Enums.CivilizedUnitType;

public class CivilizedUnit extends Unit {

    protected CivilizedUnitType civilizedUnitType;

    public CivilizedUnit(CivilizedUnitType civilizedUnitType,Nation ownerNation, int x, int y) {
        super(civilizedUnitType.name, civilizedUnitType.cost, civilizedUnitType.MP, civilizedUnitType.resourceType,
                civilizedUnitType.technologyType, civilizedUnitType.turns, civilizedUnitType.hp,ownerNation, x, y);
        this.civilizedUnitType = civilizedUnitType;
    }

    public CivilizedUnitType getCivilizedUnitType() {
        return civilizedUnitType;
    }
}
