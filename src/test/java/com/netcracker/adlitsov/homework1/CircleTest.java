package com.netcracker.adlitsov.homework1;

import org.junit.jupiter.api.RepeatedTest;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class CircleTest {
    public static final double MAX_VAL = 1000;

    @RepeatedTest(10)
    void areaShouldUseCorrectFormula() {
        Random r = new Random();
        double radius = r.nextDouble() * MAX_VAL;
        Circle circ = new Circle(radius);

        double expectedArea = Math.PI * Math.pow(radius, 2);

        double delta = 1e-5d;
        assertEquals(expectedArea, circ.getArea(), delta, circ + " should have area = " + expectedArea);
    }

}
