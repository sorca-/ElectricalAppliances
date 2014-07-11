package by.boika.electrical.model;

public class Hairdryer extends AbstractLocalAppliance {
    private final String BEGIN_TO_BLOW = "begin to blow";
    private final String FINISH_BLOW = "finish blow";
    private final int FACTOR = 31;
    private int countOfModes;

    public Hairdryer() {
    }

    public int getCountOfModes() {
        return countOfModes;
    }

    public AbstractElectricalAppliance setCountOfModes(int countOfModes) {
        this.countOfModes = countOfModes;
        return this;
    }

    @Override
    public void switchOn() {
        super.switchOn();
        LOGGER.info(toString() + " " + BEGIN_TO_BLOW);
    }

    @Override
    public void switchOff() {
        super.switchOff();
        LOGGER.info(toString() + " " + FINISH_BLOW);
    }

    @Override
    public int hashCode() {
        LOGGER.info("фен - " + getModel());
        return FACTOR * getID() + getModel().hashCode() + countOfModes + getPower();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Hairdryer sample = (Hairdryer) obj;
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
}

