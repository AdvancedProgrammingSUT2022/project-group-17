package Controller.GameControllers;
import Controller.Controller;
import Model.ConsoleColors;
import Model.LandFeatures.LandFeature;
import Model.LandFeatures.LandFeatureType;
import Model.Lands.Land;
import Model.Lands.LandType;
import Model.Pair;
import Model.Resources.Enums.ResourceType;
import Model.Resources.Resource;
import java.util.Random;
import Enums.Consts;

public class LandController extends Controller {

    public static void printMap(Land[][] map){
        int column = 5;
        int row = 10;

        for (int k = 0; k < row; k++) {
            for (int i = 0; i < 6; i++) {
                for (int j = 0; j < column; j++) {

                    String[] resourceChar = new String[2];
                    resourceChar[0] =  (map[k][j*2].getResource() == null ? "  " : ConsoleColors.GREEN_BOLD + map[k][j*2].getResource().getResourceType().name.substring(0,2) + ConsoleColors.RESET);
                    resourceChar[1] =  (map[k][j*2+1].getResource() == null ? "  " : ConsoleColors.GREEN_BOLD + map[k][j*2+1].getResource().getResourceType().name.substring(0,2)+ ConsoleColors.RESET);

                    String[] combatUnitChar = new String[2];
                    combatUnitChar[0] = (map[k][j*2].getCombatUnit() == null ? " " : ConsoleColors.RED_BOLD_BRIGHT + map[k][j*2].getCombatUnit().getName().substring(0,1)+ ConsoleColors.RESET);
                    if (k != 0)
                        combatUnitChar[1] = (map[k-1][j*2+1].getCombatUnit() == null ? " " : ConsoleColors.RED_BOLD_BRIGHT + map[k-1][j*2+1].getCombatUnit().getName().substring(0,1)+ ConsoleColors.RESET);
                    String[] civilizedUnitChar = new String[2];
                    civilizedUnitChar[0] = (map[k][j*2].getCivilizedUnit() == null ? " " : ConsoleColors.BLUE_BOLD_BRIGHT + map[k][j*2].getCivilizedUnit().getName().substring(0,1)+ ConsoleColors.RESET);
                    if (k != 0)
                        civilizedUnitChar[1] = (map[k-1][j*2+1].getCivilizedUnit() == null ? " " : ConsoleColors.BLUE_BOLD_BRIGHT + map[k-1][j*2+1].getCivilizedUnit().getName().substring(0,1)+ ConsoleColors.RESET);
                    String[] combatUnitOwnerChar = new String[2];
                    combatUnitOwnerChar[0] = (map[k][j*2].getCombatUnit() == null ? " " : ConsoleColors.RED_BOLD_BRIGHT + map[k][j*2].getCombatUnit().getOwnerNation().getNationType().name.substring(0,1)+ ConsoleColors.RESET);
                    if (k != 0)
                        combatUnitOwnerChar[1] = (map[k-1][j*2+1].getCombatUnit() == null ? " " : ConsoleColors.RED_BOLD_BRIGHT + map[k-1][j*2+1].getCombatUnit().getOwnerNation().getNationType().name.substring(0,1)+ ConsoleColors.RESET);
                    String[] civilizedUnitOwnerChar = new String[2];
                    civilizedUnitOwnerChar[0] = (map[k][j*2].getCivilizedUnit() == null ? " " : ConsoleColors.BLUE_BOLD_BRIGHT + map[k][j*2].getCivilizedUnit().getOwnerNation().getNationType().name.substring(0,1)+ ConsoleColors.RESET);
                    if (k != 0)
                        civilizedUnitOwnerChar[1] = (map[k-1][j*2+1].getCivilizedUnit() == null ? " " : ConsoleColors.BLUE_BOLD_BRIGHT + map[k-1][j*2+1].getCivilizedUnit().getOwnerNation().getNationType().name.substring(0,1)+ ConsoleColors.RESET);

                    String[] ownerString = new String[2];
                    ownerString[0] = ConsoleColors.PURPLE_BOLD_BRIGHT + (map[k][j*2].getOwnerCity() == null ? "N/A" : map[k][j*2].getOwnerCity().getOwnerNation().getNationType().name.substring(0,3)) + ConsoleColors.RESET;
                    ownerString[1] = ConsoleColors.PURPLE_BOLD_BRIGHT + (map[k][j*2+1].getOwnerCity() == null ? "N/A" : map[k][j*2+1].getOwnerCity().getOwnerNation().getNationType().name.substring(0,3))+ ConsoleColors.RESET;

                    String[] improvementString = new String[2];
                    improvementString[0] = (map[k][j*2].getImprovement() == null ? " " : ConsoleColors.PURPLE_BRIGHT +  map[k][j*2].getImprovement().getImprovementType().name.substring(0,1)+ ConsoleColors.RESET);
                    improvementString[1] = (map[k][j*2+1].getImprovement() == null ? " " : ConsoleColors.PURPLE_BRIGHT +  map[k][j*2+1].getImprovement().getImprovementType().name.substring(0,1)+ ConsoleColors.RESET);

                    String[] landFeatureString = new String[2];
                    landFeatureString[0] = (map[k][j*2].getLandFeature() == null ? " " : ConsoleColors.BLUE_BOLD_BRIGHT +  map[k][j*2].getLandFeature().getLandFeatureType().name.substring(0,1)+ ConsoleColors.RESET);
                    landFeatureString[1] = (map[k][j*2+1].getLandFeature() == null ? " " : ConsoleColors.BLUE_BOLD_BRIGHT +  map[k][j*2+1].getLandFeature().getLandFeatureType().name.substring(0,1)+ ConsoleColors.RESET);

                    String[] borderChar = new String[6];
                    borderChar[0] = (map[k][j*2].getHasRiver()[0] ? ConsoleColors.CYAN_BOLD_BRIGHT + "*" + ConsoleColors.RESET : ConsoleColors.GREEN_BOLD_BRIGHT +  "_" + ConsoleColors.RESET);
                    borderChar[1] = (map[k][j*2].getHasRiver()[1] ? ConsoleColors.CYAN_BOLD_BRIGHT + "*" + ConsoleColors.RESET : ConsoleColors.GREEN_BOLD_BRIGHT + "\\" + ConsoleColors.RESET);
                    borderChar[2] = (map[k][j*2].getHasRiver()[2] ? ConsoleColors.CYAN_BOLD_BRIGHT + "*" + ConsoleColors.RESET : ConsoleColors.GREEN_BOLD_BRIGHT + "/" + ConsoleColors.RESET);
                    borderChar[3] = (map[k][j*2].getHasRiver()[3] ? ConsoleColors.CYAN_BOLD_BRIGHT + "*" + ConsoleColors.RESET : ConsoleColors.GREEN_BOLD_BRIGHT + "_" + ConsoleColors.RESET);
                    borderChar[4] = (map[k][j*2].getHasRiver()[4] ? ConsoleColors.CYAN_BOLD_BRIGHT + "*" + ConsoleColors.RESET : ConsoleColors.GREEN_BOLD_BRIGHT + "\\" + ConsoleColors.RESET);
                    borderChar[5] = (map[k][j*2].getHasRiver()[5] ? ConsoleColors.CYAN_BOLD_BRIGHT + "*" + ConsoleColors.RESET : ConsoleColors.GREEN_BOLD_BRIGHT + "/" + ConsoleColors.RESET);
                    switch (i % 6) {
                        case 0:
                            if (j == column-1 || k == 0)
                                System.out.print("  " + borderChar[5] + " "+ ConsoleColors.BLACK_BOLD_BRIGHT + k + "--" + (j * 2) + ConsoleColors.RESET + " " + borderChar[1] + "        ");
                            else
                                System.out.print("  " + borderChar[5] + " "+ ConsoleColors.BLACK_BOLD_BRIGHT + k + "--" + (j * 2) + ConsoleColors.RESET + " " + borderChar[1] + "   " + combatUnitChar[1] + ConsoleColors.WHITE_BOLD_BRIGHT + "=>" + combatUnitOwnerChar[1] + " ");

                            break;
                        case 1:
                            if (j == column-1 || k == 0)
                                System.out.print(" " + borderChar[5] + " "+ ConsoleColors.YELLOW_BOLD + map[k][j*2].getLandType().name.substring(0,3) + ConsoleColors.WHITE_BOLD_BRIGHT + "," + ConsoleColors.RESET + resourceChar[0] + " " + borderChar[1] + "       ");
                            else
                                System.out.print(" " + borderChar[5] + " "+ ConsoleColors.YELLOW_BOLD + map[k][j*2].getLandType().name.substring(0,3) + ConsoleColors.WHITE_BOLD_BRIGHT + "," + ConsoleColors.RESET +resourceChar[0] + " " + borderChar[1] + "  " + civilizedUnitChar[1] + ConsoleColors.WHITE_BOLD_BRIGHT + "=>" + civilizedUnitOwnerChar[1] + " ");
                            break;
                        case 2:
                            if (j == column - 1) {
                                System.out.print(borderChar[5] + " " + ownerString[0] + " " + improvementString[0] + " /" + landFeatureString[0] + " " + borderChar[1]);
                            } else
                                System.out.print(borderChar[5] + " " + ownerString[0] + " " + improvementString[0] + " /" + landFeatureString[0] + " " + borderChar[1] + borderChar[0] + borderChar[0] + borderChar[0] + borderChar[0] + borderChar[0] + borderChar[0]);
                            break;
                        case 3:
                            if (j == column-1 || k == row-1)
                                System.out.print(borderChar[4] + "   " + combatUnitChar[0] + ConsoleColors.WHITE_BOLD_BRIGHT + "=>" + combatUnitOwnerChar[0] + "   " + borderChar[2] + "      ");
                            else
                                System.out.print(borderChar[4] + "   " + combatUnitChar[0] + ConsoleColors.WHITE_BOLD_BRIGHT + "=>" + combatUnitOwnerChar[0] + "   " + borderChar[2] + " " + ConsoleColors.BLACK_BOLD_BRIGHT + k + "--" + (j * 2 + 1) + ConsoleColors.RESET + " ");
                            break;
                        case 4:
                            if (k == row - 1 || j == column - 1)
                                System.out.print(" " + borderChar[4] + "  " + civilizedUnitChar[0] + ConsoleColors.WHITE_BOLD_BRIGHT + "=>" + civilizedUnitOwnerChar[0] + "  " + borderChar[2] + "       ");
                            else
                                System.out.print(" " + borderChar[4] + "  " + civilizedUnitChar[0] + ConsoleColors.WHITE_BOLD_BRIGHT + "=>" + civilizedUnitOwnerChar[0] + "  " + borderChar[2] + " " + ConsoleColors.YELLOW_BOLD + map[k][j*2+1].getLandType().name.substring(0,3) + ConsoleColors.WHITE_BOLD_BRIGHT + "," + ConsoleColors.RESET + resourceChar[1]);
                            break;
                        case 5:
                            if (k == row - 1 || j == column - 1)
                                System.out.print("  " + borderChar[4] + borderChar[3] + borderChar[3] + borderChar[3] + borderChar[3] + borderChar[3] + borderChar[3] + borderChar[2] + "        ");
                            else
                                System.out.print("  " + borderChar[4] + borderChar[3] + borderChar[3] + borderChar[3] + borderChar[3] + borderChar[3] + borderChar[3] + borderChar[2] + " " + ownerString[1] + " " + improvementString[1] + "/" + landFeatureString[1]);

                            break;
                    }
                }
                System.out.println();
            }
        }
    }

