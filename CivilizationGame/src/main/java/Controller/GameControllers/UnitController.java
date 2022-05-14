package Controller.GameControllers;

import Model.Game;
import Model.Nations.Nation;
import Model.Pair;
import Model.Units.CombatUnit;
import Model.Units.Unit;

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
                if (LandController.isPairValid(neighbors[i]) && Game.map[neighbors[i].x][neighbors[i].y].getMP() <= selectedCombatUnit.getMP() && Game.map[neighbors[i].x][neighbors[i].y].getCombatUnit() == null){
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
                } else if (LandController.isPairValid(neighbors[i]) && Game.map[neighbors[i].x][neighbors[i].y].getMP() > selectedCombatUnit.getMP())
                    System.out.println("your move Points is not enough!");

            }

            break;
        }

        System.out.println(selectedCombatUnit.getLocation().x + " " + selectedCombatUnit.getLocation().y);
    }

    private int findEasiestPath(Pair dest, int pathCost, int minPathCost) {
        Pair currentLocation = selectedCombatUnit.getLocation();
        int tmpPathCost;

        if(!LandController.isPairValid(currentLocation)) return 1000;
        if(dest.equals(currentLocation)) return pathCost;

        Pair neighbors[] = new Pair[6];
        for (int i = 0; i < 6; i++)
            neighbors[i] = LandController.getNeighborIndex(currentLocation, i);

        for (int i = 0; i < 6; i++) {
            if((tmpPathCost =findEasiestPath(dest, pathCost + Game.map[neighbors[i].x][neighbors[i].y].getMP(),
                    minPathCost)) < minPathCost)
                minPathCost = tmpPathCost;
        }

        return minPathCost;
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

}