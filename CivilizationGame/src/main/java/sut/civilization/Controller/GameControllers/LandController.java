package sut.civilization.Controller.GameControllers;

import sut.civilization.Controller.Controller;
import sut.civilization.Model.Classes.*;
import sut.civilization.Model.ModulEnums.*;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

import sut.civilization.Enums.Consts;

public class LandController extends Controller {

    public void printMap(Land[][] map) {
        updateLandVisibility();

        int column = 5;
        int row = 10;

        for (int k = 0; k < row; k++) {
            for (int i = 0; i < 6; i++) {
                for (int j = 0; j < column; j++) {

                    String[] resourceSign = new String[2];
                    resourceSign[0] = (map[k][j * 2].getResource() == null || map[k][j * 2].getVisibility() != 2 ? "  " : ConsoleColors.GREEN_BOLD + map[k][j * 2].getResource().getResourceType().name.substring(0, 2) + ConsoleColors.RESET);
                    resourceSign[1] = (map[k][j * 2 + 1].getResource() == null || map[k][j * 2 + 1].getVisibility() != 2 ? "  " : ConsoleColors.GREEN_BOLD + map[k][j * 2 + 1].getResource().getResourceType().name.substring(0, 2) + ConsoleColors.RESET);

                    String[] combatUnitSign = new String[2];
                    combatUnitSign[0] = (map[k][j * 2].getCombatUnit() == null || map[k][j * 2].getVisibility() != 2 ? " " : ConsoleColors.RED_BOLD_BRIGHT + map[k][j * 2].getCombatUnit().getName().substring(0, 1) + ConsoleColors.RESET);
                    if (k != 0)
                        combatUnitSign[1] = (map[k - 1][j * 2 + 1].getCombatUnit() == null || map[k][j * 2 + 1].getVisibility() != 2 ? " " : ConsoleColors.RED_BOLD_BRIGHT + map[k - 1][j * 2 + 1].getCombatUnit().getName().substring(0, 1) + ConsoleColors.RESET);

                    String[] civilizedUnitSign = new String[2];
                    civilizedUnitSign[0] = (map[k][j * 2].getCivilizedUnit() == null || map[k][j * 2].getVisibility() != 2 ? " " : ConsoleColors.BLUE_BOLD_BRIGHT + map[k][j * 2].getCivilizedUnit().getName().substring(0, 1) + ConsoleColors.RESET);
                    if (k != 0)
                        civilizedUnitSign[1] = (map[k - 1][j * 2 + 1].getCivilizedUnit() == null || map[k][j * 2 + 1].getVisibility() != 2 ? " " : ConsoleColors.BLUE_BOLD_BRIGHT + map[k - 1][j * 2 + 1].getCivilizedUnit().getName().substring(0, 1) + ConsoleColors.RESET);

                    String[] combatUnitOwnerSign = new String[2];
                    combatUnitOwnerSign[0] = (map[k][j * 2].getCombatUnit() == null || map[k][j * 2].getVisibility() != 2 ? " " : ConsoleColors.RED_BOLD_BRIGHT + map[k][j * 2].getCombatUnit().getOwnerNation().getNationType().name.substring(0, 1) + ConsoleColors.RESET);
                    if (k != 0)
                        combatUnitOwnerSign[1] = (map[k - 1][j * 2 + 1].getCombatUnit() == null || map[k][j * 2 + 1].getVisibility() != 2 ? " " : ConsoleColors.RED_BOLD_BRIGHT + map[k - 1][j * 2 + 1].getCombatUnit().getOwnerNation().getNationType().name.substring(0, 1) + ConsoleColors.RESET);

                    String[] civilizedUnitOwnerSign = new String[2];
                    civilizedUnitOwnerSign[0] = (map[k][j * 2].getCivilizedUnit() == null || map[k][j * 2].getVisibility() != 2 ? " " : ConsoleColors.BLUE_BOLD_BRIGHT + map[k][j * 2].getCivilizedUnit().getOwnerNation().getNationType().name.substring(0, 1) + ConsoleColors.RESET);
                    if (k != 0)
                        civilizedUnitOwnerSign[1] = (map[k - 1][j * 2 + 1].getCivilizedUnit() == null || map[k][j * 2 + 1].getVisibility() != 2 ? " " : ConsoleColors.BLUE_BOLD_BRIGHT + map[k - 1][j * 2 + 1].getCivilizedUnit().getOwnerNation().getNationType().name.substring(0, 1) + ConsoleColors.RESET);

                    String[] ownerSign = new String[2];
                    ownerSign[0] = ConsoleColors.PURPLE_BOLD_BRIGHT + (map[k][j * 2].getOwnerCity() == null || map[k][j * 2].getVisibility() != 2 ? "N/A" : map[k][j * 2].getOwnerCity().getOwnerNation().getNationType().name.substring(0, 3)) + ConsoleColors.RESET;
                    ownerSign[1] = ConsoleColors.PURPLE_BOLD_BRIGHT + (map[k][j * 2 + 1].getOwnerCity() == null || map[k][j * 2 + 1].getVisibility() != 2 ? "N/A" : map[k][j * 2 + 1].getOwnerCity().getOwnerNation().getNationType().name.substring(0, 3)) + ConsoleColors.RESET;

                    String[] improvementSign = new String[2];
                    improvementSign[0] = (map[k][j * 2].getImprovement() == null || map[k][j * 2].getVisibility() != 2 ? " " : ConsoleColors.PURPLE_BRIGHT + map[k][j * 2].getImprovement().getImprovementType().name.substring(0, 1) + ConsoleColors.RESET);
                    improvementSign[1] = (map[k][j * 2 + 1].getImprovement() == null || map[k][j * 2 + 1].getVisibility() != 2 ? " " : ConsoleColors.PURPLE_BRIGHT + map[k][j * 2 + 1].getImprovement().getImprovementType().name.substring(0, 1) + ConsoleColors.RESET);

                    String[] landFeatureString = new String[2];
                    landFeatureString[0] = (map[k][j * 2].getLandFeature() == null || map[k][j * 2].getVisibility() != 2 ? " " : ConsoleColors.BLUE_BOLD_BRIGHT + map[k][j * 2].getLandFeature().getLandFeatureType().name.substring(0, 1) + ConsoleColors.RESET);
                    landFeatureString[1] = (map[k][j * 2 + 1].getLandFeature() == null || map[k][j * 2 + 1].getVisibility() != 2 ? " " : ConsoleColors.BLUE_BOLD_BRIGHT + map[k][j * 2 + 1].getLandFeature().getLandFeatureType().name.substring(0, 1) + ConsoleColors.RESET);

                    String[] landNameString = new String[2];
                    landNameString[0] = (map[k][j * 2].getVisibility() != 2 ? "   " : ConsoleColors.YELLOW_BOLD_BRIGHT + map[k][j * 2].getLandType().name.substring(0, 3) + ConsoleColors.RESET);
                    landNameString[1] = (map[k][j * 2 + 1].getVisibility() != 2 ? "   " : ConsoleColors.YELLOW_BOLD_BRIGHT + map[k][j * 2 + 1].getLandType().name.substring(0, 3) + ConsoleColors.RESET);

                    String[] fogOfWarColor = new String[2];
                    switch (map[k][j * 2].getVisibility()) {
                        case 0:
                            fogOfWarColor[0] = ConsoleColors.WHITE_BRIGHT + "+" + ConsoleColors.RESET;
                            break;
                        case 1:
                            fogOfWarColor[0] = ConsoleColors.RED_BRIGHT + "+" + ConsoleColors.RESET;
                            break;
                        case 2:
                            fogOfWarColor[0] = ConsoleColors.YELLOW_BRIGHT + "+" + ConsoleColors.RESET;
                            break;
                    }
                    switch (map[k][j * 2 + 1].getVisibility()) {
                        case 0:
                            fogOfWarColor[1] = ConsoleColors.WHITE_BRIGHT + "+" + ConsoleColors.RESET;
                            break;
                        case 1:
                            fogOfWarColor[1] = ConsoleColors.RED_BRIGHT + "+" + ConsoleColors.RESET;
                            break;
                        case 2:
                            fogOfWarColor[1] = ConsoleColors.YELLOW_BRIGHT + "+" + ConsoleColors.RESET;
                            break;
                    }
                    String[] borderSign = new String[6];
                    String borderColor = ConsoleColors.GREEN_BOLD_BRIGHT;
                    borderSign[0] = (map[k][j * 2 + 2].getHasRiver()[0] ? ConsoleColors.CYAN_BOLD_BRIGHT + "*" : borderColor + "_") + ConsoleColors.RESET;
                    borderSign[1] = (map[k][j * 2].getHasRiver()[1] ? ConsoleColors.CYAN_BOLD_BRIGHT + "*" : borderColor + "\\") + ConsoleColors.RESET;
                    borderSign[2] = (map[k][j * 2].getHasRiver()[2] ? ConsoleColors.CYAN_BOLD_BRIGHT + "*" : borderColor + "/") + ConsoleColors.RESET;
                    borderSign[3] = (map[k][j * 2].getHasRiver()[3] ? ConsoleColors.CYAN_BOLD_BRIGHT + "*" : borderColor + "_") + ConsoleColors.RESET;
                    borderSign[4] = (map[k][j * 2].getHasRiver()[4] ? ConsoleColors.CYAN_BOLD_BRIGHT + "*" : borderColor + "\\") + ConsoleColors.RESET;
                    borderSign[5] = (map[k][j * 2].getHasRiver()[5] ? ConsoleColors.CYAN_BOLD_BRIGHT + "*" : borderColor + "/") + ConsoleColors.RESET;

                    switch (i % 6) {
                        case 0:
                            if (j == column - 1 || k == 0)
                                System.out.print("  " + borderSign[5] + " " + ConsoleColors.BLACK_BOLD_BRIGHT + k + "--" + (j * 2) + ConsoleColors.RESET + " " + borderSign[1] + "        ");
                            else
                                System.out.print("  " + borderSign[5] + " " + ConsoleColors.BLACK_BOLD_BRIGHT + k + "--" + (j * 2) + ConsoleColors.RESET + " " + borderSign[1] + "   " + combatUnitSign[1] + ConsoleColors.WHITE_BOLD_BRIGHT + "=>" + combatUnitOwnerSign[1] + " ");

                            break;
                        case 1:
                            if (j == column - 1 || k == 0)
                                System.out.print(" " + borderSign[5] + " " + landNameString[0] + ConsoleColors.WHITE_BOLD_BRIGHT + "," + ConsoleColors.RESET + resourceSign[0] + " " + borderSign[1] + "       ");
                            else
                                System.out.print(" " + borderSign[5] + " " + landNameString[0] + ConsoleColors.WHITE_BOLD_BRIGHT + "," + ConsoleColors.RESET + resourceSign[0] + " " + borderSign[1] + "  " + civilizedUnitSign[1] + ConsoleColors.WHITE_BOLD_BRIGHT + "=>" + civilizedUnitOwnerSign[1] + " ");
                            break;
                        case 2:
                            if (j == column - 1) {
                                System.out.print(borderSign[5] + " " + ownerSign[0] + fogOfWarColor[0] + improvementSign[0] + " /" + landFeatureString[0] + " " + borderSign[1]);
                            } else
                                System.out.print(borderSign[5] + " " + ownerSign[0] + fogOfWarColor[0] + improvementSign[0] + " /" + landFeatureString[0] + " " + borderSign[1] + borderSign[0] + borderSign[0] + borderSign[0] + borderSign[0] + borderSign[0] + borderSign[0]);
                            break;
                        case 3:
                            if (j == column - 1 || k == row - 1)
                                System.out.print(borderSign[4] + "   " + combatUnitSign[0] + ConsoleColors.WHITE_BOLD_BRIGHT + "=>" + combatUnitOwnerSign[0] + "   " + borderSign[2] + "      ");
                            else
                                System.out.print(borderSign[4] + "   " + combatUnitSign[0] + ConsoleColors.WHITE_BOLD_BRIGHT + "=>" + combatUnitOwnerSign[0] + "   " + borderSign[2] + " " + ConsoleColors.BLACK_BOLD_BRIGHT + k + "--" + (j * 2 + 1) + ConsoleColors.RESET + " ");
                            break;
                        case 4:
                            if (k == row - 1 || j == column - 1)
                                System.out.print(" " + borderSign[4] + "  " + civilizedUnitSign[0] + ConsoleColors.WHITE_BOLD_BRIGHT + "=>" + civilizedUnitOwnerSign[0] + "  " + borderSign[2] + "       ");
                            else
                                System.out.print(" " + borderSign[4] + "  " + civilizedUnitSign[0] + ConsoleColors.WHITE_BOLD_BRIGHT + "=>" + civilizedUnitOwnerSign[0] + "  " + borderSign[2] + " " + ConsoleColors.YELLOW_BOLD + landNameString[1] + ConsoleColors.WHITE_BOLD_BRIGHT + "," + ConsoleColors.RESET + resourceSign[1]);
                            break;
                        case 5:
                            if (k == row - 1 || j == column - 1)
                                System.out.print("  " + borderSign[4] + borderSign[3] + borderSign[3] + borderSign[3] + borderSign[3] + borderSign[3] + borderSign[3] + borderSign[2] + "        ");
                            else
                                System.out.print("  " + borderSign[4] + borderSign[3] + borderSign[3] + borderSign[3] + borderSign[3] + borderSign[3] + borderSign[3] + borderSign[2] + " " + ownerSign[1] + fogOfWarColor[1] + improvementSign[1] + "/" + landFeatureString[1]);

                            break;
                    }
                }
                System.out.println();
            }
        }
    }

