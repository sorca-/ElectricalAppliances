package by.boika.electrical.model;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

public abstract class AbstractElectricalAppliance {

    private static final String LOG_CONFIGURATIONS_PATH = "src\\main\\resources\\log4j.xml";
    static final Logger LOGGER = Logger.getLogger(AbstractElectricalAppliance.class);
    private String model;
    private String producer;
    private int power;
    private int voltage;
    private boolean switchedOn;
    private TypesOfAppliances typeOfAppliance;
    private final int ID;

    static {
        new DOMConfigurator().doConfigure(LOG_CONFIGURATIONS_PATH, LogManager.getLoggerRepository());
    }

    public AbstractElectricalAppliance(int id, TypesOfAppliances typeOfAppliance, String model, String producer, int power, int voltage) {
        this.ID = id;
        this.model = model;
        this.producer = producer;
        this.power = power;
        this.voltage = voltage;
        this.typeOfAppliance = typeOfAppliance;
    }

    public int getID() {
        return ID;
    }
    public String getModel() {
        return model;
    }
    public TypesOfAppliances getTypeOfAppliance() {
        return typeOfAppliance;
    }
    public String getProducer() {
        return producer;
    }
    public int getPower() {
        return power;
    }
    public int getVoltage() {
        return voltage;
    }
    public boolean isSwitchedOn() {
        return switchedOn;
    }
    public void switchOn () {
        this.switchedOn = true;
    }
    public void switchOff () {
        this.switchedOn = false;
    }
    @Override
    public String toString() {
        return getTypeOfAppliance().toString() + " " + getProducer() + " " + getModel() + " " + getPower();
    }
}
