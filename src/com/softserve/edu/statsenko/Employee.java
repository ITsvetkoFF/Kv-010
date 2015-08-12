package com.softserve.edu.statsenko;

/**
 * Created by stako on 08.08.2015.
 */
abstract public class Employee implements Worker, Comparable{
    private String name;
    private int age;
    private int seniority;
    private int hours;

    public Employee(String name, int age, int seniority, int hours){
        this.name = name;
        this.age = age;
        this.seniority = seniority;
        this.hours = hours;
        if (seniority > age){
                throw new MyException("Attention! " + name + "'s work experience can't be true.");
        }
    }

    public Employee(){
        this.name = null;
        this.age = 0;
        this.seniority = 0;
        this.hours = 0;
    }

    @Override
    public String toString(){
        return name + " : " + age + ", " + seniority + ", " + hours + ".";
    }

    @Override
    public int compareTo(Object obj) {
        Employee entry = (Employee) obj;
        int result = this.name.compareTo(entry.getName());
        if(result != 0) {
            return result;
        }
        return 0;
    }

    public String getName(){
        return name;
    }
    public int getAge(){
        return age;
    }
    public int getSeniority(){
        return seniority;
    }
    protected int getHours(){
        return hours;
    }
    public abstract double getPayment();
}
