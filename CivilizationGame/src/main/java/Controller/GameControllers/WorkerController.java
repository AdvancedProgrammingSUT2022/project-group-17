package Controller.GameControllers;

import Model.Game;
import Model.Improvements.ImprovementType;
import Model.Lands.LandType;
import Model.Nations.Nation;
import Model.Technologies.TechnologyType;
import Model.Units.Enums.CivilizedUnitType;

import java.util.regex.Matcher;

public class WorkerController extends GameController {

    public void workerBuildRoad() {
//        int x = Integer.parseInt(matcher.group("x"));
//        int y = Integer.parseInt(matcher.group("y"));


    }

    public void workerBuildRailroad(Matcher matcher) {

    }

    public void workerBuildFarm(Matcher matcher) {
        if(selectedCivilizedUnit != null && selectedCivilizedUnit.getCivilizedUnitType() == CivilizedUnitType.WORKER) {
            if(currentTurnUser.getNation().hasTechnology(ImprovementType.Farm.technology)) {
                if(Game.map[selectedCivilizedUnit.getX()][selectedCivilizedUnit.getY()].getLandType() != LandType.Snow) {
                    switch (Game.map[selectedCivilizedUnit.getX()][selectedCivilizedUnit.getY()].getLandFeature().getLandFeatureType()) {
                        //TODO complete building farm on special land features
                        case Jungle -> System.out.println("jungle");
                        case Forest -> System.out.println("forest");
                        case Marsh -> System.out.println("marsh");
                        default -> {
                            Game.map[selectedCivilizedUnit.getX()][selectedCivilizedUnit.getY()].setImprovementType(ImprovementType.Farm);
                            //TODO complete
                        }
                    }
                } else System.out.println("You can't build a farm on snow!");
            } else System.out.println("You don't have " + ImprovementType.Farm.technology.name + " technology!");
        } else System.out.println("Please select a Worker first!");
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
