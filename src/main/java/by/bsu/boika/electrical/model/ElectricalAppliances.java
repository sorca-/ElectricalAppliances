package by.bsu.boika.electrical.model;

public abstract class ElectricalAppliances {
    private String model;
    private String producer;
    private int power;
    private boolean status;


    public void on (boolean status) {
        this.status = status;
    }
    public void off (boolean status) {
        this.status = status;
    }
}
