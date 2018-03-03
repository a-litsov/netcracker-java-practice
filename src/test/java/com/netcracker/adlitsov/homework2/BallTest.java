package com.netcracker.adlitsov.homework2;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class BallTest {
    public static final double DELTA = 5e-3;
    public static final int MAX_SPEED = 1000;
    public static final int MAX_RADIUS = 1000;
    public static final int MAX_COORD = 1000, MIN_COORD = -1000;
    public static final int MAX_MOVES_COUNT = 100;

    public static Ball randomBall(Random rnd) {
        float startX = rnd.nextInt(MAX_COORD - MIN_COORD + 1) - MIN_COORD;
        float startY = rnd.nextInt(MAX_COORD - MIN_COORD + 1) - MIN_COORD;
        int radius = rnd.nextInt(MAX_RADIUS) + 1;
        int speed = rnd.nextInt(MAX_SPEED + 1);
        int direction = rnd.nextInt(Ball.MAX_DIRECTION - Ball.MIN_DIRECTION + 1) + Ball.MIN_DIRECTION;

        return new Ball(startX, startY, radius, speed, direction);
    }

    @Test
    void shouldThrowExceptionForNegativeAndZeroRadius() {
        assertThrows(IllegalArgumentException.class, () -> new Ball(0, 0, 0, 0, 0));
        assertThrows(IllegalArgumentException.class, () -> new Ball(0, 0, -5, 0, 0));
    }

    @Test
    void shouldThrowExceptionForNegativeSpeed() {
        assertThrows(IllegalArgumentException.class, () -> new Ball(0, 0, 12, -15, 0));
    }

    @Test
    void shouldThrowExceptionIncorrectDirection() {
        assertThrows(IllegalArgumentException.class, () -> new Ball(0, 0, 12, 0, 220));
        assertThrows(IllegalArgumentException.class, () -> new Ball(0, 0, 12, 0, -181));
    }

    @RepeatedTest(10)
    void shouldInitDeltasBySpeedAndDirection() {
        int angle = -30;
        int speed = new Random().nextInt(MAX_SPEED + 1);

        double expXDelta = (Math.sqrt(3) / 2) * speed;
        double expYDelta = 0.5 * speed;

        Ball ball = new Ball(0, 0, 2, speed, angle);
        assertEquals(expXDelta, ball.getXDelta(), DELTA, "xDelta must be " + expXDelta);
        assertEquals(expYDelta, ball.getYDelta(), DELTA, "yDelta must be " + expYDelta);
    }

    @RepeatedTest(100)
    void shouldMoveCorrectly() {
        Random rnd = new Random();

        Ball ball = randomBall(rnd);

        // Take deltas from ball, they were tested in above method
        final float xDelta = ball.getXDelta();
        final float yDelta = ball.getYDelta();

        // Generate random 'moves count' and increase 'DELTA' value
        final int movesCount = rnd.nextInt(MAX_MOVES_COUNT + 1);
        final double CURRENT_DELTA = (movesCount == 0) ? DELTA : movesCount * DELTA;

        // Expected position after moves execution
        float expX = ball.getX() + movesCount * xDelta;
        float expY = ball.getY() + movesCount * yDelta;

        for (int i = 0; i < movesCount; i++) {
            ball.move();
        }

        assertEquals(expX, ball.getX(), CURRENT_DELTA, "x must be " + expX);
        assertEquals(expY, ball.getY(), CURRENT_DELTA, "y must be " + expY);
    }

    @Test
    public void shouldReflectHorizontal() {
        Random rnd = new Random();
        Ball ball = randomBall(rnd);
        float deltaX = ball.getXDelta();
        float deltaY = ball.getYDelta();

        ball.reflectHorizontal();

        assertEquals(-deltaX, ball.getXDelta(), "deltaX must be reflected!");
        assertEquals(deltaY, ball.getYDelta(), "deltaY must not be changed!");
    }

    @Test
    public void shouldReflectVertical() {
        Random rnd = new Random();
        Ball ball = randomBall(rnd);
        float deltaX = ball.getXDelta();
        float deltaY = ball.getYDelta();

        ball.reflectVertical();

        assertEquals(deltaX, ball.getXDelta(), "deltaX must not be changed!");
        assertEquals(-deltaY, ball.getYDelta(), "deltaY must be reflected!");
    }
}
