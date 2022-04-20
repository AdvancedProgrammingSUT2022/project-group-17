package Model.Improvements;

public enum ImprovementType {
    Farm("farm", 5);



    public String name;
    public int turns;

    ImprovementType(String name, int turns){
        this.name = name;
        this.turns = turns;
    }
}
