package sut.civilization.Controller.GameControllers;

import sut.civilization.Enums.Consts;
import sut.civilization.Model.Classes.*;
import sut.civilization.Model.ModulEnums.CivilizedUnitType;
import sut.civilization.Model.ModulEnums.CloseCombatUnitType;
import sut.civilization.Model.ModulEnums.RangedCombatUnitType;
import sut.civilization.Model.ModulEnums.UnitStatus;
import sut.civilization.View.Graphical.GamePlayController;

import java.util.Objects;
import java.util.regex.Matcher;

public class UnitController extends GameController {

    public void unitGoToNeighbor(Matcher matcher){
        int destX = Integer.parseInt(matcher.group("x"));
        int destY = Integer.parseInt(matcher.group("y"));
        Pair<Integer,Integer> dest = new Pair<Integer,Integer>(destX, destY);

        System.out.println(selectedCombatUnit.getLocation().x + " " + selectedCombatUnit.getLocation().y);

        Pair<Integer,Integer> neighbor = new Pair<Integer,Integer>(destX, destY);

        Game.instance.map[neighbor.x][neighbor.y].setCombatUnit(selectedCombatUnit);
        Game.instance.map[selectedCombatUnit.getLocation().x][selectedCombatUnit.getLocation().y].setCombatUnit(null);
        selectedCombatUnit.setLocation(neighbor);

        System.out.println(selectedCombatUnit.getLocation().x + " " + selectedCombatUnit.getLocation().y);


    }

    public void unitGoToDestination(Matcher matcher, int type){
        int destX = Integer.parseInt(matcher.group("x"));
        int destY = Integer.parseInt(matcher.group("y"));
        Pair<Integer,Integer> dest = new Pair<Integer,Integer>(destX, destY);

        Unit unit;
        if (type == 1)
            unit = selectedCombatUnit;
        else
            unit = selectedCivilizedUnit;
        System.out.println(unit.getLocation().x + " " + unit.getLocation().y);
        main: while (!unit.getLocation().equals(dest)){
            Pair<Integer,Integer>[] neighbors = new Pair[6];
            for (int i = 0; i < 6; i++)
                neighbors[i] = landController.getNeighborIndex(unit.getLocation(), i);

            for (int i = 0; i < 6; i++) {
                if (Pair.isValid(neighbors[i]) && Game.instance.map[neighbors[i].x][neighbors[i].y].getMP() <= unit.getMP() && ((type == 1 && Game.instance.map[neighbors[i].x][neighbors[i].y].getCombatUnit() == null) || (type == 0 && Game.instance.map[neighbors[i].x][neighbors[i].y].getCivilizedUnit() == null))){
                    if (Math.abs(neighbors[i].x - dest.x) < Math.abs(unit.getLocation().x - dest.x) &&
                            Math.abs(neighbors[i].y - dest.y) < Math.abs(unit.getLocation().y - dest.y)){

                        if (type == 1)
                            Game.instance.map[unit.getLocation().x][unit.getLocation().y].setCombatUnit(null);
                        else
                            Game.instance.map[unit.getLocation().x][unit.getLocation().y].setCivilizedUnit(null);
                        unit.setLocation(neighbors[i]);
                        unit.decreaseMP(Game.instance.map[neighbors[i].x][neighbors[i].y].getMP());
                        if (type == 1)
                            Game.instance.map[neighbors[i].x][neighbors[i].y].setCombatUnit((CombatUnit) unit);
                        else
                            Game.instance.map[neighbors[i].x][neighbors[i].y].setCivilizedUnit((CivilizedUnit) unit);
                        continue main;
                    }

                    if (Math.abs(neighbors[i].x - dest.x) < Math.abs(unit.getLocation().x - dest.x) &&
                            Objects.equals(neighbors[i].y, unit.getLocation().y)){

                        if (type == 1)
                            Game.instance.map[unit.getLocation().x][unit.getLocation().y].setCombatUnit(null);
                        else
                            Game.instance.map[unit.getLocation().x][unit.getLocation().y].setCivilizedUnit(null);
                        unit.setLocation(neighbors[i]);
                        unit.decreaseMP(Game.instance.map[neighbors[i].x][neighbors[i].y].getMP());
                        if (type == 1)
                            Game.instance.map[neighbors[i].x][neighbors[i].y].setCombatUnit((CombatUnit) unit);
                        else
                            Game.instance.map[neighbors[i].x][neighbors[i].y].setCivilizedUnit((CivilizedUnit) unit);
                        continue main;
                    }

                    if (Math.abs(neighbors[i].y - dest.y) < Math.abs(selectedCombatUnit.getLocation().y - dest.y) &&
                            neighbors[i].x == selectedCombatUnit.getLocation().x){


                        if (type == 1)
                            Game.instance.map[unit.getLocation().x][unit.getLocation().y].setCombatUnit(null);
                        else
                            Game.instance.map[unit.getLocation().x][unit.getLocation().y].setCivilizedUnit(null);
                        unit.setLocation(neighbors[i]);
                        unit.decreaseMP(Game.instance.map[neighbors[i].x][neighbors[i].y].getMP());
                        if (type == 1)
                            Game.instance.map[neighbors[i].x][neighbors[i].y].setCombatUnit((CombatUnit) unit);
                        else
                            Game.instance.map[neighbors[i].x][neighbors[i].y].setCivilizedUnit((CivilizedUnit) unit);
                        continue main;
                    }
                } else if (Pair.isValid(neighbors[i]) && Game.instance.map[neighbors[i].x][neighbors[i].y].getMP() > unit.getMP())
                    System.out.println("your move Points is not enough!");

            }

            break;
        }

        System.out.println(unit.getLocation().x + " " + unit.getLocation().y);
    }

