package sut.civilization.Controller.GameControllers;

import sut.civilization.Enums.Consts;
import sut.civilization.Model.Classes.*;
import sut.civilization.Model.ModulEnums.*;
import sut.civilization.View.Graphical.GamePlayController;

import java.util.Objects;
import java.util.regex.Matcher;

public class UnitController extends GameController {

    public void unitGoToNeighbor(Matcher matcher) {
        int destX = Integer.parseInt(matcher.group("x"));
        int destY = Integer.parseInt(matcher.group("y"));
        Pair<Integer, Integer> dest = new Pair<Integer, Integer>(destX, destY);

        System.out.println(selectedCombatUnit.getLocation().x + " " + selectedCombatUnit.getLocation().y);

        Pair<Integer, Integer> neighbor = new Pair<Integer, Integer>(destX, destY);

        Game.instance.map[neighbor.x][neighbor.y].setCombatUnit(selectedCombatUnit);
        Game.instance.map[selectedCombatUnit.getLocation().x][selectedCombatUnit.getLocation().y].setCombatUnit(null);
        selectedCombatUnit.setLocation(neighbor);

        System.out.println(selectedCombatUnit.getLocation().x + " " + selectedCombatUnit.getLocation().y);


    }

    public void unitGoToDestination(Matcher matcher, int type) {
        int destX = Integer.parseInt(matcher.group("x"));
        int destY = Integer.parseInt(matcher.group("y"));
        Pair<Integer, Integer> dest = new Pair<Integer, Integer>(destX, destY);

        Unit unit;
        if (type == 1)
            unit = selectedCombatUnit;
        else
            unit = selectedCivilizedUnit;
        System.out.println(unit.getLocation().x + " " + unit.getLocation().y);
        main:
        while (!unit.getLocation().equals(dest)) {
            Pair<Integer, Integer>[] neighbors = new Pair[6];
            for (int i = 0; i < 6; i++)
                neighbors[i] = landController.getNeighborIndex(unit.getLocation(), i);

            for (int i = 0; i < 6; i++) {
                if (Pair.isValid(neighbors[i]) && Game.instance.map[neighbors[i].x][neighbors[i].y].getMP() <= unit.getMP() && ((type == 1 && Game.instance.map[neighbors[i].x][neighbors[i].y].getCombatUnit() == null) || (type == 0 && Game.instance.map[neighbors[i].x][neighbors[i].y].getCivilizedUnit() == null))) {
                    if (Math.abs(neighbors[i].x - dest.x) < Math.abs(unit.getLocation().x - dest.x) &&
                            Math.abs(neighbors[i].y - dest.y) < Math.abs(unit.getLocation().y - dest.y)) {

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
                            Objects.equals(neighbors[i].y, unit.getLocation().y)) {

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
                            neighbors[i].x == selectedCombatUnit.getLocation().x) {


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

    public static String unitSetPath(int x, int y, int selection) {
        Unit selectedUnit;
        if (selection == 1)
            selectedUnit = selectedCombatUnit;
        else
            selectedUnit = selectedCivilizedUnit;
        if (selectedUnit != null &&
                selectedUnit.getUnitStatus() == UnitStatus.WORKING) return "The unit is busy now!";
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
        } else
            return "Unit can't reach there";
        while (selectedUnit.getMP() > 0 && !selectedUnit.getPath().equals("") && selectedUnit.getPath() != null)
            unitGoForward(selectedUnit);
        GamePlayController.getInstance().updateWholeMap();
        return "Unit moved successfully";
    }

    public static void unitGoForward(Unit unit) {
        String path = unit.getPath();
        int neighbor = 0;

        if (path != null && !path.equals(""))
            neighbor = Integer.parseInt(String.valueOf(path.charAt(0)));
        else {
            System.out.println("path is empty");
            return;
        }

        boolean river = Game.instance.map[unit.getLocation().x][unit.getLocation().y].getHasRiver()[neighbor];
        Pair<Integer, Integer> next = LandController.getNeighborIndex(unit.getLocation(), neighbor);
        if (unit instanceof CombatUnit) {
            Game.instance.map[unit.getLocation().x][unit.getLocation().y].setCombatUnit(null);
            Game.instance.map[next.x][next.y].setCombatUnit((CombatUnit) unit);
        } else {
            Game.instance.map[unit.getLocation().x][unit.getLocation().y].setCivilizedUnit(null);
            Game.instance.map[next.x][next.y].setCivilizedUnit((CivilizedUnit) unit);
        }
        unit.setLocation(next);

        if (Game.instance.map[next.x][next.y].getRuin() != null)
            unitRetrieveRuin(unit);

        unit.setMP(Math.max(0, unit.getMP() - Game.instance.map[next.x][next.y].getMP()));
        if (Game.instance.map[next.x][next.y].getLandFeature() != null)
            unit.setMP(Math.max(0, unit.getMP() - Game.instance.map[next.x][next.y].getLandFeature().getLandFeatureType().movementCost));
        if (Game.instance.map[next.x][next.y].getZOC() != null){
            if (!Game.instance.map[next.x][next.y].getZOC().getOwnerNation().equals(unit.getOwnerNation())){
                unit.setMP(0);
            }
        }
        if (river)
            unit.setMP(0);
        unit.setPath(path.substring(1));
//        unit.setWaitingForCommand(false);
        unit.setUnitStatus(UnitStatus.MOVING);
    }

    public static String unitSleep() {
        Unit unit;
        if ((unit = selectedCivilizedUnit) != null || (unit = selectedCombatUnit) != null) {
            if (unit.getUnitStatus() != UnitStatus.WORKING && unit.getUnitStatus() != UnitStatus.SLEEP) {
                unit.setUnitStatus(UnitStatus.SLEEP);
//                unit.setWaitingForCommand(false);
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
//                selectedCombatUnit.setWaitingForCommand(false);
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
//                unit.setWaitingForCommand(true);
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
            } else {
                Game.instance.map[unit.getLocation().x][unit.getLocation().y].setCombatUnit(null);
                selectedCombatUnit = null;
            }
            return "removed successfully!";
        }
        return "Select a unit first!";
    }

    public static String unitSetCityTarget(City city) {
        if (city != null && selectedCombatUnit != null) {
            if (selectedCombatUnit.getUnitStatus() == UnitStatus.ATTACKING)
                return "The unit has already attacked!";
            if ((selectedCombatUnit instanceof CloseCombatUnit &&
                    LandController.areNeighbors(new Pair<>(city.getMainLand().getI(), city.getMainLand().getJ()), selectedCombatUnit.getLocation()))) {
                if (!selectedCombatUnit.getOwnerNation().equals(city.getOwnerNation())) {
                    selectedCombatUnit.setTargetCity(city);
                } else {
                    return "Can't attack owner nation's city";
                }
            } else if (selectedCombatUnit instanceof RangedCombatUnit) {
                int unitNum = LandController.getLandNumber(Game.instance.map[selectedCombatUnit.getLocation().x][selectedCombatUnit.getLocation().y]);
                int cityNum = LandController.getLandNumber(city.getMainLand());
                if (((RangedCombatUnit) selectedCombatUnit).getRangedCombatUnitType().range <= Game.instance.dist[unitNum][cityNum]) {
                    if (!selectedCombatUnit.getOwnerNation().equals(city.getOwnerNation())) {
                        selectedCombatUnit.setTargetCity(city);
                    } else {
                        return "Can't attack owner nation's city";
                    }
                } else
                    return "Not in range";
            }
        }
        if (selectedCombatUnit instanceof RangedCombatUnit) {
            if (((RangedCombatUnit) selectedCombatUnit).getRangedCombatUnitType().isSiege) {
                return "Siege unit stationed successfully";
            }
        }
        unitAttackCity(selectedCombatUnit);
        selectedCombatUnit.setUnitStatus(UnitStatus.ATTACKING);
//        selectedCombatUnit.setWaitingForCommand(false);
        return "Attack successful";
    }

    public static String unitSetCombatUnitTarget(Unit underAttack) {

        if (selectedCombatUnit == null) return "You have to select a combat unit first";
        if (underAttack == null) return "There is no combat unit on this land";
        if (selectedCombatUnit.getUnitStatus() == UnitStatus.ATTACKING)
            return "The unit has already attacked!";

        if ((selectedCombatUnit instanceof CloseCombatUnit &&
                LandController.areNeighbors(new Pair<>(underAttack.getLocation().x, underAttack.getLocation().y), selectedCombatUnit.getLocation()))) {
            if (!selectedCombatUnit.getOwnerNation().equals(underAttack.getOwnerNation())) {
                selectedCombatUnit.setTargetUnit(underAttack);
            } else {
                return "Can't attack owner nation's unit";
            }
        } else if (selectedCombatUnit instanceof RangedCombatUnit) {
            int unitNum = LandController.getLandNumber(Game.instance.map[selectedCombatUnit.getLocation().x][selectedCombatUnit.getLocation().y]);
            int underAttackNum = LandController.getLandNumber(Game.instance.map[underAttack.getLocation().x][underAttack.getLocation().y]);
            if (((RangedCombatUnit) selectedCombatUnit).getRangedCombatUnitType().range <= Game.instance.dist[unitNum][underAttackNum]) {
                if (!selectedCombatUnit.getOwnerNation().equals(underAttack.getOwnerNation())) {
                    selectedCombatUnit.setTargetUnit(underAttack);
                } else {
                    return "Can't attack owner nation's unit";
                }
            } else
                return "Not in range";
        }

        if (selectedCombatUnit instanceof RangedCombatUnit) {
            if (((RangedCombatUnit) selectedCombatUnit).getRangedCombatUnitType().isSiege) {
                return "Siege unit stationed successfully";
            }
        }

        unitAttackUnit(selectedCombatUnit);
        selectedCombatUnit.setUnitStatus(UnitStatus.ATTACKING);
//        selectedCombatUnit.setWaitingForCommand(false);
        return "Attack on unit successful";
    }

    public static void unitAttackCity(CombatUnit combatUnit) {
        if (combatUnit instanceof CloseCombatUnit) {
            combatUnit.setHp(combatUnit.getHp() - combatUnit.getTargetCity().getCombatStrength());
            combatUnit.getTargetCity().setHP(combatUnit.getTargetCity().getHP() - combatUnit.getCombatStrength());
        } else if (combatUnit instanceof RangedCombatUnit) {
            combatUnit.getTargetCity().setHP(combatUnit.getTargetCity().getHP() - ((RangedCombatUnit) combatUnit).getRangedStrength());
        }
        if (combatUnit.getHp() <= 0) {
            unitDeath(combatUnit);
        }
        if (combatUnit.getTargetCity().getHP() <= 0) {
            CityController.cityDeath(combatUnit.getTargetCity());
        }
        combatUnit.setTargetCity(null);
    }

    public static void unitAttackUnit(CombatUnit combatUnit) {
        Unit target = combatUnit.getTargetUnit();
        if (combatUnit instanceof CloseCombatUnit) {
            if (target instanceof CloseCombatUnit) {
                combatUnit.setHp(combatUnit.getHp() - ((CloseCombatUnit) target).getCombatStrength());
                target.setHp(target.getHp() - combatUnit.getCombatStrength());
                if (combatUnit.getHp() <= 0) {
                    unitDeath(combatUnit);
                }
                if (target.getHp() <= 0) {
                    unitDeath(target);
                }
            }else if (target instanceof RangedCombatUnit){
                target.setHp(target.getHp() - combatUnit.getCombatStrength());
                if (combatUnit.getHp() <= 0) {
                    unitDeath(combatUnit);
                }
                if (target.getHp() <= 0) {
                    unitDeath(target);
                }
            } else if (target instanceof CivilizedUnit) {
                Nation previousOwner = target.getOwnerNation();
                previousOwner.getUnits().remove(target);
                combatUnit.getOwnerNation().addUnit(target);
                target.setOwnerNation(combatUnit.getOwnerNation());
            }
        } else if (combatUnit instanceof RangedCombatUnit) {
            if (target instanceof CloseCombatUnit) {
                combatUnit.setHp(combatUnit.getHp() - ((CloseCombatUnit) target).getCombatStrength());
                target.setHp(target.getHp() - ((RangedCombatUnit) combatUnit).getRangedStrength());
                if (combatUnit.getHp() <= 0) {
                    unitDeath(combatUnit);
                }
                if (target.getHp() <= 0) {
                    unitDeath(target);
                }
            }else if (target instanceof RangedCombatUnit){
                target.setHp(target.getHp() - ((RangedCombatUnit) combatUnit).getRangedStrength());
                if (combatUnit.getHp() <= 0) {
                    unitDeath(combatUnit);
                }
                if (target.getHp() <= 0) {
                    unitDeath(target);
                }
            } else if (target instanceof CivilizedUnit) {
                Nation previousOwner = target.getOwnerNation();
                previousOwner.getUnits().remove(target);
                combatUnit.getOwnerNation().addUnit(target);
                target.setOwnerNation(combatUnit.getOwnerNation());
            }
        }

        combatUnit.setTargetUnit(null);
    }

    public static void unitDeath(Unit unit) {
        unit.getOwnerNation().getUnits().remove(unit);
        if (unit instanceof CivilizedUnit) {
            Game.instance.map[unit.getLocation().x][unit.getLocation().y].setCivilizedUnit(null);
        } else {
            Game.instance.map[unit.getLocation().x][unit.getLocation().y].setCombatUnit(null);
        }
    }

    public static String purchaseProduct(String type, String name) {
        if (selectedCity == null) {
            return "You have to select a city first";
        }

        Land mainLand = selectedCity.getMainLand();
        int x = mainLand.getI();
        int y = mainLand.getJ();

        if (type.equals("civilized unit")) {
            if (mainLand.getCivilizedUnit() != null) {
                return "There is already a civilized unit in this location";
            }
            for (CivilizedUnitType civilizedUnitType : CivilizedUnitType.values()) {
                if (civilizedUnitType.name.equals(name)) {
                    if (civilizedUnitType.cost > selectedCity.getOwnerNation().getCoin().getBalance()) {
                        return "Not enough coins";
                    }
                    CivilizedUnit newCivilizedUnit = new CivilizedUnit(civilizedUnitType,
                            selectedCity.getOwnerNation(), new Pair<>(x, y));
                    mainLand.setCivilizedUnit(newCivilizedUnit);
                    selectedCity.getOwnerNation().addUnit(newCivilizedUnit);
                    selectedCity.getOwnerNation().getCoin().addBalance(-civilizedUnitType.cost);
                    return "Civilized unit purchased successfully";
                }
            }
        }

        if (type.equals("close combat unit")) {
            if (mainLand.getCombatUnit() != null) {
                return "There is already a combat unit in this location";
            }
            for (CloseCombatUnitType closeCombatUnitType : CloseCombatUnitType.values()) {
                if (closeCombatUnitType.name.equals(name)) {
                    if (closeCombatUnitType.cost > selectedCity.getOwnerNation().getCoin().getBalance()) {
                        return "Not enough coins";
                    }
                    CloseCombatUnit newCloseCombatUnit = new CloseCombatUnit(closeCombatUnitType,
                            selectedCity.getOwnerNation(), new Pair<>(x, y));
                    mainLand.setCombatUnit(newCloseCombatUnit);
                    selectedCity.getOwnerNation().addUnit(newCloseCombatUnit);
                    selectedCity.getOwnerNation().getCoin().addBalance(-closeCombatUnitType.cost);
                    return "Close combat unit purchased successfully";
                }
            }
        }

        if (type.equals("ranged combat unit")) {
            if (mainLand.getCombatUnit() != null) {
                return "There is already a combat unit in this location";
            }
            for (RangedCombatUnitType rangedCombatUnitType : RangedCombatUnitType.values()) {
                if (rangedCombatUnitType.name.equals(name)) {
                    if (rangedCombatUnitType.cost > selectedCity.getOwnerNation().getCoin().getBalance()) {
                        return "Not enough coins";
                    }
                    RangedCombatUnit newRangedCombatUnit = new RangedCombatUnit(rangedCombatUnitType,
                            selectedCity.getOwnerNation(), new Pair<>(x, y));
                    mainLand.setCombatUnit(newRangedCombatUnit);
                    selectedCity.getOwnerNation().addUnit(newRangedCombatUnit);
                    selectedCity.getOwnerNation().getCoin().addBalance(-rangedCombatUnitType.cost);
                    return "Ranged combat unit purchased successfully";
                }
            }
        }

        if (type.equals("building")) {
            for (BuildingType buildingType : BuildingType.values()) {
                if (buildingType.name.equals(name)) {
                    if (buildingType.cost > selectedCity.getOwnerNation().getCoin().getBalance()) {
                        return "Not enough coins";
                    }
                    selectedCity.addBuilding(buildingType);
                    selectedCity.getOwnerNation().getCoin().addBalance(-buildingType.cost);
                    return "Building purchased successfully";
                }
            }
        }


        return "invalid command";
    }

    public static String productStartCreation(String type, String name) {
        if (selectedCity == null) {
            return "You have to select a city first";
        }

        if (selectedCity.hasAnInProgressProduct()) {
            return "The city is already creating a unit";
        }

        if (type.equals("civilized unit")) {
            if (selectedCity.getMainLand().getCivilizedUnit() != null) {
                return "There is already a civilized unit in this location";
            }
            for (CivilizedUnitType civilizedUnitType : CivilizedUnitType.values()) {
                if (civilizedUnitType.name.equals(name)) {
                    CivilizedUnit civilizedUnit = new CivilizedUnit(
                            civilizedUnitType, currentTurnUser.getNation(),
                            new Pair<>(selectedCity.getMainLand().getI(), selectedCity.getMainLand().getJ())
                    );
                    selectedCity.setInProgressCivilizedUnit(civilizedUnit);
                    selectedCity.setNextProductTurns(civilizedUnitType.turns);
                    selectedCity.setHasAnInProgressProduct(true);
                    return "Civilized unit is set for creation successfully";
                }
            }
        }

        else if (type.equals("close combat unit")) {
            if (selectedCity.getMainLand().getCombatUnit() != null) {
                return "There is already a combat unit in this location";
            }
            for (CloseCombatUnitType closeCombatUnitType : CloseCombatUnitType.values()) {
                if (closeCombatUnitType.name.equals(name)) {
                    if (closeCombatUnitType.resourceType != null) {
                        selectedCity.getOwnerNation().removeResource(closeCombatUnitType.resourceType);
                    }
                    CloseCombatUnit closeCombatUnit = new CloseCombatUnit(
                            closeCombatUnitType, currentTurnUser.getNation(),
                            new Pair<>(selectedCity.getMainLand().getI(), selectedCity.getMainLand().getJ())
                    );
                    selectedCity.setInProgressCloseCombatUnit(closeCombatUnit);
                    selectedCity.setNextProductTurns(closeCombatUnitType.turns);
                    selectedCity.setHasAnInProgressProduct(true);
                    return "Close combat unit is set for creation successfully";
                }
            }
        }

        else if (type.equals("ranged combat unit")) {
            if (selectedCity.getMainLand().getCombatUnit() != null) {
                return "There is already a combat unit in this location";
            }
            for (RangedCombatUnitType rangedCombatUnitType : RangedCombatUnitType.values()) {
                if (rangedCombatUnitType.name.equals(name)) {
                    if (rangedCombatUnitType.resourceType != null) {
                        selectedCity.getOwnerNation().removeResource(rangedCombatUnitType.resourceType);
                    }
                    RangedCombatUnit rangedCombatUnit = new RangedCombatUnit(
                            rangedCombatUnitType, currentTurnUser.getNation(),
                            new Pair<>(selectedCity.getMainLand().getI(), selectedCity.getMainLand().getJ())
                    );
                    selectedCity.setInProgressRangedCombatUnit(rangedCombatUnit);
                    selectedCity.setNextProductTurns(rangedCombatUnitType.turns);
                    selectedCity.setHasAnInProgressProduct(true);
                    return "Ranged combat unit is set for creation successfully";
                }
            }
        }

        else if (type.equals("building")) {
            for (BuildingType buildingType : BuildingType.values()) {
                if (buildingType.name.equals(name)) {
                    Building building = new Building(buildingType);
                    selectedCity.setInProgressBuilding(building);
                    selectedCity.setNextProductTurns(buildingType.initialTurns);
                    selectedCity.setHasAnInProgressProduct(true);
                    return "Building is set for creation successfully";
                }
            }
        }

        return "invalid command";
    }

    public static void ProductCreate(City city) {

        if (city.getInProgressCivilizedUnit() != null) {
            CivilizedUnit newCivilizedUnit = city.getInProgressCivilizedUnit();
            city.getMainLand().setCivilizedUnit(newCivilizedUnit);
            city.getOwnerNation().addUnit(newCivilizedUnit);
            city.setInProgressCivilizedUnit(null);
        }

        else if (city.getInProgressCloseCombatUnit() != null) {
            CloseCombatUnit newCloseCombatUnit = city.getInProgressCloseCombatUnit();
            city.getMainLand().setCombatUnit(newCloseCombatUnit);
            city.getOwnerNation().addUnit(newCloseCombatUnit);
            city.setInProgressCloseCombatUnit(null);
        }

        else if (city.getInProgressRangedCombatUnit() != null) {
            RangedCombatUnit newRangedCombatUnit = city.getInProgressRangedCombatUnit();
            city.getMainLand().setCombatUnit(newRangedCombatUnit);
            city.getOwnerNation().addUnit(newRangedCombatUnit);
            city.setInProgressRangedCombatUnit(null);
        }

        else if (city.getInProgressBuilding() != null) {
            Building newBuilding = city.getInProgressBuilding();
            city.addBuilding(newBuilding.getBuildingType());
            city.setInProgressBuilding(null);
        }

        city.setHasAnInProgressProduct(false);
    }

    public static void unitRetrieveRuin(Unit unit) {
        Land land = Game.instance.map[unit.getLocation().x][unit.getLocation().y];
        Ruin ruin = land.getRuin();
        Nation nation = unit.getOwnerNation();
        ruin.retrieve();
        nation.getCoin().addBalance(ruin.getGoldAmount());
        land.setRuin(null);
    }
}