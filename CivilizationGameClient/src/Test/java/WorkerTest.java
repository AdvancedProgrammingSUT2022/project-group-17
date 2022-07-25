//import sut.civilization.Controller.GameControllers.WorkerController;
//import sut.civilization.Enums.GameEnums.WorkerCommands;
//import sut.civilization.Model.Game;
//import sut.civilization.Model.Improvements.Improvement;
//import sut.civilization.Model.Improvements.ImprovementType;
//import sut.civilization.Model.LandFeatures.LandFeature;
//import sut.civilization.Model.LandFeatures.LandFeatureType;
//import sut.civilization.Model.Lands.LandType;
//import sut.civilization.Model.Nations.Nation;
//import sut.civilization.Model.Nations.NationType;
//import sut.civilization.Model.Pair;
//import sut.civilization.Model.Resources.Enums.ResourceType;
//import sut.civilization.Model.Resources.Resource;
//import sut.civilization.Model.Technologies.TechnologyType;
//import sut.civilization.Model.Units.CivilizedUnit;
//import sut.civilization.Model.Units.Enums.CivilizedUnitType;
//import sut.civilization.Model.Users.User;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.BeforeEach;
//
//import java.util.HashMap;
//
//
//public class WorkerTest extends Tester {
//    WorkerController workerController = new WorkerController();
//    Nation nation;
//    User user;
//
//    @BeforeEach
//    public void setup() {
//        nation = new Nation(NationType.PERSIA);
//        user = new User("", "", "");
//
//        HashMap<TechnologyType, Boolean> hashMap = new HashMap<>();
//        for (TechnologyType technologyType : TechnologyType.values()) {
//            hashMap.put(technologyType, false);
//        }
//        nation.setTechnologies(hashMap);
//        user.setNation(nation);
//        Game.instance.setLoggedInUser(user);
//        WorkerController.setCurrentTurnUser(user);
//        Game.instance.map[3][3].setCivilizedUnit(new CivilizedUnit(CivilizedUnitType.WORKER, user.getNation(), new Pair(3, 3)));
//        WorkerController.setSelectedCivilizedUnit(Game.instance.map[3][3].getCivilizedUnit());
//
//    }
//
//    @AfterEach
//    public void clearUp(){
//        Game.instance.map[3][3].setImprovement(null);
//        Game.instance.map[3][3].getCivilizedUnit().setWorkerWorks(null);
//    }
//
//    @Test
//    public void resourceCheckTestSuccessful(){
//        Game.instance.map[3][3].setResource(new Resource(ResourceType.IRON));
//        Assertions.assertEquals("yes", WorkerController.hasResourceOfImprovement(ImprovementType.MINE,WorkerController.getSelectedCivilizedUnit()));
//    }
//
//    @Test
//    public void resourceCheckTestWrong(){
//        Game.instance.map[3][3].setResource(null);
//        Assertions.assertNotEquals("yes", WorkerController.hasResourceOfImprovement(ImprovementType.MINE,WorkerController.getSelectedCivilizedUnit()));
//    }
//
//    @Test
//    public void removeRouteSuccessful(){
//        Game.instance.map[3][3].setRoute(new Improvement(ImprovementType.ROAD));
//
//        Assertions.assertEquals("The route will be removed!",workerController.setWorkerToRemoveRoute());
//    }
//
//    @Test
//    public void removeRouteWrong(){
//        Game.instance.map[3][3].setRoute(null);
//        Assertions.assertNotEquals("The route will be removed!",workerController.setWorkerToRemoveRoute());
//    }
//
//    @Test
//    public void removeFeatureSuccessful(){
//        Game.instance.map[3][3].setLandFeature(new LandFeature(LandFeatureType.JUNGLE));
//        Assertions.assertEquals("The feature will be removed!",workerController.setWorkerToRemoveFeature());
//    }
//
//    @Test
//    public void removeFeatureWrong(){
//        Game.instance.map[3][3].setLandFeature(null);
//        Assertions.assertNotEquals("The feature will be removed!",workerController.setWorkerToRemoveFeature());
//    }
//
//    @Test
//    public void WorkerRepairWrong1(){
//        Game.instance.map[3][3].setImprovement(null);
//        Assertions.assertEquals("There isn't any improvement here!",workerController.setWorkerToRepair());
//    }
//
//    @Test
//    public void WorkerRepairWrong2(){
//        Game.instance.map[3][3].setImprovement(new Improvement(ImprovementType.FARM));
//        Assertions.assertEquals("It isn't broken!",workerController.setWorkerToRepair());
//    }
//
//    @Test
//    public void WorkerRepairSuccessful(){
//        Game.instance.map[3][3].setImprovement(new Improvement(ImprovementType.FARM));
//        Game.instance.map[3][3].getImprovement().setBroken(true);
//
//        Assertions.assertEquals("The improvement will be repaired",workerController.setWorkerToRepair());
//    }
//
//    @Test
//    public void isLandSuitableWrong(){
//        Game.instance.map[3][3].setLandType(LandType.OCEAN);
//        Game.instance.map[3][3].setLandFeature(null);
//        Assertions.assertNotEquals("yes",workerController.isLandSuitable(ImprovementType.MINE));
//
//    }
//
//    @Test
//    public void isLandSuitableSuccessful(){
//        Game.instance.map[3][3].setLandType(LandType.GRASS_LAND);
//        Game.instance.map[3][3].setLandFeature(new LandFeature(LandFeatureType.JUNGLE));
//        Assertions.assertEquals("yes",workerController.isLandSuitable(ImprovementType.MINE));
//
//    }
//
//    @Test
//    public void generalFunctionsTesterRoadBuilder(){
//        WorkerController.getSelectedCivilizedUnit().setImprovementType(ImprovementType.ROAD);
//        workerController.workerBuildRoad(WorkerController.getSelectedCivilizedUnit());
//        Assertions.assertEquals(ImprovementType.ROAD,Game.instance.map[3][3].getRoute().getImprovementType());
//    }
//    @Test
//    public void generalFunctionsTesterFarmBuilder(){
//        Game.instance.map[3][3].setLandFeature(null);
//        Game.instance.map[3][3].setResource(null);
//        workerController.workerBuildFarm(WorkerController.getSelectedCivilizedUnit());
//        Assertions.assertEquals(ImprovementType.FARM,Game.instance.map[3][3].getImprovement().getImprovementType());
//    }
//    @Test
//    public void generalFunctionsTesterSpecialFarmBuilder(){
//        Game.instance.map[3][3].setLandFeature(new LandFeature(LandFeatureType.JUNGLE));
//        WorkerController.workerBuildSpecialFarm(WorkerController.getSelectedCivilizedUnit());
//        Assertions.assertEquals(ImprovementType.FARM,Game.instance.map[3][3].getImprovement().getImprovementType());
//    }
//    @Test
//    public void generalFunctionsTesterMineBuilder(){
//        Game.instance.map[3][3].setResource(new Resource(ResourceType.IRON));
//        WorkerController.workerBuildMine(WorkerController.getSelectedCivilizedUnit());
//        Assertions.assertEquals(ImprovementType.MINE,Game.instance.map[3][3].getImprovement().getImprovementType());
//    }
//    @Test
//    public void generalFunctionsTesterSpecialMineBuilder(){
//        Game.instance.map[3][3].setLandFeature(new LandFeature(LandFeatureType.JUNGLE));
//        Game.instance.map[3][3].setResource(new Resource(ResourceType.IRON));
//        workerController.workerBuildSpecialMine(WorkerController.getSelectedCivilizedUnit());
//        //TODO ASK Hamed Why it is not JungleMine ? it's just Mine
//        Assertions.assertEquals(ImprovementType.MINE,Game.instance.map[3][3].getImprovement().getImprovementType());
//    }
//    @Test
//    public void generalFunctionsTesterRemoveFeature(){
//        Game.instance.map[3][3].setLandFeature(new LandFeature(LandFeatureType.JUNGLE));
//        Game.instance.map[3][3].setResource(new Resource(ResourceType.IRON));
//        workerController.workerRemoveFeature(WorkerController.getSelectedCivilizedUnit());
//
//        Assertions.assertNull(Game.instance.map[3][3].getLandFeature());
//    }
//    @Test
//    public void generalFunctionsTesterRouteRemover(){
//        Game.instance.map[3][3].setLandFeature(new LandFeature(LandFeatureType.JUNGLE));
//        Game.instance.map[3][3].setRoute(new Improvement(ImprovementType.ROAD));
//        workerController.workerRemoveRoute(WorkerController.getSelectedCivilizedUnit());
//
//        Assertions.assertNull(Game.instance.map[3][3].getRoute());
//    }
//    @Test
//    public void generalFunctionsTesterRepair(){
//        Game.instance.map[3][3].setLandFeature(new LandFeature(LandFeatureType.JUNGLE));
//        Game.instance.map[3][3].setImprovement(new Improvement(ImprovementType.FARM));
//        Game.instance.map[3][3].getImprovement().setBroken(true);
//        workerController.workerRepair(WorkerController.getSelectedCivilizedUnit());
//
//        Assertions.assertFalse(Game.instance.map[3][3].getImprovement().isBroken());
//    }
//    @Test
//    public void generalFunctionsTesterNonResourcedImprovementBuilder(){
//        Game.instance.map[3][3].setLandFeature(new LandFeature(LandFeatureType.JUNGLE));
//        Game.instance.map[3][3].setResource(new Resource(ResourceType.IRON));
//        WorkerController.getSelectedCivilizedUnit().setImprovementType(ImprovementType.FACTORY);
//        workerController.workerBuildNonResourcedImprovement(WorkerController.getSelectedCivilizedUnit());
//
//        Assertions.assertEquals(ImprovementType.FACTORY,Game.instance.map[3][3].getImprovement().getImprovementType());
//    }
//    @Test
//    public void generalFunctionsTesterResourcedImprovementBuilder(){
//        Game.instance.map[3][3].setLandFeature(null);
//        Game.instance.map[3][3].setResource(new Resource(ResourceType.DEER));
//        WorkerController.getSelectedCivilizedUnit().setImprovementType(ImprovementType.CAMP);
//        workerController.workerBuildResourcedImprovement(WorkerController.getSelectedCivilizedUnit());
//
//        Assertions.assertEquals(ImprovementType.CAMP,Game.instance.map[3][3].getImprovement().getImprovementType());
//    }
//
//
//    @Test
//    public void setWorkerToBuildImprovementTestFailFarm1(){
//        commandMatcher = WorkerCommands.getMatcher("worker build Farm",WorkerCommands.BUILD_IMPROVEMENT);
//        Game.instance.map[3][3].setLandFeature(new LandFeature(LandFeatureType.ICE));
//        if (commandMatcher.matches())
//            Assertions.assertEquals("Want to build a farm on ice?!",workerController.setWorkerToBuildImprovement(commandMatcher));
//    }
//
//    @Test
//    public void setWorkerToBuildImprovementTestFailFarm2(){
//        commandMatcher = WorkerCommands.getMatcher("worker build Farm",WorkerCommands.BUILD_IMPROVEMENT);
//        Game.instance.map[3][3].setLandFeature(new LandFeature(LandFeatureType.JUNGLE));
//        System.out.println(nation.hasTechnology(TechnologyType.BRONZE_WORKING));
//        if (commandMatcher.matches())
//            Assertions.assertEquals("The Worker is now working!\nThe improvement will be ready in " + ImprovementType.JUNGLE_FARM.initialTurns + " turns",workerController.setWorkerToBuildImprovement(commandMatcher));
//    }
//
//    @Test
//    public void setWorkerToBuildImprovementTestSuccessfulFarm1(){
//        commandMatcher = WorkerCommands.getMatcher("worker build Farm",WorkerCommands.BUILD_IMPROVEMENT);
//        Game.instance.map[3][3].setLandFeature(new LandFeature(LandFeatureType.JUNGLE));
//        nation.addTechnology(TechnologyType.AGRICULTURE);
//        nation.addTechnology(TechnologyType.BRONZE_WORKING);
//        if (commandMatcher.matches())
//            Assertions.assertEquals("The Worker is now working!\nThe improvement will be ready in " + ImprovementType.JUNGLE_FARM.initialTurns + " turns",workerController.setWorkerToBuildImprovement(commandMatcher));
//    }
//
//    @Test
//    public void setWorkerToBuildImprovementTestSuccessfulFarm(){
//        commandMatcher = WorkerCommands.getMatcher("worker build Farm",WorkerCommands.BUILD_IMPROVEMENT);
//        Game.instance.map[3][3].setLandFeature(null);
//        nation.addTechnology(TechnologyType.AGRICULTURE);
//        nation.addTechnology(TechnologyType.BRONZE_WORKING);
//        if (commandMatcher.matches())
//            Assertions.assertEquals("The Worker is now working!\nThe improvement will be ready in " + ImprovementType.FARM.initialTurns + " turns",workerController.setWorkerToBuildImprovement(commandMatcher));
//    }
//
//    @Test
//    public void setWorkerToBuildImprovementTestFailMine1(){
//        commandMatcher = WorkerCommands.getMatcher("worker build Mine",WorkerCommands.BUILD_IMPROVEMENT);
//        Game.instance.map[3][3].setLandFeature(new LandFeature(LandFeatureType.JUNGLE));
//
//        if (commandMatcher.matches())
//            Assertions.assertNotEquals("The Worker is now working!\nThe improvement will be ready in " + ImprovementType.JUNGLE_MINE.initialTurns + " turns",workerController.setWorkerToBuildImprovement(commandMatcher));
//    }
//
//    @Test
//    public void setWorkerToBuildImprovementTestSuccessfulMine1(){
//        commandMatcher = WorkerCommands.getMatcher("worker build Mine",WorkerCommands.BUILD_IMPROVEMENT);
//        Game.instance.map[3][3].setLandFeature(null);
//        Game.instance.map[3][3].setResource(new Resource(ResourceType.IRON));
//        if (commandMatcher.matches())
//            Assertions.assertEquals("The Worker is now working!\nThe improvement will be ready in " + ImprovementType.MINE.initialTurns + " turns",workerController.setWorkerToBuildImprovement(commandMatcher));
//    }
//    @Test
//    public void setWorkerToBuildImprovementTestSuccessfulMine2(){
//        commandMatcher = WorkerCommands.getMatcher("worker build Mine",WorkerCommands.BUILD_IMPROVEMENT);
//        Game.instance.map[3][3].setLandFeature(new LandFeature(LandFeatureType.JUNGLE));
//        nation.addTechnology(TechnologyType.AGRICULTURE);
//        nation.addTechnology(TechnologyType.BRONZE_WORKING);
//        if (commandMatcher.matches())
//            Assertions.assertEquals("The Worker is now working!\nThe improvement will be ready in " + ImprovementType.JUNGLE_MINE.initialTurns + " turns",workerController.setWorkerToBuildImprovement(commandMatcher));
//    }
//
//    @Test
//    public void setWorkerToBuildImprovementTestFailResourcedImprovement1(){
//        commandMatcher = WorkerCommands.getMatcher("worker build Camp",WorkerCommands.BUILD_IMPROVEMENT);
//        Game.instance.map[3][3].setLandFeature(new LandFeature(LandFeatureType.JUNGLE));
//
//        if (commandMatcher.matches())
//            Assertions.assertNotEquals("The Worker is now working!\nThe improvement will be ready in " + ImprovementType.JUNGLE_MINE.initialTurns + " turns",workerController.setWorkerToBuildImprovement(commandMatcher));
//    }
//
//    @Test
//    public void setWorkerToBuildImprovementTestSuccessfulResourcedImprovement1(){
//        commandMatcher = WorkerCommands.getMatcher("worker build Camp",WorkerCommands.BUILD_IMPROVEMENT);
//        Game.instance.map[3][3].setLandFeature(new LandFeature(LandFeatureType.JUNGLE));
//        nation.addTechnology(TechnologyType.TRAPPING);
//        Game.instance.map[3][3].setResource(new Resource(ResourceType.DEER));
//        if (commandMatcher.matches())
//            Assertions.assertEquals("The Worker is now working!\nThe improvement will be ready in " + ImprovementType.CAMP.initialTurns + " turns",workerController.setWorkerToBuildImprovement(commandMatcher));
//    }
//
//    @Test
//    public void setWorkerToBuildImprovementTestFailNonResourcedImprovement(){
//        commandMatcher = WorkerCommands.getMatcher("worker build Factory",WorkerCommands.BUILD_IMPROVEMENT);
//        Game.instance.map[3][3].setLandType(LandType.OCEAN);
//
//        if (commandMatcher.matches())
//            Assertions.assertNotEquals("The Worker is now working!\nThe improvement will be ready in " + ImprovementType.FACTORY.initialTurns + " turns",workerController.setWorkerToBuildImprovement(commandMatcher));
//    }
//    @Test
//    public void setWorkerToBuildImprovementTestSuccessfulNonResourcedImprovement(){
//        commandMatcher = WorkerCommands.getMatcher("worker build Factory",WorkerCommands.BUILD_IMPROVEMENT);
//        Game.instance.map[3][3].setLandType(LandType.DESERT);
//        nation.addTechnology(TechnologyType.ENGINEERING);
//        if (commandMatcher.matches())
//            Assertions.assertEquals("The Worker is now working!\nThe improvement will be ready in " + ImprovementType.FACTORY.initialTurns + " turns",workerController.setWorkerToBuildImprovement(commandMatcher));
//    }
//
//    @Test
//    public void setWorkerToBuildImprovementTestFailRoute(){
//        commandMatcher = WorkerCommands.getMatcher("worker build Road",WorkerCommands.BUILD_IMPROVEMENT);
//        Game.instance.map[3][3].setLandType(LandType.MOUNTAIN);
//
//        if (commandMatcher.matches())
//            Assertions.assertNotEquals("The Worker is now working!\nThe improvement will be ready in " + ImprovementType.JUNGLE_MINE.initialTurns + " turns",workerController.setWorkerToBuildImprovement(commandMatcher));
//    }
//
//    @Test
//    public void setWorkerToBuildImprovementTestSuccessfulRoute(){
//        commandMatcher = WorkerCommands.getMatcher("worker build Road",WorkerCommands.BUILD_IMPROVEMENT);
//        Game.instance.map[3][3].setLandType(LandType.GRASS_LAND);
//
//        if (commandMatcher.matches())
//            Assertions.assertNotEquals("The Worker is now working!\nThe improvement will be ready in " + ImprovementType.ROAD.initialTurns + " turns",workerController.setWorkerToBuildImprovement(commandMatcher));
//    }
//
//
//}
