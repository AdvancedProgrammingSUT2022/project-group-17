//import sut.civilization.Controller.MainController;
//import sut.civilization.Enums.MainCommands;
//import sut.civilization.Model.Game;
//import org.junit.Assert;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.BeforeAll;
//
//import static org.mockito.Mockito.verify;
//
//public class MainMenuTester extends Tester{
//    MainController mainController = new MainController();
//
//    @BeforeAll
//    public static void setup(){
//        Game.instance.setUsers(Game.instance.readUserListFromDatabase());
//    }
//
//    @Test
//    public void playGameTestWithWrongPeople(){
//        commandMatcher = MainCommands.getMatcher("play game -p1 Hamed_Saboor_Yaraghi -p2 Asghar",MainCommands.playGame);
//        if (commandMatcher.matches()){
//            Assert.assertEquals("at least one user name doesn't exist",mainController.playGame(commandMatcher));
//        }
//    }
//
//    @Test
//    public void playGameTestSuccessfully(){
//        commandMatcher = MainCommands.getMatcher("play game -p1 Hamed_Saboor_Yaraghi -p2 Mohammad_Mahdi_Akbar",MainCommands.playGame);
//        if (commandMatcher.matches()){
//            Assert.assertEquals("game successfully started!",mainController.playGame(commandMatcher));
//        }
//    }
//
//    @Test
//    public void menuChangeTestWrongMenuName(){
//        commandMatcher = MainCommands.getMatcher("menu enter KhoonehHamsaye",MainCommands.MAIN_COMMANDS);
//        if (commandMatcher.matches())
//            Assert.assertEquals("invalid menu name\navailable menu names : \"login menu\" \"profile menu\"",mainController.menuChange("kooche Hamsaye"));
//    }
//
//    @Test
//    public void menuChangeTestSuccessful(){
//        commandMatcher = MainCommands.getMatcher("menu enter KhoonehHamsaye",MainCommands.MAIN_COMMANDS);
//        if (commandMatcher.matches())
//            Assert.assertEquals("menu changed successfully",mainController.menuChange("profile menu"));
//    }
//
//
//}
