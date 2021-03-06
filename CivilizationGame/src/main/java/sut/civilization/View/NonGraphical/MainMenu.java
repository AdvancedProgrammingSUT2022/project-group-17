package sut.civilization.View.NonGraphical;

import sut.civilization.Controller.MainController;
import sut.civilization.Enums.MainCommands;

import java.util.Scanner;
import java.util.regex.Matcher;

public class MainMenu extends Menu{
    private final MainController mainController = new MainController();

    @Override
    public void run(Scanner scanner) {
        String input = scanner.nextLine();
        Matcher commandMatcher;
        if ((commandMatcher = MainCommands.getMatcher(input,MainCommands.MAIN_COMMANDS)).matches()){
            System.out.println(mainController.menuChange(commandMatcher.group("menuName")));

            if(commandMatcher.group("menuName").equals("profile menu"))
                mainController.menuChange("profile menu");
            else
                mainController.menuChange("login menu");

        } else if (MainCommands.getMatcher(input,MainCommands.logout).matches()){
            System.out.println("logout successfully");
            mainController.logoutUser();

        } else if ((commandMatcher = MainCommands.getMatcher(input,MainCommands.playGame)).matches()){
            System.out.println(mainController.playGame(commandMatcher));
            new GameMenu().startingGame(scanner);

        } else if (MainCommands.getMatcher(input,MainCommands.exit).matches()){
            mainController.menuChange("login menu");

        } else {
            System.out.println("invalid command!");

        }
    }
}
