package com.netcracker.adlitsov.homework2;

import java.util.Arrays;

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
        for (int i = getDegree(); i >= 0; i--) {
            if (coeffs[i] == 0) {
                continue;
            }

            if (coeffs[i] < 0) {
                sb.append("(").append(coeffs[i]).append(")");
            } else {
                sb.append(coeffs[i]);
            }
            sb.append("x^").append(i).append((i > 0) ? "+" : "");
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

        int degree = Math.max(getDegree(), right.getDegree());
        double[] newCoeffs = new double[degree];
        for (int i = 0; i < newCoeffs.length; i++) {
            newCoeffs[i] = coeffs[i] + right.coeffs[i];
        }

        return new MyPolinomial(newCoeffs);
    }

    public MyPolinomial multiply(MyPolinomial right) {
        validatePolynomial(right);

        int degree = getDegree() + right.getDegree();
        double[] newCoeffs = new double[degree];
        for (int i = 0; i <= getDegree(); i++) {
            for (int j = 0; j <= right.getDegree(); j++) {
                newCoeffs[i + j] += coeffs[i] * right.coeffs[j];
            }
        }

        return new MyPolinomial(newCoeffs);
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
}
