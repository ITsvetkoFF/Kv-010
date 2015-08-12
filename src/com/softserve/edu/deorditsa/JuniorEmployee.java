package com.softserve.edu.deorditsa;

/**
 * One kind of Employees.
 * @author Acidroed
 */


public class JuniorEmployee extends Employee {

    public JuniorEmployee(String name, int age, int seniority, int hours) throws MyException {
        super(name, age, seniority, hours);
    }

    /** Overrided method getPayment.
     * Salary depends on seniority and working hours. For Junior maximum of salary is 1500$, minimum is 500$.
     * @return calulating of salary
     */
    @Override
    public double getPayment() {
        double salary = 500 + (super.getSeniority() * super.getHours() / 40);
        if (salary >= 1500) {
            salary = 1500.00;
        }
        return salary;
    }


}
