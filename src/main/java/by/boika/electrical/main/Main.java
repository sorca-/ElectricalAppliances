package by.boika.electrical.main;
import by.boika.electrical.exceptions.LogicalException;
import by.boika.electrical.functionality.ControlAppliances;
import by.boika.electrical.model.AbstractElectricalAppliance;
import by.boika.electrical.parser.DOMParserReader;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
    private static final String LOG_CONFIGURATIONS_PATH = "src\\main\\resources\\log4j.xml";
    static final Logger LOGGER = Logger.getLogger(Main.class);
    private static final String PATH = "src\\main\\resources\\electricalAppliances.xml";
    static {
        new DOMConfigurator().doConfigure(LOG_CONFIGURATIONS_PATH, LogManager.getLoggerRepository());
    }

    public static void main(String[] args) throws ParserConfigurationException, LogicalException {

        ArrayList<AbstractElectricalAppliance> electricalAppliances = new ArrayList<AbstractElectricalAppliance>();
        DOMParserReader domParserReader = new DOMParserReader();
        try {
            domParserReader.parseElectricalAppliance(PATH, electricalAppliances);
        } catch (IOException e) {
            LOGGER.error(e);
        } catch (SAXException e) {
            LOGGER.error(e);
        }

        for (AbstractElectricalAppliance electricalAppliance : electricalAppliances) {
            LOGGER.info(electricalAppliance.toString());
        }


//        ControlAppliances functions = new ControlAppliances(abstractElectricalAppliances);
//        functions.switchOnAllAppliance();
//        LOGGER.info(functions.calculatePower());



    }
}
