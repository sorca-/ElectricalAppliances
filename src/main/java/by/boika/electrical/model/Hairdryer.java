package by.boika.electrical.model;

public class Hairdryer extends LocalElectrical {
    private final String BEGIN_TO_BLOW = "begin to blow";
    private final String FINISH_BLOW = "finish blow";
    private final int id = 31;

    public Hairdryer(ApplianceType applianceType, String model, String producer, int power, int voltage) {
        super(applianceType, model, producer, power, voltage);
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
        return id * getModel().hashCode() + getProducer().hashCode();
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
        if (getModel() != sample.getModel())
            return false;
        if (getProducer() != sample.getProducer())
            return false;
        if (getPower() != sample.getPower())
            return false;
        if (getVoltage() != sample.getVoltage())
            return false;
        return true;
    }
}

