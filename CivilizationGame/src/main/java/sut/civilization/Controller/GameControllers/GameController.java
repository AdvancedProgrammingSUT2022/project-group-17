package sut.civilization.Controller.GameControllers;

import sut.civilization.Controller.Controller;
import sut.civilization.Enums.Consts;
import sut.civilization.Model.Classes.*;
import sut.civilization.Model.ModulEnums.NationType;
import sut.civilization.Model.ModulEnums.CurrencyType;
import sut.civilization.Model.ModulEnums.ResourceType;
import sut.civilization.Model.ModulEnums.TechnologyType;
import sut.civilization.Model.ModulEnums.CivilizedUnitType;
import sut.civilization.Model.ModulEnums.UnitStatus;

import java.util.ArrayList;
import java.util.regex.Matcher;

public class GameController extends Controller {
    protected static CombatUnit selectedCombatUnit;
    protected static CivilizedUnit selectedCivilizedUnit;
    protected static City selectedCity;
    protected static User currentTurnUser;

    protected LandController landController = new LandController();

    public static CombatUnit getSelectedCombatUnit() {
        return selectedCombatUnit;
    }

    public static void setSelectedCombatUnit(CombatUnit selectedCombatUnit) {
        GameController.selectedCombatUnit = selectedCombatUnit;
    }

    public static CivilizedUnit getSelectedCivilizedUnit() {
        return selectedCivilizedUnit;
    }

    public static void setSelectedCivilizedUnit(CivilizedUnit selectedCivilizedUnit) {
        GameController.selectedCivilizedUnit = selectedCivilizedUnit;
    }

    public static void setSelectedCity(City selectedCity) {
        GameController.selectedCity = selectedCity;
    }

    public static User getCurrentTurnUser() {
        return currentTurnUser;
    }

    public static void setCurrentTurnUser(User currentTurnUser) {
        GameController.currentTurnUser = currentTurnUser;
    }

    public void chooseNation(int chosenNumber, int playerNum) {
        Nation nation;

        switch (chosenNumber) {
            case 0:
                nation = new Nation(NationType.INDUS_VALLEY);
                break;
            case 1:
                nation = new Nation(NationType.MAYA);
                break;
            case 2:
                nation = new Nation(NationType.ANCIENT_GREECE);
                break;
            case 3:
                nation = new Nation(NationType.PERSIA);
                break;
            case 4:
                nation = new Nation(NationType.ANCIENT_EGYPT);
                break;
            case 5:
                nation = new Nation(NationType.MESOPOTAMIAN);
                break;
            case 6:
                nation = new Nation(NationType.ROME);
                break;
            case 7:
                nation = new Nation(NationType.AZTEC);
                break;
            default:
                nation = new Nation(NationType.INCA);
                break;
        }

        Game.getPlayersInGame().get(playerNum).setNation(nation);
    }

    public ArrayList<String> showResearches() {
        ArrayList<String> output = new ArrayList<>();
        output.add("All of " + currentTurnUser.getNation().getNationType().name + "'s technologies:");
        int i = 0;
        for (TechnologyType technologyType : TechnologyType.values())
            if (currentTurnUser.getNation().hasTechnology(technologyType)) {
                output.add(i + "- " + technologyType.name);
                i++;
            }
        return output;
    }

    public ArrayList<String> showUnits() {
        ArrayList<String> output = new ArrayList<>();
        output.add("All of " + currentTurnUser.getNation().getNationType().name + "'s units:");
        int i = 1;
        for (Unit unit : currentTurnUser.getNation().getUnits()) {
            output.add(String.format("%d- %s\tLocation: (%d , %d)\tStatus: %s", i, unit.getName(), unit.getLocation().x, unit.getLocation().y, unit.getUnitStatus().toString()));
            i++;
        }
        return output;
    }

    public ArrayList<String> showCities() {
        ArrayList<String> output = new ArrayList<>();
        output.add("All of " + currentTurnUser.getNation().getNationType().name + "'s cities:");
        int i = 1;
        for (City city : currentTurnUser.getNation().getCities()) {
            if (city == currentTurnUser.getNation().getCapital()) output.add("(*Capital) ");
            output.add(String.format("%d- %s\tArea Size: %d\tPopulation: %d", i, city.getName(), city.getLands().size(), city.getCitizens()));
            i++;
        }
        return output;
    }

