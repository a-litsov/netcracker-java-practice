package com.netcracker.adlitsov.homework2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MyPolinomialTest {
    @Test
    void shouldThrowExceptionWhenConstructingWithNullArray() {
        assertThrows(IllegalArgumentException.class, () -> new MyPolinomial(null));
    }

    @Test
    void shouldThrowExceptionWhenConstructingWithEmptyArray() {
        assertThrows(IllegalArgumentException.class, () -> new MyPolinomial(new double[]{}));
    }

    @Test
    void shouldThrowExceptionWhenAddingNullPolinomial() {
        assertThrows(IllegalArgumentException.class, () -> new MyPolinomial(0).add(null));
    }

    @Test
    void shouldThrowExceptionWhenMultNullPolinomial() {
        assertThrows(IllegalArgumentException.class, () -> new MyPolinomial(0).multiply(null));
    }

    @Test
    void shouldEvaluateCorrectly() {
        MyPolinomial pol = new MyPolinomial(-1, 2, 0.5, -3);
        double evalWith2Result = -1 + 2 * 2 + 0.5 * (2 * 2) - 3 * (2 * 2 * 2);

        assertEquals(evalWith2Result, pol.evaluate(2));
    }

    @Test
    void shouldEvaluateCorrectlyWithZeroes() {
        MyPolinomial pol = new MyPolinomial(0, 0);
        double evalWith3Result = 0;

        assertEquals(evalWith3Result, pol.evaluate(3));
    }

    @Test
    void shouldAddCorrectlyWithEqualDegrees() {
        MyPolinomial leftPol = new MyPolinomial(-1, 5, 0.6, 712, 0, -5, 0, 0);
        MyPolinomial rightPol = new MyPolinomial(0, 0, 1, 13, 4, 12, 0);

        MyPolinomial expectedPol = new MyPolinomial(-1, 5, 1.6, 725, 4, 7);
        assertEquals(expectedPol, leftPol.add(rightPol));
    }

    @Test
    void shouldAddCorrectlyWithDifferentDegrees() {
        MyPolinomial leftPol = new MyPolinomial(5, 3, 4, -1.6);
        MyPolinomial rightPol = new MyPolinomial(-0.1, 12);

        MyPolinomial expectedPol = new MyPolinomial(4.9, 15, 4, -1.6);
        assertEquals(expectedPol, leftPol.add(rightPol));
    }

    @Test
    void shouldAddCorrectlyWithZeroPolynomial() {
        MyPolinomial leftPol = new MyPolinomial(5, 3, 4, -1.6);
        MyPolinomial rightPol = new MyPolinomial(0);

        assertEquals(leftPol, leftPol.add(rightPol));
    }

    @Test
    void shouldMultiplyCorrectlyWithEqualDegrees() {
        MyPolinomial leftPol = new MyPolinomial(3, 12, -0.5, 2, -7, 0);
        MyPolinomial rightPol = new MyPolinomial(0, 13, 0, -5, -5, 0, 0, 0);

        MyPolinomial expectedPol = new MyPolinomial(0, 39, 156, -21.5, -49, -148.5, -7.5, 25, 35);
        assertEquals(expectedPol, leftPol.multiply(rightPol));
    }

    @Test
    void shouldMultiplyCorrectlyWithDifferentDegrees() {
        MyPolinomial leftPol = new MyPolinomial(5, 12, -1.5, 0, 0, 3.2);
        MyPolinomial rightPol = new MyPolinomial(1.5, 6, 0, 0);

        MyPolinomial expectedPol = new MyPolinomial(7.5, 48, 69.75, -9, 0, 4.8, 19.2);
        MyPolinomial resultPol = leftPol.multiply(rightPol);
        assertTrue(expectedPol.equals(resultPol, 1e-5), "\nExpected: " + expectedPol + "\nActual: " + resultPol);
    }

    @Test
    void shouldMultiplyCorrectlyWithZeroPolynomial() {
        MyPolinomial leftPol = new MyPolinomial(5, 3, 4, -1.6);
        MyPolinomial rightPol = new MyPolinomial(0);

        assertEquals(rightPol, leftPol.multiply(rightPol));
    }

    @Test
    void shouldMultiplyCorrectlyWithUnitPolynomial() {
        MyPolinomial leftPol = new MyPolinomial(5, 3, 4, -1.6);
        MyPolinomial rightPol = new MyPolinomial(1);

        assertEquals(leftPol, leftPol.multiply(rightPol));
    }

    @Test
    void shouldReturnCorrectDegreeFromZeroPolynomial() {
        MyPolinomial pol = new MyPolinomial(0);

        assertEquals(0, pol.getDegree());
    }

    @Test
    void shouldReturnCorrectDegreeFromPolynomialWithLastZeroes() {
        MyPolinomial pol = new MyPolinomial(0, 1, 0, 0, 4, 0, 0, 7, 8, 0, 0, 0, 0);

        assertEquals(8, pol.getDegree());
    }

    @Test
    void shouldConvertToCorrectStringForZeroPolynomial() {
        MyPolinomial pol = new MyPolinomial(0);

        String expectedStr = "0.0";
        assertEquals(expectedStr, pol.toString());
    }

    @Test
    void shouldConvertToCorrectStringForNegativePolynomial() {
        MyPolinomial pol = new MyPolinomial(-0.5);

        String expectedStr = "-0.5";
        assertEquals(expectedStr, pol.toString());
    }

    @Test
    void shouldConvertToCorrectStringForPolinomial() {
        MyPolinomial pol = new MyPolinomial(0.2, -5, 13, -12.3, 0, 0, 2, 0, 0);

        String expectedStr = "2.0x^6-12.3x^3+13.0x^2-5.0x+0.2";
        assertEquals(expectedStr, pol.toString());
    }
}
