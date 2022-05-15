package View;

import Controller.GameControllers.*;
import Enums.GameEnums.*;
import Model.Game;
import Model.Improvements.ImprovementType;

import java.util.Scanner;
import java.util.regex.Matcher;

public class GameMenu extends Menu{
    private final GameController gameController = new GameController();
    private final CheatController cheatController = new CheatController();
    private final UnitController unitController = new UnitController();
    private final WorkerController workerController = new WorkerController();
    private final CityController cityController = new CityController();
    private final TechnologyController technologyController = new TechnologyController();

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
            System.out.println(cheatController.putUnit(commandMatcher,scanner,GameController.getCurrentTurnUser()));

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
        } else if ((commandMatcher = GameCommands.getMatcher(input, GameCommands.SELECT_CITY)).matches()) {
            System.out.println(gameController.selectCity(commandMatcher));
        } else if ((commandMatcher = UnitCommands.getMatcher(input, UnitCommands.CIVILIZED_UNIT_MOVE_TO)).matches()){
            unitController.unitSetPath(commandMatcher, 0);
        } else if ((commandMatcher = UnitCommands.getMatcher(input, UnitCommands.COMBAT_UNIT_MOVE_TO)).matches()){
            unitController.unitSetPath(commandMatcher, 1);
        } else if ((commandMatcher = CityCommands.getMatcher(input, CityCommands.BUILD_CITY)).matches()){
            System.out.println(cityController.buildCity(commandMatcher));
        } else if ((commandMatcher = CityCommands.getMatcher(input, CityCommands.BUY_LAND)).matches()){
            System.out.println(cityController.cityBuyLand(commandMatcher));
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
            System.out.println(workerController.workerBuildRoad(ImprovementType.RAILROAD));

        } else if (WorkerCommands.getMatcher(input, WorkerCommands.BUILD_ROAD).matches()) {
            System.out.println(workerController.workerBuildRoad(ImprovementType.ROAD));

        } else if (WorkerCommands.getMatcher(input, WorkerCommands.BUILD_TRADING_POST).matches()) {
            System.out.println(workerController.workerBuildNonResourcedImprovement(ImprovementType.TRADING_POST));

        } else if (WorkerCommands.getMatcher(input, WorkerCommands.BUILD_FACTORY).matches()) {
            System.out.println(workerController.workerBuildNonResourcedImprovement(ImprovementType.FACTORY));

        } else if (WorkerCommands.getMatcher(input, WorkerCommands.REPAIR_IMPROVEMENT).matches()) {
            System.out.println(workerController.workerRepair());

        } else if (WorkerCommands.getMatcher(input, WorkerCommands.REMOVE_FEATURE).matches()) {
            System.out.println(workerController.workerRemoveFeature());

        } else if (WorkerCommands.getMatcher(input, WorkerCommands.REMOVE_ROUTE).matches()) {
            System.out.println(workerController.workerRemoveRoute());

        //info
        } else if (GameCommands.getMatcher(input, GameCommands.SHOW_RESEARCH).matches()) {
            for (String output: gameController.showResearches())
                System.out.println(output);

        } else if (GameCommands.getMatcher(input, GameCommands.SHOW_UNITS).matches()) {
            for (String output: gameController.showUnits())
                System.out.println(output);

        } else if (GameCommands.getMatcher(input, GameCommands.SHOW_CITIES).matches()) {
            for (String output: gameController.showCities())
                System.out.println(output);

        } else if (GameCommands.getMatcher(input, GameCommands.SHOW_DEMOGRAPHICS).matches()) {
            for (String output: gameController.showDemographics())
                System.out.println(output);

        } else if (GameCommands.getMatcher(input, GameCommands.SHOW_DIPLOMACIES).matches()) {
            for (String output: gameController.showDiplomacies())
                System.out.println(output);

        } else if (GameCommands.getMatcher(input, GameCommands.SHOW_MILITARIES).matches()) {
            for (String output: gameController.showMilitaries())
                System.out.println(output);

        } else if (GameCommands.getMatcher(input, GameCommands.SHOW_ECONOMICS).matches()) {
            for (String output: gameController.showEconomics())
                System.out.println(output);

        } else if ((commandMatcher = UnitCommands.getMatcher(input, UnitCommands.UNIT_ATTACK)).matches()){
            System.out.println(unitController.unitSetCityTarget());
        } else if ((commandMatcher = UnitCommands.getMatcher(input, UnitCommands.CREATE_UNIT)).matches()){
            System.out.println(unitController.unitStartCreation(commandMatcher));
        } else if ((commandMatcher = UnitCommands.getMatcher(input, UnitCommands.CREATE_UNIT)).matches()){
            System.out.println(unitController.purchaseUnit(commandMatcher));
        } else if ((commandMatcher = TechnologyCommands.getMatcher(input, TechnologyCommands.ADD_TECHNOLOGY)).matches()){
            System.out.println(technologyController.addTechnology(commandMatcher));
        } else if (input.length() != 0)
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
