package by.boika.electrical.model;

public class PortableElectrical extends ElectricalAppliance {
    private int countOfBatteries;

    public PortableElectrical(ApplianceType applianceType, String model, String producer, int power, int voltage) {
        super(applianceType, model, producer, power, voltage);
    }
}
