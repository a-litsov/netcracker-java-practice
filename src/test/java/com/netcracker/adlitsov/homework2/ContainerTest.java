package com.netcracker.adlitsov.homework2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ContainerTest {
    @Test
    void shouldNotInitByNegativeWidthOrHeight() {
        assertThrows(IllegalArgumentException.class, () -> new Container(0, 0, -1, 12));
        assertThrows(IllegalArgumentException.class, () -> new Container(0, 0, 12, -1));
    }

    @Test
    void shouldNotInitByZeroWidthOrHeight() {
        assertThrows(IllegalArgumentException.class, () -> new Container(0, 0, 0, 12));
        assertThrows(IllegalArgumentException.class, () -> new Container(0, 0, 12, 0));
    }

    @Test
    void shouldNotCollideWhenBallStronglyInside() {
        final int startX = 0, startY = 0;
        final int width = 100, height = 100;
        Container container = new Container(startX, startY, width, height);

        Ball ball = new Ball(50, 30, 20, 0, 0);
        assertFalse(container.collides(ball), ball + " should not collide with " + container);
    }

    @Test
    void shouldCollideWhenBallsSingleBoardExactlyOnContainersSide() {
        final int startX = 0, startY = 0;
        final int width = 100, height = 100;
        Container container = new Container(startX, startY, width, height);

        Ball ball = new Ball(98, 99, 1, 0, 0);
        assertTrue(container.collides(ball), ball + " should collide with " + container);
    }

    @Test
    void shouldCollideWhenBallIntersectsContainer() {
        final int startX = 0, startY = 0;
        final int width = 100, height = 100;
        Container container = new Container(startX, startY, width, height);

        Ball ball = new Ball(50, 50, 55, 0, 0);
        assertTrue(container.collides(ball), ball + " should collide with " + container);
    }
}
