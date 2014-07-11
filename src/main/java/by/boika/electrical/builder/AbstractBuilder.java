package by.boika.electrical.builder;

import by.boika.electrical.model.AbstractElectricalAppliance;
import by.boika.electrical.model.TypesOfAppliances;

public abstract class AbstractBuilder {

    public abstract AbstractElectricalAppliance buildTypeOfAppliance(TypesOfAppliances type);
    public abstract AbstractElectricalAppliance buildModel(String model);
    public abstract AbstractElectricalAppliance buildProducer(String producer);
    public abstract AbstractElectricalAppliance buildVoltage(int voltage);
    public abstract AbstractElectricalAppliance buildPower(int power);
    public abstract AbstractElectricalAppliance buildID(int id);

    public abstract AbstractElectricalAppliance getAppliance();


}
