package sut.civilization.View.NonGraphical;

import sut.civilization.Controller.GameControllers.*;
import sut.civilization.Enums.GameEnums.*;
import sut.civilization.Model.Classes.Game;
import sut.civilization.Model.ModulEnums.NationType;

import java.util.Scanner;
import java.util.regex.Matcher;

public class GameMenu extends Menu {
    private final GameController gameController = new GameController();
    private final CheatController cheatController = new CheatController();
    private final UnitController unitController = new UnitController();
    private final WorkerController workerController = new WorkerController();
    private final CityController cityController = new CityController();
    private final TechnologyController technologyController = new TechnologyController();
    private final NationController nationController = new NationController();

    @Override
    public void run(Scanner scanner) {
        String input = scanner.nextLine();
        Matcher commandMatcher;
        //cheats
        if ((commandMatcher = CheatCommands.getMatcher(input, CheatCommands.PUT_UNIT)).matches()) {
            System.out.println("choose Unit:");
            System.out.println("0: close combat unit");
            System.out.println("1: ranged combat unit");
            System.out.println("2: settler unit");
            System.out.println("3: worker unit");
            System.out.println(cheatController.putUnit(commandMatcher, scanner, GameController.getCurrentTurnUser()));

        } else if ((commandMatcher = CheatCommands.getMatcher(input, CheatCommands.ADD_TECHNOLOGY)).matches()) {
            System.out.println(cheatController.addTechnology(commandMatcher));

        } else if ((commandMatcher = CheatCommands.getMatcher(input, CheatCommands.DESTROY_IMPROVEMENT)).matches()) {
            System.out.println(cheatController.destroyImprovement(commandMatcher));

        } else if (GameCommands.getMatcher(input, GameCommands.MENU_EXIT).matches()) {
            setMenuName("MainMenu");

        } else if (GameCommands.getMatcher(input, GameCommands.SHOW_MAP).matches()) {
            gameController.mapShow();

        } else if ((commandMatcher = GameCommands.getMatcher(input, GameCommands.SELECT_CIVILIZED_UNIT)).matches()) {
            System.out.println(gameController.selectCivilizedUnit(commandMatcher));

        } else if ((commandMatcher = GameCommands.getMatcher(input, GameCommands.SELECT_COMBAT_UNIT)).matches()) {
            System.out.println((gameController.selectCombatUnit(commandMatcher)));
        } else if ((commandMatcher = GameCommands.getMatcher(input, GameCommands.SELECT_CITY)).matches()) {
            System.out.println(gameController.selectCity(commandMatcher));
        } else if ((commandMatcher = UnitCommands.getMatcher(input, UnitCommands.CIVILIZED_UNIT_MOVE_TO)).matches()){
            System.out.println(UnitController.unitSetPath(Integer.parseInt(commandMatcher.group("x")), Integer.parseInt(commandMatcher.group("y")), 0));
        } else if ((commandMatcher = UnitCommands.getMatcher(input, UnitCommands.COMBAT_UNIT_MOVE_TO)).matches()){
            System.out.println(UnitController.unitSetPath(Integer.parseInt(commandMatcher.group("x")), Integer.parseInt(commandMatcher.group("y")), 1));
        } else if ((commandMatcher = CityCommands.getMatcher(input, CityCommands.BUILD_CITY)).matches()) {
            System.out.println(cityController.buildCity(commandMatcher));
        } else if ((commandMatcher = CityCommands.getMatcher(input, CityCommands.BUY_LAND)).matches()) {
            System.out.println(cityController.cityBuyLand(commandMatcher));
        } else if((commandMatcher = CityCommands.getMatcher(input, CityCommands.SEND_CITIZEN)).matches()){
            System.out.println(cityController.sendCitizen(commandMatcher));
        } else if ((commandMatcher = CityCommands.getMatcher(input, CityCommands.RETRIEVE_CITIZEN)).matches()){
            System.out.println(cityController.retrieveCitizen(commandMatcher));
        } else if ((commandMatcher = CityCommands.getMatcher(input, CityCommands.SHOW_BANNER)).matches()){
            System.out.println(cityController.cityShowBanner());
        } else if ((commandMatcher = CityCommands.getMatcher(input, CityCommands.CITY_ATTACK)).matches()){
            System.out.println(cityController.cityRangeAttack(commandMatcher));
        } else if (GameCommands.getMatcher(input, GameCommands.NEXT_TURN).matches()) {
            System.out.println(gameController.nextPlayerTurn());

            // Improvements
        } else if ((commandMatcher = WorkerCommands.getMatcher(input, WorkerCommands.BUILD_IMPROVEMENT)).matches()) {
            System.out.println(workerController.setWorkerToBuildImprovement(commandMatcher));

        } else if (WorkerCommands.getMatcher(input, WorkerCommands.REPAIR_IMPROVEMENT).matches()) {
            System.out.println(workerController.setWorkerToRepair());

        } else if (WorkerCommands.getMatcher(input, WorkerCommands.REMOVE_FEATURE).matches()) {
            System.out.println(workerController.setWorkerToRemoveFeature());

        } else if (WorkerCommands.getMatcher(input, WorkerCommands.REMOVE_ROUTE).matches()) {
            System.out.println(workerController.setWorkerToRemoveRoute());

            //info
        } else if (GameCommands.getMatcher(input, GameCommands.SHOW_RESEARCH).matches()) {
            for (String output : gameController.showResearches())
                System.out.println(output);

        } else if (GameCommands.getMatcher(input, GameCommands.SHOW_UNITS).matches()) {
            for (String output : gameController.showUnits())
                System.out.println(output);

        } else if (GameCommands.getMatcher(input, GameCommands.SHOW_CITIES).matches()) {
            for (String output : gameController.showCities())
                System.out.println(output);

        } else if (GameCommands.getMatcher(input, GameCommands.SHOW_DEMOGRAPHICS).matches()) {
            for (String output : gameController.showDemographics())
                System.out.println(output);

        } else if (GameCommands.getMatcher(input, GameCommands.SHOW_DIPLOMACIES).matches()) {
            for (String output : gameController.showDiplomacies())
                System.out.println(output);

        } else if (GameCommands.getMatcher(input, GameCommands.SHOW_MILITARIES).matches()) {
            for (String output : gameController.showMilitaries())
                System.out.println(output);

        } else if (GameCommands.getMatcher(input, GameCommands.SHOW_ECONOMICS).matches()) {
            for (String output : gameController.showEconomics())
                System.out.println(output);

        } else if ((commandMatcher = UnitCommands.getMatcher(input, UnitCommands.UNIT_ATTACK)).matches()) {
            System.out.println(unitController.unitSetCityTarget(commandMatcher));

        } else if ((commandMatcher = UnitCommands.getMatcher(input, UnitCommands.CREATE_UNIT)).matches()) {
            System.out.println(unitController.unitStartCreation(commandMatcher));
        } else if ((commandMatcher = UnitCommands.getMatcher(input, UnitCommands.PURCHASE_UNIT)).matches()){
            System.out.println(unitController.purchaseUnit(commandMatcher));

        } else if ((commandMatcher = TechnologyCommands.getMatcher(input, TechnologyCommands.ADD_TECHNOLOGY)).matches()) {
            System.out.println(technologyController.addTechnology(commandMatcher));

        } else if (UnitCommands.getMatcher(input, UnitCommands.UNIT_SLEEP).matches()) {
            System.out.println(unitController.unitSleep());

        } else if (UnitCommands.getMatcher(input, UnitCommands.UNIT_WAKE).matches()) {
            System.out.println(unitController.unitWake());

        } else if ((commandMatcher = UnitCommands.getMatcher(input, UnitCommands.COMBAT_UNIT_ACTION)).matches()) {
            System.out.println(unitController.combatUnitAction(commandMatcher));

        } else if (UnitCommands.getMatcher(input, UnitCommands.UNIT_DELETE).matches()) {
            System.out.println(unitController.unitDelete());

        } else if ((commandMatcher = NationCommands.getMatcher(input, NationCommands.SHOW_HAPPINESS)).matches()){
            System.out.println(nationController.showHappiness());
        } else if ((commandMatcher = CheatCommands.getMatcher(input, CheatCommands.CHEAT_INCREASE_FOOD)).matches()){
            System.out.println(cheatController.increaseFood(commandMatcher));
        } else if ((commandMatcher = CheatCommands.getMatcher(input, CheatCommands.CHEAT_INCREASE_GOLD)).matches()){
            System.out.println(cheatController.increaseCoin(commandMatcher));
        } else if ((commandMatcher = CheatCommands.getMatcher(input, CheatCommands.CHEAT_INCREASE_SCIENCE)).matches()){
            System.out.println(cheatController.increaseScience(commandMatcher));
        } else if ((commandMatcher = CheatCommands.getMatcher(input, CheatCommands.CHEAT_INCREASE_PRODUCTION)).matches()){
            System.out.println(cheatController.increaseProduction(commandMatcher));
        } else if ((commandMatcher = CheatCommands.getMatcher(input, CheatCommands.CHEAT_INCREASE_TURN)).matches()){
            System.out.println(cheatController.increaseTurn(commandMatcher));
        } else if ((commandMatcher = CheatCommands.getMatcher(input, CheatCommands.CHEAT_INCREASE_HAPPINESS)).matches()){
            System.out.println(cheatController.increaseHappiness(commandMatcher));
        } else if ((commandMatcher = CheatCommands.getMatcher(input, CheatCommands.CHEAT_WIN)).matches()){
            System.out.println(cheatController.winGame());
            System.exit(0);
        } else if (input.length() != 0)
            System.out.println("invalid command !");

    }

    public void startingGame(Scanner scanner) {
        int civilizationOptionsNumber = 8;
        int chosenNumber;
        for (int i = 0; i < Game.instance.getPlayersInGame().size(); i++) {
            System.out.println("Enter number of your chosen nation : (player : " + Game.instance.getPlayersInGame().get(i).getNickname() + " )");
            int j = 0;
            for (NationType nationType : NationType.values()) {
                System.out.println(j + "- " + nationType.name);
                j++;
            }

            chosenNumber = scanner.nextInt();
            if (chosenNumber > civilizationOptionsNumber) {
                System.out.println("That is not a valid number");
                i--;
                continue;
            }

            gameController.chooseNation(chosenNumber, i);
        }
    }

}
