package by.boika.electrical.model;

public class PhotoCamera extends AbstractPortableAppliance {

    private final String CAMERA_ON = "camera on";
    private final String CAMERA_OFF = "camera off";
    private final int FACTOR = 31;
    private int resolution;

    public PhotoCamera() {
    }

    public int getResolution() {
        return resolution;
    }

    public AbstractElectricalAppliance setResolution(int resolution) {
        this.resolution = resolution;
        return this;
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
        LOGGER.info("PC - " + getModel());
        return FACTOR * getID() + getModel().hashCode() + resolution + getPower();
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
