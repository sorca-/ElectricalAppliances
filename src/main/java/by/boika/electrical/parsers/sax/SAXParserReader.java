package by.boika.electrical.parsers.sax;

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
        SAXParser saxParser;
        try {
            saxParser = saxParserFactory.newSAXParser();
            saxParser.parse(xmlFile, handler);
        } catch (ParserConfigurationException e) {
            LOGGER.error(e);
        } catch (SAXException e) {
            LOGGER.error(e);
        } catch (IOException e) {
            LOGGER.error(e);
        }
        return handler.getElectricalAppliances();
    }
}
