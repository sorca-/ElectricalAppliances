package by.boika.electrical.model;

public class AbstractLocalAppliance extends AbstractElectricalAppliance {
    private int countOfPhase;

    public AbstractLocalAppliance(int id, TypesOfAppliances typesOfAppliances, String model, String producer, int power, int voltage, int countOfPhase) {
        super(id, typesOfAppliances, model, producer, power, voltage);
        this.countOfPhase = countOfPhase;
    }
}