    public ArrayList<String> showDiplomacies() {
        ArrayList<String> output = new ArrayList<>();
        output.add(currentTurnUser.getNation().getNationType().name + "'s friends:");
        int i = 0;
        for (Nation nation : currentTurnUser.getNation().getFriends()) {
            output.add(String.format("%d- %s", i, nation.getNationType().name));
            i++;
        }

        output.add(currentTurnUser.getNation().getNationType().name + "'s enemies:");
        i = 0;
        for (Nation nation : currentTurnUser.getNation().getEnemies()) {
            output.add(String.format("%d- %s", i, nation.getNationType().name));
            i++;
        }
        return output;
    }

    public ArrayList<String> showDemographics() {
        ArrayList<String> output = new ArrayList<>();
        for (User user : Game.getPlayersInGame()) {
            output.add(user.getNation().getNationType().name + " :");
            int population = 0;
            for (City city : user.getNation().getCities())
                population += city.getCitizens();
            output.add("- Population: " + population);
            output.add("- Units: " + user.getNation().getUnits().size());
            int lands = 0;
            for (City city : user.getNation().getCities())
                lands += city.getLands().size();
            output.add("- Lands: " + lands);
        }
        return output;
    }

    public ArrayList<String> showMilitaries() {
        ArrayList<String> output = new ArrayList<>();
        output.add("All of " + currentTurnUser.getNation().getNationType().name + "'s combat units:");
        int i = 1;
        for (Unit unit : currentTurnUser.getNation().getUnits())
            if (unit instanceof CombatUnit) {
                output.add(String.format("%d- %s\tLocation: (%d , %d)\tStatus: %s", i, unit.getName(), unit.getLocation().x, unit.getLocation().y, unit.getUnitStatus().toString()));
                i++;
            }
        return output;
    }

    public ArrayList<String> showEconomics() {
        ArrayList<String> output = new ArrayList<>();
        output.add("Economic Overview:");
        int i = 0;
        for (City city : currentTurnUser.getNation().getCities()) {
            output.add(String.format("%d- %s\tLevel: %d\tStrength: %d\tCoin: %d\tFood: %d\tProduction: %d",
                    i, city.getName(), city.getLevel(), city.getHP(), city.getCoinGrowth(), city.getFoodGrowth(), city.getProductionGrowth()));
            i++;
        }
        return output;
    }


    public String selectCombatUnit(Matcher matcher) {
        int selectedLandI = Integer.parseInt(matcher.group("x"));
        int selectedLandJ = Integer.parseInt(matcher.group("y"));

        if (Land.map[selectedLandI][selectedLandJ].getCombatUnit() != null) {
            if (!Land.map[selectedLandI][selectedLandJ].getCombatUnit().getOwnerNation().equals(currentTurnUser.getNation()))
                return "You can't select opponent's unit";
            selectedCombatUnit = Land.map[selectedLandI][selectedLandJ].getCombatUnit();
            return (selectedCombatUnit.getName() + " is now selected");
        }
        return ("There is no combat unit here!");
    }

    public String selectCivilizedUnit(Matcher matcher) {
        int selectedLandI = Integer.parseInt(matcher.group("x"));
        int selectedLandJ = Integer.parseInt(matcher.group("y"));

        if (Land.map[selectedLandI][selectedLandJ].getCivilizedUnit() != null) {
            if (!Land.map[selectedLandI][selectedLandJ].getCivilizedUnit().getOwnerNation().equals(currentTurnUser.getNation()))
                return "You can't select opponent's unit";
            selectedCivilizedUnit = Land.map[selectedLandI][selectedLandJ].getCivilizedUnit();
            return (selectedCivilizedUnit.getName() + " is now selected");
        }
        return ("There is no civilized unit here!");
    }


    public String selectCity(Matcher matcher) {
        int x = Integer.parseInt(matcher.group("x"));
        int y = Integer.parseInt(matcher.group("y"));

        if (Land.map[x][y].getOwnerCity() != null) {
            if (!Land.map[x][y].getOwnerCity().getOwnerNation().equals(currentTurnUser.getNation()))
                return "You can't select opponent's city";
            selectedCity = Land.map[x][y].getOwnerCity();
            return (selectedCity.getName() + " is now selected");
        }
        return "There is no city here!";
    }

