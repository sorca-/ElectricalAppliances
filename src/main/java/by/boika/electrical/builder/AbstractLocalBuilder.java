package by.boika.electrical.builder;

import by.boika.electrical.model.AbstractElectricalAppliance;
import by.boika.electrical.model.AbstractLocalAppliance;

/**
 * Created by PC on 11.07.2014.
 */
public abstract class AbstractLocalBuilder extends AbstractBuilder {

    public abstract AbstractElectricalAppliance buildCountOfPhase(int countOfPhase);

}
