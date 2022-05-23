import sut.civilization.Controller.GameControllers.NationController;
import sut.civilization.Model.Nations.Nation;
import sut.civilization.Model.Nations.NationType;
import sut.civilization.Model.Users.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class NationTester extends Tester {
    NationController nationController = new NationController();
    User user = new User("", "","");
    Nation nation = new Nation(NationType.PERSIA);

    @BeforeEach
    public void setup(){
        user.setNation(nation);

        NationController.setCurrentTurnUser(user);
    }

    @Test
    public void ShowHappinessTest(){
        Assertions.assertEquals("100",nationController.showHappiness());
    }
}