    public void mapShow() {

        new LandController().printMap(Land.map);
    }

    private boolean isReadyForNextTurn() {
        for (Unit unit : currentTurnUser.getNation().getUnits())
            if (unit.isWaitingForCommand())
                return false;
        return true;
    }

    public String nextPlayerTurn() {
        if (isReadyForNextTurn()) {
            selectedCity = null;
            selectedCivilizedUnit = null;
            selectedCombatUnit = null;
            Game.setSubTurn(Game.getSubTurn() + 1);
            currentTurnUser = Game.getPlayersInGame().get(Game.getSubTurn() % Game.getPlayersInGame().size());
            if (Game.getSubTurn() == Game.getPlayersInGame().size()) {
                nextGameTurn();
                Game.setSubTurn(Game.getSubTurn() % Game.getPlayersInGame().size());
                return "next game turn: " + currentTurnUser.getUsername();
            }
            return "next player turn!: " + currentTurnUser.getUsername();
        }
        return "Order all your units first!";
    }


    public void nextGameTurn() {
        UnitController unitController = new UnitController();
        Game.setTurn(Game.getTurn() + 1);

        //set ZOC
        for (int i = 0; i < Consts.MAP_SIZE.amount.x; i++) {
            for (int j = 0; j < Consts.MAP_SIZE.amount.y; j++) {
                Land.map[i][j].setZOC(null);
                Pair<Integer,Integer>[] neighbors = new Pair[6];
                for (int k = 0; k < 6; k++) {
                    neighbors[k] = landController.getNeighborIndex(new Pair<Integer,Integer>(i, j), k);
                    if (Pair.isValid(new Pair<Integer,Integer>(neighbors[k].x, neighbors[k].y)) &&
                            Land.map[neighbors[k].x][neighbors[k].y].getCombatUnit() != null)
                        Land.map[i][j].setZOC(Land.map[neighbors[k].x][neighbors[k].y].getCombatUnit());
                }
            }
        }

        for (User user : Game.getPlayersInGame()) {
            Nation userNation = user.getNation();
            for (Unit unit : userNation.getUnits()) {
                if (unit instanceof RangedCombatUnit) {
                    if (((RangedCombatUnit) unit).getRangedCombatUnitType() != null && ((RangedCombatUnit) unit).getRangedCombatUnitType().isSiege) {
                        if (unit.getTargetCity() != null) {
                            unitController.unitAttackCity((CombatUnit) unit);
                        }
                    }
                }
                nextTurnUnitMove(unit);
                //Create Improvement
                nextTurnWorkerWorks(unit);

                checkFortifying(unit);

                setUnitWaitingForCommand(unit);

                //maintenance cost for units
                userNation.getCoin().addBalance(-unit.getMaintenance());
            }
            //update Currencies Balances
            userNation.getCoin().addGrowthRateToBalance();
            userNation.getFood().addGrowthRateToBalance();
            userNation.getHappiness().addGrowthRateToBalance();
            userNation.getProduction().addGrowthRateToBalance();
            userNation.getScience().addGrowthRateToBalance();

            //Create Unit => for in cities
            for (City city : userNation.getCities()) {
                if (city.hasAnInProgressUnit()) {
                    if (city.getNextUnitTurns() == 0) {
                        UnitController.unitCreate(city);
                    }
                    city.setNextUnitTurns(city.getNextUnitTurns() - 1);
                }

                //production of unemployed
                userNation.getProduction().addBalance(city.getCitizens() - city.getEmployees());

                //food consumption
                userNation.getFood().addBalance(-city.getCitizens() * 2);

                //science addition
                userNation.getScience().addBalance(3 + city.getCitizens());

                //unhappiness update
                userNation.getHappiness().addBalance(-city.getCitizens() / 3);
            }

            //unhappiness update
            userNation.getHappiness().addBalance(-userNation.getCities().size());

            //update Technology progress
            if (userNation.getInProgressTechnology() != null) {
                if (userNation.getTechnologyTurns() == 0) {
                    TechnologyController.activateTechnology(userNation);
                }
                userNation.setTechnologyTurns(userNation.getTechnologyTurns() - 1);
            }

            //reset GrowthRates
            userNation.getCoin().setGrowthRate(0);
            userNation.getProduction().setGrowthRate(0);
            userNation.getFood().setGrowthRate(0);
            userNation.getScience().setGrowthRate(0);
            userNation.getHappiness().setGrowthRate(0);

            //happiness for luxury resources
            for (ResourceType resourceType : userNation.getResourceCellar().keySet()) {
                if (resourceType.currency.equals(CurrencyType.COIN) && userNation.getResourceCellar().get(resourceType) == 1)
                    userNation.getHappiness().addBalance(4);
            }
        }

        //update GrowthRates
        for (int i = 0; i < Consts.MAP_SIZE.amount.y; i++) {
            for (int j = 0; j < Consts.MAP_SIZE.amount.x; j++) {
                Land.map[i][j].addGrowthToLandOwner();
            }
        }

        landController.updateDistances();
        landController.printMap(Land.map);
    }

