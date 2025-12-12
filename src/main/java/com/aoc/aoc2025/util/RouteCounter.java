package com.aoc.aoc2025.util;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class RouteCounter<T> {

    private final Function<T, Iterable<T>> neighborProvider;

    public RouteCounter(Function<T, Iterable<T>> neighborProvider) {
        this.neighborProvider = neighborProvider;
    }

    public long countRoutes(T start, T end) {
        return countRoutes(start, end, new HashMap<>());
    }

    private long countRoutes(T current, T target, Map<T, Long> memo) {
        if (current.equals(target)) {
            return 1L;
        }
        if (memo.containsKey(current)) {
            return memo.get(current);
        }

        long count = 0;
        for (T neighbor : neighborProvider.apply(current)) {
            count += countRoutes(neighbor, target, memo);
        }

        memo.put(current, count);
        return count;
    }
}