    public static void printNeighbors(Pair coordinate){
        System.out.printf("(%d,%d) : ",coordinate.x,coordinate.y);
        if (coordinate.y % 2 == 0){
            System.out.printf("(%d,%d)",coordinate.x-1,coordinate.y-1);
            System.out.printf("(%d,%d)",coordinate.x-1,coordinate.y);
            System.out.printf("(%d,%d)",coordinate.x-1,coordinate.y+1);
            System.out.printf("(%d,%d)",coordinate.x,coordinate.y-1);
            System.out.printf("(%d,%d)",coordinate.x+1,coordinate.y);
            System.out.printf("(%d,%d)",coordinate.x,coordinate.y+1);
        } else {
            System.out.printf("(%d,%d)",coordinate.x,coordinate.y-1);
            System.out.printf("(%d,%d)",coordinate.x-1,coordinate.y);
            System.out.printf("(%d,%d)",coordinate.x,coordinate.y+1);
            System.out.printf("(%d,%d)",coordinate.x+1,coordinate.y-1);
            System.out.printf("(%d,%d)",coordinate.x+1,coordinate.y);
            System.out.printf("(%d,%d)",coordinate.x+1,coordinate.y+1);
        }
    }

    public static Pair getNeighborIndex(Pair coordinate, int position){
        if (coordinate.y % 2 == 0){
            switch (position){
                case 0:
                    return new Pair(coordinate.x-1,coordinate.y);
                case 1:
                    return new Pair(coordinate.x-1,coordinate.y+1);
                case 2:
                    return new Pair(coordinate.x,coordinate.y-1);
                case 3:
                    return new Pair(coordinate.x+1,coordinate.y);
                case 4:
                    return new Pair(coordinate.x,coordinate.y+1);
                case 5:
                    return new Pair(coordinate.x-1,coordinate.y-1);
            }
        } else {
            switch (position){
                case 0:
                    return new Pair(coordinate.x-1,coordinate.y);
                case 1:
                    return new Pair(coordinate.x,coordinate.y+1);
                case 2:
                    return new Pair(coordinate.x+1,coordinate.y-1);
                case 3:
                    return new Pair(coordinate.x+1,coordinate.y);
                case 4:
                    return new Pair(coordinate.x+1,coordinate.y+1);
                case 5:
                    return new Pair(coordinate.x,coordinate.y-1);
            }
        }
        return null;
    }

