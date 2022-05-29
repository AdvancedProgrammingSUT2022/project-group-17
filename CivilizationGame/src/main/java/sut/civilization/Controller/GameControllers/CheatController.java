package sut.civilization.Controller.GameControllers;
import sut.civilization.Model.Classes.Game;
import sut.civilization.Model.Classes.Pair;
import sut.civilization.Model.ModulEnums.TechnologyType;
import sut.civilization.Model.Classes.CivilizedUnit;
import sut.civilization.Model.Classes.CloseCombatUnit;
import sut.civilization.Model.ModulEnums.CivilizedUnitType;
import sut.civilization.Model.ModulEnums.CloseCombatUnitType;
import sut.civilization.Model.ModulEnums.RangedCombatUnitType;
import sut.civilization.Model.Classes.RangedCombatUnit;
import sut.civilization.Model.Classes.User;

import java.util.Scanner;
import java.util.regex.Matcher;

public class CheatController extends GameController {

    public String putUnit(Matcher matcher, Scanner scanner, User user){

        Pair<Integer,Integer> coordinate = new Pair<Integer,Integer>(Integer.parseInt(matcher.group("x")),Integer.parseInt(matcher.group("y")));
        if (!Pair.isValid(coordinate))
            return "coordinate is not valid!";
        int chosenNumber = scanner.nextInt();
        switch (chosenNumber){
            case 0:
                CloseCombatUnit knight = new CloseCombatUnit(CloseCombatUnitType.KNIGHT,Game.getPlayersInGame().get(Game.getSubTurn()).getNation(), coordinate);
                Game.map[Integer.parseInt(matcher.group("x"))][Integer.parseInt(matcher.group("y"))].setCombatUnit(knight);
                Game.getPlayersInGame().get(Game.getSubTurn()).getNation().addUnit(knight);
                break;
            case 1:
                RangedCombatUnit archer = new RangedCombatUnit(RangedCombatUnitType.ARCHER,Game.getPlayersInGame().get(Game.getSubTurn()).getNation(), coordinate);
                Game.map[Integer.parseInt(matcher.group("x"))][Integer.parseInt(matcher.group("y"))].setCombatUnit(archer);
                Game.getPlayersInGame().get(Game.getSubTurn()).getNation().addUnit(archer);
                break;
            case 2:
                CivilizedUnit settler = new CivilizedUnit(CivilizedUnitType.SETTLER,Game.getPlayersInGame().get(Game.getSubTurn()).getNation(), coordinate);
                Game.map[Integer.parseInt(matcher.group("x"))][Integer.parseInt(matcher.group("y"))].setCivilizedUnit(settler);
                Game.getPlayersInGame().get(Game.getSubTurn()).getNation().addUnit(settler);
                break;
            case 3:
                CivilizedUnit worker = new CivilizedUnit (CivilizedUnitType.WORKER,Game.getPlayersInGame().get(Game.getSubTurn()).getNation(), coordinate);
                Game.map[Integer.parseInt(matcher.group("x"))][Integer.parseInt(matcher.group("y"))].setCivilizedUnit(worker);
                Game.getPlayersInGame().get(Game.getSubTurn()).getNation().addUnit(worker);
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
        return "error! technology name doesn't exist";
    }

    public String destroyImprovement(Matcher matcher) {
        Pair<Integer,Integer> location = new Pair<Integer,Integer>(Integer.parseInt(matcher.group("x")), Integer.parseInt(matcher.group("y")));
        if (Game.map[location.x][location.y].getImprovement() != null) {
            Game.map[location.x][location.y].getImprovement().setBroken(true);
            return "The improvement is now broken!";
        }
        return "There isn't any Improvement here!";
    }

    public String increaseCoin(Matcher matcher){
        int amount = Integer.parseInt(matcher.group("amount"));
        currentTurnUser.getNation().getCoin().addBalance(amount);
        return amount + " coins added successfully";
    }

    public String increaseTurn(Matcher matcher){
        int amount = Integer.parseInt(matcher.group("amount"));
        while (amount > 0)
            nextGameTurn();
        return "Progressed through " + amount + " turns successfully";
    }

    public String increaseFood(Matcher matcher){
        int amount = Integer.parseInt(matcher.group("amount"));
        currentTurnUser.getNation().getFood().addBalance(amount);
        return amount + " food added successfully";
    }

    public String increaseProduction(Matcher matcher){
        int amount = Integer.parseInt(matcher.group("amount"));
        currentTurnUser.getNation().getProduction().addBalance(amount);
        return amount + " production added successfully";
    }

    public String increaseScience(Matcher matcher){
        int amount = Integer.parseInt(matcher.group("amount"));
        currentTurnUser.getNation().getScience().addBalance(amount);
        return amount + " science added successfully";
    }

    public String increaseHappiness(Matcher matcher){
        int amount = Integer.parseInt(matcher.group("amount"));
        currentTurnUser.getNation().getHappiness().addBalance(amount);
        return amount + " happiness added successfully";
    }

    public String winGame(){
        return "You won";
    }
}