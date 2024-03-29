package sut.civilization.Controller.GameControllers;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.Light;
import javafx.scene.effect.Lighting;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Popup;
import javafx.stage.Stage;
import sut.civilization.Controller.ConnectionController;
import sut.civilization.Controller.Controller;
import sut.civilization.Enums.Consts;
import sut.civilization.Enums.Menus;
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

    public static City getSelectedCity() {
        return selectedCity;
    }

    public static void setSelectedCivilizedUnit(CivilizedUnit selectedCivilizedUnit) {
        GameController.selectedCivilizedUnit = selectedCivilizedUnit;
    }

    public static void setSelectedCity(City selectedCity) {
        GameController.selectedCity = selectedCity;
    }

    public static User getCurrentTurnUser() {
        if (currentTurnUser == null)
            return Game.instance.getPlayersInGame().get(Game.instance.getSubTurn());
        return currentTurnUser;
    }

    public static void setCurrentTurnUser(User currentTurnUser) {
        GameController.currentTurnUser = currentTurnUser;
    }

    public void chooseNation(int chosenNumber, int playerNum) {
        Nation nation;

        switch (chosenNumber) {
            case 0:
                nation = new Nation(NationType.FRANCE);
                break;
            case 1:
                nation = new Nation(NationType.MAYA);
                break;
            case 2:
                nation = new Nation(NationType.GREECE);
                break;
            case 3:
                nation = new Nation(NationType.PERSIA);
                break;
            case 4:
                nation = new Nation(NationType.EGYPT);
                break;
            case 5:
                nation = new Nation(NationType.INDIA);
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

        Game.instance.getPlayersInGame().get(playerNum).setNation(nation);
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
            if (city.equals(currentTurnUser.getNation().getCapital())) output.add("(*Capital) ");
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
        for (User user : Game.instance.getPlayersInGame()) {
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

        if (Game.instance.map[selectedLandI][selectedLandJ].getCombatUnit() != null) {
            if (!Game.instance.map[selectedLandI][selectedLandJ].getCombatUnit().getOwnerNation().equals(currentTurnUser.getNation()))
                return "You can't select opponent's unit";
            selectedCombatUnit = Game.instance.map[selectedLandI][selectedLandJ].getCombatUnit();
            return (selectedCombatUnit.getName() + " is now selected");
        }
        return ("There is no combat unit here!");
    }

    public String selectCivilizedUnit(Matcher matcher) {
        int selectedLandI = Integer.parseInt(matcher.group("x"));
        int selectedLandJ = Integer.parseInt(matcher.group("y"));

        if (Game.instance.map[selectedLandI][selectedLandJ].getCivilizedUnit() != null) {
            if (!Game.instance.map[selectedLandI][selectedLandJ].getCivilizedUnit().getOwnerNation().equals(currentTurnUser.getNation()))
                return "You can't select opponent's unit";
            selectedCivilizedUnit = Game.instance.map[selectedLandI][selectedLandJ].getCivilizedUnit();
            return (selectedCivilizedUnit.getName() + " is now selected");
        }
        return ("There is no civilized unit here!");
    }


    public String selectCity(Matcher matcher) {
        int x = Integer.parseInt(matcher.group("x"));
        int y = Integer.parseInt(matcher.group("y"));

        if (Game.instance.map[x][y].getOwnerCity() != null) {
            if (!Game.instance.map[x][y].getOwnerCity().getOwnerNation().equals(currentTurnUser.getNation()))
                return "You can't select opponent's city";
            selectedCity = Game.instance.map[x][y].getOwnerCity();
            return (selectedCity.getName() + " is now selected");
        }
        return "There is no city here!";
    }

    public void mapShow() {
        new LandController().printMap(Game.instance.map);
    }

    private static String isReadyForNextTurn() {
        for (Unit unit : currentTurnUser.getNation().getUnits())
            if (unit.getUnitStatus().isWaitingForCommand)
                return "unit";
        if (currentTurnUser.getNation().getInProgressTechnology() == null)
            return "tech";
        for (City city : currentTurnUser.getNation().getCities()) {
            if (city.getInProgressBuilding() == null && city.getInProgressRangedCombatUnit() == null &&
                    city.getInProgressCloseCombatUnit() == null && city.getInProgressCivilizedUnit() == null) {
                return "production";
            }
        }
        return "ready";
    }

    public static void checkWinLose(User user){
//        if (Game.instance.getPlayersInGame().size() == 1){
//            User user = Game.instance.getPlayersInGame().get(0);
//            user.setGameState(User.GameState.WIN);
//        }
//        for (User user : Game.instance.getPlayersInGame()) {
        Nation nation = user.getNation();
        if (nation.getCities().size() == 0){
            boolean hasSettler = false;
            for (Unit unit : nation.getUnits()) {
                if (unit instanceof CivilizedUnit &&
                        ((CivilizedUnit) unit).getCivilizedUnitType().equals(CivilizedUnitType.SETTLER)) {
                    hasSettler = true;
                    break;
                }
            }
            if (!hasSettler) {
                user.setGameState(1);
                System.out.println("Bakhti!");
            }
        }
//        }
    }

    public static String nextPlayerTurn() {
        String readyState = isReadyForNextTurn();
        switch (readyState) {
            case "ready":
                checkWinLose(currentTurnUser);
                selectedCity = null;
                selectedCivilizedUnit = null;
                selectedCombatUnit = null;
                Popup losePopup = new Popup();
                if (currentTurnUser.getGameState() == 1) {
                    losePopup.setAutoHide(true);
                    Label lose = new Label(currentTurnUser.getUsername() + " Lose!");
                    lose.getStylesheets().add("/sut/civilization/StyleSheet/Game.css");
                    lose.getStyleClass().add("header");
                    lose.setStyle("-fx-background-color: red; -fx-background-radius: 10;");
                    losePopup.getContent().add(lose);
                    losePopup.show(Game.instance.getCurrentScene().getWindow());

                    System.out.println(currentTurnUser.getUsername() + " Lose!");
                    Game.instance.getPlayersInGame().remove(currentTurnUser);
                } else Game.instance.setSubTurn(Game.instance.getSubTurn() + 1);

                currentTurnUser = Game.instance.getPlayersInGame().get(Game.instance.getSubTurn() % Game.instance.getPlayersInGame().size());

                if (Game.instance.getPlayersInGame().size() == 1) {
                    losePopup.hide();
                    Popup winPopup = new Popup();
                    Label win = new Label(currentTurnUser.getUsername() + " Win!");
                    win.getStyleClass().add("header");
                    Button goToMainMenu = new Button("Go to MainMenu");
                    goToMainMenu.setOnMouseClicked(mouseEvent -> {
                        //fixme end the game
                        ((Stage) Game.instance.getCurrentScene().getWindow()).setFullScreen(false);
                        Game.instance.changeScene(Menus.MAIN_MENU);
                        winPopup.hide();
                        Request request = new Request("game","endGame");
                        request.addToken("winner",currentTurnUser.getUsername());
                        ConnectionController.getInstance().sendUpdateToServer(request.toJson());
                    });
                    VBox winVBox = new VBox(win, goToMainMenu);
                    winVBox.setStyle("-fx-background-color: green; -fx-background-radius: 30;");
                    winPopup.getContent().add(winVBox);
                    winVBox.getStylesheets().add("/sut/civilization/StyleSheet/Game.css");
                    winVBox.setAlignment(Pos.CENTER);
                    winVBox.setPadding(new Insets(20));
                    Light light = new Light.Distant();
                    light.setColor(new Color(0.4, 0.4, 0.4, 0.5));
                    Game.instance.getCurrentScene().getRoot().setEffect(new Lighting(light));
                    Game.instance.getCurrentScene().getRoot().setDisable(true);
                    winPopup.show(Game.instance.getCurrentScene().getWindow());

                }

                if (Game.instance.getSubTurn() == Game.instance.getPlayersInGame().size()) {
                    nextGameTurn();
                    Game.instance.setSubTurn(Game.instance.getSubTurn() % Game.instance.getPlayersInGame().size());
                    return "next game turn: " + currentTurnUser.getUsername();
                }
                return "next player turn!: " + currentTurnUser.getUsername();
            case "tech":
                return "Select a technology to research!";
            case "production":
                return "Choose production of your cities!";
        }
        return "Order all your units first!";
    }


    public static void nextGameTurn() {
        UnitController unitController = new UnitController();
        Game.instance.setTurn(Game.instance.getTurn() + 1);

        //set ZOC
        for (int i = 0; i < Consts.MAP_SIZE.amount.x; i++) {
            for (int j = 0; j < Consts.MAP_SIZE.amount.y; j++) {
                Game.instance.map[i][j].setZOC(null);
                Pair<Integer, Integer>[] neighbors = new Pair[6];
                for (int k = 0; k < 6; k++) {
                    neighbors[k] = LandController.getNeighborIndex(new Pair<Integer, Integer>(i, j), k);
                    if (Pair.isValid(new Pair<>(neighbors[k].x, neighbors[k].y)) &&
                            Game.instance.map[neighbors[k].x][neighbors[k].y].getCombatUnit() != null)
                        Game.instance.map[i][j].setZOC(Game.instance.map[neighbors[k].x][neighbors[k].y].getCombatUnit());
                }
            }
        }

        for (User user : Game.instance.getPlayersInGame()) {
            Nation userNation = user.getNation();
            for (Unit unit : userNation.getUnits()) {
                if (unit instanceof RangedCombatUnit) {
                    if (((RangedCombatUnit) unit).getRangedCombatUnitType() != null && ((RangedCombatUnit) unit).getRangedCombatUnitType().isSiege) {
                        if (unit.getTargetCity() != null) {
                            UnitController.unitAttackCity((CombatUnit) unit);
                        }
                    }
                }
                nextTurnUnitMove(unit);
                //Create Improvement
                nextTurnWorkerWorks(unit);

                checkFortifying(unit);

//                setUnitWaitingForCommand(unit);
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
                if (city.hasAnInProgressProduct()) {
                    if (city.getNextProductTurns() == 0) {
                        UnitController.ProductCreate(city);
                    }
                    city.setNextProductTurns(city.getNextProductTurns() - 1);
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

            //update Technology Tree
            TechnologyController.updateNextAvailableTechnologies();

            //reset GrowthRates
            userNation.getCoin().setGrowthRate(userNation.getSpeed() * userNation.getCoin().getGrowthRate());
            userNation.getProduction().setGrowthRate(0);
            userNation.getFood().setGrowthRate(userNation.getSpeed() * userNation.getFood().getGrowthRate());
            userNation.getScience().setGrowthRate(userNation.getSpeed() * userNation.getScience().getGrowthRate());
            userNation.getHappiness().setGrowthRate(0);

            //happiness for luxury resources
            for (ResourceType resourceType : userNation.getResourceCellar().keySet()) {
                if (resourceType.currency.equals(CurrencyType.GOLD) && userNation.getResourceCellar().get(resourceType) == 1)
                    userNation.getHappiness().addBalance(4);
            }
        }

        //update GrowthRates
        for (int i = 0; i < Consts.MAP_SIZE.amount.y; i++) {
            for (int j = 0; j < Consts.MAP_SIZE.amount.x; j++) {
                Game.instance.map[i][j].addGrowthToLandOwner();
            }
        }

        CityController.updateAffordableLands();

        LandController.updateDistances();

    }

    private static void checkFortifying(Unit unit) {
        if (unit.getUnitStatus().equals(UnitStatus.FORTIFY)) {
            if (unit.getHp() < 10)
                unit.addHp(1);
        } else if (unit.getUnitStatus().equals(UnitStatus.FORTIFY_UNTIL_HEAL)) {
            if (unit.getHp() < 10)
                unit.addHp(1);
            else {
                unit.setUnitStatus(UnitStatus.AWAKE);
//                unit.setWaitingForCommand(true);
            }
        }
    }

    public static ResourceType getResourceType(String name){
        for (ResourceType resourceType : ResourceType.values()) {
            if (resourceType.name.equals(name))
                return resourceType;
        }
        return null;
    }

    public static void nextTurnUnitMove(Unit unit) {
        if (unit instanceof CivilizedUnit) {
            unit.setMP(((CivilizedUnit) unit).getCivilizedUnitType().MP);
        } else if (unit instanceof CloseCombatUnit) {
            unit.setMP(((CloseCombatUnit) unit).getCloseCombatUnitType().MP);
        } else if (unit instanceof RangedCombatUnit) {
            unit.setMP(((RangedCombatUnit) unit).getRangedCombatUnitType().MP);
        }

        if (!unit.getPath().equals("")) {
            while (unit.getMP() > 0 && !unit.getPath().equals("") && unit.getPath() != null)
                UnitController.unitGoForward(unit);
        } else {
            if (unit.getUnitStatus().equals(UnitStatus.MOVING)) unit.setUnitStatus(UnitStatus.AWAKE);
            if (unit.getUnitStatus().equals(UnitStatus.ALERT) || unit.getUnitStatus().equals(UnitStatus.ATTACKING))
                unit.setUnitStatus(UnitStatus.AWAKE);
        }
    }

    private static void nextTurnWorkerWorks(Unit unit) {
        if (unit instanceof CivilizedUnit &&
                ((CivilizedUnit) unit).getCivilizedUnitType() == CivilizedUnitType.WORKER) {
            if (unit.getUnitStatus() == UnitStatus.WORKING) {
                if (unit.getTurnsLeft() != 0) {
                    unit.decreaseTurnsLeft(1);
                } else {
                    if (((CivilizedUnit) unit).getImprovementType() != null) {
                        WorkerController.workerBuildImprovement(((CivilizedUnit) unit));
                        System.out.println(((CivilizedUnit) unit).getImprovementType().name + " was built!");
                        ((CivilizedUnit) unit).setImprovementType(null);
                        unit.setUnitStatus(UnitStatus.AWAKE);
                    } else if (((CivilizedUnit) unit).getWorkerWorks() != null) {
                        WorkerController.workerWork(((CivilizedUnit) unit));
                        System.out.println(((CivilizedUnit) unit).getWorkerWorks().toString() + " was done!");
                        ((CivilizedUnit) unit).setWorkerWorks(null);
                        unit.setUnitStatus(UnitStatus.AWAKE);
                    } else System.out.println("ERROR!");
                }
            }
        }
    }
}
