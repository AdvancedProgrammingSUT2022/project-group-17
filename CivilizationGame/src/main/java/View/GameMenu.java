package View;

import Controller.GameControllers.CheatController;
import Controller.GameControllers.GameController;
import Controller.GameControllers.UnitController;
import Controller.GameControllers.WorkerController;
import Enums.GameEnums.CheatCommands;
import Enums.GameEnums.GameCommands;
import Enums.GameEnums.UnitCommands;
import Enums.GameEnums.WorkerCommands;
import Model.Game;
import Model.Improvements.ImprovementType;

import java.util.Scanner;
import java.util.regex.Matcher;

public class GameMenu extends Menu{
    private final GameController gameController = new GameController();
    private final CheatController cheatController = new CheatController();
    private final UnitController unitController = new UnitController();
    private final WorkerController workerController = new WorkerController();

    @Override
    public void run(Scanner scanner) {
        String input = scanner.nextLine();
        Matcher commandMatcher;

        //cheats
        if ((commandMatcher = CheatCommands.getMatcher(input, CheatCommands.PUT_UNIT)).matches()){
            System.out.println("choose Unit:");
            System.out.println("0: close combat unit");
            System.out.println("1: ranged combat unit");
            System.out.println("2: settler unit");
            System.out.println("3: worker unit");
            System.out.println(cheatController.putUnit(commandMatcher,scanner));

        } else if((commandMatcher = CheatCommands.getMatcher(input, CheatCommands.ADD_TECHNOLOGY)).matches()) {
            System.out.println(cheatController.addTechnology(commandMatcher));

        } else if((commandMatcher = CheatCommands.getMatcher(input, CheatCommands.DESTROY_IMPROVEMENT)).matches()) {
            System.out.println(cheatController.destroyImprovement(commandMatcher));

        } else if (GameCommands.getMatcher(input,GameCommands.MENU_EXIT).matches()){
            setMenuName("MainMenu");

        } else if (GameCommands.getMatcher(input, GameCommands.SHOW_MAP).matches()){
            gameController.mapShow();

        } else if ((commandMatcher = GameCommands.getMatcher(input, GameCommands.SELECT_CIVILIZED_UNIT)).matches()){
            System.out.println(gameController.selectCivilizedUnit(commandMatcher));

        } else if ((commandMatcher = GameCommands.getMatcher(input, GameCommands.SELECT_COMBAT_UNIT)).matches()){
            System.out.println((gameController.selectCombatUnit(commandMatcher)));

        } else if ((commandMatcher = UnitCommands.getMatcher(input, UnitCommands.UNIT_MOVE_TO)).matches()){
            unitController.unitGoToDestination(commandMatcher);

        } else if (GameCommands.getMatcher(input,GameCommands.NEXT_TURN).matches()){
            gameController.nextPlayerTurn();

        // Improvements
        } else if (WorkerCommands.getMatcher(input, WorkerCommands.BUILD_PASTURE).matches()) {
            System.out.println(workerController.workerBuildResourcedImprovement(ImprovementType.PASTURE));

        } else if (WorkerCommands.getMatcher(input, WorkerCommands.BUILD_FARM).matches()) {
            System.out.println(workerController.workerBuildFarm());

        } else if (WorkerCommands.getMatcher(input, WorkerCommands.BUILD_MINE).matches()) {
            System.out.println(workerController.workerBuildMine());

        } else if (WorkerCommands.getMatcher(input, WorkerCommands.BUILD_CAMP).matches()) {
            System.out.println(workerController.workerBuildResourcedImprovement(ImprovementType.CAMP));

        } else if (WorkerCommands.getMatcher(input, WorkerCommands.BUILD_LUMBER_MILL).matches()) {
            System.out.println(workerController.workerBuildNonResourcedImprovement(ImprovementType.LUMBER_MILL));

        } else if (WorkerCommands.getMatcher(input, WorkerCommands.BUILD_PLANTATION).matches()) {
            System.out.println(workerController.workerBuildResourcedImprovement(ImprovementType.PLANTATION));

        } else if (WorkerCommands.getMatcher(input, WorkerCommands.BUILD_QUARRY).matches()) {
            System.out.println(workerController.workerBuildResourcedImprovement(ImprovementType.QUARRY));

        } else if (WorkerCommands.getMatcher(input, WorkerCommands.BUILD_RAILROAD).matches()) {
            System.out.println(workerController.workerBuildRailroad());

        } else if (WorkerCommands.getMatcher(input, WorkerCommands.BUILD_ROAD).matches()) {
            System.out.println(workerController.workerBuildRoad());

        } else if (WorkerCommands.getMatcher(input, WorkerCommands.BUILD_TRADING_POST).matches()) {
            System.out.println(workerController.workerBuildNonResourcedImprovement(ImprovementType.TRADING_POST));

        } else if (WorkerCommands.getMatcher(input, WorkerCommands.BUILD_FACTORY).matches()) {
            System.out.println(workerController.workerBuildNonResourcedImprovement(ImprovementType.FACTORY));

        } else if (WorkerCommands.getMatcher(input, WorkerCommands.REPAIR_IMPROVEMENT).matches()) {
            System.out.println(workerController.workerRepair());

        } else if (WorkerCommands.getMatcher(input, WorkerCommands.REMOVE_FEATURE).matches()) {
            System.out.println(workerController.workerRemoveFeature());

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
