package Model.Buildings;

public enum Buildings {
    ;

    protected Building building;

    Buildings(String name, int cost, int maintenance){
        this.building = new Building(name, cost, maintenance);
    }
}
