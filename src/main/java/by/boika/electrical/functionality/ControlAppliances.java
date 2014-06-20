package by.boika.electrical.functionality;

import by.boika.electrical.model.AbstractElectricalAppliance;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import java.util.ArrayList;

public class ControlAppliances {

    private ArrayList<AbstractElectricalAppliance> abstractElectricalAppliances;
    private static final String LOG_CONFIGURATIONS_PATH = "src\\main\\resources\\log4j.xml";
    static final Logger LOGGER = Logger.getLogger(ControlAppliances.class);
    static {
        new DOMConfigurator().doConfigure(LOG_CONFIGURATIONS_PATH, LogManager.getLoggerRepository());
    }

    public ControlAppliances(ArrayList<AbstractElectricalAppliance> abstractElectricalAppliances) {
        this.abstractElectricalAppliances = abstractElectricalAppliances;
    }

    public void switchOnAllAppliance () {
        for (AbstractElectricalAppliance appliance : abstractElectricalAppliances) {
            appliance.switchOn();
        }
        LOGGER.info("All appliances is work");
    }

    public int calculatePower() {
        int sumPower = 0;
        for (AbstractElectricalAppliance appliance : abstractElectricalAppliances) {
            if (appliance.isSwitchedOn()) {
                sumPower+=appliance.getPower();
            }
        }
        return sumPower;
    }
}
