package by.boika.electrical.model;

public class AbstractLocalAppliance extends AbstractElectricalAppliance {
    private int countOfPhase;

    public AbstractLocalAppliance() {
    }

    public int getCountOfPhase() {
        return countOfPhase;
    }

    public AbstractElectricalAppliance setCountOfPhase(int countOfPhase) {
        this.countOfPhase = countOfPhase;
        return this;
    }
}
