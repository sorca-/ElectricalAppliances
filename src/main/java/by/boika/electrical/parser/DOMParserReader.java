package by.boika.electrical.parser;

import by.boika.electrical.exceptions.LogicalException;
import by.boika.electrical.factory.AbstractApplianceFactory;
import by.boika.electrical.model.AbstractElectricalAppliance;
import by.boika.electrical.model.TypesOfAppliances;
import by.boika.electrical.model.TypesOfBatteries;
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
    private final String ID = "id";
    private final String APPLIANCE = "appliance";
    private final String TYPE_OF_APPLIANCE = "typeOfAppliance";
    private final String MODEL = "model";
    private final String PRODUCER = "producer";
    private final String POWER = "power";
    private final String VOLTAGE = "voltage";
    private final String COUNT_OF_PHASE = "countOfPhase";
    private final String COUNT_OF_BATTERIES = "countOfBatteries";
    private final String COUNT_OF_MODES = "countOfModes";
    private final String BOIL_TIME = "boilTime";
    private final String MAX_VOLUME = "maxVolume";
    private final String RESOLUTION = "resolution";
    private final String TYPE_OF_BATTERY = "typeOfBattery";

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

    public ArrayList<AbstractElectricalAppliance> parseElectricalAppliance (String path, ArrayList<AbstractElectricalAppliance> electricalAppliances) throws IOException, SAXException, LogicalException {
        xmlFile = new File(path.isEmpty() ? DEFAULT_APPLIANCES_PATH : path);
        doc = dBuilder.parse(xmlFile);
        doc.getDocumentElement().normalize();

        int id;
        int power;
        int voltage;
        int countOfPhase;
        int countOfBatteries;
        int countOfModes;
        int boilTime;
        int maxVolume;
        int resolution;
        String model;
        String producer;
        TypesOfBatteries typeOfBattery = null;
        TypesOfAppliances typeOfAppliance = null;
        Node nNode;
        Element eElement;

        nList = doc.getElementsByTagName(APPLIANCE);
        for (int i = 0; i < nList.getLength(); i++) {

            nNode = nList.item(i);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                eElement = (Element) nNode;
                id = Integer.parseInt(eElement.getAttribute(ID));

                power = selectIntValue(eElement, POWER);
                voltage = selectIntValue(eElement, VOLTAGE);
                countOfPhase = selectIntValue(eElement, COUNT_OF_PHASE);
                countOfBatteries = selectIntValue(eElement, COUNT_OF_BATTERIES);
                countOfModes = selectIntValue(eElement, COUNT_OF_MODES);
                boilTime = selectIntValue(eElement, BOIL_TIME);
                maxVolume = selectIntValue(eElement, MAX_VOLUME);
                resolution = selectIntValue(eElement, RESOLUTION);
                model = selectStringValue(eElement, MODEL);
                producer = selectStringValue(eElement, PRODUCER);
                //replace
                if (!selectStringValue(eElement, TYPE_OF_BATTERY).isEmpty()) {
                    typeOfBattery = TypesOfBatteries.valueOf(selectStringValue(eElement, TYPE_OF_BATTERY).toUpperCase());
                }
                //replace
                if (!selectStringValue(eElement, TYPE_OF_APPLIANCE).isEmpty()) {
                    typeOfAppliance = TypesOfAppliances.valueOf(selectStringValue(eElement, TYPE_OF_APPLIANCE).toUpperCase());
                }
                electricalAppliances.add(AbstractApplianceFactory.createAppliance(id, typeOfAppliance, model, producer, power, voltage, countOfPhase, countOfBatteries, typeOfBattery, countOfModes, boilTime, maxVolume, resolution));

            }
        }
        return electricalAppliances;
    }

    private int selectIntValue (Element element, String paramName) {
        Node node = element.getElementsByTagName(paramName).item(0);
        if (node != null) {
            return Integer.parseInt(node.getTextContent());
        }
        return 0;
    }

    private String selectStringValue (Element element, String paramName) {
        Node node = element.getElementsByTagName(paramName).item(0);
        if (node != null) {
            return node.getTextContent();
        }
        return "";
    }

}
