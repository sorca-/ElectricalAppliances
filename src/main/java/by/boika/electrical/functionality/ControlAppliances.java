package by.boika.electrical.functionality;

import by.boika.electrical.model.AbstractElectricalAppliance;
import by.boika.electrical.model.TypesOfAppliances;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

public class ControlAppliances {

    private ArrayList<AbstractElectricalAppliance> abstractElectricalAppliances;
    private static final String LOG_CONFIGURATIONS_PATH = "src\\main\\resources\\log4j.xml";
    static final Logger LOGGER = Logger.getLogger(ControlAppliances.class);
    static {
        new DOMConfigurator().doConfigure(LOG_CONFIGURATIONS_PATH, LogManager.getLoggerRepository());
    }

    public void switchOnAllAppliance (Iterator<AbstractElectricalAppliance> iterator) {
        while (iterator.hasNext()) {
            iterator.next().switchOn();
        }
        LOGGER.info("All appliances switch on");
    }

    public void switchOffAllAppliance (Iterator<AbstractElectricalAppliance> iterator) {
        while (iterator.hasNext()) {
            iterator.next().switchOff();
        }
        LOGGER.info("All appliances switch off");
    }

    public int calculatePower(Iterator<AbstractElectricalAppliance> iterator) {
        int sumPower = 0;
        AbstractElectricalAppliance electricalAppliance;
        while (iterator.hasNext()){
            electricalAppliance = iterator.next();
            if (electricalAppliance.isSwitchedOn()) {
                sumPower+=electricalAppliance.getPower();
            }
        }
        return sumPower;
    }

    public AbstractElectricalAppliance findElectricalAppliance (Iterator<AbstractElectricalAppliance> iterator, String typeAppliance, int power) {
        AbstractElectricalAppliance electricalAppliance;
        while (iterator.hasNext()) {
            electricalAppliance = iterator.next();
            if (TypesOfAppliances.valueOf(typeAppliance.toUpperCase()) == electricalAppliance.getTypeOfAppliance() && electricalAppliance.getPower() == power) {
                return electricalAppliance;
            }
        }
        return null;    // don't know;
    }

    public void sortElectricalAppliances (ArrayList<AbstractElectricalAppliance> electricalAppliances) {
        Comparator<AbstractElectricalAppliance> comparator = (a, b) -> a.getPower() - b.getPower();
        electricalAppliances.sort(comparator);
    }
}
