package by.bsu.boika.electrical.factory;

import by.bsu.boika.electrical.model.ElectricalAppliance;
import by.bsu.boika.electrical.model.PhotoCamera;

public class PhotoCameraCreator extends Creator {
    @Override
    public ElectricalAppliance factoryMethod() {
        return new PhotoCamera();
    }
}
