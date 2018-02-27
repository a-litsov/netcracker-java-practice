package com.edu_netcracker.nn.adlitsov.homework1;

import org.junit.jupiter.api.RepeatedTest;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class EmployeeTest {

    public static final int MAX_SALARY = Integer.MAX_VALUE / 12;
    public static final int MAX_RAISING_PERCENT = 100;

    @RepeatedTest(10)
    void raiseSalaryShouldWorkCorrectly() {
        Random rnd = new Random();
        int salary = rnd.nextInt(MAX_SALARY);
        int percent = rnd.nextInt(MAX_RAISING_PERCENT);

        Employee emp = new Employee(rnd.nextInt(Integer.MAX_VALUE), "", "", salary);
        emp.raiseSalary(percent);

        int expectedRaisedSalary = (int) (salary * (1 + percent / 100.0));

        assertEquals(expectedRaisedSalary, emp.getSalary(), "raised salary must be = " + expectedRaisedSalary);
    }
}
