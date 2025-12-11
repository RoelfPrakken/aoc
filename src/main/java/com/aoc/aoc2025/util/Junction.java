package com.aoc.aoc2025.util;

import lombok.Data;

@Data
public class Junction {

    private int x;
    private int y;
    private int z;

    public Junction(String line) {
        String[] parts = line.split(",");
        this.x = Integer.parseInt(parts[0]);
        this.y = Integer.parseInt(parts[1]);
        this.z = Integer.parseInt(parts[2]);
    }

    public double straightLineDistance(Junction other) {
        return Math.sqrt(
                Math.pow(x - other.x, 2) +
                        Math.pow(y - other.y, 2) +
                        Math.pow(z - other.z, 2));
    }
}
