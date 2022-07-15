package sut.civilization.Model.Classes;

import sut.civilization.Model.ModulEnums.BuildingType;
import sut.civilization.Model.ModulEnums.NationType;
import sut.civilization.Model.ModulEnums.CurrencyType;
import sut.civilization.Model.ModulEnums.ResourceType;
import sut.civilization.Model.ModulEnums.TechnologyType;

import java.util.ArrayList;
import java.util.HashMap;

public class Nation {


    protected ArrayList<City> cities = new ArrayList<>();
    protected City capital;
    protected NationType nationType;

    protected HashMap<ResourceType,Integer> resourceCellar = new HashMap<>();
    protected HashMap<TechnologyType,Boolean> technologies = new HashMap<>();
    protected HashMap<BuildingType,Boolean> buildings = new HashMap<>();

    protected ArrayList<Nation> friends = new ArrayList<>();
    protected ArrayList<Nation> enemies = new ArrayList<>();
    protected ArrayList<Unit> units = new ArrayList<>();

    protected Currency coin = new Currency(CurrencyType.GOLD);
    protected Currency food = new Currency(CurrencyType.FOOD);
    protected Currency production = new Currency(CurrencyType.PRODUCTION);
    protected Currency happiness = new Currency(CurrencyType.HAPPINESS);
    protected Currency science = new Currency(CurrencyType.SCIENCE);

    protected TechnologyType inProgressTechnology = null;
    protected int technologyTurns;


    public Nation (NationType nationType){
        this.nationType = nationType;

        initializeTechnologies();
        initializeResourceCellar();
        initializeBuildings();
        this.getHappiness().setBalance(100);
        this.getCoin().setBalance(100);
    }

    public void initializeTechnologies() {
        for (TechnologyType technologyType : TechnologyType.values()) {
            this.technologies.put(technologyType,false);
        }
    }

    private void initializeResourceCellar() {
        for (ResourceType resourceType : ResourceType.values()) {
            this.resourceCellar.put(resourceType,0);
        }

    }

    private void initializeBuildings() {

        for (BuildingType buildingType : BuildingType.values()) {
            this.buildings.put(buildingType,false);
        }
    }

    public ArrayList<Nation> getFriends() {
        return friends;
    }

    public ArrayList<Nation> getEnemies() {
        return enemies;
    }

    public ArrayList<Unit> getUnits() {
        return units;
    }

    public Currency getCoin() {
        return coin;
    }

    public Currency getFood() {
        return food;
    }

    public HashMap<ResourceType, Integer> getResourceCellar() {
        return resourceCellar;
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

    public void increaseCoin(int amount) {
        this.coin.setBalance(this.coin.getBalance() + amount);
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


    public HashMap<TechnologyType, Boolean> getTechnologies() {
        return technologies;
    }

    public void setCities(ArrayList<City> cities) {
        this.cities = cities;
    }

    public void setCapital(City capital) {
        this.capital = capital;
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

    public void addUnit(Unit unit) {
        units.add(unit);
    }

    public void addResource(ResourceType resourceType) {
        resourceCellar.put(resourceType, resourceCellar.get(resourceType) + 1);
    }

    public void setResourceCellar(HashMap<ResourceType, Integer> resourceCellar) {
        this.resourceCellar = resourceCellar;
    }

    public void setTechnologies(HashMap<TechnologyType, Boolean> technologies) {
        this.technologies = technologies;
    }

    public void setBuildings(HashMap<BuildingType, Boolean> buildings) {
        this.buildings = buildings;
    }

    public void removeResource(ResourceType resourceType){
        resourceCellar.put(resourceType, resourceCellar.get(resourceType) - 1);

    }

    public void addTechnology(TechnologyType technologyType) {
        technologies.put(technologyType, true);
    }

    public boolean hasTechnology(TechnologyType technologyType) {
        if (this.technologies.get(technologyType) == null)
            return false;

        return this.technologies.get(technologyType);
    }


    public HashMap<BuildingType, Boolean> getBuildings() {
        return buildings;
    }

    public void removeUnit(Unit unit){
        units.remove(unit);
    }

    public void removeCity(City city){
        cities.remove(city);
    }

    public TechnologyType getInProgressTechnology() {
        return inProgressTechnology;
    }

    public int getTechnologyTurns() {
        return technologyTurns;
    }

    public void setInProgressTechnology(TechnologyType inProgressTechnology) {
        this.inProgressTechnology = inProgressTechnology;
    }

    public void setTechnologyTurns(int technologyTurns) {
        this.technologyTurns = technologyTurns;
    }

    public void addCity(City city){
        this.cities.add(city);
    }
}
