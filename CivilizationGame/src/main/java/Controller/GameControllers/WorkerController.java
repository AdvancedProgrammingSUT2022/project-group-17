package Controller.GameControllers;

import Model.Game;
import Model.Improvements.ImprovementType;
import Model.LandFeatures.LandFeatureType;
import Model.Lands.LandType;
import Model.Resources.Enums.ResourceType;
import Model.Technologies.TechnologyType;
import Model.Units.Enums.CivilizedUnitType;

public class WorkerController extends GameController {

    public void workerBuildRoad() {

    }

    public void workerBuildRailroad() {

    }

    public void workerBuildFarm() {
        //fixme Doc's paradox in lands where can a farm built
        if (canGenerallyBuildImprovement(ImprovementType.FARM)) {

            boolean canBuild = false;
            switch (Game.map[selectedCivilizedUnit.getX()][selectedCivilizedUnit.getY()].getLandFeature().getLandFeatureType()) {
                //TODO complete building farm on special land features : change turns,...
                case Jungle -> {
                    if (currentTurnUser.getNation().hasTechnology(TechnologyType.BronzeWorking)) {
                        Game.map[selectedCivilizedUnit.getX()][selectedCivilizedUnit.getY()].setLandFeature(null);
                        canBuild = true;
                    }
                }
                case Forest -> {
                    if (currentTurnUser.getNation().hasTechnology(TechnologyType.Mining)) {
                        Game.map[selectedCivilizedUnit.getX()][selectedCivilizedUnit.getY()].setLandFeature(null);
                        canBuild = true;
                    }
                }
                case Marsh -> {
                    if (currentTurnUser.getNation().hasTechnology(TechnologyType.Masonry)) {
                        Game.map[selectedCivilizedUnit.getX()][selectedCivilizedUnit.getY()].setLandFeature(null);
                        canBuild = true;
                    }
                }
            }

            if (canBuild) {
                Game.map[selectedCivilizedUnit.getX()][selectedCivilizedUnit.getY()].setImprovementType(ImprovementType.FARM);
                //fixme Change an enum's field ?!
                Game.map[selectedCivilizedUnit.getX()][selectedCivilizedUnit.getY()].getLandType().foodGrowth += 1;
                currentTurnUser.getNation().addResource(Game.map[selectedCivilizedUnit.getX()][selectedCivilizedUnit.getY()].
                        getResource().getResourceType());
                //TODO complete
            }
        }
    }

    public void workerBuildMine() {
        //fixme Doc's paradox in lands where can a mine built
        if (canGenerallyBuildImprovement(ImprovementType.MINE) && hasResourceOfImprovement(ImprovementType.MINE)) {

            boolean canBuild = false;
            switch (Game.map[selectedCivilizedUnit.getX()][selectedCivilizedUnit.getY()].getLandFeature().getLandFeatureType()) {
                //TODO complete building farm on special land features : change turns,...
                case Jungle -> {
                    if (currentTurnUser.getNation().hasTechnology(TechnologyType.BronzeWorking)) {
                        Game.map[selectedCivilizedUnit.getX()][selectedCivilizedUnit.getY()].setLandFeature(null);
                        canBuild = true;
                    }
                }
                case Forest -> {
                    Game.map[selectedCivilizedUnit.getX()][selectedCivilizedUnit.getY()].setLandFeature(null);
                    canBuild = true;
                }
                case Marsh -> {
                    if (currentTurnUser.getNation().hasTechnology(TechnologyType.Masonry)) {
                        Game.map[selectedCivilizedUnit.getX()][selectedCivilizedUnit.getY()].setLandFeature(null);
                        canBuild = true;
                    }
                }
            }

            if (canBuild) {
                Game.map[selectedCivilizedUnit.getX()][selectedCivilizedUnit.getY()].setImprovementType(ImprovementType.MINE);
                //fixme Change an enum's field ?!
                Game.map[selectedCivilizedUnit.getX()][selectedCivilizedUnit.getY()].getLandType().productionGrowth += 1;
                currentTurnUser.getNation().addResource(Game.map[selectedCivilizedUnit.getX()][selectedCivilizedUnit.getY()].
                        getResource().getResourceType());
                //TODO complete
            }
        }
    }

    public void workerBuildTradingPost() {
        if (canGenerallyBuildImprovement(ImprovementType.TRADING_POST)) {
            Game.map[selectedCivilizedUnit.getX()][selectedCivilizedUnit.getY()].
                    setImprovementType(ImprovementType.TRADING_POST);
            //fixme Change an enum's field ?!
            Game.map[selectedCivilizedUnit.getX()][selectedCivilizedUnit.getY()].getLandType().coinGrowth += 1;
            //TODO complete
        }
    }

