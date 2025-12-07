package com.aoc.aoc2025.days;

import java.util.List;
import com.aoc.Day;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Day08 extends Day {

    public Day08() {
        super(8, 2025);
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
        Day08 day = new Day08();
        day.runExample();
        day.run();
    }
}
