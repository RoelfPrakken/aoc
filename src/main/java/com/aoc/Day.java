package com.aoc;

import java.util.List;

public abstract class Day {
    protected final int dayNumber;
    protected final int year;
    protected final List<String> input;
    protected final List<String> exampleInput;

    static {
        new java.io.File("logs").mkdirs();
    }

    public Day(int dayNumber, int year) {
        this.dayNumber = dayNumber;
        this.year = year;
        this.input = InputReader.readInput(dayNumber, year);
        // Attempt to read example, but don't fail if it doesn't exist (optional)
        List<String> ex = null;
        try {
            ex = InputReader.readExample(dayNumber, year);
        } catch (Exception e) {
            // ignore
        }
        this.exampleInput = ex;
    }

    public abstract Object part1(List<String> input);

    public abstract Object part2(List<String> input);

    public boolean shouldWarmup() {
        return false;
    }

    public void run() {
        if (shouldWarmup() || Boolean.getBoolean("warmup")) {
            System.out.println("Warming up...");
            long warmupStart = System.nanoTime();
            while (System.nanoTime() - warmupStart < 2_000_000_000L) { // 2 seconds warmup
                part1(exampleInput);
                part2(exampleInput);
            }
            System.out.println("Warmup finished.");
        }
        System.out.println("--- Day " + dayNumber + " ---");
        runPart("Part 1", this::part1, input);
        runPart("Part 2", this::part2, input);
    }

    public void runExample() {
        if (exampleInput == null) {
            System.out.println("No example input found for Day " + dayNumber);
            return;
        }
        System.out.println("--- Day " + dayNumber + " (Example) ---");
        runPart("Part 1", this::part1, exampleInput);
        runPart("Part 2", this::part2, exampleInput);
        System.out.println();
    }

    private void runPart(String name, java.util.function.Function<List<String>, Object> partMethod,
            List<String> input) {
        long start = System.nanoTime();
        Object result = partMethod.apply(input);
        long time = System.nanoTime();
        System.out.println(name + ": " + result + " (" + (time - start) / 1_000_000.0 + " ms)");
    }
}
