package com.edu_netcracker.nn.adlitsov.homework1;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

import static com.edu_netcracker.nn.adlitsov.homework1.MyTriangle.*;

public class MyTriangleTest {

    public static final int MAX_COORD = 1000;

    @RepeatedTest(10)
    void incorrectTriangleCannotBeCreated() {
        Random rnd = new Random();
        MyPoint p1, p2, p3;
        do {
            p1 = new MyPoint(rnd.nextInt(MAX_COORD), rnd.nextInt(MAX_COORD));
            p2 = new MyPoint(rnd.nextInt(MAX_COORD), rnd.nextInt(MAX_COORD));
            p3 = new MyPoint(rnd.nextInt(MAX_COORD), rnd.nextInt(MAX_COORD));
        } while (isTriangle(p1, p2, p3));

        final MyPoint v1 = p1, v2 = p2, v3 = p3;

        assertThrows(IllegalArgumentException.class, () -> new MyTriangle(v1, v2, v3));
    }

    @RepeatedTest(10)
    void isoscelesTriangleShouldHaveCorrectType() {
        Random rnd = new Random();
        MyPoint p1, p2, p3;
        while (true) {
            p1 = new MyPoint(rnd.nextInt(MAX_COORD), rnd.nextInt(MAX_COORD));
            p2 = new MyPoint(rnd.nextInt(MAX_COORD), rnd.nextInt(MAX_COORD));
            p3 = new MyPoint(rnd.nextInt(MAX_COORD), rnd.nextInt(MAX_COORD));

            double length1 = p1.distance(p2);
            double length2 = p2.distance(p3);
            double length3 = p3.distance(p1);

            if (isTriangle(p1, p2, p3) && (length1 == length2 || length2 == length3 || length1 == length3)) {
                break;
            }
        }

        final MyPoint v1 = p1, v2 = p2, v3 = p3;

        assertEquals(new MyTriangle(p1, p2, p3).getType(), Type.ISOSCELES, "Triangle with vertices:" +
                p1 + ", " + p2 + ", " + p3 + " must be isosceles!");
    }

    @RepeatedTest(10)
    void scaleneTriangleShouldHaveCorrectType() {
        Random rnd = new Random();
        MyPoint p1, p2, p3;
        while (true) {
            p1 = new MyPoint(rnd.nextInt(MAX_COORD), rnd.nextInt(MAX_COORD));
            p2 = new MyPoint(rnd.nextInt(MAX_COORD), rnd.nextInt(MAX_COORD));
            p3 = new MyPoint(rnd.nextInt(MAX_COORD), rnd.nextInt(MAX_COORD));

            double length1 = p1.distance(p2);
            double length2 = p2.distance(p3);
            double length3 = p3.distance(p1);

            if (isTriangle(p1, p2, p3) && length1 != length2 && length2 != length3 && length1 != length3) {
                break;
            }
        }

        final MyPoint v1 = p1, v2 = p2, v3 = p3;

        assertEquals(new MyTriangle(p1, p2, p3).getType(), Type.SCALENE, "Triangle with vertices:" +
                p1 + ", " + p2 + ", " + p3 + " must be scalene!");
    }

    @Disabled
    void impossibleToGenerateEquilateralTriangleWithIntegralCoords() {
        assertTimeoutPreemptively(Duration.ofSeconds(10), () -> {
            Random rnd = new Random();
            MyPoint p1, p2, p3;
            while (true) {
                p1 = new MyPoint(rnd.nextInt(MAX_COORD), rnd.nextInt(MAX_COORD));
                p2 = new MyPoint(rnd.nextInt(MAX_COORD), rnd.nextInt(MAX_COORD));
                p3 = new MyPoint(rnd.nextInt(MAX_COORD), rnd.nextInt(MAX_COORD));

                double length1 = p1.distance(p2);
                double length2 = p2.distance(p3);
                double length3 = p3.distance(p1);

                if (isTriangle(p1, p2, p3) && length1 == length2 && length2 == length3) {
                    break;
                }
            }
        });
    }
}
