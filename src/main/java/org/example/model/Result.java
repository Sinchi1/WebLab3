package org.example.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "Result")
public  class Result implements Cloneable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(nullable = false)
    double x;
    @Column(nullable = false)
    double y;
    @Column(nullable = false)
    double r;
    @Column(nullable = false)
    boolean hit;


    @Column(nullable = false)
    String timeNow;

    public Result(){}

    public Result(double x, double y, double r,boolean hit, String timeNow){
        this.x = x;
        this.y = y;
        this.r = r;
        this.hit = hit;
        this.timeNow = timeNow;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getR() {
        return r;
    }

    public boolean isHit() {
        return hit;
    }

    public String getTimeNow() {
        return timeNow;
    }


    @Override
    public String toString() {
        return "Result{" +
                "x=" + x +
                ", y=" + y +
                ", r=" + r +
                ", hit=" + hit +
                ", timeNow='" + timeNow + '\'' +
                '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setR(double r) {
        this.r = r;
    }

    public void setTimeNow(String timeNow) {
        this.timeNow = timeNow;
    }

    public void setHit(boolean hit) {
        this.hit = hit;
    }

    @Override
    public Result clone() {
        try {
            Result clone = (Result) super.clone();
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}