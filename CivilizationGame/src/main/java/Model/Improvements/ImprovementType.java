package Model.Improvements;

public enum ImprovementType {
    Farm("farm", 5),

    ;



    public String name;
    public int initialTurns;

    ImprovementType(String name, int initialTurns){
        this.name = name;
        this.initialTurns = initialTurns;
    }
}
