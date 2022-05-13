package Controller.GameControllers;

import Model.Game;
import Model.LandPair;
import Model.Lands.Land;
import Model.Pair;
import Model.Units.CombatUnit;
import Model.Units.Unit;

import java.util.regex.Matcher;

public class UnitController extends GameController {

    public void unitGoToNeighbor(Matcher matcher){
        int destX = Integer.parseInt(matcher.group("x"));
        int destY = Integer.parseInt(matcher.group("y"));
        Pair dest = new Pair(destX, destY);

        System.out.println(selectedUnit.getLocation().x + " " + selectedUnit.getLocation().y);

        Pair neighbor = new Pair(destX, destY);

        Game.map[neighbor.x][neighbor.y].setCombatUnit(selectedUnit);
        Game.map[selectedUnit.getLocation().x][selectedUnit.getLocation().y].setCombatUnit(null);
        selectedUnit.setLocation(neighbor);

        System.out.println(selectedUnit.getLocation().x + " " + selectedUnit.getLocation().y);


    }

    public void unitGoToDest(Matcher matcher){
        int destX = Integer.parseInt(matcher.group("x"));
        int destY = Integer.parseInt(matcher.group("y"));
        Pair dest = new Pair(destX, destY);

        System.out.println(selectedUnit.getLocation().x + " " + selectedUnit.getLocation().y);
        main: while (!selectedUnit.getLocation().equals(dest)){
            Pair neighbors[] = new Pair[6];
            for (int i = 0; i < 6; i++)
                neighbors[i] = LandController.getNeighborIndex(selectedUnit.getLocation(), i);

            for (int i = 0; i < 6; i++) {
                if (LandController.isPairValid(neighbors[i]) && Game.map[neighbors[i].x][neighbors[i].y].getMP() <= selectedUnit.getMP() && Game.map[neighbors[i].x][neighbors[i].y].getCombatUnit() == null){
                    if (Math.abs(neighbors[i].x - dest.x) < Math.abs(selectedUnit.getLocation().x - dest.x) &&
                            Math.abs(neighbors[i].y - dest.y) < Math.abs(selectedUnit.getLocation().y - dest.y)){

                        Game.map[selectedUnit.getLocation().x][selectedUnit.getLocation().y].setCombatUnit(null);
                        selectedUnit.setLocation(neighbors[i]);
                        selectedUnit.changeMP(Game.map[neighbors[i].x][neighbors[i].y].getMP());
                        Game.map[neighbors[i].x][neighbors[i].y].setCombatUnit((CombatUnit) selectedUnit);
                        continue main;
                    }

                    if (Math.abs(neighbors[i].x - dest.x) < Math.abs(selectedUnit.getLocation().x - dest.x) &&
                            neighbors[i].y == selectedUnit.getLocation().y){

                        Game.map[selectedUnit.getLocation().x][selectedUnit.getLocation().y].setCombatUnit(null);
                        selectedUnit.setLocation(neighbors[i]);
                        selectedUnit.changeMP(Game.map[neighbors[i].x][neighbors[i].y].getMP());
                        Game.map[neighbors[i].x][neighbors[i].y].setCombatUnit((CombatUnit) selectedUnit);
                        continue main;
                    }

                    if (Math.abs(neighbors[i].y - dest.y) < Math.abs(selectedUnit.getLocation().y - dest.y) &&
                            neighbors[i].x == selectedUnit.getLocation().x){


                        Game.map[selectedUnit.getLocation().x][selectedUnit.getLocation().y].setCombatUnit(null);
                        selectedUnit.setLocation(neighbors[i]);
                        selectedUnit.changeMP(Game.map[neighbors[i].x][neighbors[i].y].getMP());
                        Game.map[neighbors[i].x][neighbors[i].y].setCombatUnit((CombatUnit) selectedUnit);
                        continue main;
                    }
                }else if (LandController.isPairValid(neighbors[i]) && Game.map[neighbors[i].x][neighbors[i].y].getMP() > selectedUnit.getMP())
                    System.out.println("Kam dari dadash");

            }

            break;
        }

        System.out.println(selectedUnit.getLocation().x + " " + selectedUnit.getLocation().y);
    }

    public void unitSetPath(Matcher matcher){
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