package by.boika.electrical.parsers.SAXParser;

import by.boika.electrical.exceptions.TechnicalException;
import by.boika.electrical.model.AbstractElectricalAppliance;
import org.apache.log4j.Logger;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class SAXParserReader {

    private final static String DEFAULT_APPLIANCES_PATH = "src\\main\\resources\\electricalAppliances.xml";
    private static final Logger LOGGER = Logger.getLogger(SAXParserReader.class);
    private SAXParserFactory saxParserFactory;

    public SAXParserReader() {
        this.saxParserFactory = SAXParserFactory.newInstance();
    }

    public ArrayList<AbstractElectricalAppliance> parseElectricalAppliance(String path) {
        File xmlFile = new File(path.isEmpty() ? DEFAULT_APPLIANCES_PATH : path);
        SAXParserHandler handler = new SAXParserHandler();
        SAXParser saxParser = null;
        try {
            saxParser = saxParserFactory.newSAXParser();
            saxParser.parse(xmlFile, handler);
        } catch (ParserConfigurationException e) {
            LOGGER.error("ParserConfigurationException. " + e);
        } catch (SAXException e) {
            LOGGER.error("SAXException. " + e);
        } catch (IOException e) {
            LOGGER.error("IOException. " + e);
        }
        return handler.getElectricalAppliances();
    }
}
