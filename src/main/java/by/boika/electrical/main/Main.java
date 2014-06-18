package by.boika.electrical.main;
import by.boika.electrical.functionality.Functions;
import by.boika.electrical.model.ElectricalAppliance;
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

    public static void main(String[] args) throws ParserConfigurationException {

        ArrayList<ElectricalAppliance> electricalAppliances = new ArrayList<ElectricalAppliance>();
        DOMParserReader domParserReader = new DOMParserReader();
        try {
            domParserReader.parseElectricalAppliance(PATH, electricalAppliances);
        } catch (IOException e) {
            LOGGER.error(e);
        } catch (SAXException e) {
            LOGGER.error(e);
        }


        Functions functions = new Functions(electricalAppliances);
        functions.switchOnAllAppliance();
        LOGGER.info(functions.calculatePower());



    }
}
