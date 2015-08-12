package com.softserve.edu.statsenko;

/**
 * Created by stako on 08.08.2015.
 */
public class SeniorEmployee extends Employee{
    private String name;
    private int age;
    private int seniority;
    private int hours;
    private double payment;

    public SeniorEmployee(String name, int age, int seniority, int hours) {
        super(name, age, seniority, hours);
        this.name = name;
        this.age = age;
        this.seniority = seniority;
        this.hours = hours;
        getPayment();
    }

    @Override
    public double getPayment(){
        payment = 6.5 * super.getSeniority() * super.getHours();
        return payment;
    }

    @Override
    public String toString(){
        return "Senior employee " + name + " : age= " + age + ", seniority= " + seniority + ", hours= " + hours + ", payment= " + String.format("%.2f", payment) + "$.";
    }
}
