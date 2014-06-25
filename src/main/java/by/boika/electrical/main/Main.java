package by.boika.electrical.main;

import by.boika.electrical.exceptions.LogicalException;
import by.boika.electrical.functionality.ControlAppliances;
import by.boika.electrical.model.AbstractElectricalAppliance;
import by.boika.electrical.model.Home;
import by.boika.electrical.parser.DOMParserReader;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class Main {
    private static final String LOG_CONFIGURATIONS_PATH = "src\\main\\resources\\log4j.xml";
    static final Logger LOGGER = Logger.getLogger(Main.class);
    private static final String PATH = "src\\main\\resources\\electricalAppliances.xml";
    static {
        new DOMConfigurator().doConfigure(LOG_CONFIGURATIONS_PATH, LogManager.getLoggerRepository());
    }

    public static void main(String[] args) throws ParserConfigurationException, LogicalException, IOException, SAXException {

        Home home = new Home();
        DOMParserReader domParserReader = new DOMParserReader();
        home.setElectricalAppliances(domParserReader.parseElectricalAppliance(PATH));
        ControlAppliances controlAppliances = new ControlAppliances();

        //check function
        controlAppliances.switchOnAllAppliance(home.getIterator());
        LOGGER.info(controlAppliances.calculatePower(home.getIterator()));
        controlAppliances.switchOffAllAppliance(home.getIterator());
        AbstractElectricalAppliance findAppliance = controlAppliances.findElectricalAppliance(home.getIterator(), "Hairdryer", 100);
        if (findAppliance != null) {
            findAppliance.switchOn();
        }
        findAppliance = controlAppliances.findElectricalAppliance(home.getIterator(), "Hairdryer", 200);
        if (findAppliance != null) {
            findAppliance.switchOn();
        }
        LOGGER.info(controlAppliances.calculatePower(home.getIterator()));
    }
}
