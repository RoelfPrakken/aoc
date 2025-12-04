package com.aoc.aoc2025.days;

import com.aoc.Day;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Day05 extends Day {

    public Day05() {
        super(5, 2025);
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
        Day05 day = new Day05();
        day.runExample();
        day.run();
    }
}
