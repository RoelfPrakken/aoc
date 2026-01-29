package com.aoc.aoc2025.util;

import java.util.*;

public class Shape {
    private final Set<Point> points;

    public Shape(List<String> lines) {
        this.points = new HashSet<>();
        for (int r = 0; r < lines.size(); r++) {
            String line = lines.get(r);
            for (int c = 0; c < line.length(); c++) {
                if (line.charAt(c) == '#') {
                    points.add(new Point(r, c));
                }
            }
        }
        normalize();
    }

    private Shape(Set<Point> points) {
        this.points = new HashSet<>(points);
        normalize();
    }

    private void normalize() {
        if (points.isEmpty())
            return;

        int minR = points.stream().mapToInt(p -> p.r).min().orElse(0);
        int minC = points.stream().mapToInt(p -> p.c).min().orElse(0);

        Set<Point> normalized = new HashSet<>();
        for (Point p : points) {
            normalized.add(new Point(p.r - minR, p.c - minC));
        }
        points.clear();
        points.addAll(normalized);
    }

    private List<Shape> cachedOrientations;

    public List<Shape> getAllOrientations() {
        if (cachedOrientations != null) {
            return cachedOrientations;
        }

        Set<Shape> unique = new HashSet<>();

        // Original
        unique.add(this);

        // Rotations
        Shape current = this;
        for (int i = 0; i < 3; i++) {
            current = current.rotate90();
            unique.add(current);
        }

        // Flipped versions
        Shape flipped = this.flip();
        unique.add(flipped);
        current = flipped;
        for (int i = 0; i < 3; i++) {
            current = current.rotate90();
            unique.add(current);
        }

        cachedOrientations = new ArrayList<>(unique);
        return cachedOrientations;
    }

    private Shape rotate90() {
        Set<Point> rotated = new HashSet<>();
        for (Point p : points) {
            rotated.add(new Point(p.c, -p.r));
        }
        return new Shape(rotated);
    }

    private Shape flip() {
        Set<Point> flipped = new HashSet<>();
        for (Point p : points) {
            flipped.add(new Point(p.r, -p.c));
        }
        return new Shape(flipped);
    }

    public boolean canPlaceAt(int row, int col, int width, int height, boolean[][] grid) {
        for (Point p : points) {
            int r = row + p.r;
            int c = col + p.c;
            if (r < 0 || r >= height || c < 0 || c >= width || grid[r][c]) {
                return false;
            }
        }
        return true;
    }

    public void placeAt(int row, int col, boolean[][] grid) {
        for (Point p : points) {
            grid[row + p.r][col + p.c] = true;
        }
    }

    public void removeAt(int row, int col, boolean[][] grid) {
        for (Point p : points) {
            grid[row + p.r][col + p.c] = false;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Shape))
            return false;
        Shape shape = (Shape) o;
        return points.equals(shape.points);
    }

    @Override
    public int hashCode() {
        return points.hashCode();
    }

    public boolean fitsInBounds(int width, int height) {
        int maxR = points.stream().mapToInt(p -> p.r).max().orElse(0);
        int maxC = points.stream().mapToInt(p -> p.c).max().orElse(0);
        return maxR < height && maxC < width;
    }

    public int getSize() {
        return points.size();
    }

    public static class Point {
        final int r, c;

        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o)
                return true;
            if (!(o instanceof Point))
                return false;
            Point point = (Point) o;
            return r == point.r && c == point.c;
        }

        @Override
        public int hashCode() {
            return Objects.hash(r, c);
        }
    }
}
