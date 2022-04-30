package View;

import Controller.GameControllers.CheatController;
import Controller.GameControllers.GameController;
import Controller.GameControllers.UnitController;
import Enums.GameEnums.GameCommands;
import Enums.GameEnums.UnitCommands;
import Model.Game;
import Model.Nations.Nation;
import Model.Nations.NationType;

import java.util.Scanner;
import java.util.regex.Matcher;

public class GameMenu extends Menu{
    private final GameController gameController = new GameController();
    private final CheatController cheatController = new CheatController();
    private final UnitController unitController = new UnitController();

    @Override
    public void run(Scanner scanner) {
        String input = scanner.nextLine();
        Matcher commandMatcher;
        if ((commandMatcher = GameCommands.getMatcher(input,GameCommands.CHEAT_PUT_UNIT)).matches()){
            System.out.println("choose Unit:");
            System.out.println("0: close combat unit");
            System.out.println("1: ranged combat unit");
            System.out.println("2: settler unit");
            System.out.println("3: worker unit");
            System.out.println(cheatController.putUnit(commandMatcher,scanner));

        } else if ((commandMatcher = GameCommands.getMatcher(input,GameCommands.MENU_EXIT)).matches()){
            setMenuName("MainMenu");

        } else if ((commandMatcher = GameCommands.getMatcher(input, GameCommands.SHOW_MAP)).matches()){
            gameController.mapShow();

        } else if ((commandMatcher = GameCommands.getMatcher(input, GameCommands.SELECT_CIVILIZED_UNIT)).matches()){
            System.out.println(gameController.selectCivilizedUnit(commandMatcher));

        } else if ((commandMatcher = GameCommands.getMatcher(input, GameCommands.SELECT_COMBAT_UNIT)).matches()){
            System.out.println((gameController.selectCombatUnit(commandMatcher)));

        } else if ((commandMatcher = UnitCommands.getMatcher(input, UnitCommands.UNIT_MOVE_TO)).matches()){
            unitController.unitGoToDestination(commandMatcher);

        } else if (GameCommands.getMatcher(input,GameCommands.NEXT_TURN).matches()){
            gameController.nextPlayerTurn();

        } else
            System.out.println("invalid command !");
    }

    public void startingGame(Scanner scanner){
        int civilizationOptionsNumber = 8;
        int chosenNumber;
        for (int i = 0; i < Game.getPlayersInGame().size(); i++) {
            System.out.println("Enter number of your chosen nation : (player : " + Game.getPlayersInGame().get(i).getNickname() + " )");
            System.out.println("0- Indus Valley");
            System.out.println("1- Maya");
            System.out.println("2- Ancient Greece");
            System.out.println("3- Persia");
            System.out.println("4- Ancient Egypt");
            System.out.println("5- Mesopotamian");
            System.out.println("6- Rome");
            System.out.println("7- Aztec");
            System.out.println("8- Inca");

            chosenNumber = scanner.nextInt();
            if (chosenNumber > civilizationOptionsNumber) {
                System.out.println("That is not a valid number");
                i--;
                continue;
            }

            gameController.chooseNation(chosenNumber,i);
        }
    }
}
