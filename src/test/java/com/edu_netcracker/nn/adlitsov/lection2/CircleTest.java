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
    void constructorShouldCorrectlyInitRadius() {
        Random r = new Random();
        double expectedRadius = r.nextDouble();
        Circle c = new Circle(expectedRadius);

        assertEquals(expectedRadius, c.getRadius());
    }

    @RepeatedTest(10)
    void constructorShouldCorrectlyInitRadiusAndColor() {
        Random r = new Random();
        double expectedRadius = r.nextDouble();
        // just easy way to generate random string made of digits ;)
        String expectedColor = Long.toString(r.nextLong());
        Circle c = new Circle(expectedRadius, expectedColor);

        assertEquals(expectedRadius, c.getRadius());
        assertEquals(expectedColor, c.getColor());
    }

    @RepeatedTest(10)
    void radiusShouldBeSetCorrectly() {
        Random r = new Random();
        double expectedRadius = r.nextDouble();

        Circle c = new Circle();
        c.setRadius(expectedRadius);

        assertEquals(expectedRadius, c.getRadius());
    }

    @RepeatedTest(10)
    void colorShouldBeSetCorrectly() {
        Random r = new Random();
        // just easy way to generate random string made of digits ;)
        String expectedColor = Long.toString(r.nextLong());

        Circle c = new Circle();
        c.setColor(expectedColor);

        assertEquals(expectedColor, c.getColor());
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

    @RepeatedTest(10)
    void toStringShouldWorkCorrectly() {
        Random r = new Random();
        // just easy way to generate random string made of digits ;)
        String color = Long.toString(r.nextLong());
        double radius = r.nextDouble();

        Circle c = new Circle(radius, color);
        String expectedStrRepres = "Circle[radius=" + radius + ", color=" + color + "]";

        assertEquals(expectedStrRepres, c.toString());
    }
}
