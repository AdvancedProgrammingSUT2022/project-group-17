package Model.LandFeatures;

public enum LandFeatures {
    ;

    LandFeature landFeature;

    LandFeatures(String name, int goldGrowth, int productionGrowth, int foodGrowth){
        this.landFeature = new LandFeature(name, goldGrowth, productionGrowth, foodGrowth);
    }
}