    private void checkFortifying(Unit unit) {
        if (unit.getUnitStatus() == UnitStatus.FORTIFY) {
            if (unit.getHp() < 10)
                unit.addHp(1);
        } else if (unit.getUnitStatus() == UnitStatus.FORTIFY_UNTIL_HEAL) {
            if (unit.getHp() < 10)
                unit.addHp(1);
            else {
                unit.setUnitStatus(UnitStatus.AWAKE);
                unit.setWaitingForCommand(true);
            }
        }
    }

    private void setUnitWaitingForCommand(Unit unit) {
        if (unit.getUnitStatus() != UnitStatus.MOVING && unit.getUnitStatus() != UnitStatus.WORKING &&
                unit.getUnitStatus() != UnitStatus.FORTIFY_UNTIL_HEAL && unit.getUnitStatus() != UnitStatus.SLEEP &&
                unit.getUnitStatus() != UnitStatus.FORTIFY) {
            unit.setWaitingForCommand(true);
        }
    }

    public void nextTurnUnitMove(Unit unit) {
        if (unit instanceof CivilizedUnit) {
            unit.setMP(((CivilizedUnit) unit).getCivilizedUnitType().MP);
        } else if (unit instanceof CloseCombatUnit) {
            unit.setMP(((CloseCombatUnit) unit).getCloseCombatUnitType().MP);
        } else if (unit instanceof RangedCombatUnit) {
            unit.setMP(((RangedCombatUnit) unit).getRangedCombatUnitType().MP);
        }

        if (!unit.getPath().equals("")) {
            while (unit.getMP() > 0)
                new UnitController().unitGoForward(unit);
        } else {
            if (unit.getUnitStatus() == UnitStatus.MOVING) unit.setUnitStatus(UnitStatus.AWAKE);
            if (unit.getUnitStatus() != UnitStatus.MOVING && unit.getUnitStatus() != UnitStatus.WORKING &&
                    unit.getUnitStatus() != UnitStatus.FORTIFY_UNTIL_HEAL && unit.getUnitStatus() != UnitStatus.SLEEP &&
                    unit.getUnitStatus() != UnitStatus.FORTIFY)
                unit.setWaitingForCommand(true);
        }
    }

    private void nextTurnWorkerWorks(Unit unit) {
        if (unit instanceof CivilizedUnit &&
                ((CivilizedUnit) unit).getCivilizedUnitType() == CivilizedUnitType.WORKER) {
            if (unit.getUnitStatus() == UnitStatus.WORKING) {
                if (((CivilizedUnit) unit).getTurnsLeft() != 0) {
                    ((CivilizedUnit) unit).decreaseTurnsLeft(1);
                } else {
                    if (((CivilizedUnit) unit).getImprovementType() != null) {
                        WorkerController.workerBuildImprovement(((CivilizedUnit) unit));
                        System.out.println(((CivilizedUnit) unit).getImprovementType().name + " was built!");
                        ((CivilizedUnit) unit).setImprovementType(null);
                        unit.setUnitStatus(UnitStatus.AWAKE);
                        unit.setWaitingForCommand(true);
                    } else if (((CivilizedUnit) unit).getWorkerWorks() != null) {
                        WorkerController.workerWork(((CivilizedUnit) unit));
                        System.out.println(((CivilizedUnit) unit).getWorkerWorks().toString() + " was done!");
                        ((CivilizedUnit) unit).setWorkerWorks(null);
                        unit.setUnitStatus(UnitStatus.AWAKE);
                        unit.setWaitingForCommand(false);
                    } else System.out.println("ERROR!");
                }
            }
        }
    }
}
