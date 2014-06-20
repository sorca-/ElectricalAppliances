package by.boika.electrical.model;

public abstract class AbstractPortableAppliance extends AbstractElectricalAppliance {
    private int countOfBatteries;
    private TypesOfBatteries typeOfBattery;
    private final String BATTERY_CHARGING = "Battery charging";

    public AbstractPortableAppliance(int id, TypesOfAppliances typesOfAppliances, String model, String producer, int power, int voltage, int countOfBatteries, TypesOfBatteries typeOfBattery) {
        super(id, typesOfAppliances, model, producer, power, voltage);
        this.countOfBatteries = countOfBatteries;
        this.typeOfBattery = typeOfBattery;
    }

    public void chargeBattery() {
        LOGGER.info(BATTERY_CHARGING);
    }
}

