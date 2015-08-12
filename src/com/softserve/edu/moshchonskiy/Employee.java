package com.softserve.edu.moshchonskiy;

/**
 * Created by Evgen on 8/8/2015.
 */
public abstract class Employee implements IWorker {

    public final static double JUNIOR_PER_HOUR = 10.00;

    public final static double SENIOR_PER_HOUR = 20.00;

    private String name;

    private int age;

    private int seniority;

    private int hours;

    public abstract double getPayment();


    public Employee(String name, int age, int seniority, int hours) throws MyException {

        this.name = name;

        if ((age > 18) && (age < 65)) {

            this.age = age;

        } else {
            throw new MyException("age is not correct");
        }

        if (seniority >= 2) {

            this.seniority = seniority;

        } else {
            throw new MyException("seniority is less than 2 years");
        }

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

    public int getHours() {
        return hours;
    }


}