    public static String unitSetPath(int x, int y, int selection){
        Unit selectedUnit;
        if (selection == 1)
            selectedUnit = selectedCombatUnit;
        else
            selectedUnit = selectedCivilizedUnit;
//        if (selectedUnit.getUnitStatus() == UnitStatus.WORKING) return "The unit is busy now!";
//        int x = Integer.parseInt(matcher.group("x"));
//        int y = Integer.parseInt(matcher.group("y"));
        Land origin = Game.instance.map[selectedUnit.getLocation().x][selectedUnit.getLocation().y];
        Land dest = Game.instance.map[x][y];
        int originNum = LandController.getLandNumber(origin);
        int destNum = LandController.getLandNumber(dest);
        if (selection == 1 && dest.getCombatUnit() != null)
            return "There already is a combat unit in destination";
        if (selection == 0 && dest.getCivilizedUnit() != null)
            return "There already is a civilized unit in destination";
        if (Game.instance.dist[originNum][destNum] < 1000) {
            selectedUnit.setPath(Game.instance.path[originNum][destNum]);
//            System.out.println(selectedUnit.getPath());
        }
        else
            return "Unit can't reach there";
        while (selectedUnit.getMP() > 0)
            unitGoForward(selectedUnit);
        GamePlayController.updateWholeMap();
        return "Unit moved successfully";
    }

