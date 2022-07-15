package sut.civilization.Controller.GameControllers;

import sut.civilization.Enums.GameEnums.WorkerWorks;
import sut.civilization.Model.Classes.Improvement;
import sut.civilization.Model.Classes.Land;
import sut.civilization.Model.ModulEnums.ImprovementType;
import sut.civilization.Model.ModulEnums.LandFeatureType;
import sut.civilization.Model.ModulEnums.LandType;
import sut.civilization.Model.ModulEnums.ResourceType;
import sut.civilization.Model.Classes.CivilizedUnit;
import sut.civilization.Model.ModulEnums.CivilizedUnitType;
import sut.civilization.Model.ModulEnums.UnitStatus;
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
                case "Farm" : {
                    return setWorkerToBuildFarm();
                }
                case "Mine" : {
                    return setWorkerToBuildMine();
                }
                case "Camp":
                case "Pasture":
                case "Plantation":
                case "Quarry" : {
                    return setWorkerToBuildResourcedImprovement(toBeBuiltImprovement);
                }
                case "Lumber Mill":
                case "Trading Post":
                case "Factory" : {
                    return setWorkerToBuildNonResourcedImprovement(toBeBuiltImprovement);
                }
                case "Road":
                case "Railroad" : {
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
            if ((Land.map[selectedCivilizedUnit.getLocation().x][selectedCivilizedUnit.getLocation().y].getLandFeature() == null &&
                    Land.map[selectedCivilizedUnit.getLocation().x][selectedCivilizedUnit.getLocation().y].getLandType() != LandType.MOUNTAIN) ||
                    (Land.map[selectedCivilizedUnit.getLocation().x][selectedCivilizedUnit.getLocation().y].getLandFeature().getLandFeatureType() != LandFeatureType.ICE &&
                            Land.map[selectedCivilizedUnit.getLocation().x][selectedCivilizedUnit.getLocation().y].getLandFeature().getLandFeatureType() != LandFeatureType.WATERSHED)) {
                return updateWorkerBuildingStatus(improvementType);
            }
        }
        return message;
    }

    private static String setWorkerToBuildFarm() {
        ImprovementType improvementType = ImprovementType.FARM;
        String message;
        if ((message = canGenerallyBuildImprovement(improvementType)).equals("yes")) {
            if (Land.map[selectedCivilizedUnit.getLocation().x][selectedCivilizedUnit.getLocation().y].getLandFeature() == null) {
                return updateWorkerBuildingStatus(improvementType);
            } else if (Land.map[selectedCivilizedUnit.getLocation().x][selectedCivilizedUnit.getLocation().y].getLandFeature().getLandFeatureType() != LandFeatureType.ICE) {
                switch (Land.map[selectedCivilizedUnit.getLocation().x][selectedCivilizedUnit.getLocation().y].getLandFeature().getLandFeatureType()) {
                    case JUNGLE:
                        improvementType = ImprovementType.JUNGLE_FARM;
                        break;
                    case FOREST:
                        improvementType = ImprovementType.FOREST_FARM;
                        break;
                    case MARSH:
                        improvementType = ImprovementType.MARSH_FARM;
                        break;
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
            if (Land.map[selectedCivilizedUnit.getLocation().x][selectedCivilizedUnit.getLocation().y].getLandFeature() == null) {
                return updateWorkerBuildingStatus(improvementType);
            } else {
                switch (Land.map[selectedCivilizedUnit.getLocation().x][selectedCivilizedUnit.getLocation().y].getLandFeature().getLandFeatureType()) {
                    case JUNGLE:
                        improvementType = ImprovementType.JUNGLE_MINE;
                        break;
                    case FOREST:
                        improvementType = ImprovementType.FOREST_MINE;
                        break;
                    case MARSH:
                        improvementType = ImprovementType.MARSH_MINE;
                        break;
                }
                if (improvementType.technology == null || currentTurnUser.getNation().hasTechnology(improvementType.technology))
                    return updateWorkerBuildingStatus(improvementType);
                else return ("You don't have " + improvementType.technology.name + " technology!");
            }
        }
        return message;
    }

    public String setWorkerToRemoveFeature() {
        if (Land.map[selectedCivilizedUnit.getLocation().x][selectedCivilizedUnit.getLocation().y].getLandFeature() != null) {
            selectedCivilizedUnit.setUnitStatus(UnitStatus.WORKING);
            selectedCivilizedUnit.setWaitingForCommand(false);
            switch (Land.map[selectedCivilizedUnit.getLocation().x][selectedCivilizedUnit.getLocation().y].getLandFeature().getLandFeatureType()) {
                case JUNGLE:
                    selectedCivilizedUnit.setWorkerWorks(WorkerWorks.REMOVE_JUNGLE);
                    selectedCivilizedUnit.setTurnsLeft(WorkerWorks.REMOVE_JUNGLE.turns);
                    break;
                case FOREST:
                    selectedCivilizedUnit.setWorkerWorks(WorkerWorks.REMOVE_FOREST);
                    selectedCivilizedUnit.setTurnsLeft(WorkerWorks.REMOVE_FOREST.turns);
                    break;
                case MARSH:
                    selectedCivilizedUnit.setWorkerWorks(WorkerWorks.REMOVE_MARSH);
                    selectedCivilizedUnit.setTurnsLeft(WorkerWorks.REMOVE_MARSH.turns);
                    break;
            }
            return "The feature will be removed!";
        }
        return "There is no feature on this land!";
    }

    public String setWorkerToRemoveRoute() {
        if (Land.map[selectedCivilizedUnit.getLocation().x][selectedCivilizedUnit.getLocation().y].getRoute() != null) {
            selectedCivilizedUnit.setWaitingForCommand(false);
            selectedCivilizedUnit.setUnitStatus(UnitStatus.WORKING);
            selectedCivilizedUnit.setWorkerWorks(WorkerWorks.REMOVE_ROUTE);
            selectedCivilizedUnit.setTurnsLeft(WorkerWorks.REMOVE_ROUTE.turns);
            return "The route will be removed!";
        }
        return "There isn't any route here!";
    }

    public String setWorkerToRepair() {
        if (Land.map[selectedCivilizedUnit.getLocation().x][selectedCivilizedUnit.getLocation().y].getImprovement() != null) {
            if (Land.map[selectedCivilizedUnit.getLocation().x][selectedCivilizedUnit.getLocation().y].getImprovement().isBroken()) {
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
            case FARM:
                workerBuildFarm(worker);
                break;
            case JUNGLE_FARM:
            case FOREST_FARM:
            case MARSH_FARM:
                workerBuildSpecialFarm(worker);
                break;
            case MINE:
                workerBuildMine(worker);
                break;
            case JUNGLE_MINE:
            case FOREST_MINE:
            case MARSH_MINE:
                workerBuildSpecialMine(worker);
                break;
            case CAMP:
            case PASTURE:
            case PLANTATION:
            case QUARRY:
                workerBuildResourcedImprovement(worker);
                break;
            case LUMBER_MILL:
            case TRADING_POST:
            case FACTORY:
                workerBuildNonResourcedImprovement(worker);
                break;
            case ROAD:
            case RAILROAD:
                workerBuildRoad(worker);
                break;
        }
    }

    public static void workerWork (CivilizedUnit worker) {
        switch (worker.getWorkerWorks()) {
            case REPAIR:
                workerRepair(worker);
                break;
            case REMOVE_ROUTE:
                workerRemoveRoute(worker);
                break;
            case REMOVE_JUNGLE:
            case REMOVE_FOREST:
            case REMOVE_MARSH:
                workerRemoveFeature(worker);
                break;
        }
    }

    public static void workerBuildRoad(CivilizedUnit worker) {
        Land.map[worker.getLocation().x][worker.getLocation().y].setRoute(new Improvement(worker.getImprovementType()));
        Land.map[worker.getLocation().x][worker.getLocation().y].setMovementCost(0);
    }

    public static void workerBuildFarm(CivilizedUnit worker) {
        Land.map[worker.getLocation().x][worker.getLocation().y].setImprovement(new Improvement(ImprovementType.FARM));
        Land.map[worker.getLocation().x][worker.getLocation().y].addFoodGrowth(1);
        if (hasResourceOfImprovement(ImprovementType.FARM, worker).equals("yes"))
            currentTurnUser.getNation().addResource(Land.map[worker.getLocation().x][worker.getLocation().y].getResource().getResourceType());
    }

    public static void workerBuildSpecialFarm(CivilizedUnit worker) {
        Land.map[worker.getLocation().x][worker.getLocation().y].setImprovement(new Improvement(ImprovementType.FARM));
        Land.map[worker.getLocation().x][worker.getLocation().y].addFoodGrowth(1);
        if (hasResourceOfImprovement(ImprovementType.FARM, worker).equals("yes"))
            currentTurnUser.getNation().addResource(Land.map[worker.getLocation().x][worker.getLocation().y].getResource().getResourceType());
        Land.map[worker.getLocation().x][worker.getLocation().y].setLandFeature(null);
    }

    public static void workerBuildMine(CivilizedUnit worker) {
        Land.map[worker.getLocation().x][worker.getLocation().y].setImprovement(new Improvement(ImprovementType.MINE));
        Land.map[worker.getLocation().x][worker.getLocation().y].addProductionGrowth(1);
        currentTurnUser.getNation().addResource(Land.map[worker.getLocation().x][worker.getLocation().y].getResource().getResourceType());
    }

    public static void workerBuildSpecialMine(CivilizedUnit worker) {
        Land.map[worker.getLocation().x][worker.getLocation().y].setImprovement(new Improvement(ImprovementType.MINE));
        Land.map[worker.getLocation().x][worker.getLocation().y].addProductionGrowth(1);
        currentTurnUser.getNation().addResource(Land.map[worker.getLocation().x][worker.getLocation().y].getResource().getResourceType());
        Land.map[worker.getLocation().x][worker.getLocation().y].setLandFeature(null);
    }


    public static void workerRemoveFeature(CivilizedUnit worker) {
        Land.map[worker.getLocation().x][worker.getLocation().y].setLandFeature(null);
    }

    public static void workerRemoveRoute(CivilizedUnit worker) {
        Land.map[worker.getLocation().x][worker.getLocation().y].setRoute(null);
    }

    public static void workerRepair(CivilizedUnit worker) {
        Land.map[worker.getLocation().x][worker.getLocation().y].getImprovement().setBroken(false);
    }

    public static void workerBuildNonResourcedImprovement(CivilizedUnit worker) {
        ImprovementType improvementType = worker.getImprovementType();
        Land.map[worker.getLocation().x][worker.getLocation().y].setImprovement(new Improvement(improvementType));
        //TODO also calculate land's currency's growths for showing them
        switch (improvementType.currency) {
            case GOLD:
                Land.map[worker.getLocation().x][worker.getLocation().y].addCoinGrowth(improvementType.amount);
                break;
            case FOOD:
                Land.map[worker.getLocation().x][worker.getLocation().y].addFoodGrowth(improvementType.amount);
                break;
            case PRODUCTION:
                Land.map[worker.getLocation().x][worker.getLocation().y].addProductionGrowth(improvementType.amount);
                break;
        }
    }

    public static void workerBuildResourcedImprovement(CivilizedUnit worker) {
        Land.map[worker.getLocation().x][worker.getLocation().y].setImprovement(new Improvement(worker.getImprovementType()));
        currentTurnUser.getNation().addResource(Land.map[worker.getLocation().x][worker.getLocation().y].
                getResource().getResourceType());
    }


    private static String canGenerallyBuildImprovement(ImprovementType improvementType) {
        if (selectedCivilizedUnit != null) {
            if (selectedCivilizedUnit.getCivilizedUnitType() == CivilizedUnitType.WORKER) {
                if (selectedCivilizedUnit.getWorkerWorks() == null && selectedCivilizedUnit.getImprovementType() == null) {
                    if (improvementType.technology == null || currentTurnUser.getNation().hasTechnology(improvementType.technology)) {
                        if (improvementType == ImprovementType.ROAD || improvementType == ImprovementType.RAILROAD) {
                            if (Land.map[selectedCivilizedUnit.getLocation().x][selectedCivilizedUnit.getLocation().y].getRoute() == null) {
                                return "yes";
                            } else return ("There is already a road here!");
                        } else {
                            if (Land.map[selectedCivilizedUnit.getLocation().x][selectedCivilizedUnit.getLocation().y].getImprovement() == null) {
                                return "yes";
                            } else return ("There is already an Improvement here!");
                        }
                    } else return ("You don't have " + improvementType.technology.name + " technology!");
                } else return "The worker is busy now!";
            } else return ("The selected unit is not a Worker!");
        } else return ("Please select a Worker first!");
    }


    public static String hasResourceOfImprovement(ImprovementType improvementType, CivilizedUnit worker) {
        if (Land.map[worker.getLocation().x][worker.getLocation().y].getResource() != null)
            for (ResourceType resourceType : improvementType.resourcesGiven)
                if (resourceType == Land.map[worker.getLocation().x][worker.getLocation().y].getResource().
                        getResourceType())
                    return "yes";

        return ("There is not the suitable resource for " + improvementType.name + " here!");
    }


    public static String isLandSuitable(ImprovementType improvementType) {
        if (improvementType.landTypes != null)
            for (LandType landType : improvementType.landTypes)
                if (landType == Land.map[selectedCivilizedUnit.getLocation().x][selectedCivilizedUnit.getLocation().y].getLandType())
                    return "yes";
        if (improvementType.landFeatureTypes != null &&
                Land.map[selectedCivilizedUnit.getLocation().x][selectedCivilizedUnit.getLocation().y].getLandFeature() != null)
            for (LandFeatureType landFeatureType : improvementType.landFeatureTypes)
                if (landFeatureType == Land.map[selectedCivilizedUnit.getLocation().x][selectedCivilizedUnit.getLocation().y].getLandFeature().getLandFeatureType())
                    return "yes";

        return ("This Land isn't suitable for " + improvementType.name + "!");
    }

}