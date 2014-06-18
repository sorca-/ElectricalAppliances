package by.boika.electrical.model;

public class PhotoCamera extends PortableElectrical {

    private final String CAMERA_ON = "camera on";
    private final String CAMERA_OFF = "camera off";
    private final int id = 28;

    public PhotoCamera(ApplianceType applianceType, String model, String producer, int power, int voltage) {
        super(applianceType, model, producer, power, voltage);
    }

    @Override
    public void switchOn() {
        super.switchOn();
        LOGGER.info(toString() + " " + CAMERA_ON);
    }

    @Override
    public void switchOff() {
        super.switchOff();
        LOGGER.info(toString() + " " + CAMERA_OFF);
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
        PhotoCamera sample = (PhotoCamera) obj;
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
