package sut.civilization.Model.Classes;

import sut.civilization.Model.ModulEnums.TechnologyType;

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
