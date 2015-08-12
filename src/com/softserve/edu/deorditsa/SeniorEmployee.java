package com.softserve.edu.deorditsa;

/**
 * One kind of Employees.
 * @author Acidroed
 */


public class SeniorEmployee extends Employee {

    public SeniorEmployee(String name, int age, int seniority, int hours) throws MyException {
        super(name, age, seniority, hours);
    }

    /** Overrided method getPayment.
     * Salary depends on seniority and working hours. For Senior maximum of salary is 4000$, minimum is 2500$.
     * @return calulating of salary
     */
    @Override
    public double getPayment() {
        double salary = 2500 + (getSeniority() * getHours() / 40);
        if (salary >= 4000) {
            salary = 4000.00;
        }
        return salary;
    }
}
