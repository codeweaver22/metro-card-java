package org.metrocard.service;

import org.junit.jupiter.api.Test;
import org.metrocard.entity.MetroSummary;
import org.metrocard.entity.Station;
import org.metrocard.entity.StationSummary;

import static org.junit.jupiter.api.Assertions.*;

class OutputServiceTest {
    OutputService outputService = new OutputService();

    @Test
    public void generateOutputShouldBothStations() {
        StationSummary stationSummary = new StationSummary();
        stationSummary.addDiscount(100);
        stationSummary.addCollection(200);
        String outputStringCentral = outputService.generateOutput(Station.CENTRAL, stationSummary);
        String outputStringAirport = outputService.generateOutput(Station.AIRPORT, stationSummary);
        assertAll(
                () -> assertTrue(outputStringCentral.contains("TOTAL_COLLECTION CENTRAL")),
                () -> assertTrue(outputStringCentral.contains("200")),
                () -> assertTrue(outputStringCentral.contains("100"))
        );
        assertAll(
                () -> assertTrue(outputStringAirport.contains("TOTAL_COLLECTION AIRPORT")),
                () -> assertTrue(outputStringAirport.contains("200")),
                () -> assertTrue(outputStringAirport.contains("100"))
        );
    }
}