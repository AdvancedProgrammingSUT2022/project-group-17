package sut.civilization.Model.Classes;

import sut.civilization.Model.ModulEnums.LandFeatureType;

public class LandFeature {
    protected LandFeatureType landFeatureType;

    public LandFeature(LandFeatureType landFeatureType) {
        this.landFeatureType = landFeatureType;
    }

    public LandFeatureType getLandFeatureType() {
        return landFeatureType;
    }
}
