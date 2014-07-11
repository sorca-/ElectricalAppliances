package by.boika.electrical.parser;

import by.boika.electrical.exceptions.LogicalException;
import by.boika.electrical.model.*;
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
    private static final Logger LOGGER = Logger.getLogger(DOMParserReader.class);

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

        int id;
        int power;
        int voltage;
        int countOfPhase = 0;
        int countOfBatteries = 0;
        int countOfModes;
        int boilTime;
        int maxVolume;
        int resolution;
        String model;
        String producer;
        TypesOfBatteries typeOfBattery = null;
        TypesOfAppliances typeOfAppliance;

        nListAppliances = doc.getElementsByTagName(APPLIANCE);
        for (int i = 0; i < nListAppliances.getLength(); i++) {

            nNode = nListAppliances.item(i);
            if (nNode.getNodeType() != Node.ELEMENT_NODE) {
                LOGGER.error("Incorrect element node.");
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
            }
            if (eElement.getParentNode().getNodeName().equals(PropertiesOfAppliance.PORTABLE)) {
                countOfBatteries = Integer.parseInt(selectValue(eElement, PropertiesOfAppliance.COUNT_OF_BATTERIES));
                typeOfBattery = TypesOfBatteries.valueOf(selectValue(eElement, PropertiesOfAppliance.TYPE_OF_BATTERY).toUpperCase());
            }
            switch (typeOfAppliance) {
                case HAIRDRYER: {
                    countOfModes = Integer.parseInt(eElement.getAttribute(PropertiesOfAppliance.COUNT_OF_MODES.getValue()));
                    electricalAppliances.add (new Hairdryer(id, typeOfAppliance, model, producer, power, voltage, countOfPhase, countOfModes));
                    break;
                }
                case KETTLE: {
                    boilTime = Integer.parseInt(eElement.getAttribute(PropertiesOfAppliance.BOIL_TIME.getValue()));
                    electricalAppliances.add(new Kettle(id, typeOfAppliance, model, producer, power, voltage, countOfPhase, boilTime));
                    break;
                }
                case MEDIA_CENTER: {
                    maxVolume = Integer.parseInt(eElement.getAttribute(PropertiesOfAppliance.MAX_VOLUME.getValue()));
                    electricalAppliances.add(new MediaCenter(id, typeOfAppliance, model, producer, power, voltage, countOfPhase, maxVolume));
                    break;
                }
                case PHOTO_CAMERA: {
                    resolution = Integer.parseInt(eElement.getAttribute(PropertiesOfAppliance.RESOLUTION.getValue()));
                    electricalAppliances.add(new PhotoCamera(id, typeOfAppliance, model, producer, power, voltage, countOfBatteries, typeOfBattery, resolution));
                    break;
                }
                default: {
                    LOGGER.error("Can't create unknown type.");
                    break;
                }
            }
        }
        return electricalAppliances;
    }

    private static String selectValue(Element element, PropertiesOfAppliance paramName) throws LogicalException {
        Node node = element.getElementsByTagName(paramName.getValue()).item(0);
        if (node != null) {
            return node.getTextContent();
        } else {
            throw new LogicalException();
        }
    }
}
