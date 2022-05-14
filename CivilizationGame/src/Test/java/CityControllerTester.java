import Controller.GameControllers.CityController;
import Controller.GameControllers.LandController;
import Enums.GameEnums.CityCommands;
import Model.City;
import Model.Game;
import Model.Nations.Nation;
import Model.Nations.NationType;
import Model.Pair;
import Model.Users.User;
import org.junit.Assert;
import org.junit.Test;
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
}
