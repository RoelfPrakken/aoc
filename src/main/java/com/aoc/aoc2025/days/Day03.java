package com.aoc.aoc2025.days;

import com.aoc.Day;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Day03 extends Day {

    public Day03() {
        super(3, 2025);
    }

    @Override
    public boolean shouldWarmup() {
        return true;
    }

    @Override
    public Object part1(java.util.List<String> input) {
        if (input == null || input.isEmpty()) {
            return "No input";
        }

        return input.stream()
                .map(s -> getMaxNDigitJoltage(s, 2))
                .reduce(0L, Long::sum);
    }

    @Override
    public Object part2(java.util.List<String> input) {
        if (input == null || input.isEmpty()) {
            return "No input";
        }

        return input.stream()
                .map(s -> getMaxNDigitJoltage(s, 12))
                .reduce(0L, Long::sum);
    }

    public static void main(String[] args) {
        Day03 day = new Day03();
        day.runExample();
        day.run();
    }

    public long getMaxNDigitJoltage(String input, int count) {
        int len = input.length();
        long result = 0;
        int max = 0;
        int pos = -1;
        int remainingDigits = count;

        while (remainingDigits > 0) {
            remainingDigits--;
            for (int i = pos + 1; i < len - remainingDigits; i++) {
                int joltage = Integer.parseInt(input.substring(i, i + 1));
                if (joltage > max) {
                    max = joltage;
                    pos = i;
                }
            }

            result += Math.pow(10, remainingDigits) * max;
            max = 0;
        }
        log.debug("Max joltage: {}", result);
        return result;
    }

}
