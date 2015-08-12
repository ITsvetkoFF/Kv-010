package com.softserve.edu.deorditsa;

/**
 * Worker is a basic inteface for all workers in a company.
 * All workers, who implements this interface must have Name, Age, Seniority ann method for calculating salary.
 * @author Acidroed
 */
public interface Worker {


    public String getName();
    public int getAge();
    public int getSeniority();
    public double getPayment();
}
