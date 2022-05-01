package Model.Nations;

import Model.City;
import Model.Resources.Currency;
import Model.Resources.Enums.ResourceType;
import Model.Technologies.Technology;
import Model.Resources.Resource;
import Model.Technologies.TechnologyType;

import java.util.ArrayList;

public class Nation {


    protected ArrayList<City> cities = new ArrayList<>();
    protected City capital;
    protected ArrayList<ResourceType> resources = new ArrayList<>();
    protected ArrayList<TechnologyType> technologies = new ArrayList<>();
    protected ArrayList<Nation> friends = new ArrayList<>();
    protected ArrayList<Nation> enemies = new ArrayList<>();
    protected Currency[] currencies = new Currency[5];
    protected NationType nationType;

    public Nation (NationType nationType/*, City capital, Currency[] currencies*/){
        this.nationType = nationType;
//        this.capital = capital;
//        this.currencies = currencies;
        cities.add(capital);


        //technologies

        //resources

        //buildings
    }

    public ArrayList<Nation> getFriends() {
        return friends;
    }

    public ArrayList<Nation> getEnemies() {
        return enemies;
    }

    public Currency[] getCurrencies() {
        return currencies;
    }

    public NationType getNationType() {
        return nationType;
    }

    public ArrayList<City> getCities() {
        return cities;
    }

    public City getCapital() {
        return capital;
    }

    public ArrayList<ResourceType> getResources() {
        return resources;
    }

    public ArrayList<TechnologyType> getTechnologies() {
        return technologies;
    }

    public void addResource(ResourceType resource) {
        this.resources.add(resource);
    }

    public boolean hasTechnology(TechnologyType technologyType) {
        for (TechnologyType technology: technologies)
            if(technology == technologyType)
                return true;
        return false;
    }

}
