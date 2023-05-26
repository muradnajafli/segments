package com.epam.rd.autotasks.segments;

import static java.lang.Math.abs;
import static java.lang.Math.sqrt;
import static java.lang.StrictMath.pow;
class Segment {
    private Point start;
    private Point end;

    public Segment(Point start, Point end) {
        this.start = start;
        this.end = end;

        if (start.getX() == end.getX() && start.getY() == end.getY()) {
            throw new IllegalArgumentException("Segment is degenerate");
        }
    }

    public double length() {
        double deltaX = end.getX() - start.getX();
        double deltaY = end.getY() - start.getY();

        return Math.sqrt(deltaX * deltaX + deltaY * deltaY);
    }

    public Point middle() {
        double midX = (start.getX() + end.getX()) / 2;
        double midY = (start.getY() + end.getY()) / 2;

        return new Point(midX, midY);
    }

    public Point intersection(Segment another) {
        double x1 = start.getX();
        double y1 = start.getY();
        double x2 = end.getX();
        double y2 = end.getY();

        double x3 = another.start.getX();
        double y3 = another.start.getY();
        double x4 = another.end.getX();
        double y4 = another.end.getY();

        double denominator = (x1 - x2) * (y3 - y4) - (y1 - y2) * (x3 - x4);

        if (denominator == 0) {
            return null;
        }

        double intersectionX = ((x1 * y2 - y1 * x2) * (x3 - x4) - (x1 - x2) * (x3 * y4 - y3 * x4)) / denominator;
        double intersectionY = ((x1 * y2 - y1 * x2) * (y3 - y4) - (y1 - y2) * (x3 * y4 - y3 * x4)) / denominator;

        Point intersectionPoint = new Point(intersectionX, intersectionY);

        if (isPointOnSegment(intersectionPoint) && another.isPointOnSegment(intersectionPoint)) {
            return intersectionPoint;
        } else {
            return null;
        }
    }

    private boolean isPointOnSegment(Point point) {
        double minX = Math.min(start.getX(), end.getX());
        double maxX = Math.max(start.getX(), end.getX());

        double minY = Math.min(start.getY(), end.getY());
        double maxY = Math.max(start.getY(), end.getY());

        double pointX = point.getX();
        double pointY = point.getY();

        return (pointX >= minX && pointY <= maxX) && (pointY >= minY && pointY <= maxY);
    }
}