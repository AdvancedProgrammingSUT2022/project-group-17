import Controller.GameControllers.LandController;
import Model.Game;
import Model.Nations.Nation;
import Model.Nations.NationType;
import Model.Pair;
import Model.Units.CloseCombatUnit;
import Model.Units.Enums.CloseCombatUnitType;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class LandControllerTester extends Tester{

    @Test
    public void getAllNeighborsTest(){
        ArrayList<Pair> result = new ArrayList<>();
        result.add(new Pair(0,3));
        result.add(new Pair(1,4));
        result.add(new Pair(2,2));
        result.add(new Pair(2,3));
        result.add(new Pair(2,4));
        result.add(new Pair(1,2));
        Assert.assertEquals(result.toString(), LandController.getAllNeighborsIndexes(new Pair(1, 3)).toString());
    }

    @Test
    public void fogOfWarTest(){
        Game.map= LandController.mapInitializer();
        Game.map[3][3].setCombatUnit(new CloseCombatUnit(CloseCombatUnitType.KNIGHT,new Nation(NationType.PERSIA),new Pair(3,3)));
        LandController.printMap(Game.map);

        Assert.assertEquals(2, Game.map[3][3].getVisibility());
    }

}
