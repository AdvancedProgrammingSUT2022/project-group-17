package sut.civilization.Controller.GameControllers;

import sut.civilization.Enums.Consts;
import sut.civilization.Model.Classes.*;
import sut.civilization.Model.ModulEnums.LandType;
import sut.civilization.Model.ModulEnums.CivilizedUnitType;

import java.util.regex.Matcher;

import static sut.civilization.Model.Classes.Pair.isValid;

public class CityController extends GameController {

    public static String buildCity(String name){
//        int x = Integer.parseInt(matcher.group("x"));
//        int y = Integer.parseInt(matcher.group("y"));
//        String name = matcher.group("name");
        if (currentTurnUser.getNation().getCities().size() > 0 && (selectedCivilizedUnit == null || !selectedCivilizedUnit.getCivilizedUnitType().equals(CivilizedUnitType.SETTLER)))
            return "You need to select a settler first";
        int x = selectedCivilizedUnit.getLocation().x;
        int y = selectedCivilizedUnit.getLocation().y;
        Pair<Integer,Integer> main = new Pair<Integer,Integer>(x, y);
        if (isCityBuildable(main)){
            Land mainLand = Game.instance.map[x][y];
            City city = new City(currentTurnUser.getNation(), name, mainLand);
            currentTurnUser.getNation().addCity(city);
            mainLand.setCityCenter(true);
            mainLand.setOwnerCity(city);
            city.setMainLand(mainLand);

            Pair<Integer, Integer>[] neighbors = new Pair[6];
            for (int i = 0; i < 6; i++)
                neighbors[i] = LandController.getNeighborIndex(main, i);
            for (int i = 0; i < 6; i++) {
                if (isValid(neighbors[i]))
                    Game.instance.map[neighbors[i].x][neighbors[i].y].setOwnerCity(city);
            }
            if (currentTurnUser.getNation().getCities().size() > 0 && selectedCivilizedUnit != null)
                UnitController.unitDeath(selectedCivilizedUnit);
            if (mainLand.getLandType().equals(LandType.HILL))
                city.setCombatStrength(city.getCombatStrength() + 5);
            return "City built successfully";
        }
        return "Can't build a city here";
    }

    public static boolean isCityBuildable(Pair<Integer, Integer> main){
        if (!Game.instance.map[main.x][main.y].getLandType().isWalkable)
            return false;

        Pair<Integer,Integer>[] neighbors = new Pair[6];
        for (int i = 0; i < 6; i++)
            neighbors[i] = LandController.getNeighborIndex(main, i);

        for (int i = 0; i < 6; i++) {
            if (isValid(neighbors[i])){
                Pair<Integer,Integer>[] neighbors2 = new Pair[6];
                for (int j = 0; j < 6; j++)
                    neighbors2[j] = LandController.getNeighborIndex(neighbors[i], j);

                for (int j = 0; j < 6; j++) {
                    if (isValid(neighbors2[j])){
                        if (Game.instance.map[neighbors2[j].x][neighbors2[j].y].getOwnerCity() != null)
                            return false;
                    }
                }
            }
        }
        return true;
    }

    public String cityRangeAttack(Matcher matcher){
        int x = Integer.parseInt(matcher.group("x"));
        int y = Integer.parseInt(matcher.group("y"));

        if (selectedCity == null)
            return "You have to select a city first";
        Pair<Integer,Integer>[] neighbors = new Pair[6];
        for (int i = 0; i < 6; i++)
            neighbors[i] = landController.getNeighborIndex(new Pair<Integer,Integer>(x, y), i);

        for (int i = 0; i < 6; i++) {
            if (isValid(neighbors[i])){
                Pair<Integer, Integer>[] neighbors2 = new Pair[6];
                for (int j = 0; j < 6; j++)
                    neighbors2[j] = landController.getNeighborIndex(neighbors[i], j);

                for (int j = 0; j < 6; j++) {
                    if (Pair.isValid(neighbors2[j])){
                        if (Game.instance.map[neighbors2[j].x][neighbors2[j].y].getOwnerCity() != null){
                            if (Game.instance.map[neighbors2[j].x][neighbors2[j].y].getOwnerCity().equals(selectedCity)){
                                CombatUnit combatUnit = Game.instance.map[x][y].getCombatUnit();
                                combatUnit.setHp(combatUnit.getHp() - selectedCity.getRangedStrength());
                                return "Attack on unit successful";
                            }
                        }

                    }
                }
            }
        }
        return "Target unit is not in range";
    }


    public boolean isCitizenInRange(int x, int y){
        Pair<Integer,Integer>[] neighbors = new Pair[6];

        for (int i = 0; i < 6; i++) {
            neighbors[i] = landController.getNeighborIndex(new Pair<Integer,Integer>(x, y), i);
            Pair<Integer,Integer>[] neighbors2 = new Pair[6];
            for (int j = 0; j < 6; j++) {
                neighbors2[j] = landController.getNeighborIndex(neighbors[i], j);
                if (selectedCity.getMainLand().equals(Game.instance.map[neighbors2[j].x][neighbors2[j].y]))
                    return true;
            }
        }
        return false;

    }

