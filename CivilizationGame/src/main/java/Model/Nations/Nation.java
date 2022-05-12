package Model.Nations;

import Model.Buildings.BuildingType;
import Model.City;
import Model.Resources.Currency;
import Model.Resources.Enums.CurrencyType;
import Model.Resources.Enums.ResourceType;
import Model.Technologies.TechnologyType;

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

    protected Currency coin = new Currency(CurrencyType.Coin);
    protected Currency food = new Currency(CurrencyType.Food);
    protected Currency production = new Currency(CurrencyType.Production);
    protected Currency happiness = new Currency(CurrencyType.Happiness);
    protected Currency science = new Currency(CurrencyType.Science);


    public Nation (NationType nationType){
        this.nationType = nationType;

        initializeTechnologies();
        initializeResourceCellar();
        initializeBuildings();
    }

    private void initializeTechnologies() {
        technologies.put(TechnologyType.Agriculture,false);
        technologies.put(TechnologyType.AnimalHusbandry,false);
        technologies.put(TechnologyType.Chemistry,false);
        technologies.put(TechnologyType.Currency,false);
        technologies.put(TechnologyType.Engineering,false);
        technologies.put(TechnologyType.Gunpowder,false);
        technologies.put(TechnologyType.Mathematics,false);
        technologies.put(TechnologyType.Mining,false);
        technologies.put(TechnologyType.Physics,false);
        technologies.put(TechnologyType.ReplaceableParts,false);
        technologies.put(TechnologyType.SteamPower,false);
        technologies.put(TechnologyType.TheWheel,false);
        technologies.put(TechnologyType.Trapping,false);
        technologies.put(TechnologyType.Acoustics,false);
        technologies.put(TechnologyType.Archery,false);
        technologies.put(TechnologyType.BronzeWorking,false);
        technologies.put(TechnologyType.Calendar,false);
        technologies.put(TechnologyType.Chivalry,false);
        technologies.put(TechnologyType.CivilService,false);
        technologies.put(TechnologyType.Combustion,false);
        technologies.put(TechnologyType.Construction,false);
        technologies.put(TechnologyType.Dynamite,false);
        technologies.put(TechnologyType.Education,false);
        technologies.put(TechnologyType.HorsebackRiding,false);
        technologies.put(TechnologyType.IronWorking,false);
        technologies.put(TechnologyType.Masonry,false);
        technologies.put(TechnologyType.MetalCasting,false);
        technologies.put(TechnologyType.Metallurgy,false);
        technologies.put(TechnologyType.MilitaryScience,false);
        technologies.put(TechnologyType.Philosophy,false);
        technologies.put(TechnologyType.Pottery,false);
        technologies.put(TechnologyType.Rifling,false);
        technologies.put(TechnologyType.ScientificTheory,false);
        technologies.put(TechnologyType.Steel,false);
        technologies.put(TechnologyType.Archaeology,false);
        technologies.put(TechnologyType.Banking,false);
        technologies.put(TechnologyType.Biology,false);
        technologies.put(TechnologyType.Economics,false);
        technologies.put(TechnologyType.Electricity,false);
        technologies.put(TechnologyType.Fertilizer,false);
        technologies.put(TechnologyType.Machinery,false);
        technologies.put(TechnologyType.PrintingPress,false);
        technologies.put(TechnologyType.Railroad,false);
        technologies.put(TechnologyType.Theology,false);
        technologies.put(TechnologyType.Writing,false);
        technologies.put(TechnologyType.Telegraph,false);
    }

    private void initializeResourceCellar() {
        resourceCellar.put(ResourceType.Banana,0);
        resourceCellar.put(ResourceType.Cow,0);
        resourceCellar.put(ResourceType.Deer,0);
        resourceCellar.put(ResourceType.Sheep,0);
        resourceCellar.put(ResourceType.Wheat,0);
        resourceCellar.put(ResourceType.Coal,0);
        resourceCellar.put(ResourceType.Horse,0);
        resourceCellar.put(ResourceType.Iron,0);
        resourceCellar.put(ResourceType.Jewel,0);
        resourceCellar.put(ResourceType.Marble,0);
        resourceCellar.put(ResourceType.Gold,0);
        resourceCellar.put(ResourceType.Silver,0);
        resourceCellar.put(ResourceType.Cotton,0);
        resourceCellar.put(ResourceType.Fumigation,0);
        resourceCellar.put(ResourceType.Sugar,0);
        resourceCellar.put(ResourceType.Fur,0);
        resourceCellar.put(ResourceType.Silk,0);
        resourceCellar.put(ResourceType.Tusk,0);
    }

    private void initializeBuildings() {
        buildings.put(BuildingType.Armory,false);
        buildings.put(BuildingType.Arsenal,false);
        buildings.put(BuildingType.Bank,false);
        buildings.put(BuildingType.BurialTomb,false);
        buildings.put(BuildingType.Barracks,false);
        buildings.put(BuildingType.BroadcastTower,false);
        buildings.put(BuildingType.Castle,false);
        buildings.put(BuildingType.Circus,false);
        buildings.put(BuildingType.Colosseum,false);
        buildings.put(BuildingType.Courthouse,false);
        buildings.put(BuildingType.Factory,false);
        buildings.put(BuildingType.Forge,false);
        buildings.put(BuildingType.Garden,false);
        buildings.put(BuildingType.Granary,false);
        buildings.put(BuildingType.Hospital,false);
        buildings.put(BuildingType.Library,false);
        buildings.put(BuildingType.Market,false);
        buildings.put(BuildingType.MilitaryAcademy,false);
        buildings.put(BuildingType.MilitaryBase,false);
        buildings.put(BuildingType.Mint,false);
        buildings.put(BuildingType.Monastery,false);
        buildings.put(BuildingType.Monument,false);
        buildings.put(BuildingType.Museum,false);
        buildings.put(BuildingType.OperaHouse,false);
        buildings.put(BuildingType.PublicSchool,false);
        buildings.put(BuildingType.SatrapsCourt,false);
        buildings.put(BuildingType.Stable,false);
        buildings.put(BuildingType.StockExchange,false);
        buildings.put(BuildingType.Temple,false);
        buildings.put(BuildingType.Theater,false);
        buildings.put(BuildingType.University,false);
        buildings.put(BuildingType.Walls,false);
        buildings.put(BuildingType.WaterMill,false);
        buildings.put(BuildingType.Windmill,false);
        buildings.put(BuildingType.Workshop,false);
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

    public boolean hasTechnology(TechnologyType technologyType) {
        return technologies.get(technologyType);
    }

    public HashMap<TechnologyType, Boolean> getTechnologies() {
        return technologies;
    }

    public void addResource(ResourceType resourceType){
        resourceCellar.put(resourceType,resourceCellar.get(resourceType)+1);
    }

    public HashMap<BuildingType, Boolean> getBuildings() {
        return buildings;
    }
}
