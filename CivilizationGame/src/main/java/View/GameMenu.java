package View;

import Controller.Controller;
import Controller.GameControllers.CheatController;
import Controller.GameControllers.GameController;
import Controller.GameControllers.LandController;
import Controller.GameControllers.UnitController;
import Enums.GameEnums.GameCommands;
import Enums.GameEnums.UnitCommands;
import Model.Game;
import Model.Units.Enums.CloseCombatUnitType;

import javax.xml.stream.events.Comment;
import java.util.Scanner;
import java.util.regex.Matcher;

public class GameMenu extends Menu{
    private GameController gameController = new GameController();
    private CheatController cheatController = new CheatController();
    private UnitController unitController = new UnitController();

    @Override
    public void run(Scanner scanner) {
        String input = scanner.nextLine();
        Matcher commandMatcher;
        if ((commandMatcher = GameCommands.getMatcher(input,GameCommands.CHEAT_PUT_UNIT)).matches()){
            cheatController.putUnit(commandMatcher);
        } else if ((commandMatcher = GameCommands.getMatcher(input,GameCommands.MENU_EXIT)).matches()){
            Menu.setMenuName("MainMenu");
        } else if ((commandMatcher = GameCommands.getMatcher(input, GameCommands.SHOW_MAP)).matches()){
            new LandController().printMap(Game.map);
//        } else if ((commandMatcher = GameCommands.getMatcher(input, GameCommands.SELECT_CIVILIZED_UNIT)).matches()){
//            gameController.selectCivilizedUnit(commandMatcher);
        } else if ((commandMatcher = GameCommands.getMatcher(input, GameCommands.SELECT_COMBAT_UNIT)).matches()){
            gameController.selectCombatUnit(commandMatcher);
        } else if ((commandMatcher = UnitCommands.getMatcher(input, UnitCommands.UNIT_MOVE_TO)).matches()){
            unitController.unitGoToDest(commandMatcher);
        }
        else if (input == "exit")
            setMenuName("exit");
        else
            System.out.println("commandet ride");
    }
}
