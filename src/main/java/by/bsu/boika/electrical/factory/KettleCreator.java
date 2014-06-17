package by.bsu.boika.electrical.factory;

import by.bsu.boika.electrical.model.ElectricalAppliance;
import by.bsu.boika.electrical.model.Kettle;

public class KettleCreator extends Creator {
    @Override
    public ElectricalAppliance factoryMethod() {
        return new Kettle();
    }
}
