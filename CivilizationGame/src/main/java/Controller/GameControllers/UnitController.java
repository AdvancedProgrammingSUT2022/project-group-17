package Controller.GameControllers;

import Model.Game;
import Model.Pair;

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

    private void unitMultiTurnMoveTo() {

    }

    private int findEasiestPath(Pair dest, int pathCost, int minPathCost) {
        Pair currentLocation = selectedUnit.getLocation();
        int tmpPathCost;

        if(!LandController.isPairValid(currentLocation)) return 1000;
        if(dest.equals(currentLocation)) return pathCost;

        Pair neighbors[] = new Pair[6];
        for (int i = 0; i < 6; i++)
            neighbors[i] = LandController.getNeighborIndex(currentLocation, i);
        

//        Game.map[currentI][currentJ].setAPartOfPath(true);

        for (int i = 0; i < 6; i++) {
            if((tmpPathCost =findEasiestPath(dest, pathCost + Game.map[neighbors[i].x][neighbors[i].y].getMP(),
                    minPathCost)) < minPathCost)
                minPathCost = tmpPathCost;
        }

        return minPathCost;
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