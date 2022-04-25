package Model.Nations;

import Model.City;
import Model.Resources.Currency;

public enum NationType {
    AMIR("amirKingdom","amirHossein");

    public String name;
    public String leaderName;

    NationType(String name, String leaderName) {
        this.name = name;
        this.leaderName = leaderName;
    }
}
