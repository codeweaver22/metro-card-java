package org.metrocard.entity;

public enum PassengerType {
    ADULT(200),
    SENIOR_CITIZEN(100),
    KID(50);

    private int fare;

    PassengerType(int fare) {
        this.fare = fare;
    }

    public int getFare() {
        return this.fare;
    }

    public static PassengerType from(String value) {
        return PassengerType.valueOf(value.toUpperCase());
    }
}
