package Controller.GameControllers;
import Controller.Controller;
import Model.City;
import Model.Game;
import Model.Nations.Nation;
import Model.Nations.NationType;
import Model.Pair;
import Model.Technologies.TechnologyType;
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
                CloseCombatUnit knight = new CloseCombatUnit(CloseCombatUnitType.KNIGHT,Game.getPlayersInGame().get(Game.getSubTurn()).getNation(), coordinate.x, coordinate.y);
                Game.map[Integer.parseInt(matcher.group("x"))][Integer.parseInt(matcher.group("y"))].setCombatUnit(knight);
                Game.getPlayersInGame().get(Game.getSubTurn()).getNation().addUnit(knight);
                break;
            case 1:
                RangedCombatUnit archer = new RangedCombatUnit(RangedCombatUnitType.ARCHER,Game.getPlayersInGame().get(Game.getSubTurn()).getNation(), coordinate.x, coordinate.y);
                Game.map[Integer.parseInt(matcher.group("x"))][Integer.parseInt(matcher.group("y"))].setCombatUnit(archer);
                Game.getPlayersInGame().get(Game.getSubTurn()).getNation().addUnit(archer);
                break;
            case 2:
                CivilizedUnit settler = new CivilizedUnit(CivilizedUnitType.SETTLER,Game.getPlayersInGame().get(Game.getSubTurn()).getNation(), coordinate.x, coordinate.y);
                Game.map[Integer.parseInt(matcher.group("x"))][Integer.parseInt(matcher.group("y"))].setCivilizedUnit(settler);
                Game.getPlayersInGame().get(Game.getSubTurn()).getNation().addUnit(settler);
                break;
            case 3:
                CivilizedUnit worker = new CivilizedUnit (CivilizedUnitType.WORKER,Game.getPlayersInGame().get(Game.getSubTurn()).getNation(), coordinate.x, coordinate.y);
                Game.map[Integer.parseInt(matcher.group("x"))][Integer.parseInt(matcher.group("y"))].setCivilizedUnit(worker);
                Game.getPlayersInGame().get(Game.getSubTurn()).getNation().addUnit(worker);
                break;
            default:
                return chosenNumber + "is not a valid number : [0-3]";
        }
        return "cheat successfully activated !";
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