    public ArrayList<Pair<Integer, Integer>> getAllNeighborsIndexes(Pair<Integer, Integer> coordinate) {
        ArrayList<Pair<Integer, Integer>> neighborPairs = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            neighborPairs.add(getNeighborIndex(new Pair<Integer, Integer>(coordinate.x, coordinate.y), i));
        }

        return neighborPairs;
    }

    private void updateLandVisibility() {
        for (int i = 0; i < Consts.MAP_SIZE.amount.x; i++) {
            for (int j = 0; j < Consts.MAP_SIZE.amount.y; j++) {
                if (Game.instance.map[i][j].getVisibility() == 2) {
                    Game.instance.map[i][j].setVisibility(1);
                }
            }
        }

        for (int i = 0; i < Consts.MAP_SIZE.amount.x; i++) {
            for (int j = 0; j < Consts.MAP_SIZE.amount.y; j++) {
                if (Game.instance.map[i][j].getCivilizedUnit() != null || Game.instance.map[i][j].getCombatUnit() != null || Game.instance.map[i][j].getOwnerCity() != null) {
                    lightNeighbors(new Pair<Integer, Integer>(i, j));
                    Game.instance.map[i][j].setVisibility(2);
                }
            }
        }
    }

    private void lightNeighbors(Pair<Integer, Integer> coordinate) {
        for (Pair<Integer, Integer> pair : getAllNeighborsIndexes(coordinate)) {
            if (Pair.isValid(pair) && !Game.instance.map[pair.x][pair.y].getLandType().name.equals(LandType.MOUNTAIN.name))
                Game.instance.map[pair.x][pair.y].setVisibility(2);
        }

    }

    public static Pair<Integer, Integer> getNeighborIndex(Pair<Integer, Integer> coordinate, int position) {
        if (coordinate.y % 2 == 0) {
            switch (position) {
                case 0:
                    return new Pair<Integer, Integer>(coordinate.x - 1, coordinate.y);
                case 1:
                    return new Pair<Integer, Integer>(coordinate.x - 1, coordinate.y + 1);
                case 2:
                    return new Pair<Integer, Integer>(coordinate.x, coordinate.y - 1);
                case 3:
                    return new Pair<Integer, Integer>(coordinate.x + 1, coordinate.y);
                case 4:
                    return new Pair<Integer, Integer>(coordinate.x, coordinate.y + 1);
                case 5:
                    return new Pair<Integer, Integer>(coordinate.x - 1, coordinate.y - 1);
            }
        } else {
            switch (position) {
                case 0:
                    return new Pair<Integer, Integer>(coordinate.x - 1, coordinate.y);
                case 1:
                    return new Pair<Integer, Integer>(coordinate.x, coordinate.y + 1);
                case 2:
                    return new Pair<Integer, Integer>(coordinate.x + 1, coordinate.y - 1);
                case 3:
                    return new Pair<Integer, Integer>(coordinate.x + 1, coordinate.y);
                case 4:
                    return new Pair<Integer, Integer>(coordinate.x + 1, coordinate.y + 1);
                case 5:
                    return new Pair<Integer, Integer>(coordinate.x, coordinate.y - 1);
            }
        }
        return null;
    }


    public static boolean areNeighbors(Pair<Integer, Integer> land1, Pair<Integer, Integer> land2) {
        for (int i = 0; i < 6; i++) {
            if (land2.equals(getNeighborIndex(land1, i)))
                return true;
        }
        return false;
    }

    public static int getIndex(Pair<Integer, Integer> land1, Pair<Integer, Integer> land2) {
        for (int i = 0; i < 6; i++) {
            if (land2.equals(getNeighborIndex(land1, i)))
                return i;
        }
        return -1;
    }


    public static Land[][] mapInitializer() {
        Land[][] map = new Land[20][20];
        Random random = new Random(Double.doubleToLongBits(Math.random()));
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {

                LandType landtype;
                switch (random.nextInt(8)) {
                    case 0:
                        landtype = LandType.GRASS_LAND;
                        break;
                    case 1:
                        landtype = LandType.TUNDRA;
                        break;
                    case 2:
                        landtype = LandType.DESERT;
                        break;
                    case 3:
                        landtype = LandType.HILL;
                        break;
                    case 4:
                        landtype = LandType.MOUNTAIN;
                        break;
                    case 5:
                        landtype = LandType.OCEAN;
                        break;
                    case 6:
                        landtype = LandType.PLAIN;
                        break;
                    default:
                        landtype = LandType.SNOW;
                        break;
                }
                ;

                map[i][j] = new Land(landtype, random.nextInt(50) + 50, i, j);
            }
        }

        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                ResourceType[] availableResources = map[i][j].getLandType().resourceTypes;
                LandFeatureType[] landFeatureTypes = map[i][j].getLandType().landFeatureTypes;
                int randomInt;

                if (landFeatureTypes != null && landFeatureTypes.length != 0) {
                    if (random.nextInt(landFeatureTypes.length) % 2 == 0) {
                        randomInt = random.nextInt(landFeatureTypes.length);
                        map[i][j].setLandFeature(new LandFeature(landFeatureTypes[randomInt]));
                    }
                }

                if (availableResources != null && availableResources.length != 0) {
                    if (random.nextInt(availableResources.length) % 3 == 0) {
                        randomInt = random.nextInt(availableResources.length);
                        map[i][j].setResource(new Resource(availableResources[randomInt]));
                    }
                }

                for (int k = 0; k < 6; k++) {
                    if (random.nextInt() % 15 == 0) {
                        map[i][j].setRiver(k, true);
                        if (Pair.isValid(getNeighborIndex(new Pair<Integer, Integer>(i, j), k)))
                            map[Objects.requireNonNull(getNeighborIndex(new Pair<>(i, j), k)).x][Objects.requireNonNull(getNeighborIndex(new Pair<>(i, j), k)).y].setRiver((k + 3) % 6, true);
                    }
                }
            }
        }

        int i = 0;
        for (RangedCombatUnitType rangedCombatUnitType: RangedCombatUnitType.values()) {
            CivilizedUnit civilizedUnit = new CivilizedUnit(CivilizedUnitType.SETTLER, GameController.getCurrentTurnUser().getNation(), new Pair<>(i, i));
            RangedCombatUnit rangedCombatUnit = new RangedCombatUnit(rangedCombatUnitType, GameController.getCurrentTurnUser().getNation(), new Pair<>(i, i));
            map[civilizedUnit.getLocation().x][civilizedUnit.getLocation().y].setCivilizedUnit(civilizedUnit);
            map[rangedCombatUnit.getLocation().x][rangedCombatUnit.getLocation().y].setCombatUnit(rangedCombatUnit);
            GameController.getCurrentTurnUser().getNation().getUnits().add(civilizedUnit);
            GameController.getCurrentTurnUser().getNation().getUnits().add(rangedCombatUnit);
            i++;
            if (i == 10) break;
        }

        City city = new City(GameController.getCurrentTurnUser().getNation(), "Mashhad", map[2][2]);
        GameController.getCurrentTurnUser().getNation().getCities().add(city);
        map[2][2].setOwnerCity(city);
        city.setCitizens(77);
        city.setFoodGrowth(15);
        city.setProductionGrowth(25);
        city.setCoinGrowth(80);

        GameController.getCurrentTurnUser().getNation().getFriends().add(new Nation(NationType.PERSIA));
        GameController.getCurrentTurnUser().getNation().getEnemies().add(new Nation(NationType.INCA));
        GameController.getCurrentTurnUser().getNation().getEnemies().add(new Nation(NationType.EGYPT));

        GameController.getCurrentTurnUser().getNation().addTechnology(TechnologyType.AGRICULTURE);
        GameController.getCurrentTurnUser().getNation().addTechnology(TechnologyType.ANIMAL_HUSBANDRY);
