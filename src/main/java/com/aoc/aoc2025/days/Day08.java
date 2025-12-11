package com.aoc.aoc2025.days;

import java.util.ArrayList;
import java.util.List;
import com.aoc.Day;
import com.aoc.aoc2025.util.Connection;
import com.aoc.aoc2025.util.Junction;
import com.aoc.aoc2025.util.UnionFind;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Day08 extends Day {

    private int limit = 0;

    public Day08() {
        super(8, 2025);
    }

    public void runExample(int limit) {
        this.limit = limit;
        super.runExample();
    }

    public void run(int limit) {
        this.limit = limit;
        super.run();
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

        List<Junction> junctions = new ArrayList<>();
        for (String line : input) {
            Junction junction = new Junction(line);
            junctions.add(junction);
        }

        return calculateLargestCircuitsProduct(junctions, limit);
    }

    private long calculateLargestCircuitsProduct(List<Junction> junctions, int n) {
        UnionFind<Junction> uf = new UnionFind<>(junctions);
        List<Connection> sortedConnections = getSortedConnections(junctions);

        int limit = Math.min(n, sortedConnections.size());
        for (int i = 0; i < limit; i++) {
            Connection conn = sortedConnections.get(i);
            uf.union(conn.getStart(), conn.getEnd());
        }

        return uf.getComponentSizes().stream()
                .sorted()
                .skip(Math.max(0, uf.getComponentSizes().size() - 3))
                .reduce((a, b) -> a * b)
                .orElse(0);
    }

    @Override
    public Object part2(List<String> input) {
        if (input == null || input.isEmpty()) {
            return "No input";
        }

        List<Junction> junctions = new ArrayList<>();
        for (String line : input) {
            Junction junction = new Junction(line);
            junctions.add(junction);
        }

        UnionFind<Junction> uf = new UnionFind<>(junctions);
        List<Connection> sortedConnections = getSortedConnections(junctions);

        for (Connection conn : sortedConnections) {
            if (uf.union(conn.getStart(), conn.getEnd())) {
                if (uf.sets() == 1) {
                    return ((long) conn.getStart().getX()) * ((long) conn.getEnd().getX());
                }
            }
        }

        return "No single circuit found";
    }

    private List<Connection> getSortedConnections(List<Junction> junctions) {
        List<Connection> connections = new ArrayList<>();
        for (int i = 0; i < junctions.size(); i++) {
            for (int j = i + 1; j < junctions.size(); j++) {
                Junction j1 = junctions.get(i);
                Junction j2 = junctions.get(j);
                double distance = j1.straightLineDistance(j2);
                connections.add(new Connection(j1, j2, distance));
            }
        }
        connections.sort(null);
        return connections;
    }

    public static void main(String[] args) {
        Day08 day = new Day08();
        day.runExample(10);
        day.run(1000);
    }
}
