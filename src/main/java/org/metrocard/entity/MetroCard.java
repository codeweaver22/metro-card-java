package org.metrocard.entity;

public class MetroCard {
    private final String cardId;
    private boolean isReturnJourney;
    private int balance;

    public MetroCard(String cardId, int balance) {
        this.cardId = cardId;
        this.balance = balance;
        this.isReturnJourney = false;
    }

    public int getBalance() {
        return this.balance;
    }

    public boolean isReturnJourney() {
        return this.isReturnJourney;
    }

    public void deduct(int amount) {
        this.balance -= amount;
    }

    public void toggleJourney() {
        this.isReturnJourney = !isReturnJourney;
    }

    public void recharge(int amount) {
        this.balance += amount;
    }

}
