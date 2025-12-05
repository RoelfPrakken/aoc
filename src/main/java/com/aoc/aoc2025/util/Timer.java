package com.aoc.aoc2025.util;

public class Timer {
    private long startTime;
    private long endTime;

    public void start() {
        startTime = System.nanoTime();
    }

    public void stop() {
        endTime = System.nanoTime();
    }

    public long getDurationMicros() {
        return (endTime - startTime) / 1_000;
    }

    public long getDurationMillis() {
        return (endTime - startTime) / 1_000_000;
    }

    public long getDurationNanos() {
        return endTime - startTime;
    }
}
