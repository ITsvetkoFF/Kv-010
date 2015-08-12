package com.softserve.edu.deorditsa;

/**
 * Employee is the abstract base class for all employees in a company.
 * All employees, who implements this interface must have Name, Age, Seniority, workins Hours ann method for calculating salary.
 * @author Acidroed
 */


public abstract class Employee implements Worker, Comparable <Employee> {

    private String name;
    private int age;
    private int seniority;
    private int hours;

    /** Constructor fo Employee.
     *  @param name Employees name.
     *  @param age Employees age. Can't be less 18s and over 80s.
     *  @param seniority Employees seniority. Employees senioritu. Can't be less 0 and over (age - 18).
     *  @param hours Employees working hours.
     *  @throws IllegalArgumentException Exeption appears when age or seniority is out of the valid range.
     */
    public Employee(String name, int age, int seniority, int hours) throws MyException {
        this.name = name;
        this.hours = hours;

        if ((age <= 18) || (age > 80)) {
//            System.out.println("------------------------------------------------------------------------------------");
//            System.out.println("Invalid age!!! Please try again, the age must be 18 <= age < 80");
//            System.out.println("------------------------------------------------------------------------------------");
            throw new MyException("Invalid age!!! Please try again, the age must be 18 <= age < 80");
        } else {
            this.age = age;
        }
        if ((seniority < 0) || (seniority > (age-18))) {
//            System.out.println("------------------------------------------------------------------------------------");
//            System.out.println("Invalid seniority!!! Please try again, the seniority must be 0 <= seniority <= (age - 18)");
//            System.out.println("------------------------------------------------------------------------------------");
            throw new MyException("Invalid seniority!!! Please try again, the seniority must be 0 <= seniority <= (age - 18)");
        } else {
            this.seniority = seniority;
        }

    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public int getSeniority() {
        return seniority;
    }

    protected int getHours() {
        return hours;
    }

    public abstract double getPayment();

    /** Overrided method compareTo.
     * Order of sort: by name, age, seniority, working hours.
     * @param employee Abstract Employee
     * @return result of compare (-1, 0, 1)
    */
    @Override
    public int compareTo(Employee employee) {
        int compareResult = 0;
        compareResult = this.getName().compareTo(employee.getName());
        if (compareResult == 0) {
            compareResult = Integer.compare(this.getAge(), employee.getAge());
        } else if (compareResult == 0) {
            compareResult = Integer.compare(this.getSeniority(), employee.getSeniority());
        } else if (compareResult == 0) {
            compareResult = Integer.compare(this.getHours(), employee.getHours());
        }
        return compareResult;
    }
}
