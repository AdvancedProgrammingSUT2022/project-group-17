import Controller.GameControllers.TechnologyController;
import Enums.GameEnums.TechnologyCommands;
import Model.Nations.Nation;
import Model.Nations.NationType;
import Model.Technologies.TechnologyType;
import Model.Users.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TechnologyTester extends Tester{
    TechnologyController technologyController = new TechnologyController();

    Nation nation = new Nation(NationType.PERSIA);
    User user = new User("","","");

    @BeforeEach
    public void setup(){
        user.setNation(nation);

        TechnologyController.setCurrentTurnUser(user);
    }


    @Test
    public void addTechnologyTestFail(){
        commandMatcher = TechnologyCommands.getMatcher("add technology Animal Husbandry",TechnologyCommands.ADD_TECHNOLOGY);

        if (commandMatcher.matches()){
            Assertions.assertEquals("You don't have the required technologies",technologyController.addTechnology(commandMatcher));
        }
    }


    @Test
    public void addTechnologyTestSuccessful(){
        commandMatcher = TechnologyCommands.getMatcher("add technology Agriculture",TechnologyCommands.ADD_TECHNOLOGY);

        if (commandMatcher.matches()){
            Assertions.assertEquals("Technology added successfully",technologyController.addTechnology(commandMatcher));
        }
    }


    @Test
    public void addTechnologyToList(){
        nation.setInProgressTechnology(TechnologyType.Agriculture);
        TechnologyController.activateTechnology(nation);

        Assertions.assertTrue(nation.hasTechnology(TechnologyType.Agriculture));
    }
}
