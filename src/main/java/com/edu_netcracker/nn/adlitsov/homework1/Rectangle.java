package com.edu_netcracker.nn.adlitsov.homework1;

import java.util.Random;

public class Rectangle {
    private float length = 1.0f;
    private float width = 1.0f;

    public Rectangle() {

    }

    public Rectangle(float length, float width) {
        setLength(length);
        setWidth(width);
    }

    public float getLength() {
        return length;
    }

    public void setLength(float length) {
        if (length < 0) {
            throw new IllegalArgumentException("length must be non-negative float value");
        }
        this.length = length;
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        if (width < 0) {
            throw new IllegalArgumentException("width must be non-negative float value");
        }
        this.width = width;
    }

    public double getArea() {
        return length * width;
    }

    public double getPerimeter() {
        return 2 * (length + width);
    }

    @Override
    public String toString() {
        return "Rectangle[length=" + length + ", width=" + width + "]";
    }

    public static void main(String[] args) {
        Random rnd = new Random();
        final float MAX_VAL = 10;
        float length = rnd.nextFloat() * MAX_VAL;
        float width = rnd.nextFloat() * MAX_VAL;

        Rectangle rect = new Rectangle(length, width);
        System.out.println(rect);
        System.out.println("It's area: " + rect.getArea());
        System.out.println("It's perimeter: " + rect.getPerimeter());
    }
}
