package pl.palkaszymon;

import java.time.LocalTime;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());
    public static void main(String[] args) {
        String pathToStoreFile = args[0];
        String pathToOrdersFile = args[1];
        try {
            List<Order> orders = JSONFileParser.getOrdersFromFile(pathToOrdersFile);
            List<Picker> pickers = JSONFileParser.getPickersFromFile(pathToStoreFile);
            LocalTime[] times = JSONFileParser.getTimesFromFile(pathToStoreFile);
            LocalTime startTime = times[0];
            LocalTime endTime = times[1];
            Scheduler.schedule(orders, pickers, startTime, endTime);
        }
        catch (Exception e) {
            LOGGER.log( Level.WARNING, e.toString(), e);
        }
    }
}