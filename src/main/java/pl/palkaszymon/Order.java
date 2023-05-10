package pl.palkaszymon;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalTime;

public class Order implements Comparable<Order>{
    private String orderId;
    private BigDecimal orderValue;
    private Duration pickingTime;
    private LocalTime completeBy;
    private BigDecimal orderValueWeight = BigDecimal.valueOf(4);
    private BigDecimal pickingTimeWeight = BigDecimal.valueOf(-0.75);
    private BigDecimal completeByWeight = BigDecimal.valueOf(-0.5);
    private BigDecimal weightedFactor;
    public Order(String orderId, BigDecimal orderValue, Duration pickingTime, LocalTime completeBy){
        this.orderId = orderId;
        this.orderValue = orderValue;
        this.pickingTime = pickingTime;
        this.completeBy = completeBy;
    }

    public String getId() {
        return orderId;
    }

    public Duration getPickingTime() {
        return pickingTime;
    }

    public BigDecimal getOrderValue() {
        return orderValue;
    }

    public LocalTime getCompleteBy() {
        return completeBy;
    }

    public void setWeightedFactor(BigDecimal weightedFactor) {
        this.weightedFactor = weightedFactor;
    }

    public BigDecimal getWeightedFactor() {
        return weightedFactor;
    }

    public void weightedFactorWithOrderValue(LocalTime pickerTime) {
        setWeightedFactor(orderValue.multiply(orderValueWeight)
                .add((BigDecimal.valueOf(pickingTime.toMinutes()).multiply(pickingTimeWeight)))
                .add(BigDecimal.valueOf(Duration.between(pickerTime, completeBy).toMinutes()).multiply(completeByWeight)));
    }

    @Override
    public int compareTo(Order o) {
        return this.getWeightedFactor().compareTo(o.getWeightedFactor());
    }
}
