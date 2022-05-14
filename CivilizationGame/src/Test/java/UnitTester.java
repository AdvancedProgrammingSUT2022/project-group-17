import Controller.GameControllers.GameController;
import Controller.GameControllers.LandController;
import Controller.GameControllers.UnitController;
import Enums.GameEnums.GameCommands;
import Enums.GameEnums.UnitCommands;
import Model.Game;
import Model.Nations.Nation;
import Model.Nations.NationType;
import Model.Pair;
import Model.Units.CloseCombatUnit;
import Model.Units.Enums.CloseCombatUnitType;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

public class UnitTester extends Tester{
    UnitController unitController = new UnitController();
    GameController gameController = new GameController();

    @BeforeEach
    public void setup(){
        Game.map = LandController.mapInitializer();
    }

    @Test
    public void unitMoveTestSuccessful(){
        Game.map[3][3].setCombatUnit(new CloseCombatUnit(CloseCombatUnitType.HORSE_MAN,new Nation(NationType.PERSIA),new Pair(3,3)));

        commandMatcher = GameCommands.getMatcher("select combat unit on -x 3 -y 3",GameCommands.SELECT_COMBAT_UNIT);
        if (commandMatcher.matches())
            System.out.println(gameController.selectCombatUnit(commandMatcher));

        commandMatcher = UnitCommands.getMatcher("combat unit move to -x 3 -y 4",UnitCommands.COMBAT_UNIT_MOVE_TO);

        if (commandMatcher.matches())
            unitController.unitGoToDestination(commandMatcher);

        Assert.assertEquals(CloseCombatUnitType.HORSE_MAN.name,Game.map[3][4].getCombatUnit().getName());
    }

    @Test
    public void unitMoveTest(){
        Game.map[3][3].setCombatUnit(new CloseCombatUnit(CloseCombatUnitType.HORSE_MAN,new Nation(NationType.PERSIA),new Pair(3,3)));

        commandMatcher = GameCommands.getMatcher("select combat unit on -x 3 -y 3",GameCommands.SELECT_COMBAT_UNIT);
        if (commandMatcher.matches())
            System.out.println(gameController.selectCombatUnit(commandMatcher));

        commandMatcher = UnitCommands.getMatcher("combat unit move to -x 6 -y 6",UnitCommands.COMBAT_UNIT_MOVE_TO);
        if (commandMatcher.matches())
            unitController.unitGoToDestination(commandMatcher);

        Assert.assertNull(Game.map[6][6].getCombatUnit());
    }

    @Test
    public void unitMoveToNeighborTest(){
        Game.map[3][3].setCombatUnit(new CloseCombatUnit(CloseCombatUnitType.HORSE_MAN,new Nation(NationType.PERSIA),new Pair(3,3)));
        commandMatcher = GameCommands.getMatcher("select combat unit on -x 3 -y 3",GameCommands.SELECT_COMBAT_UNIT);
        if (commandMatcher.matches())
            System.out.println(gameController.selectCombatUnit(commandMatcher));

        commandMatcher = UnitCommands.getMatcher("combat unit move to -x 3 -y 4",UnitCommands.COMBAT_UNIT_MOVE_TO);
        if (commandMatcher.matches())
            unitController.unitGoToNeighbor(commandMatcher);

        Assert.assertEquals(CloseCombatUnitType.HORSE_MAN.name , Game.map[3][4].getCombatUnit().getName());
    }

//    @Test
//    public void unitMoveToTestWrongDestination(){
//        Game.map[1][1].setCombatUnit(new CloseCombatUnit(CloseCombatUnitType.KNIGHT,new Nation(NationType.PERSIA),new Pair(1,1)));
//        commandMatcher = GameCommands.getMatcher("select combat unit on -x 1 -y 1",GameCommands.SELECT_COMBAT_UNIT);
//        if (commandMatcher.matches())
//            System.out.println(gameController.selectCombatUnit(commandMatcher));
//
//        commandMatcher = UnitCommands.getMatcher("combat unit move to -x 10 -y 10",UnitCommands.COMBAT_UNIT_MOVE_TO);
//        if (commandMatcher.matches())
//            unitController.unitMoveTo(commandMatcher);
//
//        Assert.assertNull(Game.map[10][10].getCombatUnit());
//    }
//
//    @Test
//    public void unitMoveToTestSuccessful(){
//        Game.map[3][3].setCombatUnit(new CloseCombatUnit(CloseCombatUnitType.HORSE_MAN,new Nation(NationType.PERSIA),new Pair(3,3)));
//        commandMatcher = GameCommands.getMatcher("select combat unit on -x 3 -y 3",GameCommands.SELECT_COMBAT_UNIT);
//        if (commandMatcher.matches())
//            System.out.println(gameController.selectCombatUnit(commandMatcher));
//
//        commandMatcher = UnitCommands.getMatcher("combat unit move to -x 4 -y 5",UnitCommands.COMBAT_UNIT_MOVE_TO);
//        if (commandMatcher.matches())
//            unitController.unitMoveTo(commandMatcher);
//
//        Assert.assertEquals(CloseCombatUnitType.HORSE_MAN.name , Game.map[4][5].getCombatUnit().getName());
//    }
}
