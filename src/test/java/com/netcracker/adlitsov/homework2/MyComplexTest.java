package com.netcracker.adlitsov.homework2;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class MyComplexTest {
    @Test
    void magnitudeShouldWorkCorrectly() {
        MyComplex complex = new MyComplex(3, 4);
        int expectedMagn = 5;

        assertEquals(expectedMagn, complex.magnitude());
    }

    /*
     * This method uses fact that: z = magnitude * (cos(argument) + i * sin(argument))
     */
    @RepeatedTest(10)
    void argumentShouldWorkCorrectly() {
        final double MAX_COORD = 1000;
        final double DELTA = 1e-5;

        Random rnd = new Random();
        MyComplex complex = new MyComplex(rnd.nextDouble() * MAX_COORD, rnd.nextDouble() * MAX_COORD);
        double arg = complex.argument();
        double magn = complex.magnitude();

        assertEquals(magn * Math.cos(arg), complex.getReal(), DELTA);
        assertEquals(magn * Math.sin(arg), complex.getImag(), DELTA);
    }

    @RepeatedTest(10)
    void addMethodsShouldWorkCorrectly() {
        final double MAX_COORD = 1000;
        final double DELTA = 1e-5;

        Random rnd = new Random();
        double leftReal = rnd.nextDouble() * MAX_COORD, leftImag = rnd.nextDouble() * MAX_COORD;
        double rightReal = rnd.nextDouble() * MAX_COORD, rightImag = rnd.nextDouble() * MAX_COORD;
        MyComplex left = new MyComplex(leftReal, leftImag);
        MyComplex right = new MyComplex(rightReal, rightImag);
        MyComplex result = left.addNew(right);

        assertEquals(leftReal + rightReal, result.getReal());
        assertEquals(leftImag + rightImag, result.getImag());

        assertTrue(left.equals(leftReal, leftImag));
    }

    @RepeatedTest(10)
    void subtractMethodsNewShouldWorkCorrectly() {
        final double MAX_COORD = 1000;
        final double DELTA = 1e-5;

        Random rnd = new Random();
        double leftReal = rnd.nextDouble() * MAX_COORD, leftImag = rnd.nextDouble() * MAX_COORD;
        double rightReal = rnd.nextDouble() * MAX_COORD, rightImag = rnd.nextDouble() * MAX_COORD;
        MyComplex left = new MyComplex(leftReal, leftImag);
        MyComplex right = new MyComplex(rightReal, rightImag);
        MyComplex result = left.subtractNew(right);

        assertEquals(leftReal - rightReal, result.getReal());
        assertEquals(leftImag - rightImag, result.getImag());

        assertTrue(left.equals(leftReal, leftImag));
    }

    @Test
    void multiplyShouldWorkCorrectly() {

        MyComplex left = new MyComplex(5, 15);
        MyComplex right = new MyComplex(-3, 0.2);
        MyComplex res = left.multiply(right);

        final double expReal = -18, expImag = -44;

        assertTrue(res.equals(expReal, expImag), "\nExpected: (" + expReal + expImag + ")\nActual: " + res);
        assertTrue(left.equals(res));
    }

    @Test
    void divideShouldWorkCorrectly() {
        MyComplex left = new MyComplex(5, 15);
        MyComplex right = new MyComplex(-3, 0.2);
        MyComplex res = left.divide(right);

        final double expReal = -12 / 9.04, expImag = -46 / 9.04;

        assertTrue(res.equals(expReal, expImag), "\nExpected: (" + expReal + expImag + ")\nActual: " + res);
        assertTrue(left.equals(res));
    }

    @Test
    void shouldThrowIAExceptionWhenDivideByZero() {
        MyComplex left = new MyComplex(5, 15);
        MyComplex right = new MyComplex(0, 0);

        assertThrows(IllegalArgumentException.class, () -> left.divide(right));
    }

    @Test
    void toStringShouldConvertZeroComplex() {
        MyComplex complex = new MyComplex(0, 0);
        String expectedStr = "0.0";

        assertEquals(expectedStr, complex.toString());
    }

    @Test
    void toStringShouldConvertZeroImagComplex() {
        MyComplex complex = new MyComplex(0.22, 0);
        String expectedStr = "0.22";

        assertEquals(expectedStr, complex.toString());
    }

    @Test
    void toStringShouldConvertZeroRealComplex() {
        MyComplex complex = new MyComplex(0, 42.3);
        String expectedStr = "42.3i";

        assertEquals(expectedStr, complex.toString());
    }

    @Test
    void toStringShouldConvertComplex() {
        MyComplex complex = new MyComplex(13, 42.3);
        String expectedStr = "(13.0+42.3i)";

        assertEquals(expectedStr, complex.toString());
    }

    @Test
    void toStringShouldConvertNegativeComplex() {
        MyComplex complex = new MyComplex(-13, -42.3);
        String expectedStr = "(-13.0-42.3i)";

        assertEquals(expectedStr, complex.toString());
    }
}
