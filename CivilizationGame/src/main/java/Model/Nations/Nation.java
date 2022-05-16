package Model.Nations;

import Model.Buildings.BuildingType;
import Model.City;
import Model.Resources.Currency;
import Model.Resources.Enums.CurrencyType;
import Model.Resources.Enums.ResourceType;
import Model.Technologies.TechnologyType;
import Model.Units.Unit;

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

    protected Currency coin = new Currency(CurrencyType.COIN);
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

    private void initializeTechnologies() {
        technologies.put(TechnologyType.AGRICULTURE,false);
        technologies.put(TechnologyType.ANIMAL_HUSBANDRY,false);
        technologies.put(TechnologyType.CHEMISTRY,false);
        technologies.put(TechnologyType.CURRENCY,false);
        technologies.put(TechnologyType.ENGINEERING,false);
        technologies.put(TechnologyType.GUNPOWDER,false);
        technologies.put(TechnologyType.MATHEMATICS,false);
        technologies.put(TechnologyType.MINING,false);
        technologies.put(TechnologyType.PHYSICS,false);
        technologies.put(TechnologyType.REPLACEABLE_PARTS,false);
        technologies.put(TechnologyType.STEAM_POWER,false);
        technologies.put(TechnologyType.THE_WHEEL,false);
        technologies.put(TechnologyType.TRAPPING,false);
        technologies.put(TechnologyType.ACOUSTICS,false);
        technologies.put(TechnologyType.ARCHERY,false);
        technologies.put(TechnologyType.BRONZE_WORKING,false);
        technologies.put(TechnologyType.CALENDAR,false);
        technologies.put(TechnologyType.CHIVALRY,false);
        technologies.put(TechnologyType.CIVIL_SERVICE,false);
        technologies.put(TechnologyType.COMBUSTION,false);
        technologies.put(TechnologyType.CONSTRUCTION,false);
        technologies.put(TechnologyType.DYNAMITE,false);
        technologies.put(TechnologyType.EDUCATION,false);
        technologies.put(TechnologyType.HORSEBACK_RIDING,false);
        technologies.put(TechnologyType.IRON_WORKING,false);
        technologies.put(TechnologyType.MASONRY,false);
        technologies.put(TechnologyType.METAL_CASTING,false);
        technologies.put(TechnologyType.METALLURGY,false);
        technologies.put(TechnologyType.MILITARY_SCIENCE,false);
        technologies.put(TechnologyType.PHILOSOPHY,false);
        technologies.put(TechnologyType.POTTERY,false);
        technologies.put(TechnologyType.RIFLING,false);
        technologies.put(TechnologyType.SCIENTIFIC_THEORY,false);
        technologies.put(TechnologyType.STEEL,false);
        technologies.put(TechnologyType.ARCHAEOLOGY,false);
        technologies.put(TechnologyType.BANKING,false);
        technologies.put(TechnologyType.BIOLOGY,false);
        technologies.put(TechnologyType.ECONOMICS,false);
        technologies.put(TechnologyType.ELECTRICITY,false);
        technologies.put(TechnologyType.FERTILIZER,false);
        technologies.put(TechnologyType.MACHINERY,false);
        technologies.put(TechnologyType.PRINTING_PRESS,false);
        technologies.put(TechnologyType.RAILROAD,false);
        technologies.put(TechnologyType.THEOLOGY,false);
        technologies.put(TechnologyType.WRITING,false);
        technologies.put(TechnologyType.TELEGRAPH,false);
    }

    private void initializeResourceCellar() {
        resourceCellar.put(ResourceType.BANANA,0);
        resourceCellar.put(ResourceType.COW,0);
        resourceCellar.put(ResourceType.DEER,0);
        resourceCellar.put(ResourceType.SHEEP,0);
        resourceCellar.put(ResourceType.WHEAT,0);
        resourceCellar.put(ResourceType.COAL,0);
        resourceCellar.put(ResourceType.HORSE,0);
        resourceCellar.put(ResourceType.IRON,0);
        resourceCellar.put(ResourceType.JEWEL,0);
        resourceCellar.put(ResourceType.MARBLE,0);
        resourceCellar.put(ResourceType.GOLD,0);
        resourceCellar.put(ResourceType.SILVER,0);
        resourceCellar.put(ResourceType.COTTON,0);
        resourceCellar.put(ResourceType.FUMIGATION,0);
        resourceCellar.put(ResourceType.SUGAR,0);
        resourceCellar.put(ResourceType.FUR,0);
        resourceCellar.put(ResourceType.SILK,0);
        resourceCellar.put(ResourceType.TUSK,0);
    }

    private void initializeBuildings() {
        buildings.put(BuildingType.ARMORY,false);
        buildings.put(BuildingType.ARSENAL,false);
        buildings.put(BuildingType.BANK,false);
        buildings.put(BuildingType.BURIAL_TOMB,false);
        buildings.put(BuildingType.BARRACKS,false);
        buildings.put(BuildingType.BROADCAST_TOWER,false);
        buildings.put(BuildingType.CASTLE,false);
        buildings.put(BuildingType.CIRCUS,false);
        buildings.put(BuildingType.COLOSSEUM,false);
        buildings.put(BuildingType.COURTHOUSE,false);
        buildings.put(BuildingType.FACTORY,false);
        buildings.put(BuildingType.FORGE,false);
        buildings.put(BuildingType.GARDEN,false);
        buildings.put(BuildingType.GRANARY,false);
        buildings.put(BuildingType.HOSPITAL,false);
        buildings.put(BuildingType.LIBRARY,false);
        buildings.put(BuildingType.MARKET,false);
        buildings.put(BuildingType.MILITARY_ACADEMY,false);
        buildings.put(BuildingType.MILITARY_BASE,false);
        buildings.put(BuildingType.MINT,false);
        buildings.put(BuildingType.MONASTERY,false);
        buildings.put(BuildingType.MONUMENT,false);
        buildings.put(BuildingType.MUSEUM,false);
        buildings.put(BuildingType.OPERA_HOUSE,false);
        buildings.put(BuildingType.PUBLIC_SCHOOL,false);
        buildings.put(BuildingType.SATRAPS_COURT,false);
        buildings.put(BuildingType.STABLE,false);
        buildings.put(BuildingType.STOCK_EXCHANGE,false);
        buildings.put(BuildingType.TEMPLE,false);
        buildings.put(BuildingType.THEATER,false);
        buildings.put(BuildingType.UNIVERSITY,false);
        buildings.put(BuildingType.WALLS,false);
        buildings.put(BuildingType.WATER_MILL,false);
        buildings.put(BuildingType.WINDMILL,false);
        buildings.put(BuildingType.WORKSHOP,false);
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

    public void removeResource(ResourceType resourceType){
        resourceCellar.put(resourceType, resourceCellar.get(resourceType) - 1);
    }

    public void addTechnology(TechnologyType technologyType) {
        technologies.put(technologyType, true);
    }

    public boolean hasTechnology(TechnologyType technologyType) {
        return technologies.get(technologyType);
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
