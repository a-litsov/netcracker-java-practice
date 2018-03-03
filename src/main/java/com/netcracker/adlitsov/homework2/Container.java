package com.netcracker.adlitsov.homework2;

public class Container {
    private int x1, y1;
    private int x2, y2;

    public Container(int x1, int y1, int width, int height) {
        if (width <= 0 || height <= 0) {
            throw new IllegalArgumentException("width and height must be positive values!");
        }

        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x1 + width;
        this.y2 = y1 + height;
    }


    public int getX() {
        return x1;
    }

    public int getY() {
        return y1;
    }

    public int getWidth() {
        return x2 - x1;
    }

    public int getHeight() {
        return y2 - y1;
    }

    public boolean collides(Ball ball) {
        final float x = ball.getX(), y = ball.getY();
        final int radius = ball.getRadius();

        return (x - radius <= x1) || (x + radius >= x2) ||
                (y - radius <= y1) || (y + radius >= y2);
    }

    @Override
    public String toString() {
        return "Container[(" + x1 + "," + y1 + "),(" + x2 + "," + y2 + ")]";
    }
}
