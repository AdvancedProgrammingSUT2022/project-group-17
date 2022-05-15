package View;

import Controller.ProfileController;
import Enums.ProfileCommands;
import Model.Game;

import java.util.Scanner;
import java.util.regex.Matcher;

public class ProfileMenu extends Menu{
    private final ProfileController profileController = new ProfileController();

    @Override
    public void run(Scanner scanner) {
        String input = scanner.nextLine();
        Matcher commandMatcher;

        if ((commandMatcher = ProfileCommands.getMatcher(input,ProfileCommands.CHANGE_PASSWORD)).matches()){
            System.out.println(profileController.changePassword(Game.getLoggedInUser(),commandMatcher));

        } else if ((commandMatcher = ProfileCommands.getMatcher(input,ProfileCommands.CHANGE_NICKNAME)).matches()){
            System.out.println(profileController.changeNickname(Game.getLoggedInUser(),commandMatcher));

        } else if (ProfileCommands.getMatcher(input,ProfileCommands.INFO_PROFILE).matches()){
            System.out.println("user account details :\n" +
                    "----------------------------------------\n" +
                    "user name : " + Game.getLoggedInUser().getUsername() + "\n" +
                    "nick name : " + Game.getLoggedInUser().getNickname() + "\n" +
                    "score : " + Game.getLoggedInUser().getScore()
            );

        } else if ((commandMatcher = ProfileCommands.getMatcher(input,ProfileCommands.REMOVE_ACCOUNT)).matches()){
            System.out.println(profileController.removeAccount(Game.getLoggedInUser(),commandMatcher));
            Menu.setMenuName("LoginMenu");

        } else if (ProfileCommands.getMatcher(input,ProfileCommands.EXIT).matches()){
            Menu.setMenuName("MainMenu");
        } else {
            System.out.println("invalid command!");

        }
    }
}
