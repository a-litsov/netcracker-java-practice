package com.netcracker.adlitsov.homework1;

import org.junit.jupiter.api.RepeatedTest;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class MyPointTest {

    public static final int MAX_COORD = 1000;

    @RepeatedTest(10)
    void distanceShouldUseProperFormula() {
        Random rnd = new Random();
        int x = rnd.nextInt(MAX_COORD);
        int y = rnd.nextInt(MAX_COORD);

        MyPoint p = new MyPoint(rnd.nextInt(MAX_COORD), rnd.nextInt(MAX_COORD));
        double distance = p.distance(x, y);

        double expectedDistance = Math.sqrt(Math.pow(p.getX() - x, 2) + Math.pow(p.getY() - y, 2));

        assertEquals(expectedDistance, distance, "distance must be = " + expectedDistance);
    }
}
