package com.trading.tracker;

import java.util.ArrayDeque;
import java.util.Deque;

import org.springframework.stereotype.Service;

import com.trading.model.LogPacket;

@Service
public class LogTracker {
    private final Integer WINDOW_THRESHOLD_S = 10;
    private final Deque<LogPacket> window = new ArrayDeque<>();
    private double currentWindowAmt = 0;
    private double maxAmount = 0;

    public double monitor(LogPacket packet) {
        trackStalePackets(packet.getTimeInSeconds());
        window.add(packet);
        currentWindowAmt += packet.getAmount();
        maxAmount = Math.max(currentWindowAmt, maxAmount);

        return maxAmount;        
    }

    private void trackStalePackets(int currentSystemTime) {
        int cutoff = currentSystemTime - WINDOW_THRESHOLD_S;

        System.out.println("cutoff --" + cutoff);

        while (!window.isEmpty() && window.peek().getTimeInSeconds() <= cutoff) {
            LogPacket p = window.poll();
            System.out.println("Evicted" + p.getTimeInSeconds());
            currentWindowAmt -= p.getAmount();
        }       
    }

}