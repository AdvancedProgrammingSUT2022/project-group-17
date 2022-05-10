package Controller.GameControllers;

import Model.Game;
import Model.Improvements.Improvement;
import Model.Improvements.ImprovementType;
import Model.LandFeatures.LandFeatureType;
import Model.Lands.LandType;
import Model.Resources.Enums.ResourceType;
import Model.Units.Enums.CivilizedUnitType;

public class WorkerController extends GameController {

    public String workerBuildRoad() {
        String message;
        if ((message = canGenerallyBuildImprovement(ImprovementType.ROAD)).equals("yes")) {
            //TODO road can built on another improvement
            Game.map[selectedCivilizedUnit.getX()][selectedCivilizedUnit.getY()].
                    setImprovementType(ImprovementType.ROAD);
            //TODO complete
        }
        return message;
    }

    public String workerBuildRailroad() {
        return "error";
    }

    public String workerBuildFarm() {
        String message;
        if ((message = canGenerallyBuildImprovement(ImprovementType.FARM)).equals("yes")) {
            if (Game.map[selectedCivilizedUnit.getX()][selectedCivilizedUnit.getY()].getLandFeature() == null) {
                Game.map[selectedCivilizedUnit.getX()][selectedCivilizedUnit.getY()].setImprovement(new Improvement(ImprovementType.FARM));
                //fixme Change an enum's field ?!
                Game.map[selectedCivilizedUnit.getX()][selectedCivilizedUnit.getY()].getLandType().foodGrowth += 1;
                if (hasResourceOfImprovement(ImprovementType.FARM).equals("yes"))
                    currentTurnUser.getNation().addResource(Game.map[selectedCivilizedUnit.getX()][selectedCivilizedUnit.getY()].getResource().getResourceType());
                //TODO complete
                return ("Improvement has built successfully :)");
            } else if (Game.map[selectedCivilizedUnit.getX()][selectedCivilizedUnit.getY()].getLandFeature().getLandFeatureType() != LandFeatureType.Ice) {
                switch (Game.map[selectedCivilizedUnit.getX()][selectedCivilizedUnit.getY()].getLandFeature().getLandFeatureType()) {
                    case Jungle -> {
                        return workerBuildJungleFarm();
                    }
                    case Forest -> {
                        return workerBuildForestFarm();
                    }
                    case Marsh -> {
                        return workerBuildMarshFarm();
                    }
                }
            } else return ("Want to build a farm on ice?!");
        }
        return message;
    }

    private String workerBuildJungleFarm() {
        if (currentTurnUser.getNation().hasTechnology(ImprovementType.JUNGLE_FARM.technology)) {
            Game.map[selectedCivilizedUnit.getX()][selectedCivilizedUnit.getY()].setImprovement(new Improvement(ImprovementType.FARM));
            //fixme Change an enum's field ?!
            Game.map[selectedCivilizedUnit.getX()][selectedCivilizedUnit.getY()].getLandType().foodGrowth += 1;
            if (hasResourceOfImprovement(ImprovementType.FARM).equals("yes"))
                currentTurnUser.getNation().addResource(Game.map[selectedCivilizedUnit.getX()][selectedCivilizedUnit.getY()].getResource().getResourceType());
            Game.map[selectedCivilizedUnit.getX()][selectedCivilizedUnit.getY()].setLandFeature(null);
            //TODO complete
            return ("Improvement has built successfully :)");
        } else return ("You don't have " + ImprovementType.JUNGLE_FARM.technology.name + " technology!");
    }

    private String workerBuildForestFarm() {
        if (currentTurnUser.getNation().hasTechnology(ImprovementType.FOREST_FARM.technology)) {
            Game.map[selectedCivilizedUnit.getX()][selectedCivilizedUnit.getY()].setImprovement(new Improvement(ImprovementType.FARM));
            //fixme Change an enum's field ?!
            Game.map[selectedCivilizedUnit.getX()][selectedCivilizedUnit.getY()].getLandType().foodGrowth += 1;
            if (hasResourceOfImprovement(ImprovementType.FARM).equals("yes"))
                currentTurnUser.getNation().addResource(Game.map[selectedCivilizedUnit.getX()][selectedCivilizedUnit.getY()].getResource().getResourceType());
            Game.map[selectedCivilizedUnit.getX()][selectedCivilizedUnit.getY()].setLandFeature(null);
            //TODO complete
            return ("Improvement has built successfully :)");
        } else return ("You don't have " + ImprovementType.FOREST_FARM.technology.name + " technology!");
    }

