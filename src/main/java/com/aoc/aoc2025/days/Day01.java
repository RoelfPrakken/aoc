package com.aoc.aoc2025.days;

import java.util.concurrent.atomic.AtomicInteger;

import com.aoc.Day;
import com.aoc.aoc2025.util.CircleList;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Day01 extends Day {

    public Day01() {
        super(1, 2025);
    }

    @Override
    public Object part1(java.util.List<String> input) {
        if (input == null || input.isEmpty()) {
            return "No input";
        }
        //
        CircleList<Integer> list = new CircleList<>(100);
        for (int i = 0; i < 100; i++) {
            list.add(i);
        }
        final AtomicInteger index = new AtomicInteger(50);

        return input.stream().map(i -> {
            if (i.startsWith("L")) {
                index.getAndAdd(-Integer.parseInt(i.substring(1)));
            } else {
                index.getAndAdd(Integer.parseInt(i.substring(1)));
            }
            return list.get(index.get());
        })
                .filter(i -> i == 0)
                .count();
    }

    @Override
    public Object part2(java.util.List<String> input) {
        if (input == null || input.isEmpty()) {
            return "No input";
        }

        CircleList<Integer> list = new CircleList<>(100);
        for (int i = 0; i < 100; i++) {
            list.add(i);
        }
        final AtomicInteger index = new AtomicInteger(50);

        return input.stream().mapToInt(i -> {
            int previousIndex = list.calculateCircularIndex(index.get());
            index.set(previousIndex);
            if (i.startsWith("L")) {
                index.getAndAdd(-Integer.parseInt(i.substring(1)));
            } else {
                index.getAndAdd(Integer.parseInt(i.substring(1)));
            }
            return list.countZeroes(previousIndex, index.get());
        })
                .sum();
    }

    public static void main(String[] args) {
        Day01 day = new Day01();
        day.runExample();
        day.run();
    }
}