    public static void unitGoForward(Unit unit){
        String path = unit.getPath();
        int neighbor = 0;

        if (path != null && !path.equals("")) neighbor = Integer.parseInt(String.valueOf(path.charAt(0)));
        else {System.out.println("path is empty"); return;}

        boolean river = Game.instance.map[unit.getLocation().x][unit.getLocation().y].getHasRiver()[neighbor];
        Pair<Integer,Integer> next = LandController.getNeighborIndex(unit.getLocation(), neighbor);
        if (unit instanceof CombatUnit){
            Game.instance.map[unit.getLocation().x][unit.getLocation().y].setCombatUnit(null);
            Game.instance.map[next.x][next.y].setCombatUnit((CombatUnit) unit);
        }else{
            Game.instance.map[unit.getLocation().x][unit.getLocation().y].setCivilizedUnit(null);
            Game.instance.map[next.x][next.y].setCivilizedUnit((CivilizedUnit) unit);
        }
        unit.setLocation(next);

        if (Game.instance.map[next.x][next.y].getRuin() != null)
            unitRetrieveRuin(unit);

        unit.setMP(Math.max(0, unit.getMP() - Game.instance.map[next.x][next.y].getMP()));
        if (Game.instance.map[next.x][next.y].getLandFeature() != null)
            unit.setMP(-Game.instance.map[next.x][next.y].getLandFeature().getLandFeatureType().movementCost);
        if (Game.instance.map[next.x][next.y].getZOC() != null){
            if (!Game.instance.map[next.x][next.y].getZOC().getOwnerNation().equals(unit.getOwnerNation())){
                unit.setMP(0);
            }
        }
        if (river)
            unit.setMP(0);
        unit.setPath(path.substring(1));
        unit.setWaitingForCommand(false);
        unit.setUnitStatus(UnitStatus.MOVING);
    }

    public static String unitSleep() {
        Unit unit;
        if ((unit = selectedCivilizedUnit) != null || (unit = selectedCombatUnit) != null) {
            if (unit.getUnitStatus() != UnitStatus.WORKING && unit.getUnitStatus() != UnitStatus.SLEEP) {
                unit.setUnitStatus(UnitStatus.SLEEP);
                unit.setWaitingForCommand(false);
                return "Slept successfully!";
            } else return "can't sleep!";
        }
        return "Select a unit first!";
    }

    public static String combatUnitAction(String actionName) {
        //fortify, alert, fortify until heal
        UnitStatus toBeAppliedStatus = null;
        for (UnitStatus unitStatus : UnitStatus.values()) {
            if (unitStatus.toString().equalsIgnoreCase(actionName))
                toBeAppliedStatus = unitStatus;
        }
        if (toBeAppliedStatus != null) {
            if (selectedCombatUnit != null) {
                selectedCombatUnit.setUnitStatus(toBeAppliedStatus);
                selectedCombatUnit.setWaitingForCommand(false);
                return "Actioned successfully!";
            }
            return "Select a combat unit first!";
        }
        return "action name isn't correct";
    }

    public void unitGarrison() {

    }

    public void unitCancelMission() {

    }

    public static String unitWake() {
        Unit unit;
        if ((unit = selectedCivilizedUnit) != null || (unit = selectedCombatUnit) != null) {
            if (unit.getUnitStatus() == UnitStatus.SLEEP) {
                unit.setUnitStatus(UnitStatus.AWAKE);
                unit.setWaitingForCommand(true);
                return "Waked successfully!";
            }
            return "This unit isn't asleep!";
        }
        return "Select a unit first!";
    }

    public String unitDelete() {
        Unit unit;
        if ((unit = selectedCivilizedUnit) != null || (unit = selectedCombatUnit) != null) {
            currentTurnUser.getNation().getUnits().remove(unit);
            if (unit instanceof CivilizedUnit) {
                Game.instance.map[unit.getLocation().x][unit.getLocation().y].setCivilizedUnit(null);
                selectedCivilizedUnit = null;
            }
            else {
                Game.instance.map[unit.getLocation().x][unit.getLocation().y].setCombatUnit(null);
                selectedCombatUnit = null;
            }
            return "removed successfully!";
        }
        return "Select a unit first!";
    }

