package Controller.GameControllers;

import Enums.GameEnums.WorkerWorks;
import Model.Game;
import Model.Improvements.Improvement;
import Model.Improvements.ImprovementType;
import Model.LandFeatures.LandFeatureType;
import Model.Lands.LandType;
import Model.Resources.Enums.ResourceType;
import Model.Units.CivilizedUnit;
import Model.Units.Enums.CivilizedUnitType;
import Model.Units.Enums.UnitStatus;
import java.util.regex.Matcher;

public class WorkerController extends GameController {

    public String setWorkerToBuildImprovement(Matcher matcher) {
        String name = matcher.group("name");
        ImprovementType toBeBuiltImprovement = null;
        for (ImprovementType improvementType : ImprovementType.values()) {
            if (improvementType.name.equalsIgnoreCase(name))
                toBeBuiltImprovement = improvementType;
        }
        if (toBeBuiltImprovement != null) {
            selectedCivilizedUnit.setWaitingForCommand(false);
            switch (name) {
                case "Farm" -> {
                    return setWorkerToBuildFarm();
                }
                case "Mine" -> {
                    return setWorkerToBuildMine();
                }
                case "Camp", "Pasture", "Plantation", "Quarry" -> {
                    return setWorkerToBuildResourcedImprovement(toBeBuiltImprovement);
                }
                case "Lumber Mill", "Trading Post", "Factory" -> {
                    return setWorkerToBuildNonResourcedImprovement(toBeBuiltImprovement);
                }
                case "Road", "Railroad" -> {
                    return setWorkerToBuildRoad(toBeBuiltImprovement);
                }
            }
        }
        return "Improvement name isn't correct";
    }

    private static String updateWorkerBuildingStatus(ImprovementType improvementType) {
        selectedCivilizedUnit.setImprovementType(improvementType);
        selectedCivilizedUnit.setTurnsLeft(improvementType.initialTurns);
        selectedCivilizedUnit.setUnitStatus(UnitStatus.WORKING);
        return "The Worker is now working!\nThe improvement will be ready in " + improvementType.initialTurns + " turns";
    }

    private String setWorkerToBuildRoad(ImprovementType improvementType) {
        String message;
        if ((message = canGenerallyBuildImprovement(improvementType)).equals("yes")) {
            if ((Game.map[selectedCivilizedUnit.getLocation().x][selectedCivilizedUnit.getLocation().y].getLandFeature() == null &&
                    Game.map[selectedCivilizedUnit.getLocation().x][selectedCivilizedUnit.getLocation().y].getLandType() != LandType.MOUNTAIN) ||
                    (Game.map[selectedCivilizedUnit.getLocation().x][selectedCivilizedUnit.getLocation().y].getLandFeature().getLandFeatureType() != LandFeatureType.ICE &&
                            Game.map[selectedCivilizedUnit.getLocation().x][selectedCivilizedUnit.getLocation().y].getLandFeature().getLandFeatureType() != LandFeatureType.WATERSHED)) {
                return updateWorkerBuildingStatus(improvementType);
            }
        }
        return message;
    }

    private static String setWorkerToBuildFarm() {
        ImprovementType improvementType = ImprovementType.FARM;
        String message;
        if ((message = canGenerallyBuildImprovement(improvementType)).equals("yes")) {
            if (Game.map[selectedCivilizedUnit.getLocation().x][selectedCivilizedUnit.getLocation().y].getLandFeature() == null) {
                return updateWorkerBuildingStatus(improvementType);
            } else if (Game.map[selectedCivilizedUnit.getLocation().x][selectedCivilizedUnit.getLocation().y].getLandFeature().getLandFeatureType() != LandFeatureType.ICE) {
                switch (Game.map[selectedCivilizedUnit.getLocation().x][selectedCivilizedUnit.getLocation().y].getLandFeature().getLandFeatureType()) {
                    case JUNGLE -> improvementType = ImprovementType.JUNGLE_FARM;
                    case FOREST -> improvementType = ImprovementType.FOREST_FARM;
                    case MARSH -> improvementType = ImprovementType.MARSH_FARM;
                }
                if (improvementType.technology == null || currentTurnUser.getNation().hasTechnology(improvementType.technology))
                    return updateWorkerBuildingStatus(improvementType);
                else return ("You don't have " + improvementType.technology.name + " technology!");
            } else return ("Want to build a farm on ice?!");
        }
        return message;
    }

    private static String setWorkerToBuildMine() {
        ImprovementType improvementType = ImprovementType.MINE;
        String message;
        if ((message = canGenerallyBuildImprovement(improvementType)).equals("yes") && (message = hasResourceOfImprovement(improvementType, selectedCivilizedUnit)).equals("yes")) {
            if (Game.map[selectedCivilizedUnit.getLocation().x][selectedCivilizedUnit.getLocation().y].getLandFeature() == null) {
                return updateWorkerBuildingStatus(improvementType);
            } else {
                switch (Game.map[selectedCivilizedUnit.getLocation().x][selectedCivilizedUnit.getLocation().y].getLandFeature().getLandFeatureType()) {
                    case JUNGLE -> improvementType = ImprovementType.JUNGLE_MINE;
                    case FOREST -> improvementType = ImprovementType.FOREST_MINE;
                    case MARSH -> improvementType = ImprovementType.MARSH_MINE;
                }
                if (improvementType.technology == null || currentTurnUser.getNation().hasTechnology(improvementType.technology))
                    return updateWorkerBuildingStatus(improvementType);
                else return ("You don't have " + improvementType.technology.name + " technology!");
            }
        }
        return message;
    }

