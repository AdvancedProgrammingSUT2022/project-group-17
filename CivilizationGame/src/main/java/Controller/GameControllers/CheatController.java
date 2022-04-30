package Controller.GameControllers;
import Controller.Controller;
import Model.City;
import Model.Game;
import Model.Nations.Nation;
import Model.Nations.NationType;
import Model.Pair;
import Model.Units.CivilizedUnit;
import Model.Units.CloseCombatUnit;
import Model.Units.CombatUnit;
import Model.Units.Enums.CivilizedUnitType;
import Model.Units.Enums.CloseCombatUnitType;
import Model.Units.Enums.RangedCombatUnitType;
import Model.Units.RangedCombatUnit;
import java.util.Scanner;
import java.util.regex.Matcher;

public class CheatController extends GameController {

    public String putUnit(Matcher matcher, Scanner scanner){

        Pair coordinate  = new Pair(Integer.parseInt(matcher.group("x")),Integer.parseInt(matcher.group("y")));
        int chosenNumber = scanner.nextInt();
        switch (chosenNumber){
            case 0:
                Game.map[Integer.parseInt(matcher.group("x"))][Integer.parseInt(matcher.group("y"))].setCombatUnit(new CloseCombatUnit(CloseCombatUnitType.KNIGHT,currentTurnUser.getNation()));
                selectedCombatUnit.setLocation(coordinate);
                break;
            case 1:
                Game.map[Integer.parseInt(matcher.group("x"))][Integer.parseInt(matcher.group("y"))].setCombatUnit(new RangedCombatUnit(RangedCombatUnitType.ARCHER,currentTurnUser.getNation()));
                selectedCombatUnit.setLocation(coordinate);
                break;
            case 2:
                Game.map[Integer.parseInt(matcher.group("x"))][Integer.parseInt(matcher.group("y"))].setCivilizedUnit(new CivilizedUnit(CivilizedUnitType.SETTLER,currentTurnUser.getNation()));
                selectedCivilizedUnit.setLocation(coordinate);
                break;
            case 3:
                Game.map[Integer.parseInt(matcher.group("x"))][Integer.parseInt(matcher.group("y"))].setCivilizedUnit(new CivilizedUnit(CivilizedUnitType.WORKER,currentTurnUser.getNation()));
                selectedCivilizedUnit.setLocation(coordinate);
                break;
            default:
                return chosenNumber + "is not a valid number : [0-3]";
        }
        return "cheat successfully activated !";
    }


}
