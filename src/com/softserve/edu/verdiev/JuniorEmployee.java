package com.softserve.edu.verdiev;

/**
 * Created by yioteh on 10.08.15.
 */
public class JuniorEmployee extends Employee{

    private Employee jemp;

    //Subclass constructor
    public JuniorEmployee(String name, int age, int seniority, int hours){
        super(name, age, seniority, hours);
    }

    @Override
    public double getPayment(){
        int jempAge = jemp.getAge();
        int jempHours = jemp.getHours();
        return jempHours/(jempAge*1.7);
    }
}
