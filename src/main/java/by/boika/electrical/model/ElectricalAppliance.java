package by.boika.electrical.model;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

public abstract class ElectricalAppliance {

    private static final String LOG_CONFIGURATIONS_PATH = "src\\main\\resources\\log4j.xml";
    static final Logger LOGGER = Logger.getLogger(ElectricalAppliance.class);

    static {
        new DOMConfigurator().doConfigure(LOG_CONFIGURATIONS_PATH, LogManager.getLoggerRepository());
    }

    private String model;
    private String producer;
    private int power;
    private int voltage;

    private boolean switchedOn;
    private ApplianceType applianceType;

    public ElectricalAppliance(ApplianceType applianceType, String model, String producer, int power, int voltage) {
        this.model = model;
        this.producer = producer;
        this.power = power;
        this.voltage = voltage;
        this.applianceType = applianceType;
    }

    public String getModel() {
        return model;
    }

    public ApplianceType getApplianceType() {
        return applianceType;
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
        return getApplianceType().toString() + " " + getProducer() + " " + getModel();
    }
}
