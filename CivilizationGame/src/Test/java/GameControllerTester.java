import sut.civilization.Controller.GameControllers.GameController;
import sut.civilization.Controller.GameControllers.LandController;
import sut.civilization.Enums.GameEnums.GameCommands;
import sut.civilization.Model.City;
import sut.civilization.Model.Game;
import sut.civilization.Model.Nations.Nation;
import sut.civilization.Model.Nations.NationType;
import sut.civilization.Model.Pair;
import sut.civilization.Model.Units.CivilizedUnit;
import sut.civilization.Model.Units.CloseCombatUnit;
import sut.civilization.Model.Units.Enums.CivilizedUnitType;
import sut.civilization.Model.Units.Enums.CloseCombatUnitType;
import sut.civilization.Model.Users.User;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;


public class GameControllerTester extends Tester{
    GameController gameController = new GameController();


    @BeforeEach
    public void setup(){
        Game.map = LandController.mapInitializer();
        Nation persia = new Nation(NationType.PERSIA);
        Nation inca = new Nation(NationType.INCA);

        users = new ArrayList<>();
        users.add(new User("1","1","1"));
        users.add(new User("2","2","2"));
        users.get(0).setNation(persia);
        users.get(1).setNation(inca);

        Game.map[3][3].setCivilizedUnit(new CivilizedUnit(CivilizedUnitType.WORKER,persia,new Pair(3,3)));
        Game.map[3][3].setCombatUnit(new CloseCombatUnit(CloseCombatUnitType.KNIGHT,persia,new Pair(3,3)));

        City tehran = new City(persia,"tehran");
        City doghoozAbad = new City(inca,"doghooz Abad");

        Game.map[4][4].setOwnerCity(tehran);
        Game.map[5][5].setOwnerCity(doghoozAbad);

        GameController.setCurrentTurnUser(new User("","",""));
        GameController.getCurrentTurnUser().setNation(persia);
    }


    @Test
    public void combatUnitSelectionTestWrongPlace(){
        commandMatcher = GameCommands.getMatcher("select combat unit on -x 4 -y 4",GameCommands.SELECT_COMBAT_UNIT);
        if (commandMatcher.matches())
            Assert.assertEquals("There is no combat unit here!",gameController.selectCombatUnit(commandMatcher));
    }

    @Test
    public void combatUnitSelectionTestSuccessful(){
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
        user.getNation().addUnit(new CloseCombatUnit(CloseCombatUnitType.KNIGHT,user.getNation(),new Pair(3,3)));
        ArrayList<City> cities = new ArrayList<>();
        cities.add(new City(user.getNation(),"city"));
        user.getNation().setCities(cities);
        gameController.showResearches();
        gameController.showCities();
        gameController.showDemographics();
        gameController.showMilitaries();
        gameController.showUnits();
        gameController.showDiplomacies();
        gameController.showEconomics();

    }

    @Test
    public void selectCityTestFail(){

        commandMatcher = GameCommands.getMatcher("select city on -x 5 -y 5",GameCommands.SELECT_CITY);

        if (commandMatcher.matches()) Assertions.assertEquals("You can't select opponent's city",gameController.selectCity(commandMatcher));
    }

    @Test
    public void selectCityTestSuccessful(){

        commandMatcher = GameCommands.getMatcher("select city on -x 4 -y 4",GameCommands.SELECT_CITY);

        if (commandMatcher.matches()) Assertions.assertNotEquals("You can't select opponent's city",gameController.selectCity(commandMatcher));

    }

    @Test
    public void nextPlayerTurnTest(){
        Game.setPlayersInGame(users);

        Assertions.assertEquals("next player turn!: 2",gameController.nextPlayerTurn());
    }

    @Test
    public void nextGodDamnTurn(){
        Game.setPlayersInGame(users);
        gameController.nextGameTurn();
    }
}
