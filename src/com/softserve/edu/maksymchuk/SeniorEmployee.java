package com.softserve.edu.maksymchuk;

/**
 * @author Denys Maxymchuk
 */
public class SeniorEmployee extends Employee {
    public SeniorEmployee(String name, int age, int seniority, int hours) {
        super(name, age, seniority, hours);
    }

    @Override
    public double getPayment() {
        return this.getSeniority() + this.getHours()/2.0;
    }
}
