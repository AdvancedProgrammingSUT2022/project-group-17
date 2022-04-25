package View;

import Controller.GameControllers.GameController;
import Enums.GameEnums.GameCommands;
import Model.Units.Enums.CloseCombatUnitType;

import java.util.Scanner;
import java.util.regex.Matcher;

public class GameMenu extends Menu{
    private GameController gameController = new GameController();
    private
    @Override
    public void run(Scanner scanner) {
        String input = scanner.nextLine();
        Matcher commandMatcher;
        if ((commandMatcher = GameCommands.getMatcher(input,GameCommands.CHEAT_PUT_UNIT)).matches()){
            gameController.
        } else if ((commandMatcher = GameCommands.getMatcher(input,GameCommands.MENU_EXIT)).matches()){
            Menu.setMenuName("MainMenu");
        }
    }
}
