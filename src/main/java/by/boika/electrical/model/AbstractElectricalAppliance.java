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
    private int ID;

    static {
        new DOMConfigurator().doConfigure(LOG_CONFIGURATIONS_PATH, LogManager.getLoggerRepository());
    }

    public AbstractElectricalAppliance() {
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

    public AbstractElectricalAppliance setModel(String model) {
        this.model = model;
        return this;
    }
    public AbstractElectricalAppliance setProducer(String producer) {
        this.producer = producer;
        return this;
    }
    public AbstractElectricalAppliance setPower(int power) {
        this.power = power;
        return this;
    }
    public AbstractElectricalAppliance setVoltage(int voltage) {
        this.voltage = voltage;
        return this;
    }
    public AbstractElectricalAppliance setTypeOfAppliance(TypesOfAppliances typeOfAppliance) {
        this.typeOfAppliance = typeOfAppliance;
        return this;
    }
    public AbstractElectricalAppliance setID(int ID) {
        this.ID = ID;
        return this;
    }

    @Override
    public String toString() {
        return getTypeOfAppliance().toString() + " " + getProducer() + " " + getModel() + " " + getPower();
    }
}
