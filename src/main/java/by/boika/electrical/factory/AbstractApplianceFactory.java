package by.boika.electrical.factory;

import by.boika.electrical.exceptions.LogicalException;
import by.boika.electrical.model.*;

public abstract class AbstractApplianceFactory {

    public static AbstractElectricalAppliance createAppliance(int id, int power, int voltage, int countOfPhase, int countOfBatteries, int countOfModes, int boilTime,
                                                              int maxVolume, int resolution, String model, String producer, TypesOfBatteries typeOfBattery, TypesOfAppliances typeOfAppliance) throws LogicalException {
    switch (typeOfAppliance) {
        case HAIRDRYER: {
            return new Hairdryer(id, typeOfAppliance, model, producer, power, voltage, countOfPhase, countOfModes);
        }
        case KETTLE: {
            return new Kettle(id, typeOfAppliance, model, producer, power, voltage, countOfPhase, boilTime);
        }
        case MEDIA_CENTER: {
            return new MediaCenter(id, typeOfAppliance, model, producer, power, voltage, countOfPhase, boilTime);
        }
        case PHOTO_CAMERA: {
            return new PhotoCamera(id, typeOfAppliance, model, producer, power, voltage, countOfBatteries, typeOfBattery, resolution);
        }
        default: {
            throw new LogicalException();
        }
    }

    }


}
