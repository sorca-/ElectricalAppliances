package by.boika.electrical.model;

public class MediaCenter extends AbstractLocalAppliance {

    private final String BEGIN_PLAY = "begin play";
    private final String FINISH_PLAY = "finish play";
    private final int FACTOR = 31;
    private int maxVolume;

    public MediaCenter(int id, TypesOfAppliances typeOfAppliance, String model, String producer, int power, int voltage, int countOfPhase, int maxVolume) {
        super(id, typeOfAppliance, model, producer, power, voltage, countOfPhase);
        this.maxVolume = maxVolume;
    }

    @Override
    public int hashCode() {
        LOGGER.info("MC - " + getModel());
        return FACTOR * getID() + getModel().hashCode() + maxVolume + getPower();
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
