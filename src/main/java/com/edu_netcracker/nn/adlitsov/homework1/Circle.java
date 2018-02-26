package com.edu_netcracker.nn.adlitsov.homework1;

import java.util.Random;

public class Circle {
    private double radius = 1.0;
    private String color = "red";

    public Circle() {

    }

    public Circle(double radius) {
        validateRadius(radius);
        this.radius = radius;
    }

    public Circle(double radius, String color) {
        this(radius);
        this.color = color;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        validateRadius(radius);
        this.radius = radius;
    }

    private void validateRadius(double radius) {
        if (radius < 0) {
            throw new IllegalArgumentException("radius must be non-negative double value");
        }
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public double getArea() {
        return Math.PI * Math.pow(radius, 2);
    }

    @Override
    public String toString() {
        return "Circle[radius=" + radius + ", color=" + color + "]";
    }

    public static void main(String[] args) {
        Random rnd = new Random();
        final float MAX_VAL = 10;
        float radius = rnd.nextFloat() * MAX_VAL;

        Circle circ = new Circle(radius);
        System.out.println(circ);
        System.out.println("It's area: " + circ.getArea());
    }
}
