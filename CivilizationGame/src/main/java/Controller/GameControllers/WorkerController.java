package Controller.GameControllers;

import Model.Game;
import Model.Improvements.Improvement;
import Model.Improvements.ImprovementType;
import Model.LandFeatures.LandFeatureType;
import Model.Lands.LandType;
import Model.Resources.Enums.ResourceType;
import Model.Units.Enums.CivilizedUnitType;

public class WorkerController extends GameController {

    public String workerBuildRoad(ImprovementType improvementType) {
        String message;
        if ((message = canGenerallyBuildImprovement(improvementType)).equals("yes")) {
            if ((Game.map[selectedCivilizedUnit.getX()][selectedCivilizedUnit.getY()].getLandFeature() == null &&
                    Game.map[selectedCivilizedUnit.getX()][selectedCivilizedUnit.getY()].getLandType() != LandType.Mountain) ||
                    (Game.map[selectedCivilizedUnit.getX()][selectedCivilizedUnit.getY()].getLandFeature().getLandFeatureType() != LandFeatureType.Ice &&
                            Game.map[selectedCivilizedUnit.getX()][selectedCivilizedUnit.getY()].getLandFeature().getLandFeatureType() != LandFeatureType.Watershed)) {
                Game.map[selectedCivilizedUnit.getX()][selectedCivilizedUnit.getY()].setRoute(new Improvement(improvementType));
                Game.map[selectedCivilizedUnit.getX()][selectedCivilizedUnit.getY()].setMovementCost(0);
            }
        }
        return message;
    }

    public String workerBuildFarm() {
        String message;
        if ((message = canGenerallyBuildImprovement(ImprovementType.FARM)).equals("yes")) {
            if (Game.map[selectedCivilizedUnit.getX()][selectedCivilizedUnit.getY()].getLandFeature() == null) {
                Game.map[selectedCivilizedUnit.getX()][selectedCivilizedUnit.getY()].setImprovement(new Improvement(ImprovementType.FARM));
                Game.map[selectedCivilizedUnit.getX()][selectedCivilizedUnit.getY()].addFoodGrowth(1);
                if (hasResourceOfImprovement(ImprovementType.FARM).equals("yes"))
                    currentTurnUser.getNation().addResource(Game.map[selectedCivilizedUnit.getX()][selectedCivilizedUnit.getY()].getResource().getResourceType());
                return ("Improvement has built successfully :)");
            } else if (Game.map[selectedCivilizedUnit.getX()][selectedCivilizedUnit.getY()].getLandFeature().getLandFeatureType() != LandFeatureType.Ice) {
                switch (Game.map[selectedCivilizedUnit.getX()][selectedCivilizedUnit.getY()].getLandFeature().getLandFeatureType()) {
                    case Jungle -> {
                        return workerBuildSpecialFarm(ImprovementType.JUNGLE_FARM);
                    }
                    case Forest -> {
                        return workerBuildSpecialFarm(ImprovementType.FOREST_FARM);
                    }
                    case Marsh -> {
                        return workerBuildSpecialFarm(ImprovementType.MARSH_FARM);
                    }
                }
            } else return ("Want to build a farm on ice?!");
        }
        return message;
    }

    private String workerBuildSpecialFarm(ImprovementType improvementType) {
        if (currentTurnUser.getNation().hasTechnology(improvementType.technology)) {
            Game.map[selectedCivilizedUnit.getX()][selectedCivilizedUnit.getY()].setImprovement(new Improvement(ImprovementType.FARM));
            Game.map[selectedCivilizedUnit.getX()][selectedCivilizedUnit.getY()].addFoodGrowth(1);
            if (hasResourceOfImprovement(ImprovementType.FARM).equals("yes"))
                currentTurnUser.getNation().addResource(Game.map[selectedCivilizedUnit.getX()][selectedCivilizedUnit.getY()].getResource().getResourceType());
            Game.map[selectedCivilizedUnit.getX()][selectedCivilizedUnit.getY()].setLandFeature(null);
            return ("Improvement has built successfully :)");
        } else return ("You don't have " + improvementType.technology.name + " technology!");
    }

    public String workerBuildMine() {
        String message;
        if ((message = canGenerallyBuildImprovement(ImprovementType.MINE)).equals("yes") && (message = hasResourceOfImprovement(ImprovementType.MINE)).equals("yes")) {
            if (Game.map[selectedCivilizedUnit.getX()][selectedCivilizedUnit.getY()].getLandFeature() == null) {
                Game.map[selectedCivilizedUnit.getX()][selectedCivilizedUnit.getY()].setImprovement(new Improvement(ImprovementType.MINE));
                Game.map[selectedCivilizedUnit.getX()][selectedCivilizedUnit.getY()].addFoodGrowth(1);
                currentTurnUser.getNation().addResource(Game.map[selectedCivilizedUnit.getX()][selectedCivilizedUnit.getY()].getResource().getResourceType());
                return ("Improvement has built successfully :)");
            } else {
                switch (Game.map[selectedCivilizedUnit.getX()][selectedCivilizedUnit.getY()].getLandFeature().getLandFeatureType()) {
                    case Jungle -> {
                        return workerBuildSpecialMine(ImprovementType.JUNGLE_MINE);
                    }
                    case Forest -> {
                        return workerBuildSpecialMine(ImprovementType.FOREST_MINE);
                    }
                    case Marsh -> {
                        return workerBuildSpecialMine(ImprovementType.MARSH_MINE);
                    }
                }
            }
        }
        return message;
    }

