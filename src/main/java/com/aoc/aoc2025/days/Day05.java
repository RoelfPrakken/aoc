package com.aoc.aoc2025.days;

import java.util.ArrayList;
import java.util.List;

import com.aoc.Day;
import com.aoc.aoc2025.util.Range;
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

        boolean providingRanges = true;
        List<Range> ranges = new ArrayList<>();
        long count = 0;

        for (String line : input) {
            if (line.isEmpty()) {
                providingRanges = false;
                continue;
            }
            if (providingRanges) {
                String[] parts = line.split("-");
                ranges.add(new Range(parts[0], parts[1]));
            } else {
                long ingredient = Long.parseLong(line);
                for (Range range : ranges) {
                    if (range.contains(ingredient)) {
                        count++;
                        break;
                    }
                }
            }
        }
        return count;
    }

    @Override
    public Object part2(java.util.List<String> input) {
        if (input == null || input.isEmpty()) {
            return "No input";
        }

        List<Range> ranges = new ArrayList<>();

        for (String line : input) {
            if (line.isEmpty()) {
                break;
            }

            String[] parts = line.split("-");
            long start = Long.parseLong(parts[0]);
            long end = Long.parseLong(parts[1]);
            ranges.add(new Range(start, end));
        }

        // sort ranges by start value
        ranges.sort((r1, r2) -> Long.compare(r1.getStart(), r2.getStart()));

        // merge ranges
        List<Range> mergedRanges = new ArrayList<>();
        Range current = ranges.get(0);

        for (int i = 1; i < ranges.size(); i++) {
            Range next = ranges.get(i);

            if (next.getStart() <= current.getEnd()) {
                current.setEnd(Math.max(current.getEnd(), next.getEnd()));
            } else {
                mergedRanges.add(current);
                current = next;
            }
        }
        mergedRanges.add(current);

        // sum sizes of merged ranges
        long totalSize = 0;
        for (Range range : mergedRanges) {
            totalSize += range.size();
        }
        return totalSize;
    }

    public static void main(String[] args) {
        Day05 day = new Day05();
        day.runExample();
        day.run();
    }
}
