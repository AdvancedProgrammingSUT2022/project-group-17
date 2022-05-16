import Controller.GameControllers.CityController;
import Controller.GameControllers.LandController;
import Enums.GameEnums.CityCommands;
import Model.City;
import Model.Game;
import Model.Improvements.Improvement;
import Model.Improvements.ImprovementType;
import Model.Nations.Nation;
import Model.Nations.NationType;
import Model.Pair;
import Model.Users.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;

public class CityControllerTester extends Tester {
    CityController cityController = new CityController();

    @BeforeEach
    public void setup(){
        Game.map = LandController.mapInitializer();
    }

    @Test
    public void cityBuilderTestSuccessful(){
        Assert.assertTrue(cityController.isCityBuildable(new Pair(6,6)));
    }

    @Test
    public void BuildCityTest(){
        CityController.setCurrentTurnUser(new User("","",""));
        CityController.getCurrentTurnUser().setNation(new Nation(NationType.PERSIA));
        commandMatcher = CityCommands.getMatcher("build city on -x 3 -y 3",CityCommands.BUILD_CITY);
        if (commandMatcher.matches())
            cityController.buildCity(commandMatcher);
        Assert.assertFalse(cityController.isCityBuildable(new Pair(3,2)));
    }

    @Test
    public void cityBuyLandTest(){
        Nation nation = new Nation(NationType.PERSIA);
        City city = new City(nation);
        nation.increaseCoin(100000);
        Game.map[3][3].setOwnerCity(city);
        CityController.setSelectedCity(city);
        commandMatcher = CityCommands.getMatcher("buy land on -x 3 -y 4",CityCommands.BUY_LAND);
        if (commandMatcher.matches())
            System.out.println(cityController.cityBuyLand(commandMatcher));

        Assert.assertEquals(city,Game.map[3][4].getOwnerCity());

    }

    @Test
    public void sendCitizen(){
        Nation nation = new Nation(NationType.PERSIA);
        City city = new City(nation);

        CityController.setSelectedCity(city);

        Game.map[3][3].setOwnerCity(city);
        city.getLands().add(Game.map[3][3]);

        commandMatcher = CityCommands.getMatcher("send citizen to -x 3 -y 3",CityCommands.SEND_CITIZEN);
        if (commandMatcher.matches()) cityController.sendCitizen(commandMatcher);
        Assertions.assertTrue(Game.map[3][3].hasCitizen());
    }

    @Test
    public void retrieveCitizenTest(){
        Nation nation = new Nation(NationType.PERSIA);
        City city = new City(nation);

        CityController.setSelectedCity(city);

        Game.map[3][3].setOwnerCity(city);
        city.getLands().add(Game.map[3][3]);
        Game.map[3][3].setCitizen(true);
        commandMatcher = CityCommands.getMatcher("retrieve citizen from -x 3 -y 3",CityCommands.RETRIEVE_CiTIZEN);
        if (commandMatcher.matches()) cityController.retrieveCitizen(commandMatcher);

        Assertions.assertFalse(Game.map[3][3].hasCitizen());
    }

    @Test
    public void cityDeathTest(){
        Nation nation = new Nation(NationType.PERSIA);
        City city = new City(nation);

        CityController.setSelectedCity(city);

        Game.map[3][3].setOwnerCity(city);
        city.getLands().add(Game.map[3][3]);
        city.getImprovements().add(new Improvement(ImprovementType.CAMP));

        CityController.cityDeath(city);
        Assertions.assertNull(Game.map[3][3].getOwnerCity());
    }

    @Test
    public void cityTakeOverTest(){
        Nation nation = new Nation(NationType.PERSIA);
        City city = new City(nation);
        Nation doghoozAbad = new Nation(NationType.INCA);
        CityController.setSelectedCity(city);

        Game.map[3][3].setOwnerCity(city);
        city.getLands().add(Game.map[3][3]);

        CityController.cityTakeOver(city,doghoozAbad);
    }
}
