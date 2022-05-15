package Controller.GameControllers;

import Enums.GameEnums.WorkerWorks;
import Model.Game;
import Model.Improvements.Improvement;
import Model.Improvements.ImprovementType;
import Model.LandFeatures.LandFeatureType;
import Model.Lands.LandType;
import Model.Resources.Enums.ResourceType;
import Model.Units.Enums.CivilizedUnitType;
import Model.Units.Enums.UnitStatus;

public class WorkerController extends GameController {

    private String updateWorkerBuildingStatus(ImprovementType improvementType) {
        selectedCivilizedUnit.setImprovementType(improvementType);
        selectedCivilizedUnit.setTurnsLeft(improvementType.initialTurns);
        selectedCivilizedUnit.setUnitStatus(UnitStatus.WORKING);
        return "The Worker is now working!\nThe improvement will be ready in " + improvementType.initialTurns + " turns";
    }

    public String setWorkerToBuildRoad(ImprovementType improvementType) {
        String message;
        if ((message = canGenerallyBuildImprovement(improvementType)).equals("yes")) {
            if ((Game.map[selectedCivilizedUnit.getLocation().x][selectedCivilizedUnit.getLocation().y].getLandFeature() == null &&
                    Game.map[selectedCivilizedUnit.getLocation().x][selectedCivilizedUnit.getLocation().y].getLandType() != LandType.Mountain) ||
                    (Game.map[selectedCivilizedUnit.getLocation().x][selectedCivilizedUnit.getLocation().y].getLandFeature().getLandFeatureType() != LandFeatureType.Ice &&
                            Game.map[selectedCivilizedUnit.getLocation().x][selectedCivilizedUnit.getLocation().y].getLandFeature().getLandFeatureType() != LandFeatureType.Watershed)) {
                return updateWorkerBuildingStatus(improvementType);
            }
        }
        return message;
    }

    public String setWorkerToBuildFarm() {
        ImprovementType improvementType = ImprovementType.FARM;
        String message;
        if ((message = canGenerallyBuildImprovement(improvementType)).equals("yes")) {
            if (Game.map[selectedCivilizedUnit.getLocation().x][selectedCivilizedUnit.getLocation().y].getLandFeature() == null) {
                return updateWorkerBuildingStatus(improvementType);
            } else if (Game.map[selectedCivilizedUnit.getLocation().x][selectedCivilizedUnit.getLocation().y].getLandFeature().getLandFeatureType() != LandFeatureType.Ice) {
                switch (Game.map[selectedCivilizedUnit.getLocation().x][selectedCivilizedUnit.getLocation().y].getLandFeature().getLandFeatureType()) {
                    case Jungle -> improvementType = ImprovementType.JUNGLE_FARM;
                    case Forest -> improvementType = ImprovementType.FOREST_FARM;
                    case Marsh -> improvementType = ImprovementType.MARSH_FARM;
                }
                if (currentTurnUser.getNation().hasTechnology(improvementType.technology))
                    return updateWorkerBuildingStatus(improvementType);
                else return ("You don't have " + improvementType.technology.name + " technology!");
            } else return ("Want to build a farm on ice?!");
        }
        return message;
    }

    public String setWorkerToBuildMine() {
        ImprovementType improvementType = ImprovementType.MINE;
        String message;
        if ((message = canGenerallyBuildImprovement(improvementType)).equals("yes") && (message = hasResourceOfImprovement(improvementType)).equals("yes")) {
            if (Game.map[selectedCivilizedUnit.getLocation().x][selectedCivilizedUnit.getLocation().y].getLandFeature() == null) {
                return updateWorkerBuildingStatus(improvementType);
            } else {
                switch (Game.map[selectedCivilizedUnit.getLocation().x][selectedCivilizedUnit.getLocation().y].getLandFeature().getLandFeatureType()) {
                    case Jungle -> improvementType = ImprovementType.JUNGLE_MINE;
                    case Forest -> improvementType = ImprovementType.FOREST_MINE;
                    case Marsh -> improvementType = ImprovementType.MARSH_MINE;
                }
                if (currentTurnUser.getNation().hasTechnology(improvementType.technology))
                    return updateWorkerBuildingStatus(improvementType);
                else return ("You don't have " + improvementType.technology.name + " technology!");
            }
        }
        return message;
    }

    public String setWorkerToRemoveFeature() {
        if (Game.map[selectedCivilizedUnit.getLocation().x][selectedCivilizedUnit.getLocation().y].getLandFeature() != null) {
            selectedCivilizedUnit.setUnitStatus(UnitStatus.WORKING);
            switch (Game.map[selectedCivilizedUnit.getLocation().x][selectedCivilizedUnit.getLocation().y].getLandFeature().getLandFeatureType()) {
                case Jungle -> {
                    selectedCivilizedUnit.setWorkerWorks(WorkerWorks.REMOVE_JUNGLE);
                    selectedCivilizedUnit.setTurnsLeft(WorkerWorks.REMOVE_JUNGLE.turns);
                }
                case Forest -> {
                    selectedCivilizedUnit.setWorkerWorks(WorkerWorks.REMOVE_FOREST);
                    selectedCivilizedUnit.setTurnsLeft(WorkerWorks.REMOVE_FOREST.turns);
                }
                case Marsh -> {
                    selectedCivilizedUnit.setWorkerWorks(WorkerWorks.REMOVE_MARSH);
                    selectedCivilizedUnit.setTurnsLeft(WorkerWorks.REMOVE_MARSH.turns);
                }
            }
            return "The feature will be removed!";
        }
        return "There is no feature on this land!";
    }

