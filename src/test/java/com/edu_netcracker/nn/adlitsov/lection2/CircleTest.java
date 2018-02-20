package com.edu_netcracker.nn.adlitsov.lection2;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class CircleTest {

    @Test
    void defaultConstructorDoesNotChangeRadius() {
        Circle c = new Circle();

        double expectedRadius = 1.0d;
        assertEquals(expectedRadius, c.getRadius());
    }

    @RepeatedTest(10)
    void constructorShouldCorrectlyInitalizeRadius() {
        Random r = new Random();
        double expectedRadius = r.nextDouble();
        Circle c = new Circle(expectedRadius);

        assertEquals(expectedRadius, c.getRadius());
    }

    @RepeatedTest(10)
    void areaShouldUseCorrectFormula() {
        Random r = new Random();
        double radius = r.nextDouble();
        Circle c = new Circle(radius);

        double expectedArea = Math.PI * Math.pow(radius, 2);

        double delta = 1e-5d;
        assertEquals(expectedArea, c.getArea(), delta);
    }
}
