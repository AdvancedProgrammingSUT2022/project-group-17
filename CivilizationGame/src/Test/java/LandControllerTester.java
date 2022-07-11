//import sut.civilization.Controller.GameControllers.LandController;
//import sut.civilization.Model.Game;
//import sut.civilization.Model.Nations.Nation;
//import sut.civilization.Model.Nations.NationType;
//import sut.civilization.Model.Pair;
//import sut.civilization.Model.Units.CloseCombatUnit;
//import sut.civilization.Model.Units.Enums.CloseCombatUnitType;
//import org.junit.Assert;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.BeforeEach;
//
//import java.util.ArrayList;
//
//public class LandControllerTester extends Tester{
//
//    @BeforeEach
//    public void setup(){
//        Game.map = LandController.mapInitializer();
//    }
//    @Test
//    public void getAllNeighborsTest(){
//        ArrayList<Pair> result = new ArrayList<>();
//        result.add(new Pair(0,3));
//        result.add(new Pair(1,4));
//        result.add(new Pair(2,2));
//        result.add(new Pair(2,3));
//        result.add(new Pair(2,4));
//        result.add(new Pair(1,2));
//        Assert.assertEquals(result.toString(), LandController.getAllNeighborsIndexes(new Pair(1, 3)).toString());
//    }
//
//    @Test
//    public void fogOfWarTest(){
//        Game.map = LandController.mapInitializer();
//        Game.map[3][3].setCombatUnit(new CloseCombatUnit(CloseCombatUnitType.KNIGHT,new Nation(NationType.PERSIA),new Pair(3,3)));
//        LandController.printMap(Game.map);
//
//        Assert.assertEquals(2, Game.map[3][3].getVisibility());
//    }
//
//
//    @Test
//    public void areLandsNeighborFail(){
//        Assertions.assertFalse(LandController.areNeighbors(new Pair(3,3),new Pair(3,6)));
//    }
//
//    @Test
//    public void areLandsNeighborSuccessful(){
//        Assertions.assertTrue(LandController.areNeighbors(new Pair(3,3),new Pair(3,4)));
//    }
//
//    @Test
//    public void getIndexWithWrongPair(){
//        Assertions.assertEquals(-1,LandController.getIndex(new Pair(3,3),new Pair(3,5)));
//    }
//
//    @Test
//    public void getIndexWithWrongSuccessful(){
//        Assertions.assertEquals(0,LandController.getIndex(new Pair(3,3),new Pair(2,3)));
//    }
//}
