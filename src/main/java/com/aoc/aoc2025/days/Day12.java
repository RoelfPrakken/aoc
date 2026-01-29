package com.aoc.aoc2025.days;

import java.util.ArrayList;
import java.util.List;

import com.aoc.Day;
import com.aoc.aoc2025.util.Shape;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Day12 extends Day {

    public Day12() {
        super(12, 2025);
    }

    @Override
    public boolean shouldWarmup() {
        return false;
    }

    @Override
    public Object part1(List<String> input) {
        if (input == null || input.isEmpty()) {
            return "No input";
        }

        ParsedInput parsed = parseInput(input);
        // System.out.printf("DEBUG: Parsed %d shapes and %d regions%n",
        // parsed.shapes.size(), parsed.regions.size());
        int solvableCount = 0;

        for (int i = 0; i < parsed.regions.size(); i++) {
            Region region = parsed.regions.get(i);
            if (canFitPresents(region, parsed.shapes)) {
                solvableCount++;
            }
        }

        return solvableCount;
    }

    @Override
    public Object part2(List<String> input) {
        if (input == null || input.isEmpty()) {
            return "No input";
        }
        return null;
    }

    private ParsedInput parseInput(List<String> input) {
        List<com.aoc.aoc2025.util.Shape> shapes = new ArrayList<>();
        List<Region> regions = new ArrayList<>();

        int i = 0;
        // Parse shapes
        while (i < input.size()) {
            String line = input.get(i).trim();
            if (line.isEmpty()) {
                i++;
                continue; // Skip empty lines between shapes
            }
            // Check if this is a region line (e.g., "4x4: ...")
            if (line.matches("\\d+x\\d+:.*")) {
                break; // Start of regions
            }
            if (line.contains(":")) {
                i++; // Skip shape index line
                List<String> shapeLines = new ArrayList<>();
                while (i < input.size() && !input.get(i).trim().isEmpty() && !input.get(i).contains(":")) {
                    shapeLines.add(input.get(i));
                    i++;
                }
                if (!shapeLines.isEmpty()) {
                    shapes.add(new com.aoc.aoc2025.util.Shape(shapeLines));
                }
            } else {
                i++;
            }
        }

        // Parse regions
        while (i < input.size()) {
            String line = input.get(i).trim();
            if (line.isEmpty()) {
                i++;
                continue;
            }
            // Remove trailing period
            if (line.endsWith(".")) {
                line = line.substring(0, line.length() - 1).trim();
            }

            String[] parts = line.split(":");
            if (parts.length == 2) {
                String[] dims = parts[0].split("x");
                int width = Integer.parseInt(dims[0]);
                int height = Integer.parseInt(dims[1]);

                String[] quantities = parts[1].trim().split(" +");
                int[] counts = new int[quantities.length];
                for (int j = 0; j < quantities.length; j++) {
                    counts[j] = Integer.parseInt(quantities[j]);
                }
                regions.add(new Region(width, height, counts));
            }
            i++;
        }

        return new ParsedInput(shapes, regions);
    }

    private boolean canFitPresents(Region region, List<Shape> shapes) {
        // OPTIMIZATION 1: Early area check
        int totalShapeArea = 0;

        for (int i = 0; i < region.quantities.length && i < shapes.size(); i++) {
            if (region.quantities[i] > 0) {
                totalShapeArea += region.quantities[i] * shapes.get(i).getSize();
            }
        }

        int regionArea = region.width * region.height;
        if (totalShapeArea > regionArea) {
            return false; // Impossible to fit
        }

        // OPTIMIZATION 2: Pre-calculate valid orientations for each shape in this
        // region
        List<List<Shape>> validOrientationsByShapeIndex = new ArrayList<>();
        for (Shape shape : shapes) {
            List<Shape> valid = new ArrayList<>();
            for (Shape orientation : shape.getAllOrientations()) {
                if (orientation.fitsInBounds(region.width, region.height)) {
                    valid.add(orientation);
                }
            }
            validOrientationsByShapeIndex.add(valid);
        }

        boolean[][] grid = new boolean[region.height][region.width];
        List<Integer> presentsToPlace = new ArrayList<>();

        for (int i = 0; i < region.quantities.length && i < shapes.size(); i++) {
            for (int j = 0; j < region.quantities[i]; j++) {
                presentsToPlace.add(i);
            }
        }

        // OPTIMIZATION 3: Sort by size descending (place larger shapes first - more
        // constrained)
        presentsToPlace.sort((a, b) -> Integer.compare(shapes.get(b).getSize(), shapes.get(a).getSize()));

        return backtrack(grid, presentsToPlace, 0, shapes, validOrientationsByShapeIndex, region.width, region.height);
    }

    private boolean backtrack(boolean[][] grid, List<Integer> presentsToPlace, int index,
            List<Shape> shapes, List<List<Shape>> validOrientationsByShapeIndex, int width, int height) {
        if (index == presentsToPlace.size()) {
            return true;
        }

        int shapeIndex = presentsToPlace.get(index);
        // OPTIMIZATION 3: Use pre-filtered orientations
        List<Shape> validOrientations = validOrientationsByShapeIndex.get(shapeIndex);

        for (Shape orientation : validOrientations) {
            // OPTIMIZATION 4: Try positions more systematically
            for (int r = 0; r < height; r++) {
                for (int c = 0; c < width; c++) {
                    if (orientation.canPlaceAt(r, c, width, height, grid)) {
                        orientation.placeAt(r, c, grid);
                        if (backtrack(grid, presentsToPlace, index + 1, shapes, validOrientationsByShapeIndex, width,
                                height)) {
                            return true;
                        }
                        orientation.removeAt(r, c, grid);
                    }
                }
            }
        }

        return false;
    }

    private static class ParsedInput {
        final List<Shape> shapes;
        final List<Region> regions;

        ParsedInput(List<Shape> shapes, List<Region> regions) {
            this.shapes = shapes;
            this.regions = regions;
        }
    }

    private static class Region {
        final int width, height;
        final int[] quantities;

        Region(int width, int height, int[] quantities) {
            this.width = width;
            this.height = height;
            this.quantities = quantities;
        }
    }

    public static void main(String[] args) {
        Day12 day = new Day12();
        day.runExample();
        day.run();
    }
}
