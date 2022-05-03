package Controller.GameControllers;

import Model.City;
import Model.Game;
import Model.Lands.Land;
import Model.Pair;

import java.util.regex.Matcher;

public class CityController extends GameController {

    public void buildCity(Matcher matcher){
        int x = Integer.parseInt(matcher.group("x"));
        int y = Integer.parseInt(matcher.group("y"));
        Pair main = new Pair(x, y);
        City city = new City(selectedNation);
        Land mainLand = Game.map[x][y];
        mainLand.setCityCenter(true);
        mainLand.setOwnerCity(city);

        Pair neighbors[] = new Pair[6];
        for (int i = 0; i < 6; i++)
            neighbors[i] = LandController.getNeighborIndex(main, i);
        for (int i = 0; i < 6; i++) {
            if (LandController.isPairValid(neighbors[i]))
                Game.map[neighbors[i].x][neighbors[i].y].setOwnerCity(city);
        }
    }

    public void cityRangeAttack(Matcher matcher){

    }

    public void sendCitizen(Matcher matcher){

    }

    public void cityBuyLand(Matcher matcher){
        int x = Integer.parseInt(matcher.group("x"));
        int y = Integer.parseInt(matcher.group("y"));
        Pair landPair = new Pair(x, y);

        Pair neighbors[] = new Pair[6];
        for (int i = 0 ; i<6 ; i++)
            neighbors[i] = LandController.getNeighborIndex(landPair, i);

        boolean canBuy = false;
        for (int i = 0; i < 6; i++) {
            if (LandController.isPairValid(neighbors[i])){
                if (Game.map[neighbors[i].x][neighbors[i].y].getOwnerCity().equals(selectedCity))
                    canBuy = true;
            }
        }

        Land land = Game.map[x][y];

        if (selectedCity.getOwnerNation().getCoin().getBalance() >= land.getCost()
                && canBuy && land.isBuyable()){
            Game.map[x][y].setOwnerCity(selectedCity);
            selectedCity.getOwnerNation().getCoin().addBalance(-land.getCost());
        }

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

}
