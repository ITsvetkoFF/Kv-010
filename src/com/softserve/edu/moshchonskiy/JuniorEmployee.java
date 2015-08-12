package com.softserve.edu.moshchonskiy;

/**
 * Created by Evgen on 8/8/2015.
 */
public class JuniorEmployee extends Employee implements Comparable<Employee> {

    public JuniorEmployee(String name, int age, int seniority, int hours) {

        super(name, age, seniority, hours);

    }

    @Override
    public double getPayment() {

        double jun_Salary;

        jun_Salary = (getHours() * JUNIOR_PER_HOUR * (getSeniority() + 100)) / 100;

        return jun_Salary;

    }

    @Override
    public int compareTo(Employee other) {

        return getName().compareTo(other.getName());

    }

    @Override
    public String toString() {
        return "[ Name=" + getName() + ", age=" + getAge() + ", seniority=" + getSeniority() + ", hours=" + getHours() + "]";
    }

}
