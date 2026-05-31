package org.metrocard.service;

import org.metrocard.entity.MetroSummary;
import org.metrocard.entity.PassengerType;
import org.metrocard.entity.Station;
import org.metrocard.entity.StationSummary;

import java.util.Comparator;
import java.util.Map;

public class OutputService {
    public void processOutput(MetroSummary metroSummary) {
        for (Station station : Station.values()) {
            StationSummary stationSummary = metroSummary.getStationSummary(station);
            String outputString = generateOutput(station, stationSummary);
            System.out.print(outputString);
        }
    }

    public String generateOutput(Station station, StationSummary stationSummary) {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format(
                "TOTAL_COLLECTION %s %d %d%n",
                station.name(),
                stationSummary.getTotalCollection(),
                stationSummary.getTotalDiscount()
        ));
        sb.append("PASSENGER_TYPE_SUMMARY")
                .append(System.lineSeparator());
        Map<PassengerType, Integer> passengerCountMap = stationSummary.getPassengerCount();
        passengerCountMap.entrySet().stream()
                .sorted(Map.Entry.<PassengerType, Integer>comparingByValue(Comparator.reverseOrder())
                        .thenComparing(e -> e.getKey().name())
                )
                .forEach(e -> {
                    sb.append(String.format("%s %d", e.getKey(), e.getValue()))
                            .append(System.lineSeparator());
                });
        return sb.toString();
    }
}
