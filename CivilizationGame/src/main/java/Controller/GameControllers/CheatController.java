package Controller.GameControllers;
import Model.Game;
import Model.Pair;
import Model.Technologies.TechnologyType;
import Model.Units.CivilizedUnit;
import Model.Units.CloseCombatUnit;
import Model.Units.Enums.CivilizedUnitType;
import Model.Units.Enums.CloseCombatUnitType;
import Model.Units.Enums.RangedCombatUnitType;
import Model.Units.RangedCombatUnit;
import Model.Users.User;

import java.util.Scanner;
import java.util.regex.Matcher;

public class CheatController extends GameController {

    public String putUnit(Matcher matcher, Scanner scanner, User user){

        Pair coordinate  = new Pair(Integer.parseInt(matcher.group("x")),Integer.parseInt(matcher.group("y")));
        if (!Pair.isValid(coordinate))
            return "coordinate is not valid!";

        int chosenNumber = scanner.nextInt();
        switch (chosenNumber){
            case 0:
                Game.map[Integer.parseInt(matcher.group("x"))][Integer.parseInt(matcher.group("y"))].setCombatUnit(new CloseCombatUnit(CloseCombatUnitType.KNIGHT,user.getNation(), coordinate.x, coordinate.y));
                break;
            case 1:
                Game.map[Integer.parseInt(matcher.group("x"))][Integer.parseInt(matcher.group("y"))].setCombatUnit(new RangedCombatUnit(RangedCombatUnitType.ARCHER,user.getNation(), coordinate.x, coordinate.y));
                break;
            case 2:
                Game.map[Integer.parseInt(matcher.group("x"))][Integer.parseInt(matcher.group("y"))].setCivilizedUnit(new CivilizedUnit(CivilizedUnitType.SETTLER,user.getNation(), coordinate.x, coordinate.y));
                break;
            case 3:
                Game.map[Integer.parseInt(matcher.group("x"))][Integer.parseInt(matcher.group("y"))].setCivilizedUnit(new CivilizedUnit(CivilizedUnitType.WORKER,user.getNation(), coordinate.x, coordinate.y));
                break;
            default:
                return chosenNumber + " is not a valid number : [0-3]";
        }
        return "cheat successfully activated!";
    }

    public String addTechnology(Matcher matcher) {
        String technologyName = matcher.group("name");
        for(TechnologyType technologyType : TechnologyType.values()) {
            if(technologyType.name.equals(technologyName)) {
                currentTurnUser.getNation().addTechnology(technologyType);
                return "cheat successfully activated! : Technology's added";
            }
        }
        return "error!";
    }


}
