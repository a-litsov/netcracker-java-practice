package com.netcracker.adlitsov.homework1;

import java.util.Random;

public class Employee {
    private int id;
    private String firstName;
    private String lastName;
    private int salary;

    public Employee(int id, String firstName, String lastName, int salary) {
        validateName(firstName, lastName);
        validateSalary(salary);

        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.salary = salary;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getName() {
        return firstName + " " + lastName;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        validateSalary(salary);
        this.salary = salary;
    }

    public int getAnnualSalary() {
        return 12 * salary;
    }

    public int raiseSalary(int percent) {
        salary *= 1 + percent / 100.0;
        validateSalary(salary);
        return salary;
    }

    @Override
    public String toString() {
        return "Employee[id=" + id + ", name=" + firstName + " " + lastName + ", salary=" + salary + "]";
    }

    public void validateName(String firstName, String lastName) {
        if (firstName == null || lastName == null) {
            throw new IllegalArgumentException("First name and last name must be not null");
        }
    }

    public void validateSalary(int salary) {
        if (salary < 0) {
            throw new IllegalArgumentException("Salary must be non-negative number");
        }
    }

    public static void main(String[] args) {
        Random rnd = new Random();
        Employee emp = new Employee(rnd.nextInt(Integer.MAX_VALUE), "Steve", "Jobs", 1_000_000);
        System.out.println(emp);
        emp.raiseSalary(20);
        System.out.println(emp);
        System.out.println("Annual salary is:" + emp.getAnnualSalary());
    }
}