    public String setWorkerToRemoveFeature() {
        if (Game.map[selectedCivilizedUnit.getLocation().x][selectedCivilizedUnit.getLocation().y].getLandFeature() != null) {
            selectedCivilizedUnit.setUnitStatus(UnitStatus.WORKING);
            selectedCivilizedUnit.setWaitingForCommand(false);
            switch (Game.map[selectedCivilizedUnit.getLocation().x][selectedCivilizedUnit.getLocation().y].getLandFeature().getLandFeatureType()) {
                case JUNGLE -> {
                    selectedCivilizedUnit.setWorkerWorks(WorkerWorks.REMOVE_JUNGLE);
                    selectedCivilizedUnit.setTurnsLeft(WorkerWorks.REMOVE_JUNGLE.turns);
                }
                case FOREST -> {
                    selectedCivilizedUnit.setWorkerWorks(WorkerWorks.REMOVE_FOREST);
                    selectedCivilizedUnit.setTurnsLeft(WorkerWorks.REMOVE_FOREST.turns);
                }
                case MARSH -> {
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
            selectedCivilizedUnit.setWaitingForCommand(false);
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
                selectedCivilizedUnit.setWaitingForCommand(false);
                selectedCivilizedUnit.setUnitStatus(UnitStatus.WORKING);
                selectedCivilizedUnit.setWorkerWorks(WorkerWorks.REPAIR);
                selectedCivilizedUnit.setTurnsLeft(WorkerWorks.REPAIR.turns);
                return "The improvement will be repaired";
            } else return "It isn't broken!";
        }
        return "There isn't any improvement here!";
    }


    private String setWorkerToBuildNonResourcedImprovement(ImprovementType improvementType) {
        String message;
        if ((message = canGenerallyBuildImprovement(improvementType)).equals("yes") &&
                (message = isLandSuitable(improvementType)).equals("yes")) {
            return updateWorkerBuildingStatus(improvementType);
        }
        return message;
    }

    private String setWorkerToBuildResourcedImprovement(ImprovementType improvementType) {
        String message;
        if ((message = canGenerallyBuildImprovement(improvementType)).equals("yes") &&
                (message = hasResourceOfImprovement(improvementType,selectedCivilizedUnit)).equals("yes")) {
            return updateWorkerBuildingStatus(improvementType);

        }
        return message;
    }


    public static void workerBuildImprovement (CivilizedUnit worker) {
        switch (worker.getImprovementType()) {
            case FARM -> workerBuildFarm(worker);
            case JUNGLE_FARM, FOREST_FARM, MARSH_FARM -> workerBuildSpecialFarm(worker);
            case MINE -> workerBuildMine(worker);
            case JUNGLE_MINE, FOREST_MINE, MARSH_MINE -> workerBuildSpecialMine(worker);
            case CAMP, PASTURE, PLANTATION, QUARRY -> workerBuildResourcedImprovement(worker);
            case LUMBER_MILL, TRADING_POST, FACTORY -> workerBuildNonResourcedImprovement(worker);
            case ROAD, RAILROAD -> workerBuildRoad(worker);
        }
    }

    public static void workerWork (CivilizedUnit worker) {
        switch (worker.getWorkerWorks()) {
            case REPAIR -> workerRepair(worker);
            case REMOVE_ROUTE -> workerRemoveRoute(worker);
            case REMOVE_JUNGLE, REMOVE_FOREST, REMOVE_MARSH -> workerRemoveFeature(worker);
        }
    }

    public static void workerBuildRoad(CivilizedUnit worker) {
        Game.map[worker.getLocation().x][worker.getLocation().y].setRoute(new Improvement(worker.getImprovementType()));
        Game.map[worker.getLocation().x][worker.getLocation().y].setMovementCost(0);
    }

    public static void workerBuildFarm(CivilizedUnit worker) {
        Game.map[worker.getLocation().x][worker.getLocation().y].setImprovement(new Improvement(ImprovementType.FARM));
        Game.map[worker.getLocation().x][worker.getLocation().y].addFoodGrowth(1);
        if (hasResourceOfImprovement(ImprovementType.FARM, worker).equals("yes"))
            currentTurnUser.getNation().addResource(Game.map[worker.getLocation().x][worker.getLocation().y].getResource().getResourceType());
    }

    public static void workerBuildSpecialFarm(CivilizedUnit worker) {
        Game.map[worker.getLocation().x][worker.getLocation().y].setImprovement(new Improvement(ImprovementType.FARM));
        Game.map[worker.getLocation().x][worker.getLocation().y].addFoodGrowth(1);
        if (hasResourceOfImprovement(ImprovementType.FARM, worker).equals("yes"))
            currentTurnUser.getNation().addResource(Game.map[worker.getLocation().x][worker.getLocation().y].getResource().getResourceType());
        Game.map[worker.getLocation().x][worker.getLocation().y].setLandFeature(null);
    }

    public static void workerBuildMine(CivilizedUnit worker) {
        Game.map[worker.getLocation().x][worker.getLocation().y].setImprovement(new Improvement(ImprovementType.MINE));
        Game.map[worker.getLocation().x][worker.getLocation().y].addProductionGrowth(1);
        currentTurnUser.getNation().addResource(Game.map[worker.getLocation().x][worker.getLocation().y].getResource().getResourceType());
    }

    public static void workerBuildSpecialMine(CivilizedUnit worker) {
        Game.map[worker.getLocation().x][worker.getLocation().y].setImprovement(new Improvement(ImprovementType.MINE));
        Game.map[worker.getLocation().x][worker.getLocation().y].addProductionGrowth(1);
        currentTurnUser.getNation().addResource(Game.map[worker.getLocation().x][worker.getLocation().y].getResource().getResourceType());
        Game.map[worker.getLocation().x][worker.getLocation().y].setLandFeature(null);
    }


    public static void workerRemoveFeature(CivilizedUnit worker) {
        Game.map[worker.getLocation().x][worker.getLocation().y].setLandFeature(null);
    }

    public static void workerRemoveRoute(CivilizedUnit worker) {
        Game.map[worker.getLocation().x][worker.getLocation().y].setRoute(null);
    }

    public static void workerRepair(CivilizedUnit worker) {
        Game.map[worker.getLocation().x][worker.getLocation().y].getImprovement().setBroken(false);
    }

    public static void workerBuildNonResourcedImprovement(CivilizedUnit worker) {
        ImprovementType improvementType = worker.getImprovementType();
        Game.map[worker.getLocation().x][worker.getLocation().y].setImprovement(new Improvement(improvementType));
        switch (improvementType.currency) {
            //TODO also calculate land's currency's growths for showing them
            case COIN ->
                    Game.map[worker.getLocation().x][worker.getLocation().y].addCoinGrowth(improvementType.amount);
            case FOOD ->
                    Game.map[worker.getLocation().x][worker.getLocation().y].addFoodGrowth(improvementType.amount);
            case PRODUCTION ->
                    Game.map[worker.getLocation().x][worker.getLocation().y].addProductionGrowth(improvementType.amount);
        }
    }

    public static void workerBuildResourcedImprovement(CivilizedUnit worker) {
        Game.map[worker.getLocation().x][worker.getLocation().y].setImprovement(new Improvement(worker.getImprovementType()));
        currentTurnUser.getNation().addResource(Game.map[worker.getLocation().x][worker.getLocation().y].
                getResource().getResourceType());
    }


    private static String canGenerallyBuildImprovement(ImprovementType improvementType) {
        if (selectedCivilizedUnit != null) {
            if (selectedCivilizedUnit.getCivilizedUnitType() == CivilizedUnitType.WORKER) {
                if (selectedCivilizedUnit.getWorkerWorks() == null && selectedCivilizedUnit.getImprovementType() == null) {
                    if (improvementType.technology == null || currentTurnUser.getNation().hasTechnology(improvementType.technology)) {
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
                } else return "The worker is busy now!";
            } else return ("The selected unit is not a Worker!");
        } else return ("Please select a Worker first!");
    }


    public static String hasResourceOfImprovement(ImprovementType improvementType, CivilizedUnit worker) {
        if (Game.map[worker.getLocation().x][worker.getLocation().y].getResource() != null)
            for (ResourceType resourceType : improvementType.resourcesGiven)
                if (resourceType == Game.map[worker.getLocation().x][worker.getLocation().y].getResource().
                        getResourceType())
                    return "yes";

        return ("There is not the suitable resource for " + improvementType.name + " here!");
    }


    public static String isLandSuitable(ImprovementType improvementType) {
        if (improvementType.landTypes != null)
            for (LandType landType : improvementType.landTypes)
                if (landType == Game.map[selectedCivilizedUnit.getLocation().x][selectedCivilizedUnit.getLocation().y].getLandType())
                    return "yes";
        if (improvementType.landFeatureTypes != null &&
                Game.map[selectedCivilizedUnit.getLocation().x][selectedCivilizedUnit.getLocation().y].getLandFeature() != null)
            for (LandFeatureType landFeatureType : improvementType.landFeatureTypes)
                if (landFeatureType == Game.map[selectedCivilizedUnit.getLocation().x][selectedCivilizedUnit.getLocation().y].getLandFeature().getLandFeatureType())
                    return "yes";

        return ("This Land isn't suitable for " + improvementType.name + "!");
    }

}