package by.bsu.boika.electrical.factory;

import by.bsu.boika.electrical.model.ElectricalAppliance;
import by.bsu.boika.electrical.model.Hairdryer;

public class HairdryerCreator extends Creator {
    @Override
    public ElectricalAppliance factoryMethod() {
        return new Hairdryer();
    }
}
