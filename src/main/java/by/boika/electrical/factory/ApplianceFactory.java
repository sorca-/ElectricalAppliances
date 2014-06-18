package by.boika.electrical.factory;

import by.boika.electrical.model.*;

public abstract class ApplianceFactory {

    public static ElectricalAppliance createAppliance(String applianceType, String model, String producer, int power, int voltage) {
        ApplianceType type = ApplianceType.valueOf(applianceType.toUpperCase());
        switch (type) {
            case HAIRDRYER: return new Hairdryer(type, model, producer, power, voltage);
            case KETTLE: return new Kettle(type, model, producer, power, voltage);
            case MEDIA_CENTER: return new MediaCenter(type, model, producer, power, voltage);
            case PHOTO_CAMERA: return new PhotoCamera(type, model, producer, power, voltage);

            default: throw new IllegalArgumentException();
        }

    }
}
