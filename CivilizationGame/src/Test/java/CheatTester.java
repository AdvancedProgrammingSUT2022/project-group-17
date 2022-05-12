import Controller.GameControllers.CheatController;
import Enums.GameEnums.GameCommands;
import Model.Game;
import Model.Nations.Nation;
import Model.Nations.NationType;
import Model.Users.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.Scanner;

public class CheatTester extends Tester {
    CheatController cheatController = new CheatController();
    User user = new User("ali", "ali", "ali");

    @BeforeEach
    public void setup() {
        user.setNation(new Nation(NationType.PERSIA));
        Game.setLoggedInUser(user);
        CheatController.setCurrentTurnUser(user);
    }

    @Test
    public void putUnitCheatTestWrongNumberInput() {
        Scanner secondScanner = new Scanner("5");
        commandMatcher = GameCommands.getMatcher("put unit on -x 7 -y 5", GameCommands.CHEAT_PUT_UNIT);
        if (commandMatcher.matches())
            Assert.assertEquals("5 is not a valid number : [0-3]", cheatController.putUnit(commandMatcher, secondScanner, user));
    }

    @Test
    public void putUnitCheatTestWrongCoordinate() {
        Scanner scanner = new Scanner("2");
        commandMatcher = GameCommands.getMatcher("put unit on -x 15 -y 25", GameCommands.CHEAT_PUT_UNIT);
        if (commandMatcher.matches())
            Assert.assertEquals("coordinate is not valid!", cheatController.putUnit(commandMatcher, scanner, user));
    }

    @Test
    public void putUnitCheatTestSuccessful0() {
        Scanner scanner = new Scanner("0");
        commandMatcher = GameCommands.getMatcher("put unit on -x 5 -y 5", GameCommands.CHEAT_PUT_UNIT);
        if (commandMatcher.matches())
            Assert.assertEquals("cheat successfully activated!", cheatController.putUnit(commandMatcher, scanner, user));
    }
    @Test
    public void putUnitCheatTestSuccessful1() {
        Scanner scanner = new Scanner("1");
        commandMatcher = GameCommands.getMatcher("put unit on -x 5 -y 5", GameCommands.CHEAT_PUT_UNIT);
        if (commandMatcher.matches())
            Assert.assertEquals("cheat successfully activated!", cheatController.putUnit(commandMatcher, scanner, user));
    }
    @Test
    public void putUnitCheatTestSuccessful2() {
        Scanner scanner = new Scanner("2");
        commandMatcher = GameCommands.getMatcher("put unit on -x 5 -y 5", GameCommands.CHEAT_PUT_UNIT);
        if (commandMatcher.matches())
            Assert.assertEquals("cheat successfully activated!", cheatController.putUnit(commandMatcher, scanner, user));
    }
    @Test
    public void putUnitCheatTestSuccessful3() {
        Scanner scanner = new Scanner("3");
        commandMatcher = GameCommands.getMatcher("put unit on -x 5 -y 5", GameCommands.CHEAT_PUT_UNIT);
        if (commandMatcher.matches())
            Assert.assertEquals("cheat successfully activated!", cheatController.putUnit(commandMatcher, scanner, user));
    }

}
