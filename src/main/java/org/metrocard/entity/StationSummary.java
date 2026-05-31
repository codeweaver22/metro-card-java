package org.metrocard.entity;

import java.util.HashMap;
import java.util.Map;

public class StationSummary {
    private int totalCollection = 0;
    private int totalDiscount = 0;
    private Map<PassengerType, Integer> passengerCount = new HashMap<>();

    public void addCollection(int amount) {
        this.totalCollection += amount;
    }

    public void addDiscount(int discount) {
        this.totalDiscount += discount;
    }

    public void incrementPassenger(PassengerType type) {
        passengerCount.merge(type, 1, Integer::sum);
    }

    public int getTotalCollection() {
        return this.totalCollection;
    }

    public int getTotalDiscount() {
        return this.totalDiscount;
    }

    public Map<PassengerType,Integer> getPassengerCount() {
        return passengerCount;
    }
}
