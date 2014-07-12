package by.boika.electrical.model;

import java.util.ArrayList;
import java.util.Iterator;

public class Home {
    private ArrayList<AbstractElectricalAppliance> electricalAppliances = new ArrayList<>();

    public Home() {
    }

    public Iterator<AbstractElectricalAppliance> getIterator() {
        return electricalAppliances.iterator();
    }

    public void setElectricalAppliances (ArrayList<AbstractElectricalAppliance> electricalAppliances) {
        this.electricalAppliances = electricalAppliances;
    }

    public void addElectricalAppliance (AbstractElectricalAppliance electricalAppliance) {
        this.electricalAppliances.add(electricalAppliance);
    }
}