    public String unitSetCityTarget(Matcher matcher){
        int cityX = Integer.parseInt(matcher.group("x"));
        int cityY = Integer.parseInt(matcher.group("y"));
        City city = Game.instance.map[cityX][cityY].getOwnerCity();
        if (city != null && selectedCombatUnit != null){
            if ((selectedCombatUnit instanceof CloseCombatUnit &&
                    landController.areNeighbors(new Pair<Integer,Integer>(cityX, cityY), selectedCombatUnit.getLocation()))){
                if (!selectedCombatUnit.getOwnerNation().equals(city.getOwnerNation())){
                    selectedCombatUnit.setTargetCity(city);
                }
                else{
                    return "Can't attack owner nation's city";
                }
            }else if(selectedCombatUnit instanceof  RangedCombatUnit){
                int unitNum = landController.getLandNumber(Game.instance.map[selectedCombatUnit.getLocation().x][selectedCombatUnit.getLocation().y]);
                int cityNum = landController.getLandNumber(city.getMainLand());
                if (((RangedCombatUnit) selectedCombatUnit).getRangedCombatUnitType().range <= Game.instance.dist[unitNum][cityNum]){
                    if (!selectedCombatUnit.getOwnerNation().equals(city.getOwnerNation())){
                        selectedCombatUnit.setTargetCity(city);
                    }
                    else{
                        return "Can't attack owner nation's city";
                    }
                }else
                    return "Not in range";
            }
        }
        if (selectedCombatUnit instanceof RangedCombatUnit){
            if (((RangedCombatUnit) selectedCombatUnit).getRangedCombatUnitType().isSiege){
                return "Siege unit stationed successfully";
            }
        }
        unitAttackCity(selectedCombatUnit);
        return "Attack successful";
    }

    public String unitSetCombatUnitTarget(Matcher matcher){
        int unitX = Integer.parseInt(matcher.group("x"));
        int unitY = Integer.parseInt(matcher.group("y"));
        Unit underAttack = Game.instance.map[unitX][unitY].getCombatUnit();

        if (selectedCombatUnit == null) return "You have to select a combat unit first";
        if (underAttack == null)    return "There is no combat unit on this land";

        if ((selectedCombatUnit instanceof CloseCombatUnit &&
                landController.areNeighbors(new Pair<Integer,Integer>(unitX, unitY), selectedCombatUnit.getLocation()))){
            if (!selectedCombatUnit.getOwnerNation().equals(underAttack.getOwnerNation())){
                selectedCombatUnit.setTargetUnit(underAttack);
            }
            else{
                return "Can't attack owner nation's unit";
            }
        }else if(selectedCombatUnit instanceof  RangedCombatUnit){
            int unitNum = landController.getLandNumber(Game.instance.map[selectedCombatUnit.getLocation().x][selectedCombatUnit.getLocation().y]);
            int underAttackNum = landController.getLandNumber(Game.instance.map[underAttack.getLocation().x][underAttack.getLocation().y]);
            if (((RangedCombatUnit) selectedCombatUnit).getRangedCombatUnitType().range <= Game.instance.dist[unitNum][underAttackNum]){
                if (!selectedCombatUnit.getOwnerNation().equals(underAttack.getOwnerNation())){
                    selectedCombatUnit.setTargetUnit(underAttack);
                }
                else{
                    return "Can't attack owner nation's unit";
                }
            }else
                return "Not in range";
        }

        if (selectedCombatUnit instanceof RangedCombatUnit){
            if (((RangedCombatUnit) selectedCombatUnit).getRangedCombatUnitType().isSiege){
                return "Siege unit stationed successfully";
            }
        }

        unitAttackUnit(selectedCombatUnit);
        return "Attack on unit successful";
    }

    public void unitAttackCity(CombatUnit combatUnit){
        if (combatUnit instanceof CloseCombatUnit){
            combatUnit.setHp(combatUnit.getHp() - combatUnit.getTargetCity().getCombatStrength());
            combatUnit.getTargetCity().setHP(combatUnit.getTargetCity().getHP() - combatUnit.getCombatStrength());
        }else if (combatUnit instanceof RangedCombatUnit){
            combatUnit.getTargetCity().setHP(combatUnit.getTargetCity().getHP() - ((RangedCombatUnit) combatUnit).getRangedStrength());
        }
        if (combatUnit.getHp() <= 0){
            unitDeath(combatUnit);
        }
        if (combatUnit.getTargetCity().getHP() <= 0){
            CityController.cityDeath(combatUnit.getTargetCity());
        }
        combatUnit.setTargetCity(null);
    }

