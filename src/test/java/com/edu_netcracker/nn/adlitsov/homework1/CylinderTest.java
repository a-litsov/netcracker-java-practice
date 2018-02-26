package com.edu_netcracker.nn.adlitsov.homework1;

import org.junit.jupiter.api.RepeatedTest;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Random;

class CylinderTest {

    @RepeatedTest(10)
    void getVolumeShouldUseCorrectFormula() {
        Random r = new Random();
        double radius = r.nextDouble() % 1000;
        double height = r.nextDouble() % 1000;
        double expectedVolume = (Math.PI * Math.pow(radius, 2)) * height;

        Cylinder c = new Cylinder(radius, height);

        double delta = 1e-5;
        assertEquals(expectedVolume, c.getVolume(), delta);
    }

}
