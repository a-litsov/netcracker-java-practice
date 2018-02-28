package com.netcracker.adlitsov.homework1;

import org.junit.jupiter.api.RepeatedTest;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class RectangleTest {
    public static final float MAX_VAL = 1000;

    @RepeatedTest(10)
    void getAreaShouldUseCorrectFormula() {
        Random rnd = new Random();
        float length = rnd.nextFloat() * MAX_VAL;
        float width = rnd.nextFloat() * MAX_VAL;

        Rectangle rect = new Rectangle(length, width);

        float expectedArea = length * width;

        double delta = 1e-5;
        assertEquals(expectedArea, rect.getArea(), delta, rect + " should have area = " + expectedArea);
    }

    @RepeatedTest(10)
    void getPerimeterShouldUseCorrectFormula() {
        Random rnd = new Random();
        float length = rnd.nextFloat() * MAX_VAL;
        float width = rnd.nextFloat() * MAX_VAL;

        Rectangle rect = new Rectangle(length, width);

        float expectedPerimeter = 2 * (length + width);

        double delta = 1e-5;
        assertEquals(expectedPerimeter, rect.getPerimeter(), delta, rect + " should have perimeter = "
                + expectedPerimeter);
    }
}
