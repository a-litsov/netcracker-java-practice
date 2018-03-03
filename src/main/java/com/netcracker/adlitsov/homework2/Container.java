package com.netcracker.adlitsov.homework2;

import java.util.Scanner;

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

    public static void main(String[] args) throws InterruptedException {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter container's start coords:");
        int startX = sc.nextInt();
        int startY = sc.nextInt();
        System.out.print("Enter container's width and height:");
        int width = sc.nextInt();
        int height = sc.nextInt();
        Container container = new Container(startX, startY, width, height);

        System.out.print("\nEnter ball's start coords and radius:");
        float ballStartX = sc.nextFloat();
        float ballStartY = sc.nextFloat();
        int radius = sc.nextInt();
        System.out.print("Enter ball's speed and direction(angle, degrees):");
        int speed = sc.nextInt();
        int angle = sc.nextInt();
        Ball ball = new Ball(ballStartX, ballStartY, radius, speed, angle);

        System.out.print("\nEnter pause after each collision (in millis):");
        int pause = sc.nextInt();
        System.out.println();

        // Ball is bouncing off container's sides
        final int maxBouncesCount = 100;
        int bouncesCount = 0;
        while (bouncesCount <= maxBouncesCount) {
            if (container.collides(ball)) {
                System.out.println("Collision occured: " + ball + ", " + container + ". Reflecting!");
                if (ball.getX() + ball.getRadius() >= container.getX() + container.getWidth() ||
                        ball.getX() - ball.getRadius() <= container.getX()) {
                    ball.reflectHorizontal();
                }
                if (ball.getY() + ball.getRadius() >= container.getY() + container.getHeight() ||
                        ball.getY() - ball.getRadius() <= container.getY()) {
                    ball.reflectVertical();
                }
                bouncesCount++;
                Thread.sleep(pause);
            }
            ball.move();
        }
    }
}
