package by.boika.electrical.builder;

import by.boika.electrical.model.AbstractElectricalAppliance;
import by.boika.electrical.model.Hairdryer;
import by.boika.electrical.model.TypesOfAppliances;

public class HairdryerBuilder extends AbstractLocalBuilder {

    private Hairdryer hairdryer = new Hairdryer();

    public AbstractElectricalAppliance buildCountOfModes (int countOfModes) {
        return hairdryer.setCountOfModes(countOfModes);
    }

    @Override
    public AbstractElectricalAppliance buildCountOfPhase(int countOfPhase) {
        return hairdryer.setCountOfPhase(countOfPhase);
    }

    @Override
    public AbstractElectricalAppliance buildTypeOfAppliance(TypesOfAppliances type) {
        return hairdryer.setTypeOfAppliance(type);
    }

    @Override
    public AbstractElectricalAppliance buildModel(String model) {
        return hairdryer.setModel(model);
    }

    @Override
    public AbstractElectricalAppliance buildProducer(String producer) {
        return hairdryer.setProducer(producer);
    }

    @Override
    public AbstractElectricalAppliance buildVoltage(int voltage) {
        return hairdryer.setVoltage(voltage);
    }

    @Override
    public AbstractElectricalAppliance buildPower(int power) {
        return hairdryer.setPower(power);
    }

    @Override
    public AbstractElectricalAppliance buildID(int id) {
        return hairdryer.setID(id);
    }

    @Override
    public AbstractElectricalAppliance getAppliance() {
        return hairdryer;
    }
}
