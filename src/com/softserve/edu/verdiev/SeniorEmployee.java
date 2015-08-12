package com.softserve.edu.verdiev;

/**
 * Created by yioteh on 10.08.15.
 */
public class SeniorEmployee extends Employee{

    private Employee semp;

    //Subclass constructor
    public SeniorEmployee(String name, int age, int seniority, int hours){
        super(name, age, seniority, hours);
    }

    @Override
    public double getPayment() {
        int sempAge = semp.getAge();
        int sempHours = semp.getHours();
        return sempHours*(sempAge+3)/0.5;
    }
}
