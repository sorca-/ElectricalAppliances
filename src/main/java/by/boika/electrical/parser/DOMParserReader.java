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
    private final String APPLIANCE = "appliance";
    static final Logger LOGGER = Logger.getLogger(DOMParserReader.class);

    private int id;
    private static int power;
    private static int voltage;
    private static int countOfPhase;
    private static int countOfBatteries;
    private static int countOfModes;
    private static int boilTime;
    private static int maxVolume;
    private static int resolution;
    private static String model;
    private static String producer;
    private static TypesOfBatteries typeOfBattery;
    private static TypesOfAppliances typeOfAppliance;


    //XMLParser
    private DocumentBuilderFactory dbFactory;
    private DocumentBuilder dBuilder;
    private Document doc;
    private File xmlFile;

    static {
        new DOMConfigurator().doConfigure(LOG_CONFIGURATIONS_PATH, LogManager.getLoggerRepository());
    }

    public DOMParserReader() throws ParserConfigurationException {
        dbFactory = DocumentBuilderFactory.newInstance();
        dBuilder = dbFactory.newDocumentBuilder();
    }

    public ArrayList<AbstractElectricalAppliance> parseElectricalAppliance(String path) throws IOException, SAXException, LogicalException {
        ArrayList<AbstractElectricalAppliance> electricalAppliances = new ArrayList<AbstractElectricalAppliance>();
        xmlFile = new File(path.isEmpty() ? DEFAULT_APPLIANCES_PATH : path);
        doc = dBuilder.parse(xmlFile);
        doc.getDocumentElement().normalize();

        NodeList nListAppliances;
        Node nNode;
        Element eElement;

        nListAppliances = doc.getElementsByTagName(APPLIANCE);
        for (int i = 0; i < nListAppliances.getLength(); i++) {


            nNode = nListAppliances.item(i);
            if (nNode.getNodeType() != Node.ELEMENT_NODE) {
                throw new LogicalException();
            }
            eElement = (Element) nNode;
            id = Integer.parseInt(eElement.getAttribute(PropertiesOfAppliance.ID.getValue()));
            power = Integer.parseInt(selectValue(eElement, PropertiesOfAppliance.POWER));
            voltage = Integer.parseInt(selectValue(eElement, PropertiesOfAppliance.VOLTAGE));
            model = selectValue(eElement, PropertiesOfAppliance.MODEL);
            producer = selectValue(eElement, PropertiesOfAppliance.PRODUCER);
            typeOfAppliance = TypesOfAppliances.valueOf(selectValue(eElement, PropertiesOfAppliance.TYPE_OF_APPLIANCE).toUpperCase());

            if (eElement.getParentNode().getNodeName().equals(PropertiesOfAppliance.LOCAL)) {
                countOfPhase = Integer.parseInt(selectValue(eElement, PropertiesOfAppliance.COUNT_OF_PHASE));
                switch (typeOfAppliance) {
                    case HAIRDRYER: {
                        countOfModes = Integer.parseInt(eElement.getAttribute(PropertiesOfAppliance.COUNT_OF_MODES.getValue()));
                    }
                    case KETTLE: {
                        boilTime = Integer.parseInt(eElement.getAttribute(PropertiesOfAppliance.BOIL_TIME.getValue()));
                    }
                    case MEDIA_CENTER: {
                        maxVolume = Integer.parseInt(eElement.getAttribute(PropertiesOfAppliance.MAX_VOLUME.getValue()));
                    }
                    default: {
                        throw new LogicalException();
                    }
                }
            }
            if (eElement.getParentNode().getNodeName().equals(PropertiesOfAppliance.PORTABLE)) {
                countOfBatteries = Integer.parseInt(selectValue(eElement, PropertiesOfAppliance.COUNT_OF_BATTERIES));
                typeOfBattery = TypesOfBatteries.valueOf(selectValue(eElement, PropertiesOfAppliance.COUNT_OF_BATTERIES).toUpperCase());

                if (typeOfAppliance.equals(TypesOfAppliances.PHOTO_CAMERA)) {
                    resolution = Integer.parseInt(eElement.getAttribute(PropertiesOfAppliance.RESOLUTION.getValue()));
                }
            }
               electricalAppliances.add(AbstractApplianceFactory.createAppliance(id, power, voltage, countOfPhase, countOfBatteries, countOfModes, boilTime, maxVolume, resolution, model, producer, typeOfBattery, typeOfAppliance));
        }
        return electricalAppliances;
    }

    private static String selectValue(Element element, PropertiesOfAppliance paramName) throws LogicalException {
        Node node = element.getElementsByTagName(paramName.getValue()).item(0);
        if (node != null) {
            return node.getTextContent();
        }
        throw new LogicalException();
    }
}
