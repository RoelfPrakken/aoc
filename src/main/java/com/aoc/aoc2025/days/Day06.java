package com.aoc.aoc2025.days;

import com.aoc.Day;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Day06 extends Day {

    public Day06() {
        super(6, 2025);
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
        Day06 day = new Day06();
        day.runExample();
        day.run();
    }
}
