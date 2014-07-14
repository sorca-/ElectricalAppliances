package by.boika.electrical.parsers.stax;

import by.boika.electrical.constants.TagsXML;
import by.boika.electrical.model.*;
import org.apache.log4j.Logger;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;

public class StAXParser {

    private static final Logger LOGGER = Logger.getLogger(StAXParser.class);


    private XMLStreamReader reader;
    private XMLInputFactory inputFactory;
    private AbstractElectricalAppliance currentAppliance;
    private String tagContent;
    private final static String DEFAULT_APPLIANCES_PATH = "src\\main\\resources\\electricalAppliances.xml";


    public StAXParser() {
        inputFactory = XMLInputFactory.newInstance();
    }

    public ArrayList<AbstractElectricalAppliance> parse(String path) throws FileNotFoundException {
        ArrayList<AbstractElectricalAppliance> electricalAppliances = new ArrayList<>();
        File xmlFile = new File(path.isEmpty() ? DEFAULT_APPLIANCES_PATH : path);
        InputStream fis = new FileInputStream(xmlFile);

        try {
            reader = inputFactory.createXMLStreamReader(fis);
        } catch (XMLStreamException e) {
            LOGGER.error(e);
        }

        try {
            while (reader.hasNext()) {
                int type = reader.next();

                switch (type) {
                    case XMLStreamConstants.START_ELEMENT: {
                        boolean flag = true;
                        switch (reader.getLocalName()) {
                            case TagsXML.HAIRDRYER: {
                                currentAppliance = new Hairdryer();
                                ((Hairdryer) currentAppliance).setCountOfModes(Integer.parseInt(reader.getAttributeValue(1)));
                                break;
                            }
                            case TagsXML.KETTLE: {
                                currentAppliance = new Kettle();
                                ((Kettle) currentAppliance).setBoilTime(Integer.parseInt(reader.getAttributeValue(1)));
                                break;
                            }
                            case TagsXML.MEDIA_CENTER: {
                                currentAppliance = new MediaCenter();
                                ((MediaCenter) currentAppliance).setMaxVolume(Integer.parseInt(reader.getAttributeValue(1)));
                                break;
                            }
                            case TagsXML.PHOTO_CAMERA: {
                                currentAppliance = new PhotoCamera();
                                ((PhotoCamera) currentAppliance).setResolution(Integer.parseInt(reader.getAttributeValue(1)));
                                break;
                            }
                            default: {
                                flag = false;
                                break;
                            }
                        }
                        if (flag) {
                            currentAppliance.setID(Integer.parseInt(reader.getAttributeValue(0)));
                            currentAppliance.setTypeOfAppliance(TypesOfAppliances.valueOf(reader.getLocalName().toUpperCase()));
                        }
                        break;
                    }
                    case XMLStreamConstants.CHARACTERS: {
                        tagContent = reader.getText().trim();
                        break;
                    }

                    case XMLStreamConstants.END_ELEMENT: {
                        switch (reader.getLocalName()) {

                            case TagsXML.MODEL: {
                                currentAppliance.setModel(tagContent);
                                break;
                            }
                            case TagsXML.PRODUCER: {
                                currentAppliance.setProducer(tagContent);
                                break;
                            }
                            case TagsXML.VOLTAGE: {
                                currentAppliance.setVoltage(Integer.parseInt(tagContent));
                                break;
                            }
                            case TagsXML.POWER: {
                                currentAppliance.setPower(Integer.parseInt(tagContent));
                                break;
                            }
                            case TagsXML.COUNT_OF_PHASE: {
                                ((AbstractLocalAppliance)currentAppliance).setCountOfPhase(Integer.parseInt(tagContent));
                                break;
                            }
                            case TagsXML.COUNT_OF_BATTERIES: {
                                ((AbstractPortableAppliance)currentAppliance).setCountOfBatteries(Integer.parseInt(tagContent));
                                break;
                            }
                            case TagsXML.TYPE_OF_BATTERY: {
                                ((AbstractPortableAppliance)currentAppliance).setTypeOfBattery(TypesOfBatteries.valueOf(tagContent.toUpperCase()));
                                break;
                            }
                            case TagsXML.COUNT_OF_MODES: {
                                ((Hairdryer)currentAppliance).setCountOfModes(Integer.parseInt(tagContent));
                                break;
                            }
                            case TagsXML.BOIL_TIME: {
                                ((Kettle)currentAppliance).setBoilTime(Integer.parseInt(tagContent));
                                break;
                            }
                            case TagsXML.MAX_VOLUME: {
                                ((MediaCenter) currentAppliance).setMaxVolume(Integer.parseInt(tagContent));
                                break;
                            }
                            case TagsXML.RESOLUTION: {
                                ((PhotoCamera) currentAppliance).setResolution(Integer.parseInt(tagContent));
                                break;
                            }
                            case TagsXML.HAIRDRYER:
                            case TagsXML.KETTLE:
                            case TagsXML.MEDIA_CENTER:
                            case TagsXML.PHOTO_CAMERA: {
                                electricalAppliances.add(currentAppliance);
                                break;
                            }
                            case TagsXML.APPLIANCES: {
                                break;
                            }
                            case TagsXML.LOCALS: {
                                break;
                            }
                            case TagsXML.PORTABLES: {
                                break;
                            }
                            default: {
                                LOGGER.error("Unknown element: " + tagContent);
                            }
                        }
                        break;
                    }
                }
            }
        } catch (XMLStreamException e) {
            LOGGER.error(e);
        }
        return electricalAppliances;
    }
}
