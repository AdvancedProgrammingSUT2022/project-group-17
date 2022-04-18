package Model.Technologies;

import Model.Buildings.Building;
import Model.Improvements.Improvement;

import java.util.ArrayList;

public enum Technologies {
    ;

    Technology technology;

    Technologies(Technology father, boolean isAvailable, String name, ArrayList<Building> buildings,
                 ArrayList<Improvement> improvements){
        this.technology = new Technology(father, isAvailable, name, buildings, improvements);
    }
}
