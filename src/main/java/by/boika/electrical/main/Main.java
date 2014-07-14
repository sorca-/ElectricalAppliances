package by.boika.electrical.main;

import by.boika.electrical.exceptions.LogicalException;
import by.boika.electrical.functionality.ControlAppliances;
import by.boika.electrical.model.AbstractElectricalAppliance;
import by.boika.electrical.model.Home;
import by.boika.electrical.parsers.dom.DOMParserReader;
import by.boika.electrical.parsers.sax.SAXParserReader;
import by.boika.electrical.parsers.stax.StAXParser;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.Iterator;

public class Main {
    private static final String LOG_CONFIGURATIONS_PATH = "src\\main\\resources\\log4j.xml";
    static final Logger LOGGER = Logger.getLogger(Main.class);
    private static final String PATH = "src\\main\\resources\\electricalAppliances.xml";
    static {
        new DOMConfigurator().doConfigure(LOG_CONFIGURATIONS_PATH, LogManager.getLoggerRepository());
    }

    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException {

        Home home = new Home();
        DOMParserReader domParserReader = new DOMParserReader();
        SAXParserReader saxParserReader = new SAXParserReader();
        StAXParser staxParser = new StAXParser();

        //DOM
//        try {
//            home.setElectricalAppliances(domParserReader.parseElectricalAppliance(PATH));
//        } catch (LogicalException e) {
//            LOGGER.error(e);
//        }

        //SAX
        //home.setElectricalAppliances(saxParserReader.parseElectricalAppliance(PATH));

        //StAX
        home.setElectricalAppliances(staxParser.parse(PATH));

//        Iterator<AbstractElectricalAppliance> it = home.getIterator();
//        while (it.hasNext()) {
//            LOGGER.info(it.next().getProducer());
//        }

        //check function
        ControlAppliances controlAppliances = new ControlAppliances();
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
