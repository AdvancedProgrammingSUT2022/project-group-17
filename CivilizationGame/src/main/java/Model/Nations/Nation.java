package Model.Nations;

import Model.City;
import Model.Resources.Currency;
import Model.Resources.Enums.CurrencyType;
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
    protected Currency coin = new Currency(CurrencyType.Coin);
    protected Currency food = new Currency(CurrencyType.Food);
    protected Currency production = new Currency(CurrencyType.Production);
    protected Currency happiness = new Currency(CurrencyType.Happiness);
    protected Currency science = new Currency(CurrencyType.Science);
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

    public Currency getCoin() {
        return coin;
    }

    public Currency getFood() {
        return food;
    }

    public Currency getProduction() {
        return production;
    }

    public Currency getHappiness() {
        return happiness;
    }

    public Currency getScience() {
        return science;
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

    public void setCities(ArrayList<City> cities) {
        this.cities = cities;
    }

    public void setCapital(City capital) {
        this.capital = capital;
    }

    public void setResources(ArrayList<ResourceType> resources) {
        this.resources = resources;
    }

    public void setTechnologies(ArrayList<TechnologyType> technologies) {
        this.technologies = technologies;
    }

    public void setFriends(ArrayList<Nation> friends) {
        this.friends = friends;
    }

    public void setEnemies(ArrayList<Nation> enemies) {
        this.enemies = enemies;
    }

    public void setNationType(NationType nationType) {
        this.nationType = nationType;
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