    public String setWorkerToRemoveRoute() {
        if (Game.map[selectedCivilizedUnit.getLocation().x][selectedCivilizedUnit.getLocation().y].getRoute() != null) {
            selectedCivilizedUnit.setUnitStatus(UnitStatus.WORKING);
            selectedCivilizedUnit.setWorkerWorks(WorkerWorks.REMOVE_ROUTE);
            selectedCivilizedUnit.setTurnsLeft(WorkerWorks.REMOVE_ROUTE.turns);
            return "The route will be removed!";
        }
        return "There isn't any route here!";
    }

    public String setWorkerToRepair() {
        if (Game.map[selectedCivilizedUnit.getLocation().x][selectedCivilizedUnit.getLocation().y].getImprovement() != null) {
            if (Game.map[selectedCivilizedUnit.getLocation().x][selectedCivilizedUnit.getLocation().y].getImprovement().isBroken()) {
                selectedCivilizedUnit.setUnitStatus(UnitStatus.WORKING);
                selectedCivilizedUnit.setWorkerWorks(WorkerWorks.REPAIR);
                selectedCivilizedUnit.setTurnsLeft(WorkerWorks.REPAIR.turns);
            } else return "It isn't broken!";
        }
        return "There isn't any improvement here!";
    }


    public String setWorkerToBuildNonResourcedImprovement(ImprovementType improvementType) {
        String message;
        if ((message = canGenerallyBuildImprovement(improvementType)).equals("yes") &&
                (message = isLandSuitable(improvementType)).equals("yes")) {
            updateWorkerBuildingStatus(improvementType);
        }
        return message;
    }

    public String setWorkerToBuildResourcedImprovement(ImprovementType improvementType) {
        String message;
        if ((message = canGenerallyBuildImprovement(improvementType)).equals("yes") &&
                (message = hasResourceOfImprovement(improvementType)).equals("yes")) {
            updateWorkerBuildingStatus(improvementType);
        }
        return message;
    }


    public void workerBuildRoad(ImprovementType improvementType) {
        Game.map[selectedCivilizedUnit.getLocation().x][selectedCivilizedUnit.getLocation().y].setRoute(new Improvement(improvementType));
        Game.map[selectedCivilizedUnit.getLocation().x][selectedCivilizedUnit.getLocation().y].setMovementCost(0);
    }

    public void workerBuildFarm() {
        Game.map[selectedCivilizedUnit.getLocation().x][selectedCivilizedUnit.getLocation().y].setImprovement(new Improvement(ImprovementType.FARM));
        Game.map[selectedCivilizedUnit.getLocation().x][selectedCivilizedUnit.getLocation().y].addFoodGrowth(1);
        if (hasResourceOfImprovement(ImprovementType.FARM).equals("yes"))
            currentTurnUser.getNation().addResource(Game.map[selectedCivilizedUnit.getLocation().x][selectedCivilizedUnit.getLocation().y].getResource().getResourceType());
    }

    private void workerBuildSpecialFarm(ImprovementType improvementType) {
        Game.map[selectedCivilizedUnit.getLocation().x][selectedCivilizedUnit.getLocation().y].setImprovement(new Improvement(ImprovementType.FARM));
        Game.map[selectedCivilizedUnit.getLocation().x][selectedCivilizedUnit.getLocation().y].addFoodGrowth(1);
        if (hasResourceOfImprovement(ImprovementType.FARM).equals("yes"))
            currentTurnUser.getNation().addResource(Game.map[selectedCivilizedUnit.getLocation().x][selectedCivilizedUnit.getLocation().y].getResource().getResourceType());
        Game.map[selectedCivilizedUnit.getLocation().x][selectedCivilizedUnit.getLocation().y].setLandFeature(null);
    }

    public void workerBuildMine() {
        Game.map[selectedCivilizedUnit.getLocation().x][selectedCivilizedUnit.getLocation().y].setImprovement(new Improvement(ImprovementType.MINE));
        Game.map[selectedCivilizedUnit.getLocation().x][selectedCivilizedUnit.getLocation().y].addProductionGrowth(1);
        currentTurnUser.getNation().addResource(Game.map[selectedCivilizedUnit.getLocation().x][selectedCivilizedUnit.getLocation().y].getResource().getResourceType());
    }

