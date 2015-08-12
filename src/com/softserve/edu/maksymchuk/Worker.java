package com.softserve.edu.maksymchuk;


/**
 * <p>This interface describe base functionality of Employee
 * The <tt>Worker</tt> gives four get methods to access to  Employee fields
 * @author Denys Maxymchuk
 * @see com.softserve.edu.maksymchuk.Employee
 */
public interface Worker {

    /**
     *
     * @return workers name
     */
    String getName();

    /**
     *
     * @return workers age
     */
    int getAge();

    /**
     *
     * @return workers seniority
     */
    int getSeniority();

    /**
     *
     * @return workers payment
     */
    double getPayment();

}
