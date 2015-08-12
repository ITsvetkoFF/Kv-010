package com.softserve.edu.maksymchuk;

/**
 * @author Denys Maxymchuk
 * @see com.softserve.edu.maksymchuk.JuniorEmployee
 * @see com.softserve.edu.maksymchuk.SeniorEmployee
 */
public abstract class Employee implements Worker, Comparable<Employee> {
    private String name;
    private int age;
    private int seniority;
    private int hours;

    public Employee(String name, int age, int seniority, int hours) {
        if (!validateAge(age)) {
            throw new MyException("Don't valid age");
        }
        if (!validateSeniority(age, seniority)) {
            throw new MyException("Don't valid seniority");
        }
        this.name = name;
        this.age = age;
        this.seniority = seniority;
        this.hours = hours;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getAge() {
        return this.age;
    }

    @Override
    public int getSeniority() {
        return this.seniority;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb
                .append(this.getName())
                .append(", age - ")
                .append(this.getAge())
                .append(", seniority  - ")
                .append(this.getSeniority())
                .append(", hours - ")
                .append(this.getHours())
                .append(", payment - ")
                .append(this.getPayment());
        return sb.toString();
    }

    @Override
    public int compareTo(Employee o) {
        return this.name.compareTo(o.getName());
    }

    protected int getHours() {
        return this.hours;
    }

    /**
     * age validation function
     *
     * @param age the validation age value
     * @return true if age in [16,60]
     */
    private boolean validateAge(int age) {
        return (age > 15) && (age < 61);
    }

    private boolean validateSeniority(int age, int seniority) {
        return (age - seniority) > 15;
    }


}
