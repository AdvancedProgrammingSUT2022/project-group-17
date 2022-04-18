package Model.Nations;

import Model.City;
import Model.Resources.Currency;

public enum Nations {
    ;

    Nation nation;

    Nations(String name, String leaderName, City capital, Currency[] currencies){
        this.nation = new Nation(name, leaderName, capital, currencies);
    }
}
