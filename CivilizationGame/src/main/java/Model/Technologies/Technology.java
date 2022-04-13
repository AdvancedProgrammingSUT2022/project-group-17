package Model.Technologies;

import Model.Buildings.Building;
import Model.Improvement;

import java.util.ArrayList;

public class Technology {

    protected Technology father;
    protected boolean isAvailable;
    protected String name;
    protected ArrayList<Building> buildings = new ArrayList<>();
    protected ArrayList<Improvement> improvements = new ArrayList<>();
    //


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
