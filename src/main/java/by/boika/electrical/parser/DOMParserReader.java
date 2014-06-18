package by.boika.electrical.parser;

import by.boika.electrical.factory.ApplianceFactory;
import by.boika.electrical.model.ElectricalAppliance;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class DOMParserReader {
    private static final String LOG_CONFIGURATIONS_PATH = "src\\main\\resources\\log4j.xml";
    private final String DEFAULT_APPLIANCES_PATH = "src\\main\\resources\\electricalAppliances.xml";
    private final String APPLIANCE = "appliance";
    private final String NAME = "name";
    private final String MODEL = "model";
    private final String PRODUCER = "producer";
    private final String POWER = "power";
    private final String VOLTAGE = "voltage";

    private DocumentBuilderFactory dbFactory;
    private DocumentBuilder dBuilder;
    private Document doc;
    private NodeList nList;
    private File xmlFile;

    static final Logger LOGGER = Logger.getLogger(DOMParserReader.class);

    static {
        new DOMConfigurator().doConfigure(LOG_CONFIGURATIONS_PATH, LogManager.getLoggerRepository());
    }

    public DOMParserReader() throws ParserConfigurationException {
        dbFactory = DocumentBuilderFactory.newInstance();
        dBuilder = dbFactory.newDocumentBuilder();
    }

    public ArrayList<ElectricalAppliance> parseElectricalAppliance (String path, ArrayList<ElectricalAppliance> electricalAppliances) throws IOException, SAXException {
        xmlFile = new File(path.isEmpty() ? DEFAULT_APPLIANCES_PATH : path);
        doc = dBuilder.parse(xmlFile);
        doc.getDocumentElement().normalize();

        String model;
        String producer;
        int power;
        int voltage;
        String applianceType;
        Node nNode;
        Element eElement;

        nList = doc.getElementsByTagName(APPLIANCE);
        for (int i = 0; i < nList.getLength(); i++) {

            nNode = nList.item(i);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                eElement = (Element) nNode;

                applianceType = eElement.getElementsByTagName(NAME).item(0).getTextContent().toUpperCase();
                model = eElement.getElementsByTagName(MODEL).item(0).getTextContent();
                producer = eElement.getElementsByTagName(PRODUCER).item(0).getTextContent();
                power = Integer.parseInt(eElement.getElementsByTagName(POWER).item(0).getTextContent());
                voltage = Integer.parseInt(eElement.getElementsByTagName(VOLTAGE).item(0).getTextContent());

//                LOGGER.info(applianceType + " " + model + " " + producer + " " + power + " " + voltage);
                electricalAppliances.add(ApplianceFactory.createAppliance(applianceType, model, producer, power, voltage));
            }
        }
        return electricalAppliances;
    }
}
