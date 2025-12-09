package com.aoc.aoc2025.days;

import java.util.List;

import com.aoc.Day;
import com.aoc.aoc2025.util.Point;
import com.aoc.aoc2025.util.Polygon;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Day09 extends Day {

    public Day09() {
        super(9, 2025);
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

        long area = 0;

        for (int i = 0; i < input.size() - 1; i++) {
            long x = Long.parseLong(input.get(i).split(",")[0]);
            long y = Long.parseLong(input.get(i).split(",")[1]);

            for (int j = i; j < input.size(); j++) {
                long x2 = Long.parseLong(input.get(j).split(",")[0]);
                long y2 = Long.parseLong(input.get(j).split(",")[1]);
                area = Math.max(area, (Math.abs(x - x2) + 1) * (Math.abs(y - y2) + 1));
            }
        }
        return area;
    }

    @Override
    public Object part2(List<String> input) {

        Polygon polygon = new Polygon();
        for (String line : input) {
            String[] parts = line.split(",");
            polygon.addPoint(new Point(Long.parseLong(parts[0]), Long.parseLong(parts[1])));
        }

        long area = 0;

        for (int i = 0; i < input.size() - 1; i++) {
            long x = Long.parseLong(input.get(i).split(",")[0]);
            long y = Long.parseLong(input.get(i).split(",")[1]);

            for (int j = i; j < input.size(); j++) {
                long x2 = Long.parseLong(input.get(j).split(",")[0]);
                long y2 = Long.parseLong(input.get(j).split(",")[1]);
                long rectArea = (Math.abs(x - x2) + 1) * (Math.abs(y - y2) + 1);

                if (polygon.containsRectangle(new Point(x, y), new Point(x2, y2))) {
                    area = Math.max(area, rectArea);
                }
            }
        }
        return area;
    }

    public static void main(String[] args) {
        Day09 day = new Day09();
        day.runExample();
        day.run();
    }

}
