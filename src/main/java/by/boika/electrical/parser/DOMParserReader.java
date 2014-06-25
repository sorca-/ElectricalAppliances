package by.boika.electrical.parser;

import by.boika.electrical.exceptions.LogicalException;
import by.boika.electrical.factory.AbstractApplianceFactory;
import by.boika.electrical.model.AbstractElectricalAppliance;
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

    //Logger
    private static final String LOG_CONFIGURATIONS_PATH = "src\\main\\resources\\log4j.xml";
    private final String DEFAULT_APPLIANCES_PATH = "src\\main\\resources\\electricalAppliances.xml";
    private final String APPLIANCE = "appliance";
    static final Logger LOGGER = Logger.getLogger(DOMParserReader.class);


    //XMLParser
    private DocumentBuilderFactory dbFactory;
    private DocumentBuilder dBuilder;
    private Document doc;
    private NodeList nList;
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

        Node nNode;
        Element eElement;

        nList = doc.getElementsByTagName(APPLIANCE);
        for (int i = 0; i < nList.getLength(); i++) {

            nNode = nList.item(i);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                eElement = (Element) nNode;
                electricalAppliances.add(AbstractApplianceFactory.createAppliance(eElement));
            }
        }
        return electricalAppliances;
    }
}
