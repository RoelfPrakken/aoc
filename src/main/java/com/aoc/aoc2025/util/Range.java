package com.aoc.aoc2025.util;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Range {
    private long start;
    private long end;

    public Range(String start, String end) {
        this.start = Long.parseLong(start);
        this.end = Long.parseLong(end);
    }

    public Range(long start, long end) {
        this.start = start;
        this.end = end;
    }

    public boolean contains(long value) {
        return value >= start && value <= end;
    }

    public long size() {
        return end - start + 1;
    }
}
