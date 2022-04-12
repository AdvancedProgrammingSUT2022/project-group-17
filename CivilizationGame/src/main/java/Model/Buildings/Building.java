package Model.Buildings;

public class Building {

    protected String name;
    protected int cost;
    protected int maintenance;
    //FixMe does making a parents field static impact the child's field in any unwanted way
    protected static boolean isTechnologicallyAvailable = false;

    public Building(String name, int cost, int maintenance){
        this.name = name;
        this.cost = cost;
        this.maintenance = maintenance;
    }

    public String getName() {
        return name;
    }

    public int getCost() {
        return cost;
    }

    public int getMaintenance() {
        return maintenance;
    }

    public static boolean isAvailable() {
        return isTechnologicallyAvailable;
    }

    public static void setIsAvailable(boolean isAvailable) {
        Building.isTechnologicallyAvailable = isAvailable;
    }
}
