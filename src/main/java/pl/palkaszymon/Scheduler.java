package pl.palkaszymon;

import java.time.LocalTime;
import java.util.Collections;
import java.util.List;

public class Scheduler {
    public static void schedule(List<Order> orders, List<Picker> pickers, LocalTime startTime, LocalTime endTime) {
        pickers.forEach(picker -> picker.setCurrentFinishTime(startTime));
        orders.forEach(order -> order.weightedFactorWithOrderValue(startTime));
        Collections.sort(orders, Collections.reverseOrder());

        for (Order order : orders) {
            Picker assignedPicker = null;
            LocalTime earliestAvailableTime = endTime;
            for (Picker picker : pickers) {
                LocalTime pickerTime = picker.getCurrentFinishTime();
                LocalTime orderFinishTime = pickerTime.plusMinutes(order.getPickingTime().toMinutes());
                LocalTime completeBy = order.getCompleteBy();
                order.weightedFactorWithOrderValue(pickerTime);
                if (pickerTime.isBefore(completeBy)
                        && pickerTime.isBefore(earliestAvailableTime)
                        && orderFinishTime.isBefore(endTime)
                        && (orderFinishTime.isBefore(completeBy)) || orderFinishTime.equals(completeBy)) {
                    assignedPicker = picker;
                    earliestAvailableTime = pickerTime;
                    break;
                }
            }
            if (assignedPicker != null) {
                System.out.println(assignedPicker.getId() + " " + order.getId() + " " + earliestAvailableTime);
                assignedPicker.setCurrentFinishTime(earliestAvailableTime.plus(order.getPickingTime()));

            }
        }
    }
}