package com.aoc.aoc2025.util;

import lombok.Getter;

@Getter
public class Point {
    private long x;
    private long y;

    public Point(long x, long y) {
        this.x = x;
        this.y = y;
    }
}
