package by.boika.electrical.builder;

import by.boika.electrical.model.AbstractElectricalAppliance;
import by.boika.electrical.model.TypesOfBatteries;

/**
 * Created by PC on 11.07.2014.
 */
public abstract class AbstractPortableBuilder extends AbstractBuilder {

    public abstract AbstractElectricalAppliance buildCountOfBatteries(int countOfBatteries);
    public abstract AbstractElectricalAppliance buildTypeOfBattery(TypesOfBatteries type);
}
