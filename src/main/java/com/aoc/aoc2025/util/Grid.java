package com.aoc.aoc2025.util;

import java.util.List;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Getter
@Slf4j
public class Grid {
    private char[][] grid;
    private int width;
    private int height;

    public Grid(List<String> lines) {
        this.grid = new char[lines.size()][];
        this.width = lines.get(0).length();
        this.height = lines.size();
        for (int i = 0; i < lines.size(); i++) {
            grid[i] = lines.get(i).toCharArray();
        }
    }

    public char get(int x, int y) {
        return grid[y][x];
    }

    public int countOccupiedNeighbours(int x, int y) {
        int count = 0;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (i == 0 && j == 0) {
                    continue;
                }
                int newX = x + i;
                int newY = y + j;
                if (newX < 0 || newX >= width || newY < 0 || newY >= height) {
                    continue;
                }
                if (grid[newY][newX] == '@') {
                    count++;
                }
            }
        }
        return count;
    }

    public void removeAt(int x, int y) {
        grid[y][x] = '.';
    }

}