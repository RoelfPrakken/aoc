package com.aoc.aoc2025.days;

import java.util.List;

import com.aoc.Day;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Day11 extends Day {

    public Day11() {
        super(11, 2025);
    }

    @Override
    public boolean shouldWarmup() {
        return true;
    }

    @Override
    public Object part1(List<String> input) {
        if (input == null || input.isEmpty()) {
            return "No input";
        }

        return null;
    }

    @Override
    public Object part2(List<String> input) {
        if (input == null || input.isEmpty()) {
            return "No input";
        }

        return null;
    }

    public static void main(String[] args) {
        Day11 day = new Day11();
        day.runExample();
        day.run();
    }

}