    public void workerBuildSpecialMine(ImprovementType improvementType) {
        Game.map[selectedCivilizedUnit.getLocation().x][selectedCivilizedUnit.getLocation().y].setImprovement(new Improvement(ImprovementType.MINE));
        Game.map[selectedCivilizedUnit.getLocation().x][selectedCivilizedUnit.getLocation().y].addProductionGrowth(1);
        currentTurnUser.getNation().addResource(Game.map[selectedCivilizedUnit.getLocation().x][selectedCivilizedUnit.getLocation().y].getResource().getResourceType());
        Game.map[selectedCivilizedUnit.getLocation().x][selectedCivilizedUnit.getLocation().y].setLandFeature(null);
    }


    public void workerRemoveFeature() {
        Game.map[selectedCivilizedUnit.getLocation().x][selectedCivilizedUnit.getLocation().y].setLandFeature(null);
    }

    public void workerRemoveRoute() {
        Game.map[selectedCivilizedUnit.getLocation().x][selectedCivilizedUnit.getLocation().y].setRoute(null);
    }

    public void workerRepair() {
        Game.map[selectedCivilizedUnit.getLocation().x][selectedCivilizedUnit.getLocation().y].getImprovement().setBroken(false);
    }

    public void workerBuildNonResourcedImprovement(ImprovementType improvementType) {
        Game.map[selectedCivilizedUnit.getLocation().x][selectedCivilizedUnit.getLocation().y].setImprovement(new Improvement(improvementType));
        switch (improvementType.currency) {
            //TODO also calculate land's currency's growths for showing them
            case Coin ->
                    Game.map[selectedCivilizedUnit.getLocation().x][selectedCivilizedUnit.getLocation().y].addCoinGrowth(improvementType.amount);
            case Food ->
                    Game.map[selectedCivilizedUnit.getLocation().x][selectedCivilizedUnit.getLocation().y].addFoodGrowth(improvementType.amount);
            case Production ->
                    Game.map[selectedCivilizedUnit.getLocation().x][selectedCivilizedUnit.getLocation().y].addProductionGrowth(improvementType.amount);
        }
    }

    public void workerBuildResourcedImprovement(ImprovementType improvementType) {
        Game.map[selectedCivilizedUnit.getLocation().x][selectedCivilizedUnit.getLocation().y].setImprovement(new Improvement(improvementType));
        currentTurnUser.getNation().addResource(Game.map[selectedCivilizedUnit.getLocation().x][selectedCivilizedUnit.getLocation().y].
                getResource().getResourceType());
    }


    private String canGenerallyBuildImprovement(ImprovementType improvementType) {
        if (selectedCivilizedUnit != null) {
            if (selectedCivilizedUnit.getCivilizedUnitType() == CivilizedUnitType.WORKER) {
                if (currentTurnUser.getNation().hasTechnology(improvementType.technology)) {
                    if (improvementType == ImprovementType.ROAD || improvementType == ImprovementType.RAILROAD) {
                        if (Game.map[selectedCivilizedUnit.getLocation().x][selectedCivilizedUnit.getLocation().y].getRoute() == null) {
                            return "yes";
                        } else return ("There is already a road here!");
                    } else {
                        if (Game.map[selectedCivilizedUnit.getLocation().x][selectedCivilizedUnit.getLocation().y].getImprovement() == null) {
                            return "yes";
                        } else return ("There is already an Improvement here!");
                    }
                } else return ("You don't have " + improvementType.technology.name + " technology!");
            } else return ("The selected unit is not a Worker!");
        } else return ("Please select a Worker first!");
    }

    private String hasResourceOfImprovement(ImprovementType improvementType) {
        if (Game.map[selectedCivilizedUnit.getLocation().x][selectedCivilizedUnit.getLocation().y].getResource() != null)
            for (ResourceType resourceType : improvementType.resourcesGiven)
                if (resourceType == Game.map[selectedCivilizedUnit.getLocation().x][selectedCivilizedUnit.getLocation().y].getResource().
                        getResourceType())
                    return "yes";

        return ("There is not the suitable resource for " + improvementType.name + " here!");
    }

    private String isLandSuitable(ImprovementType improvementType) {
        if (improvementType.landTypes != null)
            for (LandType landType : improvementType.landTypes)
                if (landType == Game.map[selectedCivilizedUnit.getLocation().x][selectedCivilizedUnit.getLocation().y].getLandType())
                    return "yes";
        if (improvementType.landFeatureTypes != null &&
                Game.map[selectedCivilizedUnit.getLocation().x][selectedCivilizedUnit.getLocation().y].getLandFeature().getLandFeatureType() != null)
            for (LandFeatureType landFeatureType : improvementType.landFeatureTypes)
                if (landFeatureType == Game.map[selectedCivilizedUnit.getLocation().x][selectedCivilizedUnit.getLocation().y].getLandFeature().getLandFeatureType())
                    return "yes";

        return ("This Land isn't suitable for " + improvementType.name + "!");
    }

}
