package by.boika.electrical.model;

public class Kettle extends AbstractLocalAppliance {

    private final String BEGIN_BOIL = "begin boil";
    private final String FINISH_BOIL = "finish boil";
    private final int FACTOR = 31;
    private int boilTime;

    public Kettle() {
    }

    public int getBoilTime() {
        return boilTime;
    }

    public AbstractElectricalAppliance setBoilTime(int boilTime) {
        this.boilTime = boilTime;
        return this;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Kettle sample = (Kettle) obj;
        if (getModel().equals(sample.getModel()))
            return false;
        if (getProducer().equals(sample.getProducer()))
            return false;
        if (getPower() != sample.getPower())
            return false;
        if (getVoltage() != sample.getVoltage())
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        LOGGER.info("чайник - " + getModel());
        return FACTOR * getID() + getModel().hashCode() + boilTime + getPower();
    }

    @Override
    public void switchOn() {
        super.switchOn();
        LOGGER.info(toString() + " " + BEGIN_BOIL);
    }

    @Override
    public void switchOff() {
        super.switchOff();
        LOGGER.info(toString() + " " + FINISH_BOIL);
    }
}
