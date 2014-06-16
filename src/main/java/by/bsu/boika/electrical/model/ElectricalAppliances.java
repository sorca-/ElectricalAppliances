package by.bsu.boika.electrical.model;

import org.apache.log4j.Logger;

public abstract class ElectricalAppliances {

    static final Logger LOGGER = Logger.getLogger(ElectricalAppliances.class);

    private String model;
    private String producer;
    private int power;
    private boolean status;
    private int voltage;


    public void on () {
        this.status = true;
    }
    public void off () {
        this.status = false;
    }
}