    public static Land[][] mapInitializer(){
        Land[][] map = new Land[12][12];
        Random random = new Random(Double.doubleToLongBits(Math.random()));
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 12; j++) {

                LandType landtype = switch (random.nextInt(8)) {
                    case 0 -> LandType.GrassLand;
                    case 1 -> LandType.Tundra;
                    case 2 -> LandType.Desert;
                    case 3 -> LandType.Hill;
                    case 4 -> LandType.Mountain;
                    case 5 -> LandType.Ocean;
                    case 6 -> LandType.Plain;
                    default -> LandType.Snow;
                };

                map[i][j] = new Land(landtype, random.nextInt(50)+50);
            }
        }

        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 12; j++) {
                ResourceType[] availableResources = map[i][j].getLandType().resourceTypes;
                LandFeatureType[] landFeatureTypes = map[i][j].getLandType().landFeatureTypes;
                int randomInt;

                if (landFeatureTypes != null && landFeatureTypes.length != 0){
                    if (random.nextInt(landFeatureTypes.length) % 2 == 0){
                        randomInt = random.nextInt(landFeatureTypes.length);
                        map[i][j].setLandFeature(new LandFeature(landFeatureTypes[randomInt]));
                    }
                }

                if (availableResources != null && availableResources.length != 0){
                    if (random.nextInt(availableResources.length) % 3 == 0){
                        randomInt = random.nextInt(availableResources.length);
                        map[i][j].setResource(new Resource(availableResources[randomInt]));
                    }
                }

                for (int k = 0; k < 6; k++) {
                    if (random.nextInt()%15 == 0){
                        map[i][j].setRiver(k,true);
                        if (isPairValid(getNeighborIndex(new Pair(i,j),k)))
                            map[getNeighborIndex(new Pair(i,j),k).x][getNeighborIndex(new Pair(i,j),k).y].setRiver((k+3)%6,true);
                    }
                }
            }
        }

        return map;
    }

    public static boolean isPairValid(Pair pair){
        if (pair == null)
            return false;
        return pair.x >= 0 && pair.y >= 0 && pair.x < Consts.MAP_SIZE.amount.x && pair.y < Consts.MAP_SIZE.amount.y;
    }
}
