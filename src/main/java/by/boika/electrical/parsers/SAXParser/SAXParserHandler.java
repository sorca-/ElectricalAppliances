package by.boika.electrical.parsers.SAXParser;

import by.boika.electrical.constants.TagsXML;
import by.boika.electrical.model.*;
import org.apache.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;

public class SAXParserHandler extends DefaultHandler{
    private static final Logger LOGGER = Logger.getLogger(SAXParserHandler.class);

    private AbstractElectricalAppliance currentAppliance;
    private String thisElement;
    private ArrayList<AbstractElectricalAppliance> electricalAppliances = new ArrayList<>();

    public SAXParserHandler() {
    }

    public ArrayList<AbstractElectricalAppliance> getElectricalAppliances() {
        return electricalAppliances;
    }

    @Override
    public void startElement(String nameSpace, String localName, String qName, Attributes attributes) throws SAXException {
        boolean flag = true;
        switch (qName) {
            case TagsXML.HAIRDRYER: {
                currentAppliance = new Hairdryer();
                ((Hairdryer)currentAppliance).setCountOfModes(Integer.parseInt(attributes.getValue(1)));
                break;
            }
            case TagsXML.KETTLE: {
                currentAppliance = new Kettle();
                ((Kettle)currentAppliance).setBoilTime(Integer.parseInt(attributes.getValue(1)));
                break;
            }
            case TagsXML.MEDIA_CENTER: {
                currentAppliance = new MediaCenter();
                ((MediaCenter)currentAppliance).setMaxVolume(Integer.parseInt(attributes.getValue(1)));
                break;
            }
            case TagsXML.PHOTO_CAMERA: {
                currentAppliance = new PhotoCamera();
                ((PhotoCamera)currentAppliance).setResolution(Integer.parseInt(attributes.getValue(1)));
                break;
            }
            default: {
                thisElement = qName;
                flag = false;
            }
        }
        if(flag) {
            currentAppliance.setID(Integer.parseInt(attributes.getValue(0)));
            currentAppliance.setTypeOfAppliance(TypesOfAppliances.valueOf(qName.toUpperCase()));
        }
    }

    @Override
    public void characters(char[] ch, int start, int length)  throws SAXException {
        //Don't know.
        if (thisElement.isEmpty()) {
            return;
        }
        String value = new String(ch, start, length).trim();
        switch (thisElement) {
            case "": {
                break;
            }
            case TagsXML.MODEL: {
                currentAppliance.setModel(value);
                break;
            }
            case TagsXML.PRODUCER: {
                currentAppliance.setProducer(value);
                break;
            }
            case TagsXML.VOLTAGE: {
                currentAppliance.setVoltage(Integer.parseInt(value));
                break;
            }
            case TagsXML.POWER: {
                currentAppliance.setPower(Integer.parseInt(value));
                break;
            }
            case TagsXML.COUNT_OF_PHASE: {
                ((AbstractLocalAppliance) currentAppliance).setCountOfPhase(Integer.parseInt(value));
                break;
            }
            case TagsXML.COUNT_OF_BATTERIES: {
                ((AbstractPortableAppliance) currentAppliance).setCountOfBatteries(Integer.parseInt(value));
                break;
            }
            case TagsXML.TYPE_OF_BATTERY: {
                ((AbstractPortableAppliance) currentAppliance).setTypeOfBattery(TypesOfBatteries.valueOf(value.toUpperCase()));
                break;
            }
            case TagsXML.COUNT_OF_MODES: {
                ((Hairdryer) currentAppliance).setCountOfModes(Integer.parseInt(value));
                break;
            }
            case TagsXML.BOIL_TIME: {
                ((Kettle) currentAppliance).setBoilTime(Integer.parseInt(value));
                break;
            }
            case TagsXML.MAX_VOLUME: {
                ((MediaCenter) currentAppliance).setMaxVolume(Integer.parseInt(value));
                break;
            }
            case TagsXML.RESOLUTION: {
                ((PhotoCamera) currentAppliance).setResolution(Integer.parseInt(value));
                break;
            }
            case TagsXML.LOCAL :
                break;
            case TagsXML.PORTABLE :
                break;
            case TagsXML.APPLIANCES :
                break;
            default: {
                LOGGER.error("Unknown type in method \"characters\". " + thisElement);
            }
        }
    }
    @Override
    public void endElement(String nameSpace, String localName, String qName) throws SAXException {
        TypesOfAppliances.hasType(qName);
        if (TypesOfAppliances.hasType(qName)) {
            electricalAppliances.add(currentAppliance);
        }
        thisElement = "";
    }
}