package org.example.util;

public class AreaChecker {
    public  boolean isInside(double x, double y, double r) {
        return (checkCircle(x,y,r) || checkSquare(x,y,r) || checkTriangle(x,y,r));
    }

    // Поправить формулы
    public  boolean checkCircle(double x, double y, double r) {
        return ((Math.pow(x,2.0) + Math.pow(y,2.0) <= Math.pow(r,2)/4) && x>=0 && y<=0);
    }


    public boolean checkTriangle(double x, double y, double r) {
        return ((y<r/2-x) && x >= 0 && y >= 0);
    }


    public boolean checkSquare(double x, double y, double r) {
        return ((-0.5*r <= y && y <= 0) && (-1*r <= x && x <= 0 ));
    }

}
