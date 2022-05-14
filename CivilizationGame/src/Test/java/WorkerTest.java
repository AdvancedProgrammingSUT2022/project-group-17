import Controller.GameControllers.WorkerController;
import Enums.GameEnums.UnitCommands;
import Enums.GameEnums.WorkerCommands;
import Model.Game;
import Model.Improvements.ImprovementType;
import Model.Nations.Nation;
import Model.Nations.NationType;
import Model.Pair;
import Model.Technologies.TechnologyType;
import Model.Units.CivilizedUnit;
import Model.Units.Enums.CivilizedUnitType;
import Model.Users.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;



public class WorkerTest extends Tester {
    WorkerController workerController = new WorkerController();
    Nation nation;
    User user;

    @BeforeEach
    public void setup() {
        nation = new Nation(NationType.PERSIA);
        user = new User("", "", "");
        nation.initializeTechnologies();
        user.setNation(nation);
        Game.setLoggedInUser(user);
        WorkerController.setCurrentTurnUser(user);
        Game.map[3][3].setCivilizedUnit(new CivilizedUnit(CivilizedUnitType.WORKER, user.getNation(), new Pair(3, 3)));
        WorkerController.setSelectedCivilizedUnit(Game.map[3][3].getCivilizedUnit());

    }

    @Test
    public void WorkerBuildNonResourcedImprovementTestSuccessful() {
        commandMatcher = WorkerCommands.getMatcher("worker build farm", WorkerCommands.BUILD_FARM);
        nation.getTechnologies().put(TechnologyType.Agriculture,true);

        if (commandMatcher.matches())
            Assertions.assertEquals("Improvement has built successfully :)", workerController.workerBuildNonResourcedImprovement(ImprovementType.FARM));
    }

    @Test
    public void WorkerBuildNonResourcedImprovementTestWrong() {
        commandMatcher = WorkerCommands.getMatcher("worker build farm", WorkerCommands.BUILD_FARM);


        if (commandMatcher.matches())
            Assertions.assertNotEquals("Improvement has built successfully :)", workerController.workerBuildNonResourcedImprovement(ImprovementType.FARM));
    }
}
