package by.boika.electrical.model;

public class MediaCenter extends LocalElectrical {

    private final String BEGIN_PLAY = "begin play";
    private final String FINISH_PLAY = "finish play";
    private final int id = 29;

    public MediaCenter(ApplianceType applianceType, String model, String producer, int power, int voltage) {
        super(applianceType, model, producer, power, voltage);
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
        MediaCenter sample = (MediaCenter) obj;
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
    public void switchOn() {
        super.switchOn();
        LOGGER.info(toString() + " " + BEGIN_PLAY);
    }

    @Override
    public void switchOff() {
        super.switchOff();
        LOGGER.info(toString() + " " + FINISH_PLAY);
    }
}
