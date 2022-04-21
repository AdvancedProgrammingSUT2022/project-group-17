package Model.LandFeatures;

public enum LandFeatureType {
    ;

    public String name;
    public int goldGrowth;
    public int productionGrowth;
    public int foodGrowth;

    LandFeatureType(String name, int goldGrowth, int productionGrowth, int foodGrowth) {
        this.name = name;
        this.goldGrowth = goldGrowth;
        this.productionGrowth = productionGrowth;
        this.foodGrowth = foodGrowth;
    }
}
