package Model.Technologies;

import Model.Buildings.Building;
import Model.Improvements.Improvement;

import java.util.ArrayList;

public class Technology {


    protected boolean isAvailable;
    protected TechnologyType technologyType;

    public Technology(TechnologyType technologyType) {
        this.isAvailable = false;
        this.technologyType = technologyType;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public TechnologyType getTechnologyType() {
        return technologyType;
    }

    public void makeAvailable() {
        isAvailable = true;
    }
}
