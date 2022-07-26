package sut.civilization.Model.ModulEnums;

public enum NationType {
    FRANCE("France","Napoleon"),
    MAYA("Maya", "Pacal"),
    GREECE("Greece","Alexander"),
    PERSIA("Persia","Darius"),
    EGYPT("Egypt" ,"Ramesses"),
    INDIA("India","Gandhi"),
    ROME("Rome","Augustus Caesar"),
    AZTEC("Aztec","Montezuma"),
    INCA("Inca","Pachacuti");

    public final String name;
    public final String leaderName;
    public final String leaderImageAddress;
    public final String nationImageAddress;

    NationType(String name, String leaderName) {
        this.name = name;
        this.leaderName = leaderName;
        this.nationImageAddress = "/sut/civilization/Images/nations/" + name + ".png";
        this.leaderImageAddress = "/sut/civilization/Images/nations/" + leaderName + ".png";
    }

    public static NationType getNationTypeByName(String name){
        for (NationType value : NationType.values()) {
            if (value.name.equals(name))
                return value;
        }
        return null;
    }

    @Override
    public String toString() {
        return "NationType{" +
                "name='" + name + '\'' +
                '}';
    }
}
