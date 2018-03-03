package com.netcracker.adlitsov.homework2;

import java.util.Objects;
import java.util.Random;

public class MyComplex {
    private double real;
    private double imag;

    public MyComplex() {

    }

    public MyComplex(double real, double imag) {
        this.real = real;
        this.imag = imag;
    }

    public MyComplex(MyComplex other) {
        validateComplex(other);

        real = other.real;
        imag = other.imag;
    }

    public double getReal() {
        return real;
    }

    public void setReal(double real) {
        this.real = real;
    }

    public double getImag() {
        return imag;
    }

    public void setImag(double imag) {
        this.imag = imag;
    }

    public void setValue(double real, double imag) {
        this.real = real;
        this.imag = imag;
    }

    @Override
    public String toString() {
        if (imag == 0) {
            return Double.toString(real);
        }
        if (real == 0) {
            return imag + "i";
        }

        StringBuilder sb = new StringBuilder("(").append(real);
        if (imag > 0) {
            sb.append("+");
        }
        sb.append(imag).append("i").append(")");
        return sb.toString();
    }

    public boolean isReal() {
        return imag == 0;
    }

    public boolean isImaginary() {
        return real == 0;
    }

    public boolean equals(double real, double imag) {
        return this.real == real && this.imag == imag;
    }

    public boolean equals(MyComplex another) {
        if (this == another) {
            return true;
        }
        if (another == null) {
            return false;
        }
        return real == another.real && imag == another.imag;
    }

    // true overriding, above methods don't actually override Object's equals method
    @Override
    public boolean equals(Object anotherObj) {
        if (this == anotherObj) {
            return true;
        }
        if (anotherObj == null) {
            return false;
        }
        if (anotherObj.getClass() != getClass()) {
            return false;
        }
        MyComplex another = (MyComplex) anotherObj;
        return real == another.real && imag == another.imag;
    }

    // must execute contract between equals and hashCode methods
    @Override
    public int hashCode() {
        return Objects.hash(real, imag);
    }

    public double magnitude() {
        return Math.sqrt(real * real + imag * imag);
    }

    public double argument() {
        return Math.atan2(imag, real);
    }

    public MyComplex add(MyComplex right) {
        validateComplex(right);

        this.real += right.real;
        this.imag += right.imag;

        return this;
    }

    public MyComplex addNew(MyComplex right) {
        MyComplex resComplex = new MyComplex(real, imag);

        resComplex.add(right);

        return resComplex;
    }

    public MyComplex subtract(MyComplex right) {
        validateComplex(right);

        this.real -= right.real;
        this.imag -= right.imag;

        return this;
    }

    public MyComplex subtractNew(MyComplex right) {
        MyComplex resComplex = new MyComplex(real, imag);

        resComplex.subtract(right);

        return resComplex;
    }

    public MyComplex multiply(MyComplex right) {
        validateComplex(right);

        double leftReal = real, leftImag = imag;
        real = leftReal * right.real - leftImag * right.imag;
        imag = leftReal * right.imag + leftImag * right.real;

        return this;
    }

    public MyComplex divide(MyComplex right) {
        validateComplex(right);
        if (right.real == 0 && right.imag == 0) {
            throw new IllegalArgumentException("right must not be zero complex number!");
        }

        double leftReal = real, leftImag = imag;
        double divisor = right.real * right.real + right.imag * right.imag;
        real = (leftReal * right.real + leftImag * right.imag) / divisor;
        imag = (leftImag * right.real - leftReal * right.imag) / divisor;

        return this;
    }

    public MyComplex conjugate() {
        return new MyComplex(real, -imag);
    }

    private void validateComplex(MyComplex compNumber) {
        if (compNumber == null) {
            throw new IllegalArgumentException("Passed complex number must be not-null!");
        }
    }

    public static void main(String[] args) {
        final int MAX_ABS_VAL = 10;
        Random rnd = new Random();

        MyComplex complex1 = new MyComplex(rnd.nextInt(2 * MAX_ABS_VAL) - MAX_ABS_VAL,
                rnd.nextInt(2 * MAX_ABS_VAL) - MAX_ABS_VAL);
        System.out.println("Complex#1: " + complex1);
        System.out.println("Argument: " + complex1.argument() + ", Magnitude: " + complex1.magnitude());
        System.out.println("Conjugate: " + complex1.conjugate());

        MyComplex complex2 = new MyComplex(rnd.nextInt(2 * MAX_ABS_VAL) - MAX_ABS_VAL,
                rnd.nextInt(2 * MAX_ABS_VAL) - MAX_ABS_VAL);
        System.out.println("\nComplex#2: " + complex2);
        System.out.println("Argument: " + complex2.argument() + ", Magnitude: " + complex2.magnitude());
        System.out.println("Conjugate: " + complex2.conjugate());

        System.out.println("\nTheir sum: " + complex1.addNew(complex2));
        System.out.println("Their sub: " + complex1.subtractNew(complex2));
        System.out.println("Their mult: " + new MyComplex(complex1).multiply(complex2));
        System.out.println("Their div: " + new MyComplex(complex1).divide(complex2));
    }
}