    private String workerBuildMarshFarm() {
        if (currentTurnUser.getNation().hasTechnology(ImprovementType.MARSH_FARM.technology)) {
            Game.map[selectedCivilizedUnit.getX()][selectedCivilizedUnit.getY()].setImprovement(new Improvement(ImprovementType.FARM));
            //fixme Change an enum's field ?!
            Game.map[selectedCivilizedUnit.getX()][selectedCivilizedUnit.getY()].getLandType().foodGrowth += 1;
            if (hasResourceOfImprovement(ImprovementType.FARM).equals("yes"))
                currentTurnUser.getNation().addResource(Game.map[selectedCivilizedUnit.getX()][selectedCivilizedUnit.getY()].getResource().getResourceType());
            Game.map[selectedCivilizedUnit.getX()][selectedCivilizedUnit.getY()].setLandFeature(null);
            //TODO complete
            return ("Improvement has built successfully :)");
        } else return ("You don't have " + ImprovementType.MARSH_FARM.technology.name + " technology!");
    }

    public String workerBuildMine() {
        String message;
        if ((message = canGenerallyBuildImprovement(ImprovementType.MINE)).equals("yes") && (message = hasResourceOfImprovement(ImprovementType.MINE)).equals("yes")) {
            if (Game.map[selectedCivilizedUnit.getX()][selectedCivilizedUnit.getY()].getLandFeature() == null) {
                Game.map[selectedCivilizedUnit.getX()][selectedCivilizedUnit.getY()].setImprovement(new Improvement(ImprovementType.MINE));
                //fixme Change an enum's field ?!
                Game.map[selectedCivilizedUnit.getX()][selectedCivilizedUnit.getY()].getLandType().foodGrowth += 1;
                currentTurnUser.getNation().addResource(Game.map[selectedCivilizedUnit.getX()][selectedCivilizedUnit.getY()].getResource().getResourceType());
                //TODO complete
                return ("Improvement has built successfully :)");
            } else {
                switch (Game.map[selectedCivilizedUnit.getX()][selectedCivilizedUnit.getY()].getLandFeature().getLandFeatureType()) {
                    case Jungle -> {
                        return workerBuildJungleMine();
                    }
                    case Forest -> {
                        return workerBuildForestMine();
                    }
                    case Marsh -> {
                        return workerBuildMarshMine();
                    }
                }
            }
        }
        return message;
    }

    public String workerBuildForestMine() {
        if (currentTurnUser.getNation().hasTechnology(ImprovementType.FOREST_MINE.technology)) {
            Game.map[selectedCivilizedUnit.getX()][selectedCivilizedUnit.getY()].setImprovement(new Improvement(ImprovementType.MINE));
            //fixme Change an enum's field ?!
            Game.map[selectedCivilizedUnit.getX()][selectedCivilizedUnit.getY()].getLandType().productionGrowth += 1;
            if (hasResourceOfImprovement(ImprovementType.MINE).equals("yes"))
                currentTurnUser.getNation().addResource(Game.map[selectedCivilizedUnit.getX()][selectedCivilizedUnit.getY()].getResource().getResourceType());
            Game.map[selectedCivilizedUnit.getX()][selectedCivilizedUnit.getY()].setLandFeature(null);
            //TODO complete
            return ("Improvement has built successfully :)");
        }
        return ("You don't have " + ImprovementType.FOREST_MINE.technology.name + " technology!");
    }

    public String workerBuildJungleMine() {
        if (currentTurnUser.getNation().hasTechnology(ImprovementType.JUNGLE_MINE.technology)) {
            Game.map[selectedCivilizedUnit.getX()][selectedCivilizedUnit.getY()].setImprovement(new Improvement(ImprovementType.MINE));
            //fixme Change an enum's field ?!
            Game.map[selectedCivilizedUnit.getX()][selectedCivilizedUnit.getY()].getLandType().productionGrowth += 1;
            if (hasResourceOfImprovement(ImprovementType.MINE).equals("yes"))
                currentTurnUser.getNation().addResource(Game.map[selectedCivilizedUnit.getX()][selectedCivilizedUnit.getY()].getResource().getResourceType());
            Game.map[selectedCivilizedUnit.getX()][selectedCivilizedUnit.getY()].setLandFeature(null);
            //TODO complete
            return ("Improvement has built successfully :)");
        }
        return ("You don't have " + ImprovementType.JUNGLE_MINE.technology.name + " technology!");
    }

