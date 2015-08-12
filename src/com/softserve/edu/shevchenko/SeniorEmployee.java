package com.softserve.edu.shevchenko;

/**
 * Created by Администратор on 08.08.2015.
 */
public class SeniorEmployee extends Employee implements Comparable {

    public SeniorEmployee(String name, int age, int seniority, int hours) {
        super(name, age, seniority, hours);
    }

    @Override
    public double getPayment() {
        int start = 2000;
        int iteration = 150;
        return start + iteration * getSeniority();
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + getName() + '\'' +
                ", age=" + getAge() +
                ", seniority=" + getSeniority() +
                ", hours=" + getHours() +
                ", Payment=" + getPayment() +
                '}';
    }

}
