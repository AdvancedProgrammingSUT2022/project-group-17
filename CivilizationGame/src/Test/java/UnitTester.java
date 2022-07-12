//import sut.civilization.Controller.GameControllers.CityController;
//import sut.civilization.Controller.GameControllers.GameMenuController;
//import sut.civilization.Controller.GameControllers.LandController;
//import sut.civilization.Controller.GameControllers.UnitController;
//import sut.civilization.Enums.GameEnums.GameCommands;
//import sut.civilization.Enums.GameEnums.UnitCommands;
//import sut.civilization.Model.City;
//import sut.civilization.Model.Game;
//import sut.civilization.Model.Lands.LandType;
//import sut.civilization.Model.Nations.Nation;
//import sut.civilization.Model.Nations.NationType;
//import sut.civilization.Model.Pair;
//import sut.civilization.Model.Resources.Enums.ResourceType;
//import sut.civilization.Model.Technologies.TechnologyType;
//import sut.civilization.Model.Units.CloseCombatUnit;
//import sut.civilization.Model.Units.Enums.CivilizedUnitType;
//import sut.civilization.Model.Units.Enums.CloseCombatUnitType;
//import sut.civilization.Model.Units.Enums.UnitStatus;
//import sut.civilization.Model.Users.User;
//import org.junit.Assert;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.BeforeEach;
//
//public class UnitTester extends Tester{
//    UnitController unitController = new UnitController();
//    GameMenuController gameController = new GameMenuController();
//    Nation nation = new Nation(NationType.PERSIA);
//    City city = new City(nation,"city");
//
//    @BeforeEach
//    public void setup(){
//        Game.map = LandController.mapInitializer();
//        User user = new User("","","");
//        user.setNation(nation);
//        GameMenuController.setCurrentTurnUser(user);
//        Game.map[3][3].setCombatUnit(new CloseCombatUnit(CloseCombatUnitType.HORSE_MAN,nation,new Pair(3,3)));
//        Game.map[3][4].setCombatUnit(new CloseCombatUnit(CloseCombatUnitType.HORSE_MAN,nation,new Pair(3,3)));
//        GameMenuController.setSelectedCombatUnit(Game.map[3][4].getCombatUnit());
//        UnitController.setSelectedCombatUnit(Game.map[3][4].getCombatUnit());
//        nation.getCoin().setBalance(10000);
//        city.setMainLand(Game.map[2][3]);
//        Game.map[2][3].setOwnerCity(city);
//        CityController.setSelectedCity(city);
//        UnitController.setSelectedCity(city);
//
//    }
//
//    @AfterEach
//    public void clearUp(){
//        Game.map[2][3].setCivilizedUnit(null);
//        Game.map[2][3].setCombatUnit(null);
//    }
//
//    @Test
//    public void unitMoveTestSuccessful(){
//
//        commandMatcher = GameCommands.getMatcher("select combat unit on -x 3 -y 3",GameCommands.SELECT_COMBAT_UNIT);
//        if (commandMatcher.matches())
//            System.out.println(gameController.selectCombatUnit(commandMatcher));
//
//        commandMatcher = UnitCommands.getMatcher("combat unit move to -x 3 -y 4",UnitCommands.COMBAT_UNIT_MOVE_TO);
//
//        if (commandMatcher.matches())
//            unitController.unitGoToDestination(commandMatcher,1);
//
//        Assert.assertEquals(CloseCombatUnitType.HORSE_MAN.name,Game.map[3][4].getCombatUnit().getName());
//    }
//
//    @Test
//    public void unitMoveTest(){
//        commandMatcher = GameCommands.getMatcher("select combat unit on -x 3 -y 3",GameCommands.SELECT_COMBAT_UNIT);
//        if (commandMatcher.matches())
//            System.out.println(gameController.selectCombatUnit(commandMatcher));
//
//        commandMatcher = UnitCommands.getMatcher("combat unit move to -x 6 -y 6",UnitCommands.COMBAT_UNIT_MOVE_TO);
//        if (commandMatcher.matches())
//            unitController.unitGoToDestination(commandMatcher,1);
//
//        Assert.assertNull(Game.map[6][6].getCombatUnit());
//    }
//
//    @Test
//    public void unitMoveToNeighborTest(){
//       commandMatcher = GameCommands.getMatcher("select combat unit on -x 3 -y 3",GameCommands.SELECT_COMBAT_UNIT);
//        if (commandMatcher.matches())
//            System.out.println(gameController.selectCombatUnit(commandMatcher));
//
//        commandMatcher = UnitCommands.getMatcher("combat unit move to -x 3 -y 4",UnitCommands.COMBAT_UNIT_MOVE_TO);
//        if (commandMatcher.matches())
//            unitController.unitGoToNeighbor(commandMatcher);
//
//        Assert.assertEquals(CloseCombatUnitType.HORSE_MAN.name , Game.map[3][4].getCombatUnit().getName());
//    }
//
//
//    @Test
//    public void unitAttackCityWrongDestination(){
//        Nation iran = new Nation(NationType.PERSIA);
//        City tehran = new City(iran,"tehran");
//
//        UnitController.setSelectedCity(tehran);
//        tehran.setMainLand(Game.map[3][4]);
//        Game.map[3][4].setOwnerCity(tehran);
//        Game.map[3][3].setCombatUnit(new CloseCombatUnit(CloseCombatUnitType.KNIGHT,iran,new Pair(3,3)));
//        UnitController.setSelectedCombatUnit(Game.map[3][3].getCombatUnit());
//        commandMatcher = UnitCommands.getMatcher("unit attack city on -x 3 -y 4",UnitCommands.UNIT_ATTACK);
//
//        if (commandMatcher.matches()) Assertions.assertEquals("Can't attack owner nation's city", unitController.unitSetCityTarget(commandMatcher));
//    }
//
//    @Test
//    public void unitAttackCitySuccessfully(){
//        Nation iran = new Nation(NationType.PERSIA);
//        City tehran = new City(iran,"tehran");
//        City doghozAbad = new City(new Nation(NationType.INCA),"doghoozAbad");
//
//        doghozAbad.setMainLand(Game.map[3][2]);
//
//        UnitController.setSelectedCity(doghozAbad);
//        Game.map[3][2].setOwnerCity(doghozAbad);
//
//        Game.map[3][3].setCombatUnit(new CloseCombatUnit(CloseCombatUnitType.KNIGHT,iran,new Pair(3,3)));
//        UnitController.setSelectedCombatUnit(Game.map[3][3].getCombatUnit());
//        commandMatcher = UnitCommands.getMatcher("unit attack city on -x 3 -y 2",UnitCommands.UNIT_ATTACK);
//        if (commandMatcher.matches()) Assertions.assertEquals("Attack successful", unitController.unitSetCityTarget(commandMatcher));
//    }
//
//    @Test
//    public void unitSetPathTestWrongDestination(){
//        Game.map[3][4].setLandType(LandType.MOUNTAIN);
//
//        commandMatcher = UnitCommands.getMatcher("combat unit move to -x 3 -y 4",UnitCommands.COMBAT_UNIT_MOVE_TO);
//        if (commandMatcher.matches())
//            Assertions.assertEquals("There already is a combat unit in destination",unitController.unitSetPath(commandMatcher,1));
//
//    }
//
////    @Test
////    public void unitSetPathTestSuccessfulDestination(){
////        Game.map[3][4].setLandType(LandType.GrassLand);
////        Game.map[3][4].setCombatUnit(null);
////
////        commandMatcher = UnitCommands.getMatcher("combat unit move to -x 3 -y 4",UnitCommands.COMBAT_UNIT_MOVE_TO);
////        if (commandMatcher.matches())
////            Assertions.assertEquals("Unit moved successfully",unitController.unitSetPath(commandMatcher,1));
////
////    }
//
//    @Test
//    public void unitGoForwardTest(){
//        Game.map[3][3].setLandType(LandType.GRASS_LAND);
//        Game.map[3][3].getCombatUnit().setPath("0");
//
//        UnitController.unitGoForward(Game.map[3][3].getCombatUnit());
//        Assertions.assertEquals(CloseCombatUnitType.HORSE_MAN.name,Game.map[2][3].getCombatUnit().getName());
//    }
//
//    @Test
//    public void unitSleep(){
//        Assertions.assertEquals("Slept successfully!",unitController.unitSleep());
//    }
//
//    @Test
//    public void unitWake(){
//        UnitController.getSelectedCombatUnit().setUnitStatus(UnitStatus.SLEEP);
//        Assertions.assertEquals("Waked successfully!",unitController.unitWake());
//    }
//
//    @Test
//    public void unitRemove(){
//        Assertions.assertEquals("removed successfully!",unitController.unitDelete());
//        Game.map[3][4].setCombatUnit(new CloseCombatUnit(CloseCombatUnitType.HORSE_MAN,nation,new Pair(3,4)));
//    }
//
//    @Test
//    public void PurchaseUnitCivilized(){
//
//        commandMatcher = UnitCommands.getMatcher("purchase a Worker unit with type civilized unit",UnitCommands.PURCHASE_UNIT);
//        if (commandMatcher.matches()) Assertions.assertEquals("Civilized unit purchased successfully",unitController.purchaseUnit(commandMatcher));
//    }
//
//    @Test
//    public void PurchaseUnitCloseCombatUnit(){
//
//        commandMatcher = UnitCommands.getMatcher("purchase a Knight unit with type close combat unit",UnitCommands.PURCHASE_UNIT);
//        if (commandMatcher.matches()) Assertions.assertEquals("Close combat unit purchased successfully",unitController.purchaseUnit(commandMatcher));
//    }
//
//    @Test
//    public void PurchaseUnitRangedCombatUnit(){
//
//
//        commandMatcher = UnitCommands.getMatcher("purchase a Archer unit with type ranged combat unit",UnitCommands.PURCHASE_UNIT);
//        if (commandMatcher.matches()) Assertions.assertEquals("Ranged combat unit purchased successfully",unitController.purchaseUnit(commandMatcher));
//    }
//
//    @Test
//    public void CreateUnitCivilized(){
//
//        commandMatcher = UnitCommands.getMatcher("create a Worker unit with type civilized unit",UnitCommands.CREATE_UNIT);
//        if (commandMatcher.matches()) Assertions.assertEquals("Civilized unit is set for creation successfully",unitController.unitStartCreation(commandMatcher));
//        city.setHasAnInProgressUnit(false);
//    }
//
//    @Test
//    public void CreateUnitCloseCombatUnit(){
//
//        commandMatcher = UnitCommands.getMatcher("create a Knight unit with type close combat unit",UnitCommands.CREATE_UNIT);
//        nation.addResource(ResourceType.HORSE);
//        nation.addTechnology(TechnologyType.CHIVALRY);
//        if (commandMatcher.matches()) Assertions.assertEquals("Close combat unit is set for creation successfully",unitController.unitStartCreation(commandMatcher));
//        city.setHasAnInProgressUnit(false);
//
//    }
//
//    @Test
//    public void CreateUnitRangedCombatUnit(){
//
//        commandMatcher = UnitCommands.getMatcher("create a Archer unit with type ranged combat unit",UnitCommands.CREATE_UNIT);
//        nation.addTechnology(TechnologyType.ARCHERY);
//        if (commandMatcher.matches()) Assertions.assertEquals("Ranged combat unit is set for creation successfully",unitController.unitStartCreation(commandMatcher));
//        city.setHasAnInProgressUnit(false);
//
//    }
//    @Test
//    public void CreateUnitPlacingInMap(){
//        city.setInProgressCivilizedUnit(CivilizedUnitType.WORKER);
//        city.setInProgressCloseCombatUnit(CloseCombatUnitType.KNIGHT);
//        UnitController.unitCreate(city);
//        LandController.printMap(Game.map);
//        Assertions.assertEquals(CloseCombatUnitType.KNIGHT.name+CivilizedUnitType.WORKER.name,Game.map[2][3].getCombatUnit().getName() + Game.map[2][3].getCivilizedUnit().getName());
//
//    }
//}
