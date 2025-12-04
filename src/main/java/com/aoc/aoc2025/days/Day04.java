package com.aoc.aoc2025.days;

import java.util.ArrayList;
import java.util.List;

import com.aoc.Day;
import com.aoc.aoc2025.util.Grid;
import com.aoc.aoc2025.util.Point;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Day04 extends Day {

    public Day04() {
        super(4, 2025);
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

        Grid grid = new Grid(input);
        long count = 0;
        for (int y = 0; y < grid.getHeight(); y++) {
            for (int x = 0; x < grid.getWidth(); x++) {
                if (grid.get(x, y) == '@') {
                    if (grid.countOccupiedNeighbours(x, y) < 4) {
                        count++;
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

        Grid grid = new Grid(input);
        int count = 0;
        List<Point> pointsToRemove = new ArrayList<>();
        do {
            pointsToRemove.clear();
            for (int y = 0; y < grid.getHeight(); y++) {
                for (int x = 0; x < grid.getWidth(); x++) {
                    if (grid.get(x, y) == '@') {
                        if (grid.countOccupiedNeighbours(x, y) < 4) {
                            pointsToRemove.add(new Point(x, y));
                            count++;
                        }
                    }
                }
            }
            for (Point point : pointsToRemove) {
                grid.removeAt(point.getX(), point.getY());
            }
        } while (!pointsToRemove.isEmpty());
        return count;
    }

    public static void main(String[] args) {
        Day04 day = new Day04();
        day.runExample();
        day.run();
    }
}