    public String workerBuildMarshMine() {
        if (currentTurnUser.getNation().hasTechnology(ImprovementType.MARSH_MINE.technology)) {
            Game.map[selectedCivilizedUnit.getX()][selectedCivilizedUnit.getY()].setImprovement(new Improvement(ImprovementType.MINE));
            //fixme Change an enum's field ?!
            Game.map[selectedCivilizedUnit.getX()][selectedCivilizedUnit.getY()].getLandType().productionGrowth += 1;
            if (hasResourceOfImprovement(ImprovementType.MINE).equals("yes"))
                currentTurnUser.getNation().addResource(Game.map[selectedCivilizedUnit.getX()][selectedCivilizedUnit.getY()].getResource().getResourceType());
            Game.map[selectedCivilizedUnit.getX()][selectedCivilizedUnit.getY()].setLandFeature(null);
            //TODO complete
            return ("Improvement has built successfully :)");
        }
        return ("You don't have " + ImprovementType.MARSH_MINE.technology.name + " technology!");
    }

    public String workerBuildTradingPost() {
        String message;
        if ((message = canGenerallyBuildImprovement(ImprovementType.TRADING_POST)).equals("yes") && (message = isLandSuitable(ImprovementType.TRADING_POST)).equals("yes")) {
            Game.map[selectedCivilizedUnit.getX()][selectedCivilizedUnit.getY()].setImprovement(new Improvement(ImprovementType.TRADING_POST));
            //fixme Change an enum's field ?!
            Game.map[selectedCivilizedUnit.getX()][selectedCivilizedUnit.getY()].getLandType().coinGrowth += 1;
            //TODO complete
            return ("Improvement has built successfully :)");
        }
        return message;
    }

    public String workerBuildLumberMill() {
        String message;
        if ((message = canGenerallyBuildImprovement(ImprovementType.LUMBER_MILL)).equals("yes") && (message = isLandSuitable(ImprovementType.LUMBER_MILL)).equals("yes")) {
            Game.map[selectedCivilizedUnit.getX()][selectedCivilizedUnit.getY()].setImprovement(new Improvement(ImprovementType.LUMBER_MILL));
            //fixme Change an enum's field ?!
            Game.map[selectedCivilizedUnit.getX()][selectedCivilizedUnit.getY()].getLandType().productionGrowth += 1;
            //TODO complete
            return ("Improvement has built successfully :)");
        }
        return message;
    }

    public String workerBuildPasture() {
        String message;
        if ((message = canGenerallyBuildImprovement(ImprovementType.PASTURE)).equals("yes") && (message = hasResourceOfImprovement(ImprovementType.PASTURE)).equals("yes")) {
            Game.map[selectedCivilizedUnit.getX()][selectedCivilizedUnit.getY()].setImprovement(new Improvement(ImprovementType.PASTURE));
            currentTurnUser.getNation().addResource(Game.map[selectedCivilizedUnit.getX()][selectedCivilizedUnit.getY()].
                    getResource().getResourceType());
            //TODO complete
            return ("Improvement has built successfully :)");
        }
        return message;
    }

    public String workerBuildCamp() {
        String message;
        if ((message = canGenerallyBuildImprovement(ImprovementType.CAMP)).equals("yes") && (message = hasResourceOfImprovement(ImprovementType.CAMP)).equals("yes")) {
            Game.map[selectedCivilizedUnit.getX()][selectedCivilizedUnit.getY()].setImprovement(new Improvement(ImprovementType.CAMP));
            currentTurnUser.getNation().addResource(Game.map[selectedCivilizedUnit.getX()][selectedCivilizedUnit.getY()].
                    getResource().getResourceType());
            //TODO complete
            return ("Improvement has built successfully :)");
        }
        return message;
    }

    public String workerBuildPlantation() {
        String message;
        if ((message = canGenerallyBuildImprovement(ImprovementType.PLANTATION)).equals("yes") && (message = hasResourceOfImprovement(ImprovementType.PLANTATION)).equals("yes")) {
            Game.map[selectedCivilizedUnit.getX()][selectedCivilizedUnit.getY()].setImprovement(new Improvement(ImprovementType.PLANTATION));
            currentTurnUser.getNation().addResource(Game.map[selectedCivilizedUnit.getX()][selectedCivilizedUnit.getY()].
                    getResource().getResourceType());
            //TODO complete
            return ("Improvement has built successfully :)");
        }
        return message;
    }

