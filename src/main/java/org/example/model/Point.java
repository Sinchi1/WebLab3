package org.example.model;

public class Point {
    private double x;
    private double y;
    private double r;
    private boolean hit;

    public Point(double x, double y, double r, boolean hit) {
        this.x = x;
        this.y = y;
        this.r = r;
        this.hit = hit;
    }

    public void setR(double r) {
        this.r = r;
    }

    public boolean isHit() {
        return hit;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setHit(boolean hit) {
        this.hit = hit;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getR() {
        return r;
    }

    public double getY() {
        return y;
    }

    public double getX() {
        return x;
    }
}
