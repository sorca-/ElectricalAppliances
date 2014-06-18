package by.boika.electrical.model;

public class LocalElectrical extends ElectricalAppliance {
    private int countOfPhase;

    public LocalElectrical(ApplianceType applianceType, String model, String producer, int power, int voltage) {
        super(applianceType, model, producer, power, voltage);
    }
}