    public void workerBuildLumberMill() {
        if (canGenerallyBuildImprovement(ImprovementType.LUMBER_MILL)) {
            Game.map[selectedCivilizedUnit.getX()][selectedCivilizedUnit.getY()].
                    setImprovementType(ImprovementType.LUMBER_MILL);
            //fixme Change an enum's field ?!
            Game.map[selectedCivilizedUnit.getX()][selectedCivilizedUnit.getY()].getLandType().productionGrowth += 1;
            //TODO complete
        }
    }

    public void workerBuildPasture() {
        if (canGenerallyBuildImprovement(ImprovementType.PASTURE) &&
                hasResourceOfImprovement(ImprovementType.PASTURE)) {
            Game.map[selectedCivilizedUnit.getX()][selectedCivilizedUnit.getY()].
                    setImprovementType(ImprovementType.PASTURE);
            currentTurnUser.getNation().addResource(Game.map[selectedCivilizedUnit.getX()][selectedCivilizedUnit.getY()].
                    getResource().getResourceType());
            //TODO complete
        }
    }

    public void workerBuildCamp() {
        if (canGenerallyBuildImprovement(ImprovementType.CAMP) &&
                hasResourceOfImprovement(ImprovementType.CAMP)) {
            Game.map[selectedCivilizedUnit.getX()][selectedCivilizedUnit.getY()].
                    setImprovementType(ImprovementType.CAMP);
            currentTurnUser.getNation().addResource(Game.map[selectedCivilizedUnit.getX()][selectedCivilizedUnit.getY()].
                    getResource().getResourceType());
            //TODO complete
        }
    }

    public void workerBuildPlantation() {
        if (canGenerallyBuildImprovement(ImprovementType.PLANTATION) &&
                hasResourceOfImprovement(ImprovementType.PLANTATION)) {
            Game.map[selectedCivilizedUnit.getX()][selectedCivilizedUnit.getY()].
                    setImprovementType(ImprovementType.PLANTATION);
            currentTurnUser.getNation().addResource(Game.map[selectedCivilizedUnit.getX()][selectedCivilizedUnit.getY()].
                    getResource().getResourceType());
            //TODO complete
        }
    }

    public void workerBuildQuarry() {
        if (canGenerallyBuildImprovement(ImprovementType.QUARRY) &&
                hasResourceOfImprovement(ImprovementType.QUARRY)) {
            Game.map[selectedCivilizedUnit.getX()][selectedCivilizedUnit.getY()].
                    setImprovementType(ImprovementType.QUARRY);
            currentTurnUser.getNation().addResource(Game.map[selectedCivilizedUnit.getX()][selectedCivilizedUnit.getY()].
                    getResource().getResourceType());
            //TODO complete
        }
    }

    public void workerBuildFactory() {
        if (canGenerallyBuildImprovement(ImprovementType.FACTORY)) {
            Game.map[selectedCivilizedUnit.getX()][selectedCivilizedUnit.getY()].
                    setImprovementType(ImprovementType.FACTORY);
            //fixme Change an enum's field ?!
            Game.map[selectedCivilizedUnit.getX()][selectedCivilizedUnit.getY()].getLandType().productionGrowth += 2;
            //TODO complete
        }
    }

    public void workerRemoveJungle() {

    }

    public void workerRemoveRoute() {

    }

    public void workerRepair() {

    }

    private boolean canGenerallyBuildImprovement(ImprovementType improvementType) {
        if (selectedCivilizedUnit != null) {
            if (selectedCivilizedUnit.getCivilizedUnitType() == CivilizedUnitType.WORKER) {
                if (currentTurnUser.getNation().hasTechnology(improvementType.technology)) {
                    if (Game.map[selectedCivilizedUnit.getX()][selectedCivilizedUnit.getY()].getImprovementType()
                            == null) {
                        //fixme what is the relation between lands and landFeatures in building improvements
                        for (LandType landType : improvementType.landTypes)
                            if (landType == Game.map[selectedCivilizedUnit.getX()][selectedCivilizedUnit.getY()].
                                    getLandType())
                                return true;
                        for (LandFeatureType landFeatureType : improvementType.landFeatureTypes)
                            if (landFeatureType == Game.map[selectedCivilizedUnit.getX()][selectedCivilizedUnit.getY()].
                                    getLandFeature().getLandFeatureType())
                                return true;

                        System.out.println("This Land isn't suitable for " + improvementType.name + "!");

                    } else System.out.println("There is already an Improvement here!");
                } else System.out.println("You don't have " + ImprovementType.FARM.technology.name + " technology!");
            } else System.out.println("The selected unit is not a Worker!");
        } else System.out.println("Please select a Worker first!");

        return false;
    }

    private boolean hasResourceOfImprovement(ImprovementType improvementType) {
        for (ResourceType resourceType : improvementType.resourcesGiven)
            if (resourceType == Game.map[selectedCivilizedUnit.getX()][selectedCivilizedUnit.getY()].getResource().
                    getResourceType())
                return true;

        System.out.println("There is not the suitable resource for " + improvementType.name + " here!");
        return false;
    }

    //FIXME is this method necessary?
    public void workerSetupImprovement() {

    }
}
