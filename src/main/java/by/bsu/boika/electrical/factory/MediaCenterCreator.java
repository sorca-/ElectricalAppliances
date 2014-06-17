package by.bsu.boika.electrical.factory;

import by.bsu.boika.electrical.model.ElectricalAppliance;
import by.bsu.boika.electrical.model.MediaCenter;

public class MediaCenterCreator extends Creator {
    @Override
    public ElectricalAppliance factoryMethod() {
        return new MediaCenter();
    }
}
