package pl.palkaszymon;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;



public class OrderTest {
    LocalTime time = LocalTime.of(9, 0);
    @Test
    void weightedFactorTest() {
        Order order1 = new Order("order-1", BigDecimal.valueOf(40.00), Duration.parse("PT60M"), LocalTime.parse("10:00"));
        Order order2 = new Order("order-2", BigDecimal.valueOf(5.00), Duration.parse("PT15M"), LocalTime.parse("09:15"));
        Order order3 = new Order("order-3", BigDecimal.valueOf(10.00), Duration.parse("PT45M"), LocalTime.parse("09:30"));
        ArrayList<Order> orders = new ArrayList<>();
        orders.add(order1);
        orders.add(order2);
        orders.add(order3);

        orders.forEach(order -> order.weightedFactorWithOrderValue(time));
        Assertions.assertEquals(order1.getWeightedFactor(), new BigDecimal("85.00"));
        Assertions.assertEquals(order2.getWeightedFactor(), new BigDecimal("1.25"));
        Assertions.assertEquals(order3.getWeightedFactor(), new BigDecimal("-8.75"));
    }

    @Test
    void sortingTest() {
        Order order1 = new Order("order-1", BigDecimal.valueOf(40.00), Duration.parse("PT60M"), LocalTime.parse("10:00"));
        Order order2 = new Order("order-2", BigDecimal.valueOf(5.00), Duration.parse("PT15M"), LocalTime.parse("09:15"));
        Order order3 = new Order("order-3", BigDecimal.valueOf(10.00), Duration.parse("PT45M"), LocalTime.parse("09:30"));
        ArrayList<Order> orders = new ArrayList<>();
        orders.add(order1);
        orders.add(order2);
        orders.add(order3);
        orders.forEach(order -> order.weightedFactorWithOrderValue(time));
        Collections.sort(orders, Collections.reverseOrder());
        Assertions.assertEquals(orders.get(0).getWeightedFactor(), new BigDecimal("85.00"));
        Assertions.assertEquals(orders.get(2).getWeightedFactor(), new BigDecimal("-8.75"));
    }
}
