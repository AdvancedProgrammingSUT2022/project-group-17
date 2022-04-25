package Controller.GameControllers;
import Controller.Controller;
import Model.City;
import Model.Game;
import Model.Nations.Nation;
import Model.Nations.NationType;
import Model.Pair;
import Model.Units.CloseCombatUnit;
import Model.Units.CombatUnit;
import Model.Units.Enums.CloseCombatUnitType;

import java.util.regex.Matcher;

public class CheatController extends Controller {

    public static String putUnit(Matcher matcher){
        Pair coordinate  = new Pair(Integer.parseInt(matcher.group("x")),Integer.parseInt(matcher.group("y")));
        CloseCombatUnit closeCombatUnit = new CloseCombatUnit(CloseCombatUnitType.KNIGHT, new Nation(NationType.AMIR));
        closeCombatUnit.setLocation(coordinate);
        Game.map[Integer.parseInt(matcher.group("x"))][Integer.parseInt(matcher.group("y"))].setCombatUnit(closeCombatUnit);
        return "OK";
    }
}
