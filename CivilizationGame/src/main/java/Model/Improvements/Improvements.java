package Model.Improvements;

public enum Improvements {
    ;

    Improvement improvement;

    Improvements(String name, int turns){
        this.improvement = new Improvement(name, turns);
    }
}
