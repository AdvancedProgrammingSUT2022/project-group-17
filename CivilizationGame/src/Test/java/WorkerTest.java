import Controller.GameControllers.WorkerController;
import Enums.GameEnums.WorkerCommands;
import Model.Game;
import Model.Improvements.Improvement;
import Model.Improvements.ImprovementType;
import Model.LandFeatures.LandFeature;
import Model.LandFeatures.LandFeatureType;
import Model.Lands.LandType;
import Model.Nations.Nation;
import Model.Nations.NationType;
import Model.Pair;
import Model.Resources.Enums.ResourceType;
import Model.Resources.Resource;
import Model.Technologies.TechnologyType;
import Model.Units.CivilizedUnit;
import Model.Units.Enums.CivilizedUnitType;
import Model.Users.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.HashMap;


public class WorkerTest extends Tester {
    WorkerController workerController = new WorkerController();
    Nation nation;
    User user;

    @BeforeEach
    public void setup() {
        nation = new Nation(NationType.PERSIA);
        user = new User("", "", "");

        HashMap<TechnologyType, Boolean> hashMap = new HashMap<>();
        for (TechnologyType technologyType : TechnologyType.values()) {
            hashMap.put(technologyType, false);
        }
        nation.getTechnologies().put(TechnologyType.Agriculture,false);
        nation.setTechnologies(hashMap);
        user.setNation(nation);
        Game.setLoggedInUser(user);
        WorkerController.setCurrentTurnUser(user);
        Game.map[3][3].setCivilizedUnit(new CivilizedUnit(CivilizedUnitType.WORKER, user.getNation(), new Pair(3, 3)));
        WorkerController.setSelectedCivilizedUnit(Game.map[3][3].getCivilizedUnit());

    }

    @Test
    public void WorkerBuildNonResourcedImprovementTestSuccessful() {
        commandMatcher = WorkerCommands.getMatcher("worker build farm", WorkerCommands.BUILD_FARM);
        nation.getTechnologies().put(TechnologyType.Agriculture, true);

        if (commandMatcher.matches())
            Assertions.assertEquals("Improvement has built successfully :)", workerController.workerBuildNonResourcedImprovement(ImprovementType.FARM));
    }

    @Test
    public void WorkerBuildNonResourcedImprovementTestWrong() {
        commandMatcher = WorkerCommands.getMatcher("worker build farm", WorkerCommands.BUILD_FARM);


        if (commandMatcher.matches())
            Assertions.assertNotEquals("Improvement has built successfully :)", workerController.workerBuildNonResourcedImprovement(ImprovementType.FARM));
    }

    @Test
    public void resourceCheckTestSuccessful(){
        Game.map[3][3].setResource(new Resource(ResourceType.Iron));
        Assertions.assertEquals("yes",workerController.hasResourceOfImprovement(ImprovementType.MINE));
    }

    @Test
    public void resourceCheckTestWrong(){
        Game.map[3][3].setResource(null);
        Assertions.assertNotEquals("yes",workerController.hasResourceOfImprovement(ImprovementType.MINE));
    }

    @Test
    public void removeRouteSuccessful(){
        Game.map[3][3].setRoute(new Improvement(ImprovementType.ROAD));

        Assertions.assertEquals("Route has been removed successfully!",workerController.workerRemoveRoute());
    }

    @Test
    public void removeRouteWrong(){
        Game.map[3][3].setRoute(null);
        Assertions.assertNotEquals("Route has been removed successfully!",workerController.workerRemoveRoute());
    }

    @Test
    public void removeFeatureSuccessful(){
        Game.map[3][3].setLandFeature(new LandFeature(LandFeatureType.Jungle));
        Assertions.assertEquals("Feature has been removed successfully",workerController.workerRemoveFeature());
    }

    @Test
    public void removeFeatureWrong(){
        Game.map[3][3].setLandFeature(null);
        Assertions.assertNotEquals("Feature has been removed successfully",workerController.workerRemoveFeature());
    }

    @Test
    public void WorkerRepairWrong1(){
        Game.map[3][3].setImprovement(null);
        Assertions.assertEquals("There isn't any improvement here!",workerController.workerRepair());
    }

    @Test
    public void WorkerRepairWrong2(){
        Game.map[3][3].setImprovement(new Improvement(ImprovementType.FARM));
        Assertions.assertEquals("It isn't broken!",workerController.workerRepair());
    }

    @Test
    public void WorkerRepairSuccessful(){
        Game.map[3][3].setImprovement(new Improvement(ImprovementType.FARM));
        Game.map[3][3].getImprovement().setBroken(true);

        Assertions.assertEquals("The improvement has been repaired successfully",workerController.workerRepair());
    }

    @Test
    public void isLandSuitableWrong(){
        Game.map[3][3].setLandType(LandType.Ocean);
        Game.map[3][3].setLandFeature(null);
        Assertions.assertNotEquals("yes",workerController.isLandSuitable(ImprovementType.MINE));

    }

    @Test
    public void isLandSuitableSuccessful(){
        Game.map[3][3].setLandType(LandType.GrassLand);
        Game.map[3][3].setLandFeature(new LandFeature(LandFeatureType.Jungle));
        Assertions.assertEquals("yes",workerController.isLandSuitable(ImprovementType.MINE));

    }
}
