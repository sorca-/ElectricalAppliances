package by.boika.electrical.parser;

public enum PropertiesOfAppliance {
    ID("id"),
    TYPE_OF_APPLIANCE("typeOfAppliance"),
    MODEL("model"),
    PRODUCER("producer"),
    POWER("power"),
    VOLTAGE("voltage"),
    COUNT_OF_PHASE("countOfPhase"),
    COUNT_OF_BATTERIES("countOfBatteries"),
    COUNT_OF_MODES("countOfModes"),
    BOIL_TIME("boilTime"),
    MAX_VOLUME("maxVolume"),
    RESOLUTION("resolution"),
    TYPE_OF_BATTERY("typeOfBattery"),
    LOCAL("local"),
    PORTABLE("portable");

    private String value;

    PropertiesOfAppliance(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
