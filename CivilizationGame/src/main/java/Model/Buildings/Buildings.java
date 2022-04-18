package Model.Buildings;

public enum Buildings {
    ;

    protected Building building;

    Buildings(String name, int cost, int maintenance, int turns){
        this.building = new Building(name, cost, maintenance, turns);
    }
}
