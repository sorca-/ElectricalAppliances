package by.boika.electrical.builder;

import by.boika.electrical.model.AbstractElectricalAppliance;
import by.boika.electrical.model.MediaCenter;
import by.boika.electrical.model.TypesOfAppliances;

public class MediaCenterBuilder extends AbstractLocalBuilder {
   
    private MediaCenter mediaCenter = new MediaCenter();

    public AbstractElectricalAppliance buildMaxVolume (int maxVolume) {
        return mediaCenter.setMaxVolume(maxVolume);
    }

    @Override
    public AbstractElectricalAppliance buildCountOfPhase(int countOfPhase) {
        return mediaCenter.setCountOfPhase(countOfPhase);
    }

    @Override
    public AbstractElectricalAppliance buildTypeOfAppliance(TypesOfAppliances type) {
        return mediaCenter.setTypeOfAppliance(type);
    }

    @Override
    public AbstractElectricalAppliance buildModel(String model) {
        return mediaCenter.setModel(model);
    }

    @Override
    public AbstractElectricalAppliance buildProducer(String producer) {
        return mediaCenter.setProducer(producer);
    }

    @Override
    public AbstractElectricalAppliance buildVoltage(int voltage) {
        return mediaCenter.setVoltage(voltage);
    }

    @Override
    public AbstractElectricalAppliance buildPower(int power) {
        return mediaCenter.setPower(power);
    }

    @Override
    public AbstractElectricalAppliance buildID(int id) {
        return mediaCenter.setID(id);
    }

    @Override
    public AbstractElectricalAppliance getAppliance() {
        return mediaCenter;
    }
}
