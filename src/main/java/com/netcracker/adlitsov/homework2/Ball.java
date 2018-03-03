package com.netcracker.adlitsov.homework2;

public class Ball {
    public static final int MAX_DIRECTION = 180, MIN_DIRECTION = -180;

    private float x, y;
    private int radius;
    private float xDelta, yDelta;

    public Ball(float x, float y, int radius, int speed, int direction) {
        validateRadius(radius);
        if (speed < 0) {
            throw new IllegalArgumentException("Ball must have non-negative speed!");
        }
        if (direction > MAX_DIRECTION || direction < MIN_DIRECTION) {
            throw new IllegalArgumentException("Ball's direction must be in [-180*, +180*] segment!");
        }

        this.x = x;
        this.y = y;
        this.radius = radius;
        xDelta = (float) (speed * Math.cos(Math.toRadians(direction)));
        yDelta = (float) (-speed * Math.sin(Math.toRadians(direction)));
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        validateRadius(radius);

        this.radius = radius;
    }

    public float getXDelta() {
        return xDelta;
    }

    public void setXDelta(float xDelta) {
        this.xDelta = xDelta;
    }

    public float getYDelta() {
        return yDelta;
    }

    public void setYDelta(float yDelta) {
        this.yDelta = yDelta;
    }

    public void move() {
        x += xDelta;
        y += yDelta;
    }

    public void reflectHorizontal() {
        xDelta *= -1;
    }

    public void reflectVertical() {
        yDelta *= -1;
    }

    @Override
    public String toString() {
        return "Ball[(" + x + "," + y + "),speed=(" + xDelta + "," + yDelta + ")]";
    }

    private void validateRadius(int radius) {
        if (radius <= 0) {
            throw new IllegalArgumentException("Ball must have a positive radius!");
        }
    }
}
