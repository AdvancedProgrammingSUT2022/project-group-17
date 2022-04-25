package Controller.GameControllers;

import Model.Game;

import java.util.regex.Matcher;

public class UnitController extends GameController {

    public void unitMoveTo(Matcher matcher) {
        int destI = Integer.parseInt(matcher.group("i"));
        int destJ = Integer .parseInt(matcher.group("j"));

        int minPathCost = findEasiestPath(destI, destJ, 0, 1000);
        if(minPathCost <= selectedUnit.getMP()) {
            selectedUnit.setI(destI);
            selectedUnit.setJ(destJ);
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

    private int findEasiestPath(int destI, int destJ, int pathCost, int minPathCost) {
        int currentI = selectedUnit.getI();
        int currentJ = selectedUnit.getJ();
        int tmpPathCost;

        if(currentI == destI && currentJ == destJ) return pathCost;
        if(Game.map[currentI][currentJ] == null) return 1000;

        Game.map[currentI][currentJ].setAPartOfPath(true);

        if(Game.map[currentI + 1][currentJ] != null)
            if((tmpPathCost =findEasiestPath(destI, destJ,
                    pathCost + Game.map[currentI + 1][currentJ].getMP(), minPathCost)) < minPathCost)
                minPathCost = tmpPathCost;
        if(Game.map[currentI - 1][currentJ] != null)
            findEasiestPath(destI, destJ,
                    pathCost + Game.map[currentI - 1][currentJ].getMP(), minPathCost);
        if(Game.map[currentI][currentJ + 1] != null)
            findEasiestPath(destI, destJ,
                    pathCost + Game.map[currentI][currentJ + 1].getMP(), minPathCost);
        if(Game.map[currentI][currentJ - 1] != null)
            findEasiestPath(destI, destJ,
                    pathCost + Game.map[currentI][currentJ - 1].getMP(), minPathCost);
        if(Game.map[currentI + 1][currentJ + 1] != null)
            findEasiestPath(destI, destJ,
                    pathCost + Game.map[currentI + 1][currentJ + 1].getMP(), minPathCost);
        if(Game.map[currentI - 1][currentJ - 1] != null)
            findEasiestPath(destI, destJ,
                    pathCost + Game.map[currentI - 1][currentJ - 1].getMP(), minPathCost);



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