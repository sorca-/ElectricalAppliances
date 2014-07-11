package by.boika.electrical.model;

public abstract class AbstractPortableAppliance extends AbstractElectricalAppliance {
    private int countOfBatteries;
    private TypesOfBatteries typeOfBattery;
    private final String BATTERY_CHARGING = "Battery charging";

    public AbstractPortableAppliance() {
    }

    public int getCountOfBatteries() {
        return countOfBatteries;
    }

    public AbstractElectricalAppliance setCountOfBatteries(int countOfBatteries) {
        this.countOfBatteries = countOfBatteries;
        return this;
    }

    public TypesOfBatteries getTypeOfBattery() {
        return typeOfBattery;
    }

    public AbstractElectricalAppliance setTypeOfBattery(TypesOfBatteries typeOfBattery) {
        this.typeOfBattery = typeOfBattery;
        return this;
    }

    public void chargeBattery() {
        LOGGER.info(BATTERY_CHARGING);
    }
}

