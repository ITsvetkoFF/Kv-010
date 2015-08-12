package com.softserve.edu.romanchenko;


import com.softserve.edu.romanchenko.MyException;
import com.softserve.edu.romanchenko.Worker;

public abstract class Employee implements Worker, Comparable<Employee> {
    protected String name;
    protected int age;
    protected int seniority;
    protected long hours;

    public Employee(String eName, int eAge, int eSeniority, int eHours) {
        this.name = eName;
        this.hours = (long)eHours;

        try {
            if(eAge < 18 || eAge > 80) {
                throw new MyException("Age of " + eName + " is out of range. " + "Check it and create this object again.");
            }

            this.age = eAge;
            if(eSeniority < 0 || eSeniority > 62) {
                throw new MyException("Seniority of " + eName + " is out of range. " + "Check it and create this object again.");
            }

            this.seniority = eSeniority;
            if(eAge < eSeniority + 17) {
                throw new MyException("Seniority and Age of " + eName + " are incorrect. " + "Check it and create this object again.");
            }
        } catch (MyException var6) {
            var6.printStackTrace();
            System.exit(1);
        }

    }

    public String getName() {
        return this.name;
    }

    public int getAge() {
        return this.age;
    }

    public int getSeniority() {
        return this.seniority;
    }

    public int compareTo(Employee employee) {
        return this.getName().compareTo(employee.getName());
    }

    protected long getHours() {
        return this.hours;
    }
}
