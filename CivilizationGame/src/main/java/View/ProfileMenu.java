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

        if ((commandMatcher = ProfileCommands.getMatcher(input,ProfileCommands.changePassword)).matches()){
            System.out.println(profileController.changePassword(commandMatcher));

        } else if ((commandMatcher = ProfileCommands.getMatcher(input,ProfileCommands.changeNickname)).matches()){
            System.out.println(profileController.changeNickname(commandMatcher));

        } else if (ProfileCommands.getMatcher(input,ProfileCommands.infoProfile).matches()){
            System.out.println("user account details :\n" +
                    "----------------------------------------\n" +
                    "user name : " + Game.getLoggedInUser().getUsername() + "\n" +
                    "nick name : " + Game.getLoggedInUser().getNickname() + "\n" +
                    "score : " + Game.getLoggedInUser().getScore()
            );

        } else if ((commandMatcher = ProfileCommands.getMatcher(input,ProfileCommands.removeAccount)).matches()){
            System.out.println(profileController.removeAccount(commandMatcher));
            profileController.changeMenu("LoginMenu");

        } else if (ProfileCommands.getMatcher(input,ProfileCommands.exit).matches()){
            profileController.changeMenu("MainMenu");
        } else {
            System.out.println("invalid command!");

        }
    }
}
