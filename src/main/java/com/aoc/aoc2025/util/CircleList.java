package com.aoc.aoc2025.util;

import lombok.extern.slf4j.Slf4j;
import java.util.ArrayList;
import java.util.Collection;

@Slf4j
public class CircleList<T> extends ArrayList<T> {

    public CircleList() {
        super();
    }

    public CircleList(Collection<? extends T> c) {
        super(c);
    }

    public CircleList(int initialCapacity) {
        super(initialCapacity);
    }

    @Override
    public T get(int index) {
        if (isEmpty()) {
            throw new IndexOutOfBoundsException("List is empty");
        }
        return super.get(calculateCircularIndex(index));
    }

    @Override
    public T set(int index, T element) {
        if (isEmpty()) {
            throw new IndexOutOfBoundsException("List is empty");
        }
        return super.set(calculateCircularIndex(index), element);
    }

    public int calculateCircularIndex(int index) {
        int size = size();
        // Java's % operator can return negative values for negative operands.
        // ((index % size) + size) % size handles both positive and negative indices
        // correctly.
        return ((index % size) + size) % size;
    }

    public int countZeroes(int start, int end) {
        int size = size();
        int min = Math.min(start, end);
        int max = Math.max(start, end);

        // Count how many multiples of size are strictly between min and max
        // This corresponds to crossing 0 (or any other index % size == 0 point)
        int overflows = Math.floorDiv(max - 1, size) - Math.floorDiv(min, size);

        if ((end + size) % size == 0)
            overflows += 1;
        return overflows;
    }
}
