package by.boika.electrical.parser;

public enum PropertiesOfAppliance {
    ID("id"),
    TYPE_OF_APPLIANCE("type-of-appliance"),
    MODEL("model"),
    PRODUCER("producer"),
    POWER("power"),
    VOLTAGE("voltage"),
    COUNT_OF_PHASE("count-of-phase"),
    COUNT_OF_BATTERIES("count-of-batteries"),
    COUNT_OF_MODES("count-of-modes"),
    BOIL_TIME("boil-time"),
    MAX_VOLUME("max-volume"),
    RESOLUTION("resolution"),
    TYPE_OF_BATTERY("type-of-battery"),
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
