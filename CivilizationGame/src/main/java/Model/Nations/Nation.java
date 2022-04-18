package Model.Nations;

import Model.City;
import Model.Resources.Currency;
import Model.Technologies.Technology;
import Model.Resources.Resource;

import java.util.ArrayList;

public class Nation {
    protected String name;
    protected String leaderName;
    protected ArrayList<City> cities = new ArrayList<>();
    protected City capital;
    protected ArrayList<Resource> resources = new ArrayList<>();
    protected ArrayList<Technology> technologies = new ArrayList<>();
    protected ArrayList<Nation> friends = new ArrayList<>();
    protected ArrayList<Nation> enemies = new ArrayList<>();
    protected Currency[] currencies = new Currency[5];

    public Nation (String name, String leaderName, City capital, Currency[] currencies){
        this.name = name;
        this.leaderName = leaderName;
        this.capital = capital;
        this.currencies = currencies;
        cities.add(capital);


        //technologies

        //resources

        //buildings
    }

    public String getName() {
        return name;
    }

    public String getLeaderName() {
        return leaderName;
    }

    public ArrayList<City> getCities() {
        return cities;
    }

    public City getCapital() {
        return capital;
    }

    public ArrayList<Resource> getResources() {
        return resources;
    }

    public ArrayList<Technology> getTechnologies() {
        return technologies;
    }

}
