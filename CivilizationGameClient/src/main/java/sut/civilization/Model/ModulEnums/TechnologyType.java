package sut.civilization.Model.ModulEnums;

import javafx.scene.image.Image;
import sut.civilization.Civilization;
import sut.civilization.Model.Classes.Unit;

public enum TechnologyType {
    //1
    AGRICULTURE("Agriculture", 20, null, null, new ImprovementType[]{ImprovementType.FARM}, null),
    //2
    POTTERY("Pottery", 35, new TechnologyType[]{AGRICULTURE}, null, null, null),
    ANIMAL_HUSBANDRY("Animal Husbandry", 35, new TechnologyType[]{AGRICULTURE}, null, null, null),
    ARCHERY("Archery", 35, new TechnologyType[]{AGRICULTURE}, null, null, null),
    MINING("Mining", 35, new TechnologyType[]{AGRICULTURE}, null, null, null),
    //3
    WRITING("Writing", 55, new TechnologyType[]{POTTERY}, null, null, null),
    CALENDAR("Calendar", 70, new TechnologyType[]{POTTERY}, null, null, null),
    THE_WHEEL("The Wheel", 55, new TechnologyType[]{ANIMAL_HUSBANDRY}, null, null, null),
    TRAPPING("Trapping", 55, new TechnologyType[]{ANIMAL_HUSBANDRY}, null, null, null),
    MASONRY("Masonry", 55, new TechnologyType[]{MINING}, null, null, null),
    BRONZE_WORKING("Bronze Working", 55, new TechnologyType[]{MINING}, null, null, null),
    //4
    PHILOSOPHY("Philosophy", 100, new TechnologyType[]{WRITING}, null, null, null),
    HORSEBACK_RIDING("Horseback Riding", 100, new TechnologyType[]{THE_WHEEL}, null, null, null),
    MATHEMATICS("Mathematics", 100, new TechnologyType[]{THE_WHEEL, ARCHERY}, null, null, null),
    CONSTRUCTION("Construction", 100, new TechnologyType[]{MASONRY}, null, null, null),
    IRON_WORKING("Iron Working", 150, new TechnologyType[]{BRONZE_WORKING}, null, null, null),
    //5
    CIVIL_SERVICE("Civil Service", 400, new TechnologyType[]{PHILOSOPHY, TRAPPING}, null, null, null),
    THEOLOGY("Theology", 250, new TechnologyType[]{CALENDAR, PHILOSOPHY}, null, null, null),
    CURRENCY("Currency", 250, new TechnologyType[]{MATHEMATICS}, null, null, null),
    ENGINEERING("Engineering", 250, new TechnologyType[]{MATHEMATICS, CONSTRUCTION}, null, null, null),
    METAL_CASTING("Metal Casting", 240, new TechnologyType[]{IRON_WORKING}, null, null, null),
    //6
    CHIVALRY("Chivalry", 440, new TechnologyType[]{CIVIL_SERVICE, HORSEBACK_RIDING, CURRENCY}, null, null, null),
    EDUCATION("Education", 440, new TechnologyType[]{THEOLOGY}, null, null, null),
    MACHINERY("Machinery", 440, new TechnologyType[]{ENGINEERING}, null, null, null),
    PHYSICS("Physics", 440, new TechnologyType[]{ENGINEERING, METAL_CASTING}, null, null, null),
    STEEL("Steel", 440, new TechnologyType[]{METAL_CASTING}, null, null, null),
    //7
    BANKING("Banking", 650, new TechnologyType[]{EDUCATION, CHIVALRY}, null, null, null),
    ACOUSTICS("Acoustics", 650, new TechnologyType[]{EDUCATION}, null, null, null),
    PRINTING_PRESS("Printing Press", 650, new TechnologyType[]{MACHINERY, PHYSICS}, null, null, null),
    GUNPOWDER("Gunpowder", 680, new TechnologyType[]{PHYSICS, STEEL}, null, null, null),
    //8
    ECONOMICS("Economics", 900, new TechnologyType[]{BANKING, PRINTING_PRESS}, null, null, null),
    CHEMISTRY("Chemistry", 900, new TechnologyType[]{GUNPOWDER}, null, null, null),
    METALLURGY("Metallurgy", 900, new TechnologyType[]{GUNPOWDER}, null, null, null),
    //9
    ARCHAEOLOGY("Archaeology", 1300, new TechnologyType[]{ACOUSTICS}, null, null, null),
    MILITARY_SCIENCE("Military Science", 1300, new TechnologyType[]{ECONOMICS, CHEMISTRY}, null, null, null),
    FERTILIZER("Fertilizer", 1300, new TechnologyType[]{CHEMISTRY}, null, null, null),
    RIFLING("Rifling", 1425, new TechnologyType[]{METALLURGY}, null, null, null),
    SCIENTIFIC_THEORY("Scientific Theory", 1300, new TechnologyType[]{ACOUSTICS}, null, null, null),
    //10
    BIOLOGY("Biology", 1680, new TechnologyType[]{ARCHAEOLOGY, SCIENTIFIC_THEORY}, null, null, null),
    STEAM_POWER("Steam Power", 1680, new TechnologyType[]{SCIENTIFIC_THEORY, MILITARY_SCIENCE}, null, null, null),
    //11
    REPLACEABLE_PARTS("Replaceable Parts", 1900, new TechnologyType[]{STEAM_POWER}, null, null, null),
    RAILROAD("Railroad", 1900, new TechnologyType[]{STEAM_POWER}, null, null, null),
    DYNAMITE("Dynamite", 1900, new TechnologyType[]{FERTILIZER, RIFLING}, null, null, null),
    ELECTRICITY("Electricity", 1900, new TechnologyType[]{BIOLOGY, STEAM_POWER}, null, null, null),
    //12
    COMBUSTION("Combustion", 2200, new TechnologyType[]{REPLACEABLE_PARTS, RAILROAD, DYNAMITE}, null, null, null),
    TELEGRAPH("Telegraph", 2200, new TechnologyType[]{ELECTRICITY}, null, null, null),
    ;

    public final String name;
    public final int cost;
    public final TechnologyType[] fathers;
    public final BuildingType[] buildings;
    public final ImprovementType[] improvements;
    public final Unit[] units;
    public final int turns;
    public final String imageAddress;


    TechnologyType(String name, int cost, TechnologyType[] fathers, BuildingType[] buildings, ImprovementType[] improvements, Unit[] units) {
        this.name = name;
        this.cost = cost;
        this.fathers = fathers;
        this.buildings = buildings;
        this.improvements = improvements;
        this.units = units;
        this.turns = 3;
        this.imageAddress = "/sut/civilization/Images/technologies/" + name + ".png";
    }
}
