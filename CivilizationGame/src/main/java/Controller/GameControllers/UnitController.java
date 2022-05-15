package Controller.GameControllers;

import Enums.Consts;
import Model.City;
import Model.Game;
import Model.LandPair;
import Model.Lands.Land;
import Model.Nations.Nation;
import Model.Pair;
import Model.Units.*;
import Model.Units.Enums.CivilizedUnitType;
import Model.Units.Enums.CloseCombatUnitType;
import Model.Units.Enums.RangedCombatUnitType;

import java.util.regex.Matcher;

public class UnitController extends GameController {
    public void unitMoveTo(Matcher matcher) {
        int destX = Integer.parseInt(matcher.group("i"));
        int destY = Integer .parseInt(matcher.group("j"));
        Pair dest = new Pair(destX, destY);

        int minPathCost = findEasiestPath(dest, 0, 1000);
        if(minPathCost <= selectedCombatUnit.getMP()) {
            selectedCombatUnit.setLocation(dest);
            selectedCombatUnit.changeMP(minPathCost);
            selectedCombatUnit.setWaitingForCommand(false);
            //TODO set isAPartOfPath of all Lands zero
        } else if(minPathCost < 1000) {
            unitMultiTurnMoveTo();
        } else {
            System.out.println("The unit can't move to this position!");
        }
    }

    public void unitGoToNeighbor(Matcher matcher){
        int destX = Integer.parseInt(matcher.group("x"));
        int destY = Integer.parseInt(matcher.group("y"));
        Pair dest = new Pair(destX, destY);

        System.out.println(selectedCombatUnit.getLocation().x + " " + selectedCombatUnit.getLocation().y);

        Pair neighbor = new Pair(destX, destY);

        Game.map[neighbor.x][neighbor.y].setCombatUnit(selectedCombatUnit);
        Game.map[selectedCombatUnit.getLocation().x][selectedCombatUnit.getLocation().y].setCombatUnit(null);
        selectedCombatUnit.setLocation(neighbor);

        System.out.println(selectedCombatUnit.getLocation().x + " " + selectedCombatUnit.getLocation().y);


    }

    public void unitGoToDestination(Matcher matcher){
        int destX = Integer.parseInt(matcher.group("x"));
        int destY = Integer.parseInt(matcher.group("y"));
        Pair dest = new Pair(destX, destY);

        System.out.println(selectedCombatUnit.getLocation().x + " " + selectedCombatUnit.getLocation().y);
        main: while (!selectedCombatUnit.getLocation().equals(dest)){
            Pair neighbors[] = new Pair[6];
            for (int i = 0; i < 6; i++)
                neighbors[i] = LandController.getNeighborIndex(selectedCombatUnit.getLocation(), i);

            for (int i = 0; i < 6; i++) {
                if (Pair.isValid(neighbors[i]) && Game.map[neighbors[i].x][neighbors[i].y].getMP() <= selectedCombatUnit.getMP() && Game.map[neighbors[i].x][neighbors[i].y].getCombatUnit() == null){
                    if (Math.abs(neighbors[i].x - dest.x) < Math.abs(selectedCombatUnit.getLocation().x - dest.x) &&
                            Math.abs(neighbors[i].y - dest.y) < Math.abs(selectedCombatUnit.getLocation().y - dest.y)){

                        Game.map[selectedCombatUnit.getLocation().x][selectedCombatUnit.getLocation().y].setCombatUnit(null);
                        selectedCombatUnit.setLocation(neighbors[i]);
                        selectedCombatUnit.changeMP(Game.map[neighbors[i].x][neighbors[i].y].getMP());
                        Game.map[neighbors[i].x][neighbors[i].y].setCombatUnit((CombatUnit) selectedCombatUnit);
                        continue main;
                    }

                    if (Math.abs(neighbors[i].x - dest.x) < Math.abs(selectedCombatUnit.getLocation().x - dest.x) &&
                            neighbors[i].y == selectedCombatUnit.getLocation().y){

                        Game.map[selectedCombatUnit.getLocation().x][selectedCombatUnit.getLocation().y].setCombatUnit(null);
                        selectedCombatUnit.setLocation(neighbors[i]);
                        selectedCombatUnit.changeMP(Game.map[neighbors[i].x][neighbors[i].y].getMP());
                        Game.map[neighbors[i].x][neighbors[i].y].setCombatUnit((CombatUnit) selectedCombatUnit);
                        continue main;
                    }

                    if (Math.abs(neighbors[i].y - dest.y) < Math.abs(selectedCombatUnit.getLocation().y - dest.y) &&
                            neighbors[i].x == selectedCombatUnit.getLocation().x){


                        Game.map[selectedCombatUnit.getLocation().x][selectedCombatUnit.getLocation().y].setCombatUnit(null);
                        selectedCombatUnit.setLocation(neighbors[i]);
                        selectedCombatUnit.changeMP(Game.map[neighbors[i].x][neighbors[i].y].getMP());
                        Game.map[neighbors[i].x][neighbors[i].y].setCombatUnit((CombatUnit) selectedCombatUnit);
                        continue main;
                    }
                } else if (Pair.isValid(neighbors[i]) && Game.map[neighbors[i].x][neighbors[i].y].getMP() > selectedCombatUnit.getMP())
                    System.out.println("your move Points is not enough!");

            }

            break;
        }

        System.out.println(selectedCombatUnit.getLocation().x + " " + selectedCombatUnit.getLocation().y);
    }

