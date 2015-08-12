package com.softserve.edu.shevchenko;

/**
 * Created by Администратор on 08.08.2015.
 */
public abstract class Employee implements Worker, Comparable {

    private String name;
    private int age;
    private int seniority;
    private int hours;

    public Employee(String name, int age, int seniority, int hours) {
        this.name = name;
        this.age = age;
        this.seniority = seniority;
        this.hours = hours;

        int checkCorrect = age - seniority;
        int minOldForJob = 16;
        if (age < 16) {
            System.out.println("You typed incorrect age, pls check it");
            throw new MyExceptionIlligalArgument("incorrect type");
        }
        if (checkCorrect < minOldForJob) {
            System.out.println("You typed incorrect seniority, pls check it because you could not work " +
                    "earlier than you were sixteen years old");
            throw new MyExceptionIlligalArgument("incorrect type");
        }
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

    protected int getHours() {
        return hours;
    }

    @Override
    public int compareTo(Object employee) {
        return getName().compareTo(((Employee) employee).getName());
    }


}
