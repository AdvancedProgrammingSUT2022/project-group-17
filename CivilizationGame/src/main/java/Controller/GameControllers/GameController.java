package Controller.GameControllers;

import Controller.Controller;
import Model.Nations.Nation;
import Model.Nations.NationType;
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

    public void chooseNation(int chosenNumber,int playerNum){

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

    }

    public void showUnits() {
        System.out.println("All of " + currentTurnUser.getNation().getNationType().name + "'s units:");
        int i = 1;
        for (Unit unit : currentTurnUser.getNation().getUnits()) {
            System.out.printf("%d- %s\tLocation: (%d , %d)\n", i, unit.getName(), unit.getLocation().x, unit.getLocation().y);
        }
    }

    public void showCities() {

    }

    public void showDiplomacies() {

    }

    public void showVictories() {

    }

    public void showDemographics() {

    }

    public void showNotifications() {

    }

    public void showMilitaries() {

    }

    public void showEconomics() {

    }

    public void showDiplomatics() {

    }

    public void showDeals() {

    }

    public String selectCombatUnit(Matcher matcher) {
        int selectedLandI = Integer.parseInt(matcher.group("x"));
        int selectedLandJ = Integer.parseInt(matcher.group("y"));

        if(Game.map[selectedLandI][selectedLandJ].getCombatUnit() != null)
        {
            selectedCombatUnit = Game.map[selectedLandI][selectedLandJ].getCombatUnit();
            return (selectedCombatUnit.getName() + " is now selected");
        }
        return("There is no combat unit here!");
    }

    public String selectCivilizedUnit(Matcher matcher) {
        int selectedLandI = Integer.parseInt(matcher.group("x"));
        int selectedLandJ = Integer.parseInt(matcher.group("y"));

        if(Game.map[selectedLandI][selectedLandJ].getCivilizedUnit() != null){
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

    public void nextPlayerTurn(){
        Game.setSubTurn(Game.getSubTurn() + 1);
        currentTurnUser = Game.getPlayersInGame().get(Game.getSubTurn() % Game.getPlayersInGame().size());
        if (Game.getSubTurn() == Game.getPlayersInGame().size()){
            nextGameTurn();
            Game.setSubTurn(Game.getSubTurn() % Game.getPlayersInGame().size());
        }
    }

    public void nextGameTurn(){
        Game.setTurn(Game.getTurn() + 1);
    }

    public void saveGame(){

    }





}
