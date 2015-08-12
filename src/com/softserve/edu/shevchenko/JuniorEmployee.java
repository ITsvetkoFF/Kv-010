package com.softserve.edu.shevchenko;



public class JuniorEmployee extends Employee implements Comparable {

    public JuniorEmployee(String name, int age, int seniority, int hours) {
        super(name, age, seniority, hours);
    }

    @Override
    public double getPayment() {
        int start = 500;
        int iteration = 200;
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
