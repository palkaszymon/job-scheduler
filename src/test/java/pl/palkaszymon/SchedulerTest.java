package pl.palkaszymon;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalTime;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SchedulerTest {

    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }
    @Test
    void completeByTest() {
        try {
            List<Order> orders = JSONFileParser.getOrdersFromFile("testdata/orders.json");
            List<Picker> pickers = JSONFileParser.getPickersFromFile("testdata/store.json");
            LocalTime[] times = JSONFileParser.getTimesFromFile("testdata/store.json");
            LocalTime startTime = times[0];
            LocalTime endTime = times[1];
            Scheduler.schedule(orders, pickers, startTime, endTime);
        }
        catch (Exception e) {
            LOGGER.log( Level.WARNING, e.toString(), e);
        }
        Assertions.assertEquals("P1 order-2 09:00", outputStreamCaptor.toString()
                .trim());
    }

    @Test
    void anyOrderLengthIsOkTest() {
        try {
            List<Order> orders = JSONFileParser.getOrdersFromFile("testdata/orders.json");
            List<Picker> pickers = JSONFileParser.getPickersFromFile("testdata/store.json");
            LocalTime[] times = JSONFileParser.getTimesFromFile("testdata/store.json");
            LocalTime startTime = times[0];
            LocalTime endTime = times[1];
            Scheduler.schedule(orders, pickers, startTime, endTime);
        }
        catch (Exception e) {
            LOGGER.log( Level.WARNING, e.toString(), e);
        }
        Assertions.assertEquals("P1 order-2 09:00", outputStreamCaptor.toString()
                .trim());
    }

    }

