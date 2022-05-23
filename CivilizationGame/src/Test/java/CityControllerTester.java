import sut.civilization.Controller.GameControllers.CityController;
import sut.civilization.Controller.GameControllers.LandController;
import sut.civilization.Enums.GameEnums.CityCommands;
import sut.civilization.Model.City;
import sut.civilization.Model.Game;
import sut.civilization.Model.Improvements.Improvement;
import sut.civilization.Model.Improvements.ImprovementType;
import sut.civilization.Model.Lands.LandType;
import sut.civilization.Model.Nations.Nation;
import sut.civilization.Model.Nations.NationType;
import sut.civilization.Model.Pair;
import sut.civilization.Model.Units.CivilizedUnit;
import sut.civilization.Model.Units.Enums.CivilizedUnitType;
import sut.civilization.Model.Users.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;

public class CityControllerTester extends Tester {
    CityController cityController = new CityController();
    Nation nation = new Nation(NationType.PERSIA);
    City city = new City(nation,"tehran");
    Nation doghoozAbad = new Nation(NationType.INCA);
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
        CityController.setSelectedCivilizedUnit(new CivilizedUnit(CivilizedUnitType.SETTLER,CityController.getCurrentTurnUser().getNation(),new Pair(3,3)));
        Game.map[3][3].setCivilizedUnit(CityController.getSelectedCivilizedUnit());
        if (commandMatcher.matches())
            System.out.println(cityController.buildCity(commandMatcher));

        CityController.setCurrentTurnUser(new User("","",""));
        CityController.getCurrentTurnUser().setNation(new Nation(NationType.PERSIA));
        Game.map[3][3].setLandType(LandType.GRASS_LAND);
        Assert.assertTrue(cityController.isCityBuildable(new Pair(3,2)));
    }

    @Test
    public void cityBuyLandTest(){
        Nation nation = new Nation(NationType.PERSIA);
        City city = new City(nation,"tehran");
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
        City city = new City(nation,"tehran");

        CityController.setSelectedCity(city);
        city.setMainLand(Game.map[3][3]);
        Game.map[3][3].setOwnerCity(city);
        city.getLands().add(Game.map[3][3]);

        commandMatcher = CityCommands.getMatcher("send citizen to -x 3 -y 3",CityCommands.SEND_CITIZEN);
        if (commandMatcher.matches()) cityController.sendCitizen(commandMatcher);
        Assertions.assertTrue(Game.map[3][3].hasCitizen());
    }

    @Test
    public void retrieveCitizenTest(){
        Nation nation = new Nation(NationType.PERSIA);
        City city = new City(nation,"tehran");

        CityController.setSelectedCity(city);

        Game.map[3][3].setOwnerCity(city);
        city.getLands().add(Game.map[3][3]);
        Game.map[3][3].setCitizen(true);
        commandMatcher = CityCommands.getMatcher("retrieve citizen from -x 3 -y 3",CityCommands.RETRIEVE_CITIZEN);
        if (commandMatcher.matches()) cityController.retrieveCitizen(commandMatcher);

        Assertions.assertFalse(Game.map[3][3].hasCitizen());
    }

    @Test
    public void cityDeathTest(){
        Nation nation = new Nation(NationType.PERSIA);
        City city = new City(nation,"tehran");

        CityController.setSelectedCity(city);

        Game.map[3][3].setOwnerCity(city);
        city.getLands().add(Game.map[3][3]);
        city.getImprovements().add(new Improvement(ImprovementType.CAMP));

        CityController.cityDeath(city);
        Assertions.assertNull(Game.map[3][3].getOwnerCity());
    }

    @Test
    public void cityTakeOverTest(){

        CityController.setSelectedCity(city);

        Game.map[3][3].setOwnerCity(city);
        city.getLands().add(Game.map[3][3]);

        CityController.cityTakeOver(city,doghoozAbad);
    }

    @Test
    public void cityShowBanner(){
        CityController.setSelectedCity(city);

        Assertions.assertEquals("Name: " + city.getName() + " CombatStrength: " + city.getCombatStrength(),cityController.cityShowBanner());
    }
}
