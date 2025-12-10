package com.aoc.aoc2025.days;

import java.util.List;

import com.aoc.Day;
import com.aoc.aoc2025.util.Machine;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Day10 extends Day {

    public Day10() {
        super(10, 2025);
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

        long count = 0;
        for (String line : input) {
            count += processMachineForLights(line);
        }
        return count;
    }

    @Override
    public Object part2(List<String> input) {
        if (input == null || input.isEmpty()) {
            return "No input";
        }
        return null;
    }

    public static void main(String[] args) {
        Day10 day = new Day10();
        day.runExample();
        day.run();
    }

    private long processMachineForLights(String line) {

        Machine machine = new Machine(line);
        return machine.solveLights();
    }
}
