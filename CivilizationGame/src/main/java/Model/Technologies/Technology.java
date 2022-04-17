package Model.Technologies;

import Model.Buildings.Building;
import Model.Improvements.Improvement;

import java.util.ArrayList;

public class Technology {

    protected Technology father;
    protected boolean isAvailable;
    protected String name;
    protected ArrayList<Building> buildings = new ArrayList<>();
    protected ArrayList<Improvement> improvements = new ArrayList<>();
    //


    public Technology(Technology father, boolean isAvailable, String name, ArrayList<Building> buildings, ArrayList<Improvement> improvements) {
        this.father = father;
        this.isAvailable = isAvailable;
        this.name = name;
        this.buildings = buildings;
        this.improvements = improvements;
    }

    public Technology getFather() {
        return father;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Building> getBuildings() {
        return buildings;
    }

    public ArrayList<Improvement> getImprovements() {
        return improvements;
    }
}
