package com.softserve.edu.vlasenko;

/**
 * Created by v1doq on 10.08.2015.
 */
public abstract class Employee implements Worker, Comparable<Employee> {
    private final int Age_check = 18;
    String name;
    int age;
    int seniority;
    int hours;

    public Employee(String name, int age, int seniority, int hours) {
        if (age > Age_check && seniority >= 0 && (age - Age_check) >= seniority) {
            this.age = age;
            this.seniority = seniority;
        } else {
            throw new MyException("Error");
        }
        this.name = name;
        this.hours = hours;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getAge() {
        return age;
    }

    @Override
    public int getSeniority() {
        return seniority;
    }

    @Override
    public abstract double getPayment();
    protected int getHours()
    {
        return hours;

    }

    public int compareTo (Employee employee)
    {
        return employee.getName().compareTo(employee.getName());
    }
}

