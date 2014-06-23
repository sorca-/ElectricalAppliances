package by.boika.electrical.factory;

import by.boika.electrical.exceptions.LogicalException;
import by.boika.electrical.model.*;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public abstract class AbstractApplianceFactory {
    private static final String ID = "id";
    private static final String TYPE_OF_APPLIANCE = "typeOfAppliance";
    private static final String MODEL = "model";
    private static final String PRODUCER = "producer";
    private static final String POWER = "power";
    private static final String VOLTAGE = "voltage";
    private static final String COUNT_OF_PHASE = "countOfPhase";
    private static final String COUNT_OF_BATTERIES = "countOfBatteries";
    private static final String COUNT_OF_MODES = "countOfModes";
    private static final String BOIL_TIME = "boilTime";
    private static final String MAX_VOLUME = "maxVolume";
    private static final String RESOLUTION = "resolution";
    private static final String TYPE_OF_BATTERY = "typeOfBattery";

    private static int id;
    private static int power;
    private static int voltage;
    private static int countOfPhase;
    private static int countOfBatteries;
    private static int countOfModes;
    private static int boilTime;
    private static int maxVolume;
    private static int resolution;
    private static String model;
    private static String producer;
    private static TypesOfBatteries typeOfBattery;
    private static TypesOfAppliances typeOfAppliance;

    public static AbstractElectricalAppliance createAppliance(Element element) throws LogicalException {
        id = Integer.parseInt(element.getAttribute(ID));
        power = Integer.parseInt(selectValue(element, POWER));
        voltage = Integer.parseInt(selectValue(element, VOLTAGE));
        model = selectValue(element, MODEL);
        producer = selectValue(element, PRODUCER);
        typeOfAppliance = TypesOfAppliances.valueOf(selectValue(element, TYPE_OF_APPLIANCE).toUpperCase());

        switch (typeOfAppliance) {
            case HAIRDRYER: {
                countOfPhase = Integer.parseInt(selectValue(element, COUNT_OF_PHASE));
                countOfModes = Integer.parseInt(selectValue(element, COUNT_OF_MODES));
                return new Hairdryer(id, typeOfAppliance, model, producer, power, voltage, countOfPhase, countOfModes);
            }
            case KETTLE: {
                countOfPhase = Integer.parseInt(selectValue(element, COUNT_OF_PHASE));
                boilTime = Integer.parseInt(selectValue(element, BOIL_TIME));
                return new Kettle(id, typeOfAppliance, model, producer, power, voltage, countOfPhase, boilTime);
            }
            case MEDIA_CENTER: {
                countOfPhase = Integer.parseInt(selectValue(element, COUNT_OF_PHASE));
                maxVolume = Integer.parseInt(selectValue(element, MAX_VOLUME));
                return new MediaCenter(id, typeOfAppliance, model, producer, power, voltage, countOfPhase, maxVolume);
            }
            case PHOTO_CAMERA: {
                countOfBatteries = Integer.parseInt(selectValue(element, COUNT_OF_BATTERIES));
                typeOfBattery = TypesOfBatteries.valueOf(selectValue(element, TYPE_OF_BATTERY));
                resolution = Integer.parseInt(selectValue(element, RESOLUTION));
                return new PhotoCamera(id, typeOfAppliance, model, producer, power, voltage, countOfBatteries, typeOfBattery, resolution);
            }
            default:
                throw new LogicalException();
        }
    }


    private static String selectValue(Element element, String paramName) throws LogicalException {
        Node node = element.getElementsByTagName(paramName).item(0);
        if (node != null) {
            return node.getTextContent();
        }
        throw new LogicalException();
    }


}
