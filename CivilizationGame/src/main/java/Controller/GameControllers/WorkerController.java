package Controller.GameControllers;

import Model.Game;

import java.util.regex.Matcher;

public class WorkerController extends GameController {

    public void workerBuildRoad() {
//        int x = Integer.parseInt(matcher.group("x"));
//        int y = Integer.parseInt(matcher.group("y"));

        if(selectedCivilizedUnit != null && selectedCivilizedUnit.getCivilizedUnitType().name.equals("Worker")) {

        } else System.out.println("Please select a Worker first!");
    }

    public void workerBuildRailroad(Matcher matcher) {

    }

    public void workerBuildFarm(Matcher matcher) {

    }

    public void workerBuildMine() {

    }

    public void workerBuildTradingPost() {

    }

    public void workerBuildLumbermill() {

    }

    public void workerBuildPasture() {

    }

    public void workerBuildCamp() {

    }

    public void workerBuildPlantation() {

    }

    public void workerBuildQuarry() {

    }

    public void workerRemoveJungle() {

    }

    public void workerRemoveRoute() {

    }

    public void workerRepair() {

    }

    //FIXME is this method necessary?
    public void workerSetupImprovement(){

    }
}