    public String workerBuildSpecialMine(ImprovementType improvementType) {
        if (currentTurnUser.getNation().hasTechnology(improvementType.technology)) {
            Game.map[selectedCivilizedUnit.getX()][selectedCivilizedUnit.getY()].setImprovement(new Improvement(ImprovementType.MINE));
            Game.map[selectedCivilizedUnit.getX()][selectedCivilizedUnit.getY()].addProductionGrowth(1);
            if (hasResourceOfImprovement(ImprovementType.MINE).equals("yes"))
                currentTurnUser.getNation().addResource(Game.map[selectedCivilizedUnit.getX()][selectedCivilizedUnit.getY()].getResource().getResourceType());
            Game.map[selectedCivilizedUnit.getX()][selectedCivilizedUnit.getY()].setLandFeature(null);
            return ("Improvement has built successfully :)");
        }
        return ("You don't have " + improvementType.technology.name + " technology!");
    }


    public String workerRemoveFeature() {
        if (Game.map[selectedCivilizedUnit.getX()][selectedCivilizedUnit.getY()].getLandFeature() != null) {
            switch (Game.map[selectedCivilizedUnit.getX()][selectedCivilizedUnit.getY()].getLandFeature().getLandFeatureType()) {
                case Jungle, Forest, Marsh -> {
                    Game.map[selectedCivilizedUnit.getX()][selectedCivilizedUnit.getY()].setLandFeature(null);
                    return "Feature has been removed successfully";
                }
            }
        } return "There is no feature on this land!";
    }

    public String workerRemoveRoute() {
        if (Game.map[selectedCivilizedUnit.getX()][selectedCivilizedUnit.getY()].getRoute() != null) {
            Game.map[selectedCivilizedUnit.getX()][selectedCivilizedUnit.getY()].setRoute(null);
            return "Route has been removed successfully!";
        }
        return "There isn't any route here!";
    }

    public String workerRepair() {
        if (Game.map[selectedCivilizedUnit.getX()][selectedCivilizedUnit.getY()].getImprovement() != null) {
            if (Game.map[selectedCivilizedUnit.getX()][selectedCivilizedUnit.getY()].getImprovement().isBroken()) {
                Game.map[selectedCivilizedUnit.getX()][selectedCivilizedUnit.getY()].getImprovement().setBroken(false);
                return "The improvement has been repaired successfully";
            } else return "It isn't broken!";
        } else return "There isn't any improvement here!";
    }

    public String workerBuildNonResourcedImprovement(ImprovementType improvementType) {
        String message;
        if ((message = canGenerallyBuildImprovement(improvementType)).equals("yes") && (message = isLandSuitable(improvementType)).equals("yes")) {
            Game.map[selectedCivilizedUnit.getX()][selectedCivilizedUnit.getY()].setImprovement(new Improvement(improvementType));
            switch (improvementType.currency) {
                //TODO also calculate land's currency's growths for showing them
                case Coin -> Game.map[selectedCivilizedUnit.getX()][selectedCivilizedUnit.getY()].addCoinGrowth(improvementType.amount);
                case Food -> Game.map[selectedCivilizedUnit.getX()][selectedCivilizedUnit.getY()].addFoodGrowth(improvementType.amount);
                case Production -> Game.map[selectedCivilizedUnit.getX()][selectedCivilizedUnit.getY()].addProductionGrowth(improvementType.amount);
            }
            return ("Improvement has built successfully :)");
        }
        return message;
    }

    public String workerBuildResourcedImprovement(ImprovementType improvementType) {
        String message;
        if ((message = canGenerallyBuildImprovement(improvementType)).equals("yes") && (message = hasResourceOfImprovement(improvementType)).equals("yes")) {
            Game.map[selectedCivilizedUnit.getX()][selectedCivilizedUnit.getY()].setImprovement(new Improvement(improvementType));
            currentTurnUser.getNation().addResource(Game.map[selectedCivilizedUnit.getX()][selectedCivilizedUnit.getY()].
                    getResource().getResourceType());
            return ("Improvement has built successfully :)");
        }
        return message;
    }

    private String canGenerallyBuildImprovement(ImprovementType improvementType) {
        if (selectedCivilizedUnit != null) {
            if (selectedCivilizedUnit.getCivilizedUnitType() == CivilizedUnitType.WORKER) {
                if (currentTurnUser.getNation().hasTechnology(improvementType.technology)) {
                    if (improvementType == ImprovementType.ROAD || improvementType == ImprovementType.RAILROAD) {
                        if (Game.map[selectedCivilizedUnit.getX()][selectedCivilizedUnit.getY()].getRoute() == null) {
                            return "yes";
                        } else return ("There is already a road here!");
                    } else {
                        if (Game.map[selectedCivilizedUnit.getX()][selectedCivilizedUnit.getY()].getImprovement() == null) {
                            return "yes";
                        } else return ("There is already an Improvement here!");
                    }
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