package org.metrocard.entity;

public enum Station {
    CENTRAL,
    AIRPORT;

    public static Station from(String value) {
        return Station.valueOf(value.toUpperCase());
    }
}
