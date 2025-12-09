package com.aoc.aoc2025.util;

import java.util.ArrayList;
import java.util.List;

public class Polygon {

    private final List<Point> points;

    public Polygon(List<Point> points) {
        this.points = points;
    }

    public Polygon() {
        this.points = new ArrayList<>();
    }

    public void addPoint(Point point) {
        points.add(point);
    }

    public boolean contains(Point test) {
        int i;
        int j;
        boolean result = false;
        for (i = 0, j = points.size() - 1; i < points.size(); j = i++) {
            // Check if point is on the boundary
            if (isOnSegment(test, points.get(i), points.get(j))) {
                return true;
            }

            if ((points.get(i).getY() > test.getY()) != (points.get(j).getY() > test.getY())) {
                if (test.getX() < points.get(i).getX()) {
                    result = !result;
                }
            }
        }
        return result;
    }

    public boolean containsRectangle(Point p1, Point p2) {
        long minX = Math.min(p1.getX(), p2.getX());
        long maxX = Math.max(p1.getX(), p2.getX());
        long minY = Math.min(p1.getY(), p2.getY());
        long maxY = Math.max(p1.getY(), p2.getY());

        // first check if all 4 corners are inside or on boundary
        if (!contains(new Point(minX, minY)) ||
                !contains(new Point(maxX, minY)) ||
                !contains(new Point(minX, maxY)) ||
                !contains(new Point(maxX, maxY))) {
            return false;
        }

        // then check for edge intersections
        Point[] rectCorners = {
                new Point(minX, minY), new Point(maxX, minY),
                new Point(maxX, maxY), new Point(minX, maxY)
        };

        for (int k = 0; k < 4; k++) {
            Point r1 = rectCorners[k];
            Point r2 = rectCorners[(k + 1) % 4];

            for (int i = 0; i < points.size(); i++) {
                Point pA = points.get(i);
                Point pB = points.get((i + 1) % points.size());

                if (segmentsStrictlyIntersect(r1, r2, pA, pB)) {
                    return false;
                }
            }
        }

        return true;
    }

    private boolean segmentsStrictlyIntersect(Point a, Point b, Point c, Point d) {
        // only horizontal and vertical lines are supported
        boolean abVert = a.getX() == b.getX();
        boolean cdVert = c.getX() == d.getX();

        if (abVert == cdVert) {
            return false; // parallel lines don't cross
        }

        // one is vertical, one is horizontal
        long vX, vYmin, vYmax;
        long hY, hXmin, hXmax;

        if (abVert) { // ab is vertical, cd is horizontal
            vX = a.getX();
            vYmin = Math.min(a.getY(), b.getY());
            vYmax = Math.max(a.getY(), b.getY());
            hY = c.getY();
            hXmin = Math.min(c.getX(), d.getX());
            hXmax = Math.max(c.getX(), d.getX());
        } else { // ab is horizontal, cd is vertical
            hY = a.getY();
            hXmin = Math.min(a.getX(), b.getX());
            hXmax = Math.max(a.getX(), b.getX());
            vX = c.getX();
            vYmin = Math.min(c.getY(), d.getY());
            vYmax = Math.max(c.getY(), d.getY());
        }

        // intersect if vertical x is strictly inside horizontal x-range
        // AND horizontal y is strictly inside vertical y-range
        return vX > hXmin && vX < hXmax && hY > vYmin && hY < vYmax;
    }

    private boolean isOnSegment(Point p, Point a, Point b) {
        return p.getX() >= Math.min(a.getX(), b.getX()) && p.getX() <= Math.max(a.getX(), b.getX()) &&
                p.getY() >= Math.min(a.getY(), b.getY()) && p.getY() <= Math.max(a.getY(), b.getY());
    }

}
