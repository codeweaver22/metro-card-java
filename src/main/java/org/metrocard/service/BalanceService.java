package org.metrocard.service;

import org.metrocard.entity.*;

import java.util.HashMap;
import java.util.Map;

public class BalanceService {
    private final Map<String, MetroCard> cards = new HashMap<>();
    private final MetroSummary metroSummary;

    private final double serviceFee = 0.02;
    private final double discountRate = 0.5;

    public BalanceService(MetroSummary metroSummary) {
        this.metroSummary = metroSummary;
    }

    public void addCard(String cardId, Integer balance) {
        MetroCard metroCard = new MetroCard(cardId, balance);
        cards.put(cardId, metroCard);
    }

    public MetroCard getCard(String cardId) {
        return cards.getOrDefault(cardId, null);
    }

    public void processCheckIn(String cardId, PassengerType type, Station station) {
        MetroCard card = cards.get(cardId);
        if (card == null) {
            throw new IllegalArgumentException("Card does not exist");
        }
        int fare = type.getFare();
        int discount = 0;
        if (card.isReturnJourney()) {
            discount = calculateDiscount(fare);
            fare -= discount;
        }
        fare = rechargeCard(card, fare);
        card.deduct(fare);
        card.toggleJourney();

        calculateStationSummary(type, station, discount, fare);
    }

    public int rechargeCard(MetroCard card, int fare) {
        int serviceFee = 0;
        int rechargeAmt = 0;
        int cardBalance = card.getBalance();
        if (cardBalance < fare) {
            int diff = fare - cardBalance;
            serviceFee = calculateServiceFee(diff);
            rechargeAmt = (diff) + serviceFee;
            card.recharge(rechargeAmt);
            fare += serviceFee;
        }
        return fare;
    }

    public void calculateStationSummary(PassengerType type, Station station, int discount, int fare) {
        StationSummary stationSummary = metroSummary.getStationSummary(station);
        stationSummary.addDiscount(discount);
        stationSummary.addCollection(fare);
        stationSummary.incrementPassenger(type);
    }

    public int calculateDiscount(int fare) {
        return (int) (discountRate * fare);
    }

    public int calculateServiceFee(int amount) {
        return (int) (serviceFee * amount);
    }


}
