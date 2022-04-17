package Model.Nations;

import Model.City;

public enum Nations {
    ;

    Nation nation;

    Nations(String name, String leaderName, City capital){
        this.nation = new Nation(name, leaderName, capital);
    }
}
