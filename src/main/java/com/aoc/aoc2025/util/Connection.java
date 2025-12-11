package com.aoc.aoc2025.util;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Connection implements Comparable<Connection> {
    private Junction start;
    private Junction end;
    private double distance;

    @Override
    public int compareTo(Connection other) {
        return Double.compare(this.distance, other.distance);
    }
}
