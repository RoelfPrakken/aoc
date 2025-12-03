package com.aoc.aoc2025.util;

import lombok.Getter;

@Getter
public class Range {
    private final long start;
    private final long end;

    public Range(String start, String end) {
        this.start = Long.parseLong(start);
        this.end = Long.parseLong(end);
    }

}