    private int findEasiestPath(Pair dest, int pathCost, int minPathCost) {
        Pair currentLocation = selectedCombatUnit.getLocation();
        int tmpPathCost;

        if (!Pair.isValid(currentLocation)) return 1000;
        if (dest.equals(currentLocation)) return pathCost;

        Pair neighbors[] = new Pair[6];
        for (int i = 0; i < 6; i++)
            neighbors[i] = LandController.getNeighborIndex(currentLocation, i);

        for (int i = 0; i < 6; i++) {
            if ((tmpPathCost = findEasiestPath(dest, pathCost + Game.map[neighbors[i].x][neighbors[i].y].getMP(),
                    minPathCost)) < minPathCost)
                minPathCost = tmpPathCost;
        }

        return minPathCost;
    }

    public void unitSetPath(Matcher matcher, int selection){
        Unit selectedUnit;
        if (selection == 1)
            selectedUnit = selectedCombatUnit;
        else
            selectedUnit = selectedCivilizedUnit;
        int x = Integer.parseInt(matcher.group("x"));
        int y = Integer.parseInt(matcher.group("y"));
        Land origin = Game.map[selectedUnit.getLocation().x][selectedUnit.getLocation().y];
        Land dest = Game.map[x][y];
        if (Game.dist.get(new LandPair(origin, dest)) < 1000)
            selectedUnit.setPath(Game.path.get(new LandPair(origin, dest)));
        while (selectedUnit.getMP() > 0)
            unitGoForward(selectedUnit);
    }

    public void unitGoForward(Unit unit){
        String path = unit.getPath();
        int neighbor = Integer.parseInt(String.valueOf(path.charAt(0)));
        Pair next = LandController.getNeighborIndex(unit.getLocation(), neighbor);
        Game.map[unit.getLocation().x][unit.getLocation().y].setCombatUnit(null);
        Game.map[next.x][next.y].setCombatUnit((CombatUnit) unit);
        unit.setLocation(next);
        unit.setMP(Math.max(0, unit.getMP() - Game.map[next.x][next.y].getMP()));
        unit.setPath(path.substring(1));
    }

    private void unitMultiTurnMoveTo() {

    }

    public void unitSleep() {

    }

    public void unitAlert() {

    }

    public void unitFortify() {

    }

    public void unitFortifyHeal() {

    }

    public void unitGarrison() {

    }

    public void unitSetup() {

    }

    public void unitAttack(Matcher matcher) {

    }

    public void unitFoundCity() {

    }

    public void unitCancelMission() {

    }

    public void unitWake() {

    }

