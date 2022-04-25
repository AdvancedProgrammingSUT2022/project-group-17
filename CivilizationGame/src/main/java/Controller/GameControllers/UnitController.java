package Controller.GameControllers;

import Model.Game;
import Model.Pair;
import Model.Units.CombatUnit;

import java.util.regex.Matcher;

public class UnitController extends GameController {

    public void unitMoveTo(Matcher matcher) {
        int destX = Integer.parseInt(matcher.group("i"));
        int destY = Integer .parseInt(matcher.group("j"));
        Pair dest = new Pair(destX, destY);

        int minPathCost = findEasiestPath(dest, 0, 1000);
        if(minPathCost <= selectedUnit.getMP()) {
            selectedUnit.setLocation(dest);
            selectedUnit.changeMP(minPathCost);
            selectedUnit.setWaitingForCommand(false);
            //TODO set isAPartOfPath of all Lands zero
        } else if(minPathCost < 1000) {
            unitMultiTurnMoveTo();
        } else {
            System.out.println("The unit can't move to this position!");
        }
    }

    private void unitGoToDest(Pair dest){
        main: while (!selectedUnit.getLocation().equals(dest)){
            Pair neighbors[] = new Pair[6];
            for (int i = 0; i < 6; i++)
                neighbors[i] = LandController.getNeighborIndex(selectedUnit.getLocation(), i);

            for (int i = 0; i < 6; i++) {
                if (Game.map[neighbors[i].x][neighbors[i].y].getMP() <= selectedUnit.getMP() &&
                        LandController.isPairValid(neighbors[i]) && Game.map[neighbors[i].x][neighbors[i].y].getCombatUnit() == null){
                    if (Math.abs(neighbors[i].x - dest.x) < Math.abs(selectedUnit.getLocation().x - dest.x) &&
                            Math.abs(neighbors[i].y - dest.y) < Math.abs(selectedUnit.getLocation().y - dest.y)){

                        Game.map[selectedUnit.getLocation().x][selectedUnit.getLocation().y].setCombatUnit(null);
                        selectedUnit.setLocation(neighbors[i]);
                        Game.map[neighbors[i].x][neighbors[i].y].setCombatUnit((CombatUnit) selectedUnit);
                        continue main;
                    }

                    if (Math.abs(neighbors[i].x - dest.x) < Math.abs(selectedUnit.getLocation().x - dest.x) &&
                            neighbors[i].y == selectedUnit.getLocation().y){

                        Game.map[selectedUnit.getLocation().x][selectedUnit.getLocation().y].setCombatUnit(null);
                        selectedUnit.setLocation(neighbors[i]);
                        Game.map[neighbors[i].x][neighbors[i].y].setCombatUnit((CombatUnit) selectedUnit);
                        continue main;
                    }

                    if (Math.abs(neighbors[i].y - dest.y) < Math.abs(selectedUnit.getLocation().y - dest.y) &&
                            neighbors[i].x == selectedUnit.getLocation().x){


                        Game.map[selectedUnit.getLocation().x][selectedUnit.getLocation().y].setCombatUnit(null);
                        selectedUnit.setLocation(neighbors[i]);
                        Game.map[neighbors[i].x][neighbors[i].y].setCombatUnit((CombatUnit) selectedUnit);
                        continue main;
                    }
                }

            }

            break;
        }
    }

    private int findEasiestPath(Pair dest, int pathCost, int minPathCost) {
        Pair currentLocation = selectedUnit.getLocation();
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

    public void unitBuildRoad() {

    }

    public void unitBuildRailroad() {

    }

    public void unitBuildFarm() {

    }

    public void unitBuildMine() {

    }

    public void unitBuildTradingPost() {

    }

    public void unitBuildLumbermill() {

    }

    public void unitBuildPasture() {

    }

    public void unitBuildCamp() {

    }

    public void unitBuildPlantation() {

    }

    public void unitBuildQuarry() {

    }

    public void unitRemoveJungle() {

    }

    public void unitRemoveRoute() {

    }

    public void unitRepair() {

    }

    public void workerSetupImprovement(){

    }
}