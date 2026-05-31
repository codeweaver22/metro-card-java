package org.metrocard.service;

import org.junit.jupiter.api.Test;
import org.metrocard.entity.*;

import static org.junit.jupiter.api.Assertions.*;


class BalanceServiceTest {
    MetroSummary metroSummary = new MetroSummary();
    BalanceService balanceService = new BalanceService(metroSummary);

    @Test
    void processCheckInWithSufficientBalance() {
        String cardId = "MC1";
        balanceService.addCard(cardId, 500);

        balanceService.processCheckIn(cardId, PassengerType.ADULT, Station.CENTRAL);


        assertEquals(300, balanceService.getCard(cardId).getBalance());
    }

    @Test
    void processCheckInWithRechargeNeeded() {
        String cardId = "MC1";
        balanceService.addCard(cardId, 100);

        balanceService.processCheckIn(cardId, PassengerType.ADULT, Station.CENTRAL);
        MetroCard card = balanceService.getCard(cardId);

        StationSummary stationSummary = metroSummary.getStationSummary(Station.CENTRAL);

        assertAll(
                () -> assertEquals(0, card.getBalance()),
                () -> assertEquals(202, stationSummary.getTotalCollection())
        );
    }

    @Test
    void processCheckInWithReturnJourney() {
        String cardId = "MC1";
        balanceService.addCard(cardId, 50);
        MetroCard card = balanceService.getCard(cardId);
        card.toggleJourney();

        balanceService.processCheckIn(cardId, PassengerType.SENIOR_CITIZEN, Station.AIRPORT);
        StationSummary stationSummary = metroSummary.getStationSummary(Station.AIRPORT);

        assertAll(
                () -> assertEquals(50, stationSummary.getTotalDiscount()),
                () -> assertEquals(50, stationSummary.getTotalCollection())
        );
    }

    @Test
    void processInvalidCardShouldThrowException() {
        assertThrows(
                IllegalArgumentException.class,
                () -> balanceService.processCheckIn("UNKNOWN", PassengerType.ADULT, Station.CENTRAL)
        );
    }

    @Test
    void shouldToggleReturnJourneyFlag() {
        String cardId = "MC1";
        balanceService.addCard(cardId, 100);
        balanceService.processCheckIn(cardId, PassengerType.KID, Station.CENTRAL);
        assertTrue(balanceService.getCard(cardId).isReturnJourney());
    }
}