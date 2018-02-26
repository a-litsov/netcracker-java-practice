package com.edu_netcracker.nn.adlitsov.lection2;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class CircleTest {

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
