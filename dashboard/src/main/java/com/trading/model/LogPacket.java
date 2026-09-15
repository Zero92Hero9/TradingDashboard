package com.trading.model;

import java.time.Instant;

public class LogPacket {
    private String id;
    private int timeInSeconds;
    private double amount;
    private final Instant currentTime = Instant.now();

    public String getId() {
        return id;
    }
    public int getTimeInSeconds() {
        return timeInSeconds;
    }
    public double getAmount() {
        return amount;
    }
    public Instant getCurrentTime() {
        return currentTime;
    }

    public void setId(String id) {
        this.id = id;
    }
    public void setTimeInSeconds(int timeInSeconds) {
        this.timeInSeconds = timeInSeconds;
    }
    public void setAmount(double amount) {
        this.amount = amount;
    }
}