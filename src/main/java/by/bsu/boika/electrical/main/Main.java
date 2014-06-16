package by.bsu.boika.electrical.main;
import org.apache.log4j.Logger;

public class Main {
    static final Logger LOGGER = Logger.getLogger(Main.class);

//    static {
//        new DOMConfigurator().doConfigure("log4j.xml", LogManager.getLoggerRepository());
//    }

    public static void main(String[] args) {
        LOGGER.info("Hello!");
    }
}
