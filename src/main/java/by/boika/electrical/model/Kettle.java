package by.boika.electrical.model;

public class Kettle extends LocalElectrical{

    private final String BEGIN_BOIL = "begin boil";
    private final String FINISH_BOIL = "finish boil";
    private final int id = 30;

    public Kettle(ApplianceType applianceType, String model, String producer, int power, int voltage) {
        super(applianceType, model, producer, power, voltage);
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

    @Override
    public int hashCode() {
        return id * getModel().hashCode() + getProducer().hashCode();
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
