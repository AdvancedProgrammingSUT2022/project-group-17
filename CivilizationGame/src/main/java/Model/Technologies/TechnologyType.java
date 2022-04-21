package Model.Technologies;

import Model.Buildings.Building;
import Model.Improvements.Improvement;

import java.util.ArrayList;

public enum TechnologyType {
    ;

    public Technology father;
    public String name;
    public ArrayList<Building> buildings = new ArrayList<>();
    public ArrayList<Improvement> improvements = new ArrayList<>();
}
