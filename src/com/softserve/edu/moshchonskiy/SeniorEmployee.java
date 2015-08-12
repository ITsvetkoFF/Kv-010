package com.softserve.edu.moshchonskiy;

/**
 * Created by Evgen on 8/9/2015.
 */
public class SeniorEmployee extends Employee implements Comparable<Employee> {

    public SeniorEmployee(String name, int age, int seniority, int hours) {

        super(name, age, seniority, hours);

    }

    @Override
    public double getPayment() {

        double senior_Salary;

        senior_Salary = (getHours() * SENIOR_PER_HOUR * (getSeniority() + 100)) / 100;

        return senior_Salary;

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
