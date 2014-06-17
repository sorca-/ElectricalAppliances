package by.bsu.boika.electrical.model;

import org.apache.log4j.Logger;

public abstract class ElectricalAppliance {

    static final Logger LOGGER = Logger.getLogger(ElectricalAppliance.class);

    private String model;
    private String producer;
    private int power;
    private boolean switchedOn;
    private int voltage;


    public void switchOn () {
        this.switchedOn = true;
    }
    public void switchOff () {
        this.switchedOn = false;
    }
}