    public void unitAttackUnit(CombatUnit combatUnit){
        Unit target = combatUnit.getTargetUnit();
        if (combatUnit instanceof CloseCombatUnit){
            if (target instanceof CloseCombatUnit){
                combatUnit.setHp(combatUnit.getHp() - ((CloseCombatUnit) target).getCombatStrength());
                target.setHp(target.getHp() - combatUnit.getCombatStrength());
                if (combatUnit.getHp() <= 0){
                    unitDeath(combatUnit);
                }
                if (target.getHp() <= 0){
                    unitDeath(target);
                }
            }else if (target instanceof RangedCombatUnit){
                combatUnit.setHp(combatUnit.getHp() - ((RangedCombatUnit) target).getCombatStrength());
                target.setHp(target.getHp() - combatUnit.getCombatStrength());
                if (combatUnit.getHp() <= 0){
                    unitDeath(combatUnit);
                }
                if (target.getHp() <= 0){
                    unitDeath(target);
                }
            }else if (target instanceof CivilizedUnit){
                Nation previousOwner = target.getOwnerNation();
                previousOwner.getUnits().remove(target);
                combatUnit.getOwnerNation().addUnit(target);
                target.setOwnerNation(combatUnit.getOwnerNation());
            }
        }else if (combatUnit instanceof RangedCombatUnit){
            if (target instanceof CloseCombatUnit){
                combatUnit.setHp(combatUnit.getHp() - ((CloseCombatUnit) target).getCombatStrength());
                target.setHp(target.getHp() - ((RangedCombatUnit) combatUnit).getRangedStrength());
                if (combatUnit.getHp() <= 0){
                    unitDeath(combatUnit);
                }
                if (target.getHp() <= 0){
                    unitDeath(target);
                }
            }else if (target instanceof RangedCombatUnit){
                combatUnit.setHp(combatUnit.getHp() - ((RangedCombatUnit) target).getCombatStrength());
                target.setHp(target.getHp() - ((RangedCombatUnit) combatUnit).getRangedStrength());
                if (combatUnit.getHp() <= 0){
                    unitDeath(combatUnit);
                }
                if (target.getHp() <= 0){
                    unitDeath(target);
                }
            }else if (target instanceof CivilizedUnit){
                Nation previousOwner = target.getOwnerNation();
                previousOwner.getUnits().remove(target);
                combatUnit.getOwnerNation().addUnit(target);
                target.setOwnerNation(combatUnit.getOwnerNation());
            }
        }

        combatUnit.setTargetUnit(null);
    }

    public static void unitDeath(Unit unit){
        Nation nation = unit.getOwnerNation();
        nation.removeUnit(unit);
        unit = null;
        System.gc();
    }

