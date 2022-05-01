package Model.Technologies;

import Model.Buildings.BuildingType;
import Model.Improvements.ImprovementType;

import javax.lang.model.type.UnionType;

public enum TechnologyType {
    Agriculture("Agriculture", 20, null, null, new ImprovementType[]{ImprovementType.FARM}, null),
    AnimalHusbandry("Animal Husbandry", 35, new TechnologyType[]{Agriculture}, null, null, null),
    Archery("Archery", 35, new TechnologyType[]{Agriculture}, null, null, null),
    Mining("Mining", 35, new TechnologyType[]{Agriculture}, null, null, null),
    BronzeWorking("Bronze Working", 55, new TechnologyType[]{Mining}, null, null, null),
    Pottery("Pottery", 35, new TechnologyType[]{Agriculture}, null, null, null),
    Calendar("Calendar", 70, new TechnologyType[]{Pottery}, null, null, null),
    Masonry("Masonry", 55, new TechnologyType[]{Mining}, null, null, null),
    TheWheel("TheWheel", 55, new TechnologyType[]{AnimalHusbandry}, null, null, null),
    Trapping("Trapping", 55, new TechnologyType[]{AnimalHusbandry}, null, null, null),
    Writing("Writing", 55, new TechnologyType[]{Pottery}, null, null, null),
    Construction("Construction", 100, new TechnologyType[]{Masonry}, null, null, null),
    HorsebackRiding("Horseback Riding", 100, new TechnologyType[]{TheWheel}, null, null, null),
    IronWorking("Iron Working", 150, new TechnologyType[]{BronzeWorking}, null, null, null),
    Mathematics("Mathematics", 100, new TechnologyType[]{TheWheel, Archery}, null, null, null),
    Philosophy("Philosophy", 100, new TechnologyType[]{Writing}, null, null, null),
    CivilService("Civil Service", 400, new TechnologyType[]{Philosophy, Trapping}, null, null, null),
    Currency("Currency", 250, new TechnologyType[]{Mathematics}, null, null, null),
    Chivalry("Chivalry", 440, new TechnologyType[]{CivilService, HorsebackRiding, Currency}, null, null, null),
    Theology("Theology", 250, new TechnologyType[]{Calendar, Philosophy}, null, null, null),
    Education("Education", 440, new TechnologyType[]{Theology}, null, null, null),
    Engineering("Engineering", 250, new TechnologyType[]{Mathematics, Construction}, null, null, null),
    Machinery("Machinery", 440, new TechnologyType[]{Engineering}, null, null, null),
    MetalCasting("Metal Casting", 240, new TechnologyType[]{IronWorking}, null, null, null),
    Physics("Physics", 440, new TechnologyType[]{Engineering, MetalCasting}, null, null, null),
    Steel("Steel", 440, new TechnologyType[]{MetalCasting}, null, null, null),
    Acoustics("Acoustics", 650, new TechnologyType[]{Education}, null, null, null),
    Archaeology("Archaeology", 1300, new TechnologyType[]{Acoustics}, null, null, null),
    Banking("Banking", 650, new TechnologyType[]{Education, Chivalry}, null, null, null),
    Gunpowder("Gunpowder", 680, new TechnologyType[]{Physics, Steel}, null, null, null),
    Chemistry("Chemistry", 900, new TechnologyType[]{Gunpowder}, null, null, null),
    PrintingPress("Printing Press", 650, new TechnologyType[]{Machinery, Physics}, null, null, null),
    Economics("Economics", 900, new TechnologyType[]{Banking, PrintingPress}, null, null, null),
    Fertilizer("Fertilizer", 1300, new TechnologyType[]{Chemistry}, null, null, null),
    Metallurgy("Metallurgy", 900, new TechnologyType[]{Gunpowder}, null, null, null),
    MilitaryScience("Military Science", 1300, new TechnologyType[]{Economics, Chemistry}, null, null, null),
    Rifling("Rifling", 1425, new TechnologyType[]{Metallurgy}, null, null, null),
    ScientificTheory("Scientific Theory", 1300, new TechnologyType[]{Acoustics}, null, null, null),
    Biology("Biology", 1680, new TechnologyType[]{Archaeology, ScientificTheory}, null, null, null),
    SteamPower("Steam Power", 1680, new TechnologyType[]{ScientificTheory, MilitaryScience}, null, null, null),
    ReplaceableParts("Replaceable Parts", 1900, new TechnologyType[]{SteamPower}, null, null, null),
    Railroad("Railroad", 1900, new TechnologyType[]{SteamPower}, null, null, null),
    Dynamite("Dynamite", 1900, new TechnologyType[]{Fertilizer, Rifling}, null, null, null),
    Combustion("Combustion", 2200, new TechnologyType[]{ReplaceableParts, Railroad, Dynamite}, null, null, null),
    Electricity("Electricity", 1900, new TechnologyType[]{Biology, SteamPower}, null, null, null),
    Telegraph("Telegraph", 2200, new TechnologyType[]{Electricity}, null, null, null),




    ;

    public String name;
    public int cost;
    public TechnologyType[] fathers;
    public BuildingType[] buildings;
    public ImprovementType[] improvements;
    public UnionType[] units;


    TechnologyType(String name, int cost, TechnologyType[] fathers, BuildingType[] buildings, ImprovementType[] improvements, UnionType[] units) {
        this.name = name;
        this.cost = cost;
        this.fathers = fathers;
        this.buildings = buildings;
        this.improvements = improvements;
        this.units = units;
    }
}
