import sut.civilization.Controller.GameControllers.CheatController;
import sut.civilization.Enums.GameEnums.CheatCommands;
import sut.civilization.Model.Game;
import sut.civilization.Model.Improvements.Improvement;
import sut.civilization.Model.Improvements.ImprovementType;
import sut.civilization.Model.Nations.Nation;
import sut.civilization.Model.Nations.NationType;
import sut.civilization.Model.Users.User;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import java.util.Scanner;

public class CheatTester extends Tester {
    CheatController cheatController = new CheatController();
    User user = new User("ali", "ali", "ali");

    @BeforeEach
    public void setup() {
        user.setNation(new Nation(NationType.PERSIA));
        Game.setSubTurn(0);
        Game.getPlayersInGame().add(user);
        Game.getPlayersInGame().add(new User("","",""));
        Game.getPlayersInGame().get(1).setNation(new Nation(NationType.PERSIA));
        Game.getPlayersInGame().get(Game.getSubTurn()).setNation(new Nation(NationType.PERSIA));
        Game.setLoggedInUser(user);
        CheatController.setCurrentTurnUser(user);
    }

    @Test
    public void putUnitCheatTestWrongNumberInput() {
        Scanner secondScanner = new Scanner("5");
        commandMatcher = CheatCommands.getMatcher("put unit on -x 7 -y 5", CheatCommands.PUT_UNIT);
        if (commandMatcher.matches())
            Assert.assertEquals("5 is not a valid number : [0-3]", cheatController.putUnit(commandMatcher, secondScanner, user));
    }

    @Test
    public void putUnitCheatTestWrongCoordinate() {
        Scanner scanner = new Scanner("2");
        commandMatcher = CheatCommands.getMatcher("put unit on -x 25 -y 25", CheatCommands.PUT_UNIT);
        if (commandMatcher.matches())
            Assert.assertEquals("coordinate is not valid!", cheatController.putUnit(commandMatcher, scanner, user));
    }

    @Test
    public void putUnitCheatTestSuccessful0() {
        Scanner scanner = new Scanner("0");
        commandMatcher = CheatCommands.getMatcher("put unit on -x 5 -y 5", CheatCommands.PUT_UNIT);
        if (commandMatcher.matches())
            Assert.assertEquals("cheat successfully activated!", cheatController.putUnit(commandMatcher, scanner, user));
    }

    @Test
    public void putUnitCheatTestSuccessful1() {
        Scanner scanner = new Scanner("1");
        commandMatcher = CheatCommands.getMatcher("put unit on -x 5 -y 5", CheatCommands.PUT_UNIT);
        if (commandMatcher.matches())
            Assert.assertEquals("cheat successfully activated!", cheatController.putUnit(commandMatcher, scanner, user));
    }

    @Test
    public void putUnitCheatTestSuccessful2() {
        Scanner scanner = new Scanner("2");
        user.setNation(new Nation(NationType.PERSIA));
        commandMatcher = CheatCommands.getMatcher("put unit on -x 5 -y 5", CheatCommands.PUT_UNIT);
        if (commandMatcher.matches())
            Assert.assertEquals("cheat successfully activated!", cheatController.putUnit(commandMatcher, scanner, user));
    }

    @Test
    public void putUnitCheatTestSuccessful3() {
        Scanner scanner = new Scanner("3");
        commandMatcher = CheatCommands.getMatcher("put unit on -x 5 -y 5", CheatCommands.PUT_UNIT);
        if (commandMatcher.matches())
            Assert.assertEquals("cheat successfully activated!", cheatController.putUnit(commandMatcher, scanner, user));
    }

    @Test
    public void technologyCheatWrongName() {
        commandMatcher = CheatCommands.getMatcher("add technology --name kooft", CheatCommands.ADD_TECHNOLOGY);
        if (commandMatcher.matches())
            Assert.assertEquals("error! technology name doesn't exist", cheatController.addTechnology(commandMatcher));
    }

    @Test
    public void technologyCheatSuccessful() {
        commandMatcher = CheatCommands.getMatcher("add technology --name Agriculture", CheatCommands.ADD_TECHNOLOGY);
        user.setNation(new Nation(NationType.PERSIA));
        CheatController.setCurrentTurnUser(user);
        if (commandMatcher.matches())
            Assert.assertEquals("cheat successfully activated! : Technology's added", cheatController.addTechnology(commandMatcher));
    }

    @Test
    public void destroyImprovementCheatWrongPlace(){
        commandMatcher = CheatCommands.getMatcher("destroy improvement on -x 5 -y 5",CheatCommands.DESTROY_IMPROVEMENT);
        if (commandMatcher.matches())
            Assert.assertEquals("There isn't any Improvement here!",cheatController.destroyImprovement(commandMatcher));
    }

    @Test
    public void destroyImprovementCheatRightPlace(){
        Game.map[3][3].setImprovement(new Improvement(ImprovementType.FARM));
        commandMatcher = CheatCommands.getMatcher("destroy improvement on -x 3 -y 3",CheatCommands.DESTROY_IMPROVEMENT);
        if (commandMatcher.matches())
            Assert.assertEquals("The improvement is now broken!",cheatController.destroyImprovement(commandMatcher));
    }

    @Test
    public void cheatIncreaseCoin(){
        commandMatcher = CheatCommands.getMatcher("increase --gold 100",CheatCommands.CHEAT_INCREASE_GOLD);
        if (commandMatcher.matches())
            Assertions.assertEquals("100 coins added successfully",cheatController.increaseCoin(commandMatcher));
    }
//    @Test
//    public void cheatIncreaseTurn(){
//        commandMatcher = CheatCommands.getMatcher("increase --turn 1",CheatCommands.CHEAT_INCREASE_TURN);
//        if (commandMatcher.matches())
//            Assertions.assertEquals("Progress through 1 turns successfully",cheatController.increaseTurn(commandMatcher));
//    }
    @Test
    public void cheatIncreaseFood(){
        commandMatcher = CheatCommands.getMatcher("increase --food 100",CheatCommands.CHEAT_INCREASE_FOOD);
        if (commandMatcher.matches())
            Assertions.assertEquals("100 food added successfully",cheatController.increaseFood(commandMatcher));
    }
    @Test
    public void cheatIncreaseProduction(){
        commandMatcher = CheatCommands.getMatcher("increase --production 100",CheatCommands.CHEAT_INCREASE_PRODUCTION);
        if (commandMatcher.matches())
            Assertions.assertEquals("100 production added successfully",cheatController.increaseProduction(commandMatcher));
    }
    @Test
    public void cheatIncreaseHappiness(){
        commandMatcher = CheatCommands.getMatcher("increase --happiness 100",CheatCommands.CHEAT_INCREASE_HAPPINESS);
        if (commandMatcher.matches())
            Assertions.assertEquals("100 happiness added successfully",cheatController.increaseHappiness(commandMatcher));
    }
    @Test
    public void cheatWon(){
        commandMatcher = CheatCommands.getMatcher("win game",CheatCommands.CHEAT_WIN);
        if (commandMatcher.matches())
            Assertions.assertEquals("You won",cheatController.winGame());
    }
}