    public String purchaseUnit(Matcher matcher){
        String type = matcher.group("type");
        String name = matcher.group("name");
        if (selectedCity == null){
            return "You have to select a city first";
        }

        int x = -1, y = -1;
        main: for (int i = 0; i < Consts.MAP_SIZE.amount.x; i++) {
            for (int j = 0; j < Consts.MAP_SIZE.amount.y; j++) {
                if (Game.instance.map[i][j].equals(selectedCity.getMainLand())){
                    x = i;
                    y = j;
                    break main;
                }
            }
        }

        if (type.equals("civilized unit")){
            if (selectedCity.getMainLand().getCivilizedUnit() != null){
                return "There is already a civilized unit in this location";
            }
            for (CivilizedUnitType civilizedUnitType : CivilizedUnitType.values()) {
                if (civilizedUnitType.name.equals(name)){
                    if (civilizedUnitType.cost > selectedCity.getOwnerNation().getCoin().getBalance()){
                        return "Not enough coins";
                    }
                    CivilizedUnit newCivilizedUnit = new CivilizedUnit(civilizedUnitType,
                            selectedCity.getOwnerNation(), new Pair<Integer,Integer>(x, y));
                    selectedCity.getMainLand().setCivilizedUnit(newCivilizedUnit);
                    selectedCity.getOwnerNation().addUnit(newCivilizedUnit);
                    selectedCity.getOwnerNation().getCoin().addBalance(-civilizedUnitType.cost);
                    return "Civilized unit purchased successfully";
                }
            }
        }

        if (type.equals("close combat unit")){
            if (selectedCity.getMainLand().getCombatUnit() != null){
                return "There is already a combat unit in this location";
            }
            for (CloseCombatUnitType closeCombatUnitType : CloseCombatUnitType.values()) {
                if (closeCombatUnitType.name.equals(name)){
                    if (closeCombatUnitType.cost > selectedCity.getOwnerNation().getCoin().getBalance()){
                        return "Not enough coins";
                    }
                    CloseCombatUnit newCloseCombatUnit = new CloseCombatUnit(closeCombatUnitType,
                            selectedCombatUnit.getOwnerNation(), new Pair<Integer,Integer>(x, y));
                    selectedCity.getMainLand().setCombatUnit(newCloseCombatUnit);
                    selectedCombatUnit.getOwnerNation().addUnit(newCloseCombatUnit);
                    selectedCity.getOwnerNation().getCoin().addBalance(-closeCombatUnitType.cost);
                    return "Close combat unit purchased successfully";
                }
            }
        }

        if (type.equals("ranged combat unit")){
            if (selectedCity.getMainLand().getCombatUnit() != null){
                return "There is already a combat unit in this location";
            }
            for (RangedCombatUnitType rangedCombatUnitType : RangedCombatUnitType.values()) {
                if (rangedCombatUnitType.cost > selectedCity.getOwnerNation().getCoin().getBalance()){
                    return "Not enough coins";
                }
                RangedCombatUnit newRangedCombatUnit = new RangedCombatUnit(rangedCombatUnitType,
                        selectedCombatUnit.getOwnerNation(), new Pair<Integer,Integer>(x, y));
                selectedCity.getMainLand().setCombatUnit(newRangedCombatUnit);
                selectedCombatUnit.getOwnerNation().addUnit(newRangedCombatUnit);
                selectedCity.getOwnerNation().getCoin().addBalance(-rangedCombatUnitType.cost);
                return "Ranged combat unit purchased successfully";
            }
        }


        return "invalid command";
    }

    //fixme not using this function
    public String unitStartCreation(Matcher matcher){
        String type = matcher.group("type");
        String name = matcher.group("name");
        if (selectedCity == null){
            return "You have to select a city first";
        }

        if (selectedCity.hasAnInProgressUnit()){
            return "The city is already creating a unit";
        }

        if (type.equals("civilized unit")){
            if (selectedCity.getMainLand().getCivilizedUnit() != null){
                return "There is already a civilized unit in this location";
            }
            for (CivilizedUnitType civilizedUnitType : CivilizedUnitType.values()) {
                if (civilizedUnitType.name.equals(name)){
//                    selectedCity.setInProgressCivilizedUnit(civilizedUnitType);
                    selectedCity.setNextUnitTurns(civilizedUnitType.turns);
                    selectedCity.setHasAnInProgressUnit(true);
                    return "Civilized unit is set for creation successfully";
                }
            }
        }

        if (type.equals("close combat unit")){
            if (selectedCity.getMainLand().getCombatUnit() != null){
                return "There is already a combat unit in this location";
            }
            for (CloseCombatUnitType closeCombatUnitType : CloseCombatUnitType.values()) {
                if (closeCombatUnitType.name.equals(name)){
                    if (closeCombatUnitType.resourceType != null && selectedCity.getOwnerNation().getResourceCellar().get(closeCombatUnitType.resourceType) == 0){
                        return "Not enough resources";
                    }
                    if (closeCombatUnitType.technologyType != null && selectedCity.getOwnerNation().getTechnologies().get(closeCombatUnitType.technologyType) == false){
                        return "You don't have the required technology";
                    }
                    if (closeCombatUnitType.resourceType != null){
                        selectedCity.getOwnerNation().removeResource(closeCombatUnitType.resourceType);
                    }
//                    selectedCity.setInProgressCloseCombatUnit(closeCombatUnitType);
                    selectedCity.setNextUnitTurns(closeCombatUnitType.turns);
                    selectedCity.setHasAnInProgressUnit(true);
                    return "Close combat unit is set for creation successfully";
                }
            }
        }

        if (type.equals("ranged combat unit")){
            if (selectedCity.getMainLand().getCombatUnit() != null){
                return "There is already a combat unit in this location";
            }
            for (RangedCombatUnitType rangedCombatUnitType : RangedCombatUnitType.values()) {
                if (rangedCombatUnitType.resourceType != null && selectedCity.getOwnerNation().getResourceCellar().get(rangedCombatUnitType.resourceType) == 0){
                    return "Not enough resources";
                }
                if (rangedCombatUnitType.technologyType != null && selectedCity.getOwnerNation().getTechnologies().get(rangedCombatUnitType.technologyType) == false){
                    return "You don't have the required technology";
                }
                if (rangedCombatUnitType.resourceType != null){
                    selectedCity.getOwnerNation().removeResource(rangedCombatUnitType.resourceType);
                }
//                selectedCity.setInProgressRangedCombatUnit(rangedCombatUnitType);
                selectedCity.setNextUnitTurns(rangedCombatUnitType.turns);
                selectedCity.setHasAnInProgressUnit(true);
                return "Ranged combat unit is set for creation successfully";
            }
        }

        return "invalid command";
    }

