package by.boika.electrical.functionality;

import by.boika.electrical.model.ElectricalAppliance;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import java.util.ArrayList;

public class Functions {

    private static final String LOG_CONFIGURATIONS_PATH = "src\\main\\resources\\log4j.xml";
    static final Logger LOGGER = Logger.getLogger(Functions.class);
    static {
        new DOMConfigurator().doConfigure(LOG_CONFIGURATIONS_PATH, LogManager.getLoggerRepository());
    }
    private ArrayList<ElectricalAppliance> electricalAppliances;

    public Functions(ArrayList<ElectricalAppliance> electricalAppliances) {
        this.electricalAppliances = electricalAppliances;
    }

    public void switchOnAllAppliance () {
        for (ElectricalAppliance appliance : electricalAppliances) {
            appliance.switchOn();
        }
        LOGGER.info("All appliances is work");
    }

    public int calculatePower() {
        int sumPower = 0;
        for (ElectricalAppliance appliance : electricalAppliances) {
            if (appliance.isSwitchedOn()) {
                sumPower+=appliance.getPower();
            }
        }
        return sumPower;
    }
}
