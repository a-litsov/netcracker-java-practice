package com.netcracker.adlitsov.homework2;

import java.util.Objects;

public class MyComplex {
    private double real;
    private double imag;

    public MyComplex() {

    }

    public MyComplex(double real, double imag) {
        this.real = real;
        this.imag = imag;
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
        return "(" + real + "+" + imag + ")";
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
        validateComplex(right);

        MyComplex resComplex = new MyComplex(real, imag);
        resComplex.real += right.real;
        resComplex.imag += right.imag;

        return resComplex;
    }

    public MyComplex subtract(MyComplex right) {
        validateComplex(right);

        this.real -= right.real;
        this.imag -= right.imag;

        return this;
    }

    public MyComplex subtractNew(MyComplex right) {
        validateComplex(right);

        MyComplex resComplex = new MyComplex(real, imag);
        resComplex.real -= right.real;
        resComplex.imag -= right.imag;

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
}
