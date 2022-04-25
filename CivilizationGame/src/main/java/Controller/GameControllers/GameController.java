package Controller.GameControllers;

import Controller.Controller;
import Enums.Consts;
import Model.City;
import Model.Game;
import Model.Lands.Land;
import Model.Units.CombatUnit;
import Model.Units.Unit;

import java.util.regex.Matcher;

public class GameController extends Controller {
    public static CombatUnit selectedUnit;
    protected City selectedCity;


    public void showResearches() {

    }

    public void showUnits() {

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

    public void selectCombatUnit(Matcher matcher) {

        int selectedLandI = Integer.parseInt(matcher.group("x"));
        int selectedLandJ = Integer.parseInt(matcher.group("y"));

        if(Game.map[selectedLandI][selectedLandJ].getCombatUnit() != null)
        {
            selectedUnit = Game.map[selectedLandI][selectedLandJ].getCombatUnit();
            System.out.println(selectedUnit.getLocation().x + "    " + selectedUnit.getLocation().y);
        }
        else System.out.println("There is no combat unit here!");
    }

//    public void selectCivilizedUnit(Matcher matcher) {
//        int selectedLandI = Integer.parseInt(matcher.group("x"));
//        int selectedLandJ = Integer.parseInt(matcher.group("y"));
//
//        if(Game.map[selectedLandI][selectedLandJ].getCivilizedUnit() != null)
//            selectedUnit = Game.map[selectedLandI][selectedLandJ].getCivilizedUnit();
//        else System.out.println("There is no civilized unit here!");
//    }

    public void selectCity(Matcher matcher) {

    }

    public void mapShow(Matcher matcher) {

    }

    public void mapMove(Matcher matcher) {

    }

    public void nextTurn(){
        // remember to check if a unit has multi-turn movement then it won't wait for command.
    }

    public void saveGame(){

    }





}
