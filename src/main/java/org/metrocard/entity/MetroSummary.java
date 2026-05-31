package org.metrocard.entity;

import java.util.HashMap;
import java.util.Map;

public class MetroSummary {
    private Map<Station, StationSummary> stationSummaryMap = new HashMap<>();

    public StationSummary getStationSummary(Station station) {
        return stationSummaryMap.computeIfAbsent(station, s -> new StationSummary());
    }
}
