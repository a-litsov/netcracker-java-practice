package com.netcracker.adlitsov.homework2;

import java.util.Arrays;
import java.util.Random;

public class MyPolinomial {
    private double[] coeffs;

    public MyPolinomial(double... coeffs) {
        validateCoeffs(coeffs);
        this.coeffs = Arrays.copyOf(coeffs, coeffs.length);
    }

    public int getDegree() {
        for (int i = coeffs.length - 1; i >= 0; i--) {
            if (coeffs[i] != 0) {
                return i;
            }
        }
        return 0;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        int degree = getDegree();
        for (int i = degree; i >= 0; i--) {
            // Skipping all zero terms
            if (coeffs[i] == 0) {
                continue;
            }

            // Adding positive sign for all positive terms except the eldest one
            if (coeffs[i] > 0 && i < degree) {
                sb.append("+");
            }
            sb.append(coeffs[i]);

            // adding a degree
            if (i > 1) {
                sb.append("x^").append(i);
            } else {
                if (i == 1) {
                    sb.append("x");
                }
            }
        }

        if (sb.length() == 0) {
            sb.append(0.0);
        }

        return sb.toString();
    }

    public double evaluate(double x) {
        double res = 0;
        for (int i = 0; i <= getDegree(); i++) {
            res += coeffs[i] * Math.pow(x, i);
        }
        return res;
    }

    public MyPolinomial add(MyPolinomial right) {
        validatePolynomial(right);

        int leftDegree = getDegree(), rightDegree = right.getDegree();
        int degree = Math.max(leftDegree, rightDegree);

        double[] newCoeffs = new double[degree + 1];

        for (int i = 0; i <= degree; i++) {
            if (i <= leftDegree) {
                newCoeffs[i] += coeffs[i];
            }
            if (i <= rightDegree) {
                newCoeffs[i] += right.coeffs[i];
            }
        }

        return new MyPolinomial(newCoeffs);
    }

    public MyPolinomial multiply(MyPolinomial right) {
        validatePolynomial(right);

        int degree = getDegree() + right.getDegree();
        double[] newCoeffs = new double[degree + 1];
        for (int i = 0; i <= getDegree(); i++) {
            for (int j = 0; j <= right.getDegree(); j++) {
                newCoeffs[i + j] += coeffs[i] * right.coeffs[j];
            }
        }

        return new MyPolinomial(newCoeffs);
    }

    /*
     * This method based on first (degree + 1) 'coeffs' elements comparison.
     * Two double values are equal if they lay in the same delta segment.
     */
    public boolean equals(MyPolinomial other, double delta) {
        if (this == other) {
            return true;
        }
        if (other == null) {
            return false;
        }
        int degree = getDegree();
        if (degree != other.getDegree()) {
            return false;
        }
        for (int i = 0; i <= degree; i++) {
            if (Math.abs(coeffs[i] - other.coeffs[i]) > delta) {
                return false;
            }
        }
        return true;
    }

    /*
     * This method based on first (degree + 1) 'coeffs' elements comparison.
     * Two double values are equal if they are exactly the same.
     */
    @Override
    public boolean equals(Object otherObj) {
        if (this == otherObj) {
            return true;
        }
        if (otherObj == null) {
            return false;
        }
        if (getClass() != otherObj.getClass()) {
            return false;
        }
        MyPolinomial other = (MyPolinomial) otherObj;

        int degree = getDegree();
        if (degree != other.getDegree()) {
            return false;
        }
        for (int i = 0; i <= degree; i++) {
            if (coeffs[i] != other.coeffs[i]) {
                return false;
            }
        }
        return true;
    }

    /*
     * Default Arrays.hashCode(double[]) but for first (degree + 1) elements.
     */
    @Override
    public int hashCode() {
        int res = 1;
        for (int i = 0; i <= getDegree(); i++) {
            res += 31 * Double.hashCode(coeffs[i]);
        }
        return res;
    }

    private void validateCoeffs(double... coeffs) {
        if (coeffs == null) {
            throw new IllegalArgumentException("coeffs array must be not null!");
        }
        if (coeffs.length == 0) {
            throw new IllegalArgumentException("coeffs array must be not empty!");
        }
    }

    private void validatePolynomial(MyPolinomial polynomial) {
        if (polynomial == null) {
            throw new IllegalArgumentException("polynomial must be not null!");
        }
    }

    public static void main(String[] args) {
        final int MAX_DEGREE = 5;
        final int MIN_VALUE = -5;
        final int MAX_VALUE = 5;

        // Generating random polynomials and showing them; using Math.round to simplify coeffs
        Random rnd = new Random();
        double[] firstCoeffs = rnd.doubles(rnd.nextInt(MAX_DEGREE + 1) + 1, MIN_VALUE, MAX_VALUE)
                                  .map(Math::round)
                                  .toArray();
        System.out.println("Coeffs#1: " + Arrays.toString(firstCoeffs));

        MyPolinomial firstPolynomial = new MyPolinomial(firstCoeffs);
        System.out.println("Polynomial#1: " + firstPolynomial);

        double[] secondCoeffs = rnd.doubles(rnd.nextInt(MAX_DEGREE + 1) + 1, MIN_VALUE, MAX_VALUE)
                                   .map(Math::round)
                                   .toArray();
        System.out.println("\nCoeffs#2: " + Arrays.toString(secondCoeffs));

        MyPolinomial secondPolynomial = new MyPolinomial(secondCoeffs);
        System.out.println("Polynomial#2: " + secondPolynomial);

        // Executing sum and multiply methods
        System.out.println("\nTheir sum: " + firstPolynomial.add(secondPolynomial));
        System.out.println("\nTheir mult: " + firstPolynomial.multiply(secondPolynomial));
    }
}
