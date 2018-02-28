package com.edu_netcracker.nn.adlitsov.homework1;

import java.util.Random;

public class MyPoint {
    private int x, y;

    public MyPoint() {

    }

    public MyPoint(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public MyPoint(MyPoint point) {
        if (point == null) {
            throw new IllegalArgumentException("Point must be not-null reference");
        }
        this.x = point.x;
        this.y = point.y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int[] getXY() {
        return new int[]{x, y};
    }

    public void setXY(int x, int y) {
        setX(x);
        setY(y);
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }

    public double distance(int x, int y) {
        return Math.sqrt(Math.pow(x - this.x, 2) + Math.pow(y - this.y, 2));
    }

    public double distance(MyPoint another) {
        return distance(another.x, another.y);
    }

    public double distance() {
        return distance(0, 0);
    }

    public static void main(String[] args) {
        Random rnd = new Random();
        final int MAX_COORD = 10;

        MyPoint p = new MyPoint(rnd.nextInt(MAX_COORD), rnd.nextInt(MAX_COORD));
        System.out.println("My point is: " + p);

        int x = rnd.nextInt(MAX_COORD), y = rnd.nextInt(MAX_COORD);
        System.out.println("Distance between " + p + " and (" + x + ", " + y + ") is: " + p.distance(x, y));

        MyPoint other = new MyPoint(rnd.nextInt(MAX_COORD), rnd.nextInt(MAX_COORD));
        System.out.println("Distance between " + p + " and " + other + " is: " + p.distance(other));

        System.out.println("Distance between " + p + " and (0, 0) is: " + p.distance());
    }
}
