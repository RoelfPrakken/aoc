package com.aoc.aoc2025.days;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.aoc.Day;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Day07 extends Day {

    public Day07() {
        super(7, 2025);
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
        List<String> workingInput = new ArrayList<>(input);

        int count = 0;
        for (int i = 1; i < workingInput.size(); i++) {
            String line = workingInput.get(i);
            for (int l = 0; l < line.length(); l++) {
                char charAbove = workingInput.get(i - 1).charAt(l);
                char charHere = line.charAt(l);

                if (i == 1 && charAbove == 'S') {
                    line = line.substring(0, l) + '|' + line.substring(l + 1);
                    workingInput.set(i, line);
                }

                if (charHere == '^' && charAbove == '|') {
                    line = line.substring(0, l - 1) + "|^|" + line.substring(l + 2);
                    workingInput.set(i, line);
                    count++;
                }

                if (charHere == '.' && charAbove == '|') {
                    line = line.substring(0, l) + "|" + line.substring(l + 1);
                    workingInput.set(i, line);
                }
            }
            log.info("Line after processing: {}", line);
        }
        return count;
    }

    @Override
    public Object part2(List<String> input) {
        if (input == null || input.isEmpty()) {
            return "No input";
        }

        Map<Integer, Long> currentTimelines = new HashMap<>();

        String firstLine = input.get(0);
        int startCol = firstLine.indexOf('S');
        currentTimelines.put(startCol, 1L);

        for (int r = 0; r < input.size() - 1; r++) {
            Map<Integer, Long> nextTimelines = new HashMap<>();
            String nextLine = input.get(r + 1);

            for (Map.Entry<Integer, Long> entry : currentTimelines.entrySet()) {
                int col = entry.getKey();
                long count = entry.getValue();

                if (col < 0 || col >= nextLine.length()) {
                    continue;
                }

                char nextChar = nextLine.charAt(col);

                if (nextChar == '^') {
                    // Splitter: splits to left (col - 1) and right (col + 1)
                    nextTimelines.put(col - 1, nextTimelines.getOrDefault(col - 1, 0L) + count);
                    nextTimelines.put(col + 1, nextTimelines.getOrDefault(col + 1, 0L) + count);
                } else {
                    // Normal path ('.') or any other char that isn't a splitter, beam goes straight
                    nextTimelines.put(col, nextTimelines.getOrDefault(col, 0L) + count);
                }
            }
            currentTimelines = nextTimelines;
        }

        long totalTimelines = 0;
        for (long count : currentTimelines.values()) {
            totalTimelines += count;
        }

        return totalTimelines;
    }

    public static void main(String[] args) {
        Day07 day = new Day07();
        day.runExample();
        day.run();
    }
}
