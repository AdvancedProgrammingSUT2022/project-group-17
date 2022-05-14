import Controller.GameControllers.GameController;
import Controller.GameControllers.LandController;
import Enums.GameEnums.GameCommands;
import Model.City;
import Model.Game;
import Model.Nations.Nation;
import Model.Nations.NationType;
import Model.Units.CivilizedUnit;
import Model.Units.CloseCombatUnit;
import Model.Units.Enums.CivilizedUnitType;
import Model.Units.Enums.CloseCombatUnitType;
import Model.Units.Unit;
import Model.Users.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;

import java.util.ArrayList;

import static org.mockito.Mockito.when;

public class GameControllerTester extends Tester{
    GameController gameController = new GameController();


    @BeforeAll
    public static void setup(){
        Game.map = LandController.mapInitializer();

    }


    @Test
    public void combatUnitSelectionTestWrongPlace(){
        commandMatcher = GameCommands.getMatcher("select combat unit on -x 4 -y 4",GameCommands.SELECT_COMBAT_UNIT);
        if (commandMatcher.matches())
            Assert.assertEquals("There is no combat unit here!",gameController.selectCombatUnit(commandMatcher));
    }

    @Test
    public void combatUnitSelectionTestSuccessful(){
        Game.map[3][3].setCombatUnit(new CloseCombatUnit(CloseCombatUnitType.KNIGHT,new Nation(NationType.PERSIA),3,3));
        commandMatcher = GameCommands.getMatcher("select combat unit on -x 3 -y 3",GameCommands.SELECT_COMBAT_UNIT);
        if (commandMatcher.matches())
            Assert.assertEquals(CloseCombatUnitType.KNIGHT.name + " is now selected",gameController.selectCombatUnit(commandMatcher));
    }

    @Test
    public void civilizedUnitSelectionTestWrongPlace(){
        commandMatcher = GameCommands.getMatcher("select civilized unit on -x 4 -y 4",GameCommands.SELECT_CIVILIZED_UNIT);
        if (commandMatcher.matches())
            Assert.assertEquals("There is no civilized unit here!",gameController.selectCivilizedUnit(commandMatcher));
    }

    @Test
    public void civilizedUnitSelectionTestSuccessful(){
        Game.map[3][3].setCivilizedUnit(new CivilizedUnit(CivilizedUnitType.WORKER,new Nation(NationType.PERSIA),3,3));
        commandMatcher = GameCommands.getMatcher("select civilized unit on -x 3 -y 3",GameCommands.SELECT_CIVILIZED_UNIT);
        if (commandMatcher.matches())
            Assert.assertEquals(CivilizedUnitType.WORKER.name + " is now selected",gameController.selectCivilizedUnit(commandMatcher));
    }

    @Test
    public void ShowCommandRuntimeErrorTest(){
        User user = new User("","","");
        ArrayList<User> users = new ArrayList<>();
        users.add(user);
        Game.setPlayersInGame(users);
        GameController.setCurrentTurnUser(Game.getPlayersInGame().get(0));
        Game.setUsers(users);
        for (int i = 0; i < 9; i++) {
            gameController.chooseNation(i,0);
        }
        user.getNation().addUnit(new CloseCombatUnit(CloseCombatUnitType.KNIGHT,user.getNation(),3,3));
        ArrayList<City> cities = new ArrayList<>();
        cities.add(new City(user.getNation()));
        user.getNation().setCities(cities);
        gameController.showResearches();
        gameController.showCities();
        gameController.showDemographics();
        gameController.showMilitaries();
        gameController.showUnits();
        gameController.showDiplomacies();
        gameController.showEconomics();

    }
}
