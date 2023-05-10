package pl.palkaszymon;

import java.time.LocalTime;
import java.util.*;
public class Picker {
    private String id;
    private LocalTime currentFinishTime;
    private List<Order> orders;

    public Picker(String id) {
        this.id = id;
        currentFinishTime = null;
    }

    public String getId() {
        return id;
    }

    public LocalTime getCurrentFinishTime() {
        return currentFinishTime;
    }

    public void setCurrentFinishTime(LocalTime currentTime) {
        this.currentFinishTime = currentTime;
    }
}
