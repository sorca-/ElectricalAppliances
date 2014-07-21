package by.boika.electrical.parsers;
import by.boika.electrical.exceptions.LogicalException;
import by.boika.electrical.model.AbstractElectricalAppliance;
import by.boika.electrical.parsers.dom.DOMParserReader;
import by.boika.electrical.parsers.sax.SAXParserReader;
import by.boika.electrical.parsers.stax.StAXParser;
import org.apache.log4j.Logger;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class ParserFactory {
    private final static Logger LOGGER = Logger.getLogger(ParserFactory.class);
    public static ArrayList<AbstractElectricalAppliance> parse (String parserName, String path) {
        ArrayList<AbstractElectricalAppliance> devicesList = new ArrayList<>();
        final String DOM = "DOM";
        final String STAX  = "STAX";
        final String SAX = "SAX";

        switch (parserName.toUpperCase()) {
            case DOM: {
                LOGGER.info(parserName);
                try {
                    devicesList = new DOMParserReader().parseElectricalAppliance(path);
                } catch (ParserConfigurationException|SAXException|LogicalException |IOException e) {
                    LOGGER.error(e);
                }
                break;
            }
            case SAX: {
                LOGGER.info(parserName);
                devicesList = new SAXParserReader().parseElectricalAppliance(path);
            }
            case STAX: {
                LOGGER.info(parserName);
                try {
                    devicesList = new StAXParser().parse(path);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                break;
            }
            default: {
                LOGGER.info(parserName);
                LOGGER.error("Unknown parser");
                break;
            }
        }
        return devicesList;
    }
}
