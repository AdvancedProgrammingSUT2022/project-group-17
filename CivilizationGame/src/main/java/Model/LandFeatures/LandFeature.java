package Model.LandFeatures;

public class LandFeature {
    protected LandFeatureType landFeatureType;

    public LandFeature(LandFeatureType landFeatureType) {
        this.landFeatureType = landFeatureType;
    }

    public LandFeatureType getLandFeatureType() {
        return landFeatureType;
    }
}