//        GameController.getCurrentTurnUser().getNation().setInProgressTechnology(TechnologyType.BRONZE_WORKING);
//        for (TechnologyType technologyType : TechnologyType.values()) {
//            GameController.getCurrentTurnUser().getNation().addTechnology(technologyType);
//        }
        for (ResourceType resourceType: ResourceType.values()) {
            GameController.getCurrentTurnUser().getNation().addResource(resourceType);
        }

        GameController.getCurrentTurnUser().getNation().getCities().get(0).setInProgressBuilding(new Building(BuildingType.BANK));

        for (int i1 = 0; i1 < 20; i1++) {
            for (int j = 0; j < 20; j++) {
                Land land = map[i1][j];
                int chance = new Random().nextInt(10);
                if (chance <= 1)
                    land.setRuin(new Ruin());
            }
        }

        return map;
    }

    public static Land getLandByCoordinates(int x, int y) {
        return Game.instance.map[x][y];
    }

    public static int getLandNumber(Land land) {
        int num = -1;
        for (int i = 0; i < Consts.MAP_SIZE.amount.x; i++) {
            for (int j = 0; j < Consts.MAP_SIZE.amount.y; j++) {
                if (Game.instance.map[i][j].equals(land))
                    num = i * Consts.MAP_SIZE.amount.x + j;
            }
        }
        return num;
    }

    public static void initializeDistances() {
        for (int i1 = 0; i1 < Consts.MAP_SIZE.amount.x; i1++) {
            for (int j1 = 0; j1 < Consts.MAP_SIZE.amount.y; j1++) {
                for (int i2 = 0; i2 < Consts.MAP_SIZE.amount.x; i2++) {
                    for (int j2 = 0; j2 < Consts.MAP_SIZE.amount.y; j2++) {
                        Land land1 = getLandByCoordinates(i1, j1);
                        Land land2 = getLandByCoordinates(i2, j2);
                        int num1 = getLandNumber(land1);
                        int num2 = getLandNumber(land2);
                        if (num1 == num2) {
                            Game.instance.dist[num1][num2] = 0;
                            Game.instance.path[num1][num2] = "";
                        } else if (areNeighbors(new Pair<Integer, Integer>(i1, j1), new Pair<Integer, Integer>(i2, j2))) {
                            Game.instance.dist[num1][num2] = land2.getMP();
                            if (land2.getLandFeature() != null)
                                Game.instance.dist[num1][num2] += land2.getLandFeature().getLandFeatureType().movementCost;
                            Game.instance.path[num1][num2] = "" + getIndex(new Pair<Integer, Integer>(i1, j1), new Pair<Integer, Integer>(i2, j2));
                        } else {
                            Game.instance.dist[num1][num2] = 1000;
                            Game.instance.path[num1][num2] = "";
                        }
                    }
                }
            }
        }
    }

    public static void updateDistances() {
        for (int i1 = 0; i1 < Consts.MAP_SIZE.amount.x; i1++) {
            for (int j1 = 0; j1 < Consts.MAP_SIZE.amount.y; j1++) {
                for (int i2 = 0; i2 < Consts.MAP_SIZE.amount.x; i2++) {
                    for (int j2 = 0; j2 < Consts.MAP_SIZE.amount.y; j2++) {
                        for (int i3 = 0; i3 < Consts.MAP_SIZE.amount.x; i3++) {
                            for (int j3 = 0; j3 < Consts.MAP_SIZE.amount.y; j3++) {
                                Land firstLand = getLandByCoordinates(i1, j1);
                                Land secondLand = getLandByCoordinates(i2, j2);
                                Land thirdLand = getLandByCoordinates(i3, j3);
                                int num1 = getLandNumber(firstLand);
                                int num2 = getLandNumber(secondLand);
                                int num3 = getLandNumber(thirdLand);
                                if (Game.instance.dist[num1][num2] + Game.instance.dist[num2][num3] < Game.instance.dist[num1][num3]) {
                                    Game.instance.dist[num1][num3] = Game.instance.dist[num1][num2] + Game.instance.dist[num2][num3];
                                    Game.instance.path[num1][num3] = Game.instance.path[num1][num2] + Game.instance.path[num2][num3];
                                }
                            }
                        }
                    }
                }
            }
        }
    }

}
