package by.bsu.boika.electrical.model;

public class Hairdryer extends LocalElectrical {
    private final String BEGIN_TO_BLOW = "begin to blow";
    private final String FINISH_BLOW = "finish blow";

    private void startBlow() {
        LOGGER.info(BEGIN_TO_BLOW);
    }
    private void finishBlow() {
        LOGGER.info(FINISH_BLOW);
    }

    @Override
    public void on() {
        super.on();
        startBlow();
    }

    @Override
    public void off() {
        super.off();
        finishBlow();
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public String toString() {
        return "Это фен";           //change this method
    }
}

