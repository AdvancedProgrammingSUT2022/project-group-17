package Controller.GameControllers;
import Controller.Controller;
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

    public void printMap(Land[][] map){
        int column = 5;
        int row = 10;

        for (int k = 0; k < row; k++) {
            for (int i = 0; i < 6; i++) {
                for (int j = 0; j < column; j++) {
                    switch (i % 6) {
                        case 0:
                            System.out.print("  /      \\        ");
                            break;
                        case 1:
                            System.out.print(" /  " + k + "," + (j * 2) + "   \\       ");
                            break;
                        case 2:
                            if (j == column - 1) {
                                System.out.print("/          \\");
                            } else
                                System.out.print("/    "+ map[k][j*2].getLandType().name.charAt(0) + "     \\______");

                            break;
                        case 3:
                            System.out.print("\\          /      ");
                            break;
                        case 4:
                            if (j == column - 1 || k == row - 1) {
                                System.out.print(" \\        /       ");
                            } else {
                                System.out.print(" \\        /  " + k + "," + (j * 2 + 1) + "  ");
                            }
                            break;
                        case 5:
                            if (k == row - 1 || j == column - 1)
                                System.out.print("  \\______/        ");
                            else
                                System.out.print("  \\______/        ");
                            break;
                    }
                }
                System.out.println();
            }
        }
    }

    public void printNeighbors(Pair coordinate){
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

    public Pair getNeighborIndex(Pair coordinate, int position){
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

    public Land[][] mapInitializer(){
        Land[][] map = new Land[10][10];
        Random random = new Random(Double.doubleToLongBits(Math.random()));
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {

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

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                ResourceType[] availableResources = map[i][j].getLandType().resourceTypes;
                LandFeatureType[] landFeatureTypes = map[i][j].getLandType().landFeatureTypes;

                if (landFeatureTypes != null && landFeatureTypes.length != 0)
                    map[i][j].setLandFeature(new LandFeature(landFeatureTypes[random.nextInt(landFeatureTypes.length)]));

                if (availableResources != null && availableResources.length != 0)
                    map[i][j].setResource(new Resource(availableResources[random.nextInt(availableResources.length)]));

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

    public boolean isPairValid(Pair pair){
        return pair.x >= 0 && pair.y >= 0 && pair.x < Consts.MAP_SIZE.amount.x && pair.y < Consts.MAP_SIZE.amount.y;
    }
}