    public String workerBuildQuarry() {
        String message;
        if ((message = canGenerallyBuildImprovement(ImprovementType.QUARRY)).equals("yes") && (message = hasResourceOfImprovement(ImprovementType.QUARRY)).equals("yes")) {
            Game.map[selectedCivilizedUnit.getX()][selectedCivilizedUnit.getY()].setImprovement(new Improvement(ImprovementType.QUARRY));
            currentTurnUser.getNation().addResource(Game.map[selectedCivilizedUnit.getX()][selectedCivilizedUnit.getY()].
                    getResource().getResourceType());
            //TODO complete
            return ("Improvement has built successfully :)");
        }
        return message;
    }

    public String workerBuildFactory() {
        String message;
        if ((message = canGenerallyBuildImprovement(ImprovementType.FACTORY)).equals("yes") && (message = isLandSuitable(ImprovementType.FACTORY)).equals("yes")) {
            Game.map[selectedCivilizedUnit.getX()][selectedCivilizedUnit.getY()].setImprovement(new Improvement(ImprovementType.FACTORY));
            //fixme Change an enum's field ?!
            Game.map[selectedCivilizedUnit.getX()][selectedCivilizedUnit.getY()].getLandType().productionGrowth += 2;
            //TODO complete
            return ("Improvement has built successfully :)");
        }
        return message;
    }

    public String workerRemoveFeature() {
        switch (Game.map[selectedCivilizedUnit.getX()][selectedCivilizedUnit.getY()].getLandFeature().getLandFeatureType()) {
            case Jungle, Forest, Marsh -> {
                Game.map[selectedCivilizedUnit.getX()][selectedCivilizedUnit.getY()].setLandFeature(null);
                return "Feature has been removed successfully";
                //TODO complete
            }
        }
        return "error!";
    }

    public String workerRemoveRoute() {
        if (Game.map[selectedCivilizedUnit.getX()][selectedCivilizedUnit.getY()].getImprovementType() == ImprovementType.ROAD) {
            //fixme just remove road, not other improvements
            Game.map[selectedCivilizedUnit.getX()][selectedCivilizedUnit.getY()].setImprovement(null);
            return "Route has been removed successfully!";
        }
        return "error";
    }

    public String workerRepair() {
        if (Game.map[selectedCivilizedUnit.getX()][selectedCivilizedUnit.getY()].getImprovement().isBroken()) {
            Game.map[selectedCivilizedUnit.getX()][selectedCivilizedUnit.getY()].getImprovement().setBroken(false);
            return "The improvement has been repaired successfully";
            //TODO complete
        }
        return "error";
    }

    private String canGenerallyBuildImprovement(ImprovementType improvementType) {
        if (selectedCivilizedUnit != null) {
            if (selectedCivilizedUnit.getCivilizedUnitType() == CivilizedUnitType.WORKER) {
                if (currentTurnUser.getNation().hasTechnology(improvementType.technology)) {
                    if (Game.map[selectedCivilizedUnit.getX()][selectedCivilizedUnit.getY()].getImprovement() == null) {
                        return "yes";
                    } else return ("There is already an Improvement here!");
                } else return ("You don't have " + improvementType.technology.name + " technology!");
            } else return ("The selected unit is not a Worker!");
        } else return ("Please select a Worker first!");
    }

    private String hasResourceOfImprovement(ImprovementType improvementType) {
        if (Game.map[selectedCivilizedUnit.getX()][selectedCivilizedUnit.getY()].getResource() != null)
            for (ResourceType resourceType : improvementType.resourcesGiven)
                if (resourceType == Game.map[selectedCivilizedUnit.getX()][selectedCivilizedUnit.getY()].getResource().
                        getResourceType())
                    return "yes";

        return ("There is not the suitable resource for " + improvementType.name + " here!");
    }

    private String isLandSuitable(ImprovementType improvementType) {
        if (improvementType.landTypes != null)
            for (LandType landType : improvementType.landTypes)
                if (landType == Game.map[selectedCivilizedUnit.getX()][selectedCivilizedUnit.getY()].getLandType())
                    return "yes";
        if (improvementType.landFeatureTypes != null &&
                Game.map[selectedCivilizedUnit.getX()][selectedCivilizedUnit.getY()].getLandFeature().getLandFeatureType() != null)
            for (LandFeatureType landFeatureType : improvementType.landFeatureTypes)
                if (landFeatureType == Game.map[selectedCivilizedUnit.getX()][selectedCivilizedUnit.getY()].getLandFeature().getLandFeatureType())
                    return "yes";

        return ("This Land isn't suitable for " + improvementType.name + "!");
    }

}
