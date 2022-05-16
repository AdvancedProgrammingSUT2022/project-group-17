package Controller.GameControllers;

import Model.City;
import Model.Game;
import Model.Improvements.Improvement;
import Model.Lands.Land;
import Model.Nations.Nation;
import Model.Pair;
import Model.Units.CombatUnit;
import Model.Units.Enums.CivilizedUnitType;

import java.util.regex.Matcher;

public class CityController extends GameController {

    public String buildCity(Matcher matcher){
        int x = Integer.parseInt(matcher.group("x"));
        int y = Integer.parseInt(matcher.group("y"));
        String name = matcher.group("name");
        if (currentTurnUser.getNation().getCities().size() > 0 && (selectedCivilizedUnit == null || !selectedCivilizedUnit.getCivilizedUnitType().equals(CivilizedUnitType.SETTLER)))
            return "You need to select a settler first";
        Pair main = new Pair(x, y);
        if (isCityBuildable(main)){
            City city = new City(currentTurnUser.getNation(), name);
            currentTurnUser.getNation().addCity(city);
            Land mainLand = Game.map[x][y];
            mainLand.setCityCenter(true);
            mainLand.setOwnerCity(city);
            city.setMainLand(mainLand);

            Pair neighbors[] = new Pair[6];
            for (int i = 0; i < 6; i++)
                neighbors[i] = LandController.getNeighborIndex(main, i);
            for (int i = 0; i < 6; i++) {
                if (Pair.isValid(neighbors[i]))
                    Game.map[neighbors[i].x][neighbors[i].y].setOwnerCity(city);
            }
            if (currentTurnUser.getNation().getCities().size() > 0 && selectedCivilizedUnit != null)
                UnitController.unitDeath(selectedCivilizedUnit);
            return "City built successfully";
        }
        return "Can't build a city here";
    }

    public boolean isCityBuildable(Pair main){
        if (Game.map[main.x][main.y].getLandType().isWalkable == false)
            return false;
        Pair neighbors[] = new Pair[6];
        for (int i = 0; i < 6; i++)
            neighbors[i] = LandController.getNeighborIndex(main, i);

        for (int i = 0; i < 6; i++) {
            if (Pair.isValid(neighbors[i])){
                Pair neighbors2[] = new Pair[6];
                for (int j = 0; j < 6; j++)
                    neighbors2[j] = LandController.getNeighborIndex(neighbors[i], j);

                for (int j = 0; j < 6; j++) {
                    if (Pair.isValid(neighbors2[j])){
                        if (Game.map[neighbors2[j].x][neighbors2[j].y].getOwnerCity() != null)
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
        Pair neighbors[] = new Pair[6];
        for (int i = 0; i < 6; i++)
            neighbors[i] = LandController.getNeighborIndex(new Pair(x, y), i);

        for (int i = 0; i < 6; i++) {
            if (Pair.isValid(neighbors[i])){
                Pair neighbors2[] = new Pair[6];
                for (int j = 0; j < 6; j++)
                    neighbors2[j] = LandController.getNeighborIndex(neighbors[i], j);

                for (int j = 0; j < 6; j++) {
                    if (Pair.isValid(neighbors2[j])){
                        if (Game.map[neighbors2[j].x][neighbors2[j].y].getOwnerCity() != null){
                            if (Game.map[neighbors2[j].x][neighbors2[j].y].getOwnerCity().equals(selectedCity)){
                                CombatUnit combatUnit = Game.map[x][y].getCombatUnit();
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
        Pair[] neighbors = new Pair[6];
        for (int i = 0; i < 6; i++) {
            neighbors[i] = LandController.getNeighborIndex(new Pair(x, y), i);
            Pair[] neighbors2 = new Pair[6];
            for (int j = 0; j < 6; j++) {
                neighbors2[j] = LandController.getNeighborIndex(neighbors[i], j);
                if (selectedCity.getMainLand().equals(Game.map[neighbors2[j].x][neighbors2[j].y]))
                    return true;
            }
        }
        return false;

    }

    public String sendCitizen(Matcher matcher){
        int x = Integer.parseInt(matcher.group("x"));
        int y = Integer.parseInt(matcher.group("y"));
        Pair dest = new Pair(x, y);

        if (!isCitizenInRange(x, y))
            return "You can't send a citizen that far";
        if (selectedCity != null){
            if (Game.map[x][y].getOwnerCity() == null || !Game.map[x][y].getOwnerCity().equals(selectedCity))
                return "This land is not part of your city";
            if (selectedCity.getEmployees() < selectedCity.getCitizens()){
                if (!Game.map[x][y].hasCitizen()){
                    selectedCity.setEmployees(selectedCity.getEmployees() + 1);
                    Game.map[dest.x][dest.y].setCitizen(true);
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
        Pair origin = new Pair(x, y);

        if (selectedCity != null){
            if (Game.map[x][y].hasCitizen()){
                Game.map[x][y].setCitizen(false);
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
        Pair landPair = new Pair(x, y);

        Pair neighbors[] = new Pair[6];
        for (int i = 0 ; i<6 ; i++)
            neighbors[i] = LandController.getNeighborIndex(landPair, i);

        boolean canBuy = false;
        for (int i = 0; i < 6; i++) {
            if (Pair.isValid(neighbors[i])){
                if (Game.map[neighbors[i].x][neighbors[i].y].getOwnerCity() != null && Game.map[neighbors[i].x][neighbors[i].y].getOwnerCity().equals(selectedCity))
                    canBuy = true;
            }
        }

        Land land = Game.map[x][y];
        if (selectedCity.getOwnerNation().getCoin().getBalance() >= land.getCost()
                && canBuy && land.isBuyable()){
            land.setOwnerCity(selectedCity);
            selectedCity.getOwnerNation().getCoin().addBalance(-land.getCost());
            return "Land bought successfully";
        }
        return "The specific land is not buyable";

    }

    public void cityBuyBuilding(Matcher matcher){

    }

    public void cityProduceBuilding(Matcher matcher){

    }

    public void cityProgressBuilding(){

    }

    public void cityBuyUnit(){

    }

    public void cityProduceUnit(){

    }

    public void cityProgressUnit(){

    }

    public void cityChangeState(){

    }

    public void cityLevelUp(City city){

    }

    public static void cityDeath(City city){
        Nation nation = city.getOwnerNation();
        nation.removeCity(city);
        for (Improvement improvement : city.getImprovements()) {
            city.getImprovements().remove(improvement);
        }
        for (Land land : city.getLands()) {
            land.setOwnerCity(null);
            land.setImprovement(null);
            land.setCityCenter(false);
            city.getLands().remove(land);
        }
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