    public String sendCitizen(Matcher matcher){
        int x = Integer.parseInt(matcher.group("x"));
        int y = Integer.parseInt(matcher.group("y"));
        Pair<Integer,Integer> dest = new Pair<Integer,Integer>(x, y);

        if (!isCitizenInRange(x, y))
            return "You can't send a citizen that far";
        if (selectedCity != null){
            if (Game.instance.map[x][y].getOwnerCity() == null || !Game.instance.map[x][y].getOwnerCity().equals(selectedCity))
                return "This land is not part of your city";
            if (selectedCity.getEmployees() < selectedCity.getCitizens()){
                if (!Game.instance.map[x][y].hasCitizen()){
                    selectedCity.setEmployees(selectedCity.getEmployees() + 1);
                    Game.instance.map[dest.x][dest.y].setCitizen(true);
                    return "Citizen sent to work successfully";
                }
                return "There already is a citizen on this land";
            }
        }
        return "You have to select a city first";
    }

    public String retrieveCitizen(Matcher matcher){
        int x = Integer.parseInt(matcher.group("x"));
        int y = Integer.parseInt(matcher.group("y"));
        Pair<Integer,Integer> origin = new Pair<Integer,Integer>(x, y);

        if (selectedCity != null){
            if (Game.instance.map[x][y].hasCitizen()){
                Game.instance.map[x][y].setCitizen(false);
                selectedCity.setEmployees(selectedCity.getEmployees() - 1);
                return "Citizen retrieved successfully";
            }
            return "There is no citizen working on this land";
        }
        return "You have to select a city first";
    }

    public String cityBuyLand(Matcher matcher){
        if (selectedCity == null)
            return "you have to select a city first";
        int x = Integer.parseInt(matcher.group("x"));
        int y = Integer.parseInt(matcher.group("y"));
        Pair<Integer,Integer> landPair = new Pair<Integer,Integer>(x, y);

        Pair<Integer,Integer>[] neighbors = new Pair[6];
        for (int i = 0 ; i<6 ; i++)
            neighbors[i] = landController.getNeighborIndex(landPair, i);

        boolean canBuy = false;
        for (int i = 0; i < 6; i++) {
            if (Pair.isValid(neighbors[i])){
                if (Game.instance.map[neighbors[i].x][neighbors[i].y].getOwnerCity() != null && Game.instance.map[neighbors[i].x][neighbors[i].y].getOwnerCity().equals(selectedCity)){
                    canBuy = true;
                    break;
                }
            }
        }
        Land land = Game.instance.map[x][y];
        if (selectedCity.getOwnerNation().getCoin().getBalance() >= land.getCost()
                && canBuy && land.isBuyable()){
            land.setOwnerCity(selectedCity);
            selectedCity.getOwnerNation().getCoin().addBalance(-land.getCost());
            return "Land bought successfully";
        }

        return "The specific land is not buyable";

    }

    public static void updateAffordableLands(){
//        for (User user : Game.instance.getPlayersInGame()) {
//            Nation nation = user.getNation();
//            for (City city : nation.getCities()) {
//                city.clearAffordableLands();
//                for (int x = 0; x < Consts.MAP_SIZE.amount.x; x++){
//                    for (int y = 0; y < Consts.MAP_SIZE.amount.y; y++){
//
//                        Pair<Integer,Integer> landPair = new Pair<Integer,Integer>(x, y);
//                        Pair<Integer,Integer>[] neighbors = new Pair[6];
//                        for (int i = 0 ; i<6 ; i++)
//                            neighbors[i] = LandController.getNeighborIndex(landPair, i);
//
//                        boolean canBuy = false;
//                        for (int i = 0; i < 6; i++) {
//                            if (Pair.isValid(neighbors[i])){
//                                if (Game.instance.map[neighbors[i].x][neighbors[i].y].getOwnerCity() != null && Game.instance.map[neighbors[i].x][neighbors[i].y].getOwnerCity().equals(city)){
//                                    canBuy = true;
//                                    break;
//                                }
//                            }
//                        }
//                        Land land = Game.instance.map[x][y];
//                        if (city.getOwnerNation().getCoin().getBalance() >= land.getCost()
//                                && canBuy && land.isBuyable()){
//                            city.addAffordableLand(land);
//                        }
//                    }
//                }
//            }
//        }
    }

    public static void cityDeath(City city){
        Nation nation = city.getOwnerNation();
        nation.removeCity(city);
        city.getImprovements().clear();
        for (Land land : city.getLands()) {
            land.setOwnerCity(null);
            land.setImprovement(null);
            land.setCityCenter(false);
        }
        city.getLands().clear();
        city.setOwnerNation(null);
        city = null;
        System.gc();
    }

    public static void cityTakeOver(City city, Nation nextNation){
        Nation previousNation = city.getOwnerNation();
        city.setOwnerNation(nextNation);
        previousNation.removeCity(city);
        nextNation.getHappiness().addBalance(-10);
    }

    public String cityShowBanner(){
        if (selectedCity == null)
            return "You have to select a city first";
        return "Name: " + selectedCity.getName() + " CombatStrength: " + selectedCity.getCombatStrength();
    }


}
