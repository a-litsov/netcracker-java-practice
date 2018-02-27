package com.edu_netcracker.nn.adlitsov.homework1;

import java.util.Random;

public class MyTriangle {

    public enum Type {
        EQUILATERAL, ISOSCELES, SCALENE
    }

    private MyPoint v1, v2, v3;

    public MyTriangle(int x1, int y1, int x2, int y2, int x3, int y3) {
        v1 = new MyPoint(x1, y1);
        v2 = new MyPoint(x2, y2);
        v3 = new MyPoint(x3, y3);

        validateTriangle(v1, v2, v3);
    }

    public MyTriangle(MyPoint v1, MyPoint v2, MyPoint v3) {
        this(v1.getX(), v1.getY(), v2.getX(), v2.getY(), v3.getX(), v3.getY());
    }

    private void validateTriangle(MyPoint v1, MyPoint v2, MyPoint v3) {
        if (!isTriangle(v1, v2, v3)) {
            throw new IllegalArgumentException("Triangle does not exist: " +
                    "sum of two sides must be greater than other side");
        }
    }

    public static boolean isTriangle(MyPoint p1, MyPoint p2, MyPoint p3) {
        double length1 = p1.distance(p2);
        double length2 = p2.distance(p3);
        double length3 = p3.distance(p1);

        return length1 + length2 > length3 && length2 + length3 > length1 && length1 + length3 > length2;
    }

    @Override
    public String toString() {
        return "MyTriangle[v1=" + v1 + ", v2=" + v2 + ", v3=" + v3 + "]";
    }

    public double getPerimeter() {
        return v1.distance(v2) + v2.distance(v3) + v3.distance(v1);
    }

    public Type getType() {
        double length1 = v1.distance(v2);
        double length2 = v2.distance(v3);
        double length3 = v3.distance(v1);

        if (length1 == length2 && length2 == length3) {
            return Type.EQUILATERAL;
        } else {
            if (length1 == length2 || length2 == length3 || length1 == length3) {
                return Type.ISOSCELES;
            } else {
                return Type.SCALENE;
            }
        }
    }

    public static void main(String[] args) {
        Random rnd = new Random();
        final int MAX_COORD = 10;

        MyPoint p1 = new MyPoint(rnd.nextInt(MAX_COORD), rnd.nextInt(MAX_COORD));
        MyPoint p2 = new MyPoint(rnd.nextInt(MAX_COORD), rnd.nextInt(MAX_COORD));
        MyPoint p3 = new MyPoint(rnd.nextInt(MAX_COORD), rnd.nextInt(MAX_COORD));

        try {
            MyTriangle triangle = new MyTriangle(p1, p2, p3);
            System.out.println("My triangle is: " + triangle + ", it's type: " + triangle.getType() + ", it's " +
                    "perimeter: " + triangle.getPerimeter());
        } catch (IllegalArgumentException ex) {
            ex.printStackTrace();
        }
    }
}
