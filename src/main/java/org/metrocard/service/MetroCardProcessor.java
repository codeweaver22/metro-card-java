package org.metrocard.service;

import org.metrocard.entity.MetroSummary;
import org.metrocard.entity.PassengerType;
import org.metrocard.entity.Station;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class MetroCardProcessor {
    private final MetroSummary metroSummary = new MetroSummary();
    private final BalanceService balanceService = new BalanceService(metroSummary);
    private final OutputService outputService = new OutputService();

    public void process(String filePath) {
        try (Stream<String> lines = Files.lines(Paths.get(filePath))) {
            lines.forEach(this::executeCommand);
        } catch (IOException ex) {
            throw new RuntimeException("Error reading file", ex);
        }
    }

    private void executeCommand(String line) {
        String[] parts = line.trim().split(" ");

        String command = parts[0];

        switch (command.toUpperCase()) {
            //BALANCE MC1 600
            case "BALANCE": {
                String cardId = parts[1];
                int balance = Integer.parseInt(parts[2]);
                balanceService.addCard(cardId, balance);
                break;
            }
            // CHECK_IN MC1 ADULT CENTRAL
            case "CHECK_IN": {
                String cardId = parts[1];
                String strPassengerType = parts[2];
                PassengerType type = PassengerType.from(strPassengerType);
                String strStation = parts[3];
                Station station = Station.from(strStation);
                balanceService.processCheckIn(cardId, type, station);
                break;
            }
            case "PRINT_SUMMARY": {
                outputService.processOutput(metroSummary);
                break;
            }
            default: {
                throw new IllegalArgumentException("Unknown command: " + command);
            }
        }
    }
}
