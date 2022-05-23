package sut.civilization.Model.ModulEnums;

public enum NationType {
    INDUS_VALLEY("Indus Valley","Ghaggar Hakra"),
    MAYA("Maya", "Mama The Maya"),
    ANCIENT_GREECE("Ancient Greece","Alexander The Great"),
    PERSIA("Persia","Cyrus the Great"),
    ANCIENT_EGYPT("Egypt" ,"Ramesses the Great"),
    MESOPOTAMIAN("Mesopotamian","Mess Mess the Potamia"),
    ROME("Rome","Julius Caesar"),
    AZTEC("Aztec","Hernan Cortes"),
    INCA("Inca","Sapa Inca");

    public final String name;
    public final String leaderName;

    NationType(String name, String leaderName) {
        this.name = name;
        this.leaderName = leaderName;
    }
}
