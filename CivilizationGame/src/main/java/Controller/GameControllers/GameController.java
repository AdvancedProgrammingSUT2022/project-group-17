package Controller.GameControllers;

import Controller.Controller;
import Model.Nations.Nation;
import Model.Nations.NationType;
import Model.Technologies.Technology;
import Model.Technologies.TechnologyType;
import Model.Units.*;
import Model.City;
import Model.Game;
import Model.Units.CombatUnit;
import Model.Users.User;

import java.util.regex.Matcher;

public class GameController extends Controller {
    protected static CombatUnit selectedCombatUnit;
    protected static CivilizedUnit selectedCivilizedUnit;
    protected static City selectedCity;
    protected static User currentTurnUser;


    public void chooseNation(int chosenNumber, int playerNum) {

        Nation nation = switch (chosenNumber) {
            case 0 -> new Nation(NationType.INDUS_VALLEY);
            case 1 -> new Nation(NationType.MAYA);
            case 2 -> new Nation(NationType.ANCIENT_GREECE);
            case 3 -> new Nation(NationType.PERSIA);
            case 4 -> new Nation(NationType.ANCIENT_EGYPT);
            case 5 -> new Nation(NationType.MESOPOTAMIAN);
            case 6 -> new Nation(NationType.ROME);
            case 7 -> new Nation(NationType.AZTEC);
            default -> new Nation(NationType.INCA);
        };

        Game.getPlayersInGame().get(playerNum).setNation(nation);
    }

    public void showResearches() {
        System.out.println("All of " + currentTurnUser.getNation().getNationType().name + "'s technologies:");
        int i = 0;
        for (TechnologyType technologyType : TechnologyType.values()) {
            if (currentTurnUser.getNation().getTechnologies().get(technologyType))
                System.out.printf("%d- %s\n", i, technologyType.name);
            i++;
        }
        System.out.println();
    }

    public static void setCurrentTurnUser(User currentTurnUser) {
        GameController.currentTurnUser = currentTurnUser;
    }

    public void showUnits() {
        System.out.println("All of " + currentTurnUser.getNation().getNationType().name + "'s units:");
        int i = 1;
        for (Unit unit : currentTurnUser.getNation().getUnits()) {
            System.out.printf("%d- %s\tLocation: (%d , %d)\tStatus: %s\n", i, unit.getName(), unit.getLocation().x, unit.getLocation().y, unit.getUnitStatus().name);
            i++;
        }
        System.out.println();
    }

    public void showCities() {
        System.out.println("All of " + currentTurnUser.getNation().getNationType().name + "'s cities:");
        int i = 1;
        for (City city : currentTurnUser.getNation().getCities()) {
            if (city == currentTurnUser.getNation().getCapital()) System.out.print("(*Capital) ");
            System.out.printf("%d- %s\tArea Size: %d\tPopulation: %d\n", i, city.getName(), city.getLands().size(), city.getCitizens());
            i++;
        }
    }

    public void showDiplomacies() {
        System.out.println(currentTurnUser.getNation().getNationType().name + "'s friends:");
        int i = 0;
        for (Nation nation : currentTurnUser.getNation().getFriends()) {
            System.out.printf("%d- %s\n", i, nation.getNationType().name);
            i++;
        }

        System.out.println(currentTurnUser.getNation().getNationType().name + "'s enemies:");
        i = 0;
        for (Nation nation : currentTurnUser.getNation().getEnemies()) {
            System.out.printf("%d- %s\n", i, nation.getNationType().name);
            i++;
        }
    }

    public void showDemographics() {
        for (User user : Game.getUsers()) {
            System.out.println(user.getNation().getNationType().name + " :");
            int population = 0;
            for (City city : user.getNation().getCities())
                population += city.getCitizens();
            System.out.println("- Population: " + population);
            System.out.println("- Soldiers: " + user.getNation().getUnits().size());
            int lands = 0;
            for (City city : user.getNation().getCities())
                lands += city.getLands().size();
            System.out.println("- Lands: " + lands);
        }
    }

    public void showNotifications() {

    }

    public void showMilitaries() {
        System.out.println("All of " + currentTurnUser.getNation().getNationType().name + "'s combat units:");
        int i = 1;
        for (Unit unit : currentTurnUser.getNation().getUnits())
            if (unit instanceof CombatUnit) {
                System.out.printf("%d- %s\tLocation: (%d , %d)\tStatus: %s\n", i, unit.getName(), unit.getLocation().x, unit.getLocation().y, unit.getUnitStatus().name);
                i++;
            }
        System.out.println();
    }

    public void showEconomics() {
        System.out.println("Economic Overview:");
        int i = 0;
        for(City city : currentTurnUser.getNation().getCities()) {
            System.out.printf("%d- %s\tLevel: %d\tStrength: %d\tCoin: %d\tFood: %d\tProduction: %d\n" ,
                    i, city.getName(), city.getLevel(), city.getHP(), city.getCoinGrowth(), city.getFoodGrowth(), city.getProductionGrowth());
            i++;
        }
        System.out.println();
    }
    

    public String selectCombatUnit(Matcher matcher) {
        int selectedLandI = Integer.parseInt(matcher.group("x"));
        int selectedLandJ = Integer.parseInt(matcher.group("y"));

        if (Game.map[selectedLandI][selectedLandJ].getCombatUnit() != null) {
            selectedCombatUnit = Game.map[selectedLandI][selectedLandJ].getCombatUnit();
            return (selectedCombatUnit.getName() + " is now selected");
        }
        return ("There is no combat unit here!");
    }

    public String selectCivilizedUnit(Matcher matcher) {
        int selectedLandI = Integer.parseInt(matcher.group("x"));
        int selectedLandJ = Integer.parseInt(matcher.group("y"));

        if (Game.map[selectedLandI][selectedLandJ].getCivilizedUnit() != null) {
            selectedCivilizedUnit = Game.map[selectedLandI][selectedLandJ].getCivilizedUnit();
            return (selectedCivilizedUnit + " is now selected");
        }
        return ("There is no civilized unit here!");
    }

    public void selectCity(Matcher matcher) {

    }

    public void mapShow() {
        LandController.printMap(Game.map);
    }

    public void mapMove(Matcher matcher) {

    }

    public void nextPlayerTurn() {
        Game.setSubTurn(Game.getSubTurn() + 1);
        currentTurnUser = Game.getPlayersInGame().get(Game.getSubTurn() % Game.getPlayersInGame().size());
        if (Game.getSubTurn() == Game.getPlayersInGame().size()) {
            nextGameTurn();
            Game.setSubTurn(Game.getSubTurn() % Game.getPlayersInGame().size());
        }
    }

    public void nextGameTurn() {
        Game.setTurn(Game.getTurn() + 1);
    }

    public void saveGame() {

    }


}