    public static void unitCreate(City city){
        int x = -1, y = -1;
        main: for (int i = 0; i < Consts.MAP_SIZE.amount.x; i++) {
            for (int j = 0; j < Consts.MAP_SIZE.amount.y; j++) {
                if (Game.instance.map[i][j].equals(city.getMainLand())){
                    x = i;
                    y = j;
                    break main;
                }
            }
        }

        if (city.getInProgressCivilizedUnit() != null){
//            CivilizedUnit newCivilizedUnit = new CivilizedUnit(city.getInProgressCivilizedUnit(),
//                    city.getOwnerNation(), new Pair<Integer,Integer>(x, y));
            CivilizedUnit newCivilizedUnit = city.getInProgressCivilizedUnit();
            city.getMainLand().setCivilizedUnit(newCivilizedUnit);
            city.getOwnerNation().addUnit(newCivilizedUnit);
            city.setInProgressCivilizedUnit(null);
        }

        if (city.getInProgressCloseCombatUnit() != null){
//            CloseCombatUnit newCloseCombatUnit = new CloseCombatUnit(city.getInProgressCloseCombatUnit(),
//                    city.getOwnerNation(), new Pair<Integer,Integer>(x, y));
            CloseCombatUnit newCloseCombatUnit = city.getInProgressCloseCombatUnit();
            city.getMainLand().setCombatUnit(newCloseCombatUnit);
            city.getOwnerNation().addUnit(newCloseCombatUnit);
            city.setInProgressCloseCombatUnit(null);
        }

        if (city.getInProgressRangedCombatUnit() != null){
//            RangedCombatUnit newRangedCombatUnit = new RangedCombatUnit(city.getInProgressRangedCombatUnit(),
//                    city.getOwnerNation(), new Pair<Integer,Integer>(x, y));
            RangedCombatUnit newRangedCombatUnit = city.getInProgressRangedCombatUnit();
            city.getMainLand().setCombatUnit(newRangedCombatUnit);
            city.getOwnerNation().addUnit(newRangedCombatUnit);
            city.setInProgressRangedCombatUnit(null);
        }

        city.setHasAnInProgressUnit(false);
    }

    public static void unitRetrieveRuin(Unit unit){
        Land land = Game.instance.map[unit.getLocation().x][unit.getLocation().y];
        Ruin ruin = land.getRuin();
        Nation nation = unit.getOwnerNation();
        ruin.retrieve();
        nation.getCoin().addBalance(ruin.getGoldAmount());
        land.setRuin(null);
    }
}