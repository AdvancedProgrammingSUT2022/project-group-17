package Model.LandFeatures;

public class LandFeature {
    protected String name;
    protected int goldGrowth;
    protected int productionGrowth;
    protected int foodGrowth;

    public LandFeature(String name, int goldGrowth, int productionGrowth, int foodGrowth) {
        this.name = name;
        this.goldGrowth = goldGrowth;
        this.productionGrowth = productionGrowth;
        this.foodGrowth = foodGrowth;
    }

    public String getName() {
        return name;
    }

    public int getGoldGrowth() {
        return goldGrowth;
    }

    public int getProductionGrowth() {
        return productionGrowth;
    }

    public int getFoodGrowth() {
        return foodGrowth;
    }
}
