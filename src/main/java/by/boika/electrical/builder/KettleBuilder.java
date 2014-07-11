package by.boika.electrical.builder;

import by.boika.electrical.model.AbstractElectricalAppliance;
import by.boika.electrical.model.Kettle;
import by.boika.electrical.model.TypesOfAppliances;

public class KettleBuilder extends AbstractLocalBuilder {
  
   private Kettle kettle = new Kettle();

    public AbstractElectricalAppliance buildBoilTime (int boilTime) {
        return kettle.setBoilTime(boilTime);
    }

    @Override
    public AbstractElectricalAppliance buildCountOfPhase(int countOfPhase) {
        return kettle.setCountOfPhase(countOfPhase);
    }

    @Override
    public AbstractElectricalAppliance buildTypeOfAppliance(TypesOfAppliances type) {
        return kettle.setTypeOfAppliance(type);
    }

    @Override
    public AbstractElectricalAppliance buildModel(String model) {
        return kettle.setModel(model);
    }

    @Override
    public AbstractElectricalAppliance buildProducer(String producer) {
        return kettle.setProducer(producer);
    }

    @Override
    public AbstractElectricalAppliance buildVoltage(int voltage) {
        return kettle.setVoltage(voltage);
    }

    @Override
    public AbstractElectricalAppliance buildPower(int power) {
        return kettle.setPower(power);
    }

    @Override
    public AbstractElectricalAppliance buildID(int id) {
        return kettle.setID(id);
    }

    @Override
    public AbstractElectricalAppliance getAppliance() {
        return kettle;
    }
}
