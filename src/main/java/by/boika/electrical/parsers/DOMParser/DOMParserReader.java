package by.boika.electrical.parsers.DOMParser;

import by.boika.electrical.builder.Director;
import by.boika.electrical.constants.TagsXML;
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

    private final static String LOG_CONFIGURATIONS_PATH = "src\\main\\resources\\log4j.xml";
    private final static String DEFAULT_APPLIANCES_PATH = "src\\main\\resources\\electricalAppliances.xml";
    private final static Logger LOGGER = Logger.getLogger(DOMParserReader.class);

    private DocumentBuilderFactory dbFactory;
    private DocumentBuilder dBuilder;

    static {
        new DOMConfigurator().doConfigure(LOG_CONFIGURATIONS_PATH, LogManager.getLoggerRepository());
    }

    public DOMParserReader() throws ParserConfigurationException {
        dbFactory = DocumentBuilderFactory.newInstance();
        dBuilder = dbFactory.newDocumentBuilder();
    }

    public ArrayList<AbstractElectricalAppliance> parseElectricalAppliance(String path) throws SAXException, LogicalException, IOException {
        ArrayList<AbstractElectricalAppliance> electricalAppliances = new ArrayList<>();
        File xmlFile = new File(path.isEmpty() ? DEFAULT_APPLIANCES_PATH : path);
        Document doc = dBuilder.parse(xmlFile);
        doc.getDocumentElement().normalize();

        NodeList nListLocal = doc.getElementsByTagName(TagsXML.LOCAL).item(0).getChildNodes();
        NodeList nListPortable = doc.getElementsByTagName(TagsXML.PORTABLE).item(0).getChildNodes();

        parseNodeList(nListLocal, electricalAppliances);
        parseNodeList(nListPortable, electricalAppliances);
        return electricalAppliances;
    }

    private static String selectValue(Element element, String paramName) throws LogicalException {
        Node node = element.getElementsByTagName(paramName).item(0);
        if (node != null) {
            return node.getTextContent();
        } else {
            throw new LogicalException("Cant read null element. ");
        }
    }

    private void parseNodeList (NodeList nodeList, ArrayList<AbstractElectricalAppliance> electricalAppliances) throws LogicalException {
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

        Node nNode;
        Element eElement;
        for (int i = 0; i < nodeList.getLength(); i++) {

            nNode = nodeList.item(i);
            if (nNode.getNodeType() != Node.ELEMENT_NODE) {
                continue;
            }
            eElement = (Element) nNode;
            typeOfAppliance = TypesOfAppliances.valueOf(eElement.getNodeName().toUpperCase());
            id = Integer.parseInt(eElement.getAttribute(TagsXML.ID));
            power = Integer.parseInt(selectValue(eElement, TagsXML.POWER));
            voltage = Integer.parseInt(selectValue(eElement, TagsXML.VOLTAGE));
            model = selectValue(eElement, TagsXML.MODEL);
            producer = selectValue(eElement, TagsXML.PRODUCER);

            if (eElement.getParentNode().getNodeName().equals(TagsXML.LOCAL)) {
                countOfPhase = Integer.parseInt(selectValue(eElement, TagsXML.COUNT_OF_PHASE));
            }
            if (eElement.getParentNode().getNodeName().equals(TagsXML.PORTABLE)) {
                countOfBatteries = Integer.parseInt(selectValue(eElement, TagsXML.COUNT_OF_BATTERIES));
                typeOfBattery = TypesOfBatteries.valueOf(selectValue(eElement, TagsXML.TYPE_OF_BATTERY).toUpperCase());
            }
            switch (typeOfAppliance) {
                case HAIRDRYER: {
                    countOfModes = Integer.parseInt(eElement.getAttribute(TagsXML.COUNT_OF_MODES));
                    electricalAppliances.add (Director.getHairdryer(id, typeOfAppliance, model, producer, power, voltage, countOfPhase, countOfModes));
                    break;
                }
                case KETTLE: {
                    boilTime = Integer.parseInt(eElement.getAttribute(TagsXML.BOIL_TIME));
                    electricalAppliances.add(Director.getKettle(id, typeOfAppliance, model, producer, power, voltage, countOfPhase, boilTime));
                    break;
                }
                case MEDIA_CENTER: {
                    maxVolume = Integer.parseInt(eElement.getAttribute(TagsXML.MAX_VOLUME));
                    electricalAppliances.add(Director.getMediaCenter(id, typeOfAppliance, model, producer, power, voltage, countOfPhase, maxVolume));
                    break;
                }
                case PHOTO_CAMERA: {
                    resolution = Integer.parseInt(eElement.getAttribute(TagsXML.RESOLUTION));
                    electricalAppliances.add(Director.getPhotoCamera(id, typeOfAppliance, model, producer, power, voltage, countOfBatteries, typeOfBattery, resolution));
                    break;
                }
                default: {
                    LOGGER.error("Can't create unknown type.");
                    break;
                }
            }
        }
    }
}
