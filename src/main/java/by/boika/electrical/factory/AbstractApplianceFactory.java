package by.boika.electrical.factory;

import by.boika.electrical.exceptions.LogicalException;
import by.boika.electrical.model.*;

public abstract class AbstractApplianceFactory {

    public static AbstractElectricalAppliance createAppliance(int id, TypesOfAppliances typeOfAppliance, String model, String producer, int power, int voltage, int countOfPhase, int countOfBatteries, TypesOfBatteries typesOfBattery, int countOfModes, int boilTime, int maxVolume, int resolution) throws LogicalException {

        switch (typeOfAppliance) {
            case HAIRDRYER: return new Hairdryer(id, typeOfAppliance, model, producer, power, voltage, countOfPhase, countOfModes);
            case KETTLE: return new Kettle(id, typeOfAppliance, model, producer, power, voltage, countOfPhase, boilTime);
            case MEDIA_CENTER: return new MediaCenter(id, typeOfAppliance, model, producer, power, voltage, countOfPhase, maxVolume);
            case PHOTO_CAMERA: return new PhotoCamera(id, typeOfAppliance, model, producer, power, voltage, countOfBatteries, typesOfBattery, resolution);

            default: throw new LogicalException();
        }
    }
}
