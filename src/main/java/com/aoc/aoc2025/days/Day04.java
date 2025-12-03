package com.aoc.aoc2025.days;

import com.aoc.Day;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Day04 extends Day {

    public Day04() {
        super(4, 2025);
    }

    @Override
    public boolean shouldWarmup() {
        return false;
    }

    @Override
    public Object part1(java.util.List<String> input) {
        if (input == null || input.isEmpty()) {
            return "No input";
        }

        return null;
    }

    @Override
    public Object part2(java.util.List<String> input) {
        if (input == null || input.isEmpty()) {
            return "No input";
        }

        return null;
    }

    public static void main(String[] args) {
        Day04 day = new Day04();
        day.runExample();
        day.run();
    }
}