    public void unitDelete() {

    }

    public String unitSetCityTarget(){
        if (selectedCity != null && selectedCombatUnit != null){
            //if are neighbors
            if (!selectedCombatUnit.getOwnerNation().equals(selectedCity.getOwnerNation())){
                selectedCombatUnit.setTargetCity(selectedCity);
            }
            else{
                return "Can't attack owner nation's city";
            }
        }
        unitAttackCity(selectedCombatUnit);
        return "Attack successful";
    }

    public void unitAttackCity(CombatUnit combatUnit){
        combatUnit.setHp(combatUnit.getHp() - combatUnit.getTargetCity().getCombatStrength());
        combatUnit.getTargetCity().setHP(combatUnit.getTargetCity().getHP() - combatUnit.getCombatStrength());
        if (combatUnit.getHp() <= 0){
            unitDeath(combatUnit);
        }
        if (combatUnit.getTargetCity().getHP() <= 0){
            CityController.cityDeath(combatUnit.getTargetCity());
        }
    }

    public void unitDeath(Unit unit){
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

        if (type.equals("civilized unit")){
            if (selectedCity.getMainLand().getCivilizedUnit() != null){
                return "There is already a civilized unit in this location";
            }
            for (CivilizedUnitType civilizedUnitType : CivilizedUnitType.values()) {
                if (civilizedUnitType.name.equals(name)){
                    if (civilizedUnitType.cost > selectedCity.getOwnerNation().getCoin().getBalance()){
                        return "Not enough coins";
                    }
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
                selectedCity.getOwnerNation().getCoin().addBalance(-rangedCombatUnitType.cost);
                return "Ranged combat unit purchased successfully";
            }
        }


        return "invalid command";
    }

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
                    selectedCity.setInProgressCivilizedUnit(civilizedUnitType);
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
                    selectedCity.setInProgressCloseCombatUnit(closeCombatUnitType);
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
                selectedCity.setInProgressRangedCombatUnit(rangedCombatUnitType);
                selectedCity.setNextUnitTurns(rangedCombatUnitType.turns);
                selectedCity.setHasAnInProgressUnit(true);
                return "Ranged combat unit is set for creation successfully";
            }
        }

        return "invalid command";
    }

    public void unitCreate(City city){
        int x = -1, y = -1;
        main: for (int i = 0; i < Consts.MAP_SIZE.amount.x; i++) {
            for (int j = 0; j < Consts.MAP_SIZE.amount.y; j++) {
                if (Game.map[i][j].equals(city.getMainLand())){
                    x = i;
                    y = j;
                    break main;
                }
            }
        }

        if (city.getInProgressCivilizedUnit() != null){
            CivilizedUnit newCivilizedUnit = new CivilizedUnit(city.getInProgressCivilizedUnit(),
                    city.getOwnerNation(), new Pair(x, y));
            city.getMainLand().setCivilizedUnit(newCivilizedUnit);
            city.getOwnerNation().addUnit(newCivilizedUnit);
            city.setInProgressCivilizedUnit(null);
            return;
        }

        if (city.getInProgressCloseCombatUnit() != null){
            CloseCombatUnit newCloseCombatUnit = new CloseCombatUnit(city.getInProgressCloseCombatUnit(),
                    city.getOwnerNation(), new Pair(x, y));
            city.getMainLand().setCombatUnit(newCloseCombatUnit);
            city.getOwnerNation().addUnit(newCloseCombatUnit);
            city.setInProgressCloseCombatUnit(null);
            return;
        }

        if (city.getInProgressRangedCombatUnit() != null){
            RangedCombatUnit newRangedCombatUnit = new RangedCombatUnit(city.getInProgressRangedCombatUnit(),
                    city.getOwnerNation(), new Pair(x, y));
            city.getMainLand().setCombatUnit(newRangedCombatUnit);
            city.getOwnerNation().addUnit(newRangedCombatUnit);
            city.setInProgressRangedCombatUnit(null);
            return;
        }

        city.setHasAnInProgressUnit(false);
    }
}