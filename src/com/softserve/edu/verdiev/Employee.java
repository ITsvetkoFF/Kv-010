package com.softserve.edu.verdiev;

import com.softserve.edu.error.*;

abstract class Employee implements Worker, Comparable {

    public String name;
    public int    age;
    public int    seniority;
    public int    hours;

    public String getName(){
        return name;
    }

    public int getAge(){
        return age;
    }

    public int getSeniority() {
        return seniority;
    }

    protected int getHours(){
        return hours;
    }

    public abstract double getPayment();

    @Override
    public int compareTo(Object obj) {
        Employee tmp = (Employee) obj;
        return this.name.compareTo(tmp.name);
    }

    //Constructor
    public Employee(String name, int age, int seniority, int hours) throws MyException{
        this.name = name;

        if (age < 16) {
            System.out.println("It's forbidden to use teenagers as non-payable workers");
            throw new MyException("invalid age");
        } else {
            this.age = age;
        }

        if (seniority > age){
            throw new MyException("Seniority greater than age");
        } else{
            this.seniority = seniority;
        }

        this.hours = hours;
    }
}
