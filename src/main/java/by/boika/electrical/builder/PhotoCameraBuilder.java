package by.boika.electrical.builder;

import by.boika.electrical.model.AbstractElectricalAppliance;
import by.boika.electrical.model.PhotoCamera;
import by.boika.electrical.model.TypesOfAppliances;
import by.boika.electrical.model.TypesOfBatteries;

public class PhotoCameraBuilder extends AbstractPortableBuilder {

    private PhotoCamera photoCamera = new PhotoCamera();

    public AbstractElectricalAppliance buildResolution(int resolution) {
        return photoCamera.setResolution(resolution);
    }

    @Override
    public AbstractElectricalAppliance buildCountOfBatteries(int countOfBatteries) {
        return photoCamera.setCountOfBatteries(countOfBatteries);
    }

    @Override
    public AbstractElectricalAppliance buildTypeOfBattery(TypesOfBatteries type) {
        return photoCamera.setTypeOfBattery(type);
    }

    @Override
    public AbstractElectricalAppliance buildTypeOfAppliance(TypesOfAppliances type) {
        return photoCamera.setTypeOfAppliance(type);
    }

    @Override
    public AbstractElectricalAppliance buildModel(String model) {
        return photoCamera.setModel(model);
    }

    @Override
    public AbstractElectricalAppliance buildProducer(String producer) {
        return photoCamera.setProducer(producer);
    }

    @Override
    public AbstractElectricalAppliance buildVoltage(int voltage) {
        return photoCamera.setVoltage(voltage);
    }

    @Override
    public AbstractElectricalAppliance buildPower(int power) {
        return photoCamera.setPower(power);
    }

    @Override
    public AbstractElectricalAppliance buildID(int id) {
        return photoCamera.setID(id);
    }

    @Override
    public AbstractElectricalAppliance getAppliance() {
        return photoCamera;
    }

}
