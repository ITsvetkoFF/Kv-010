package com.softserve.edu.vlasenko;

/**
 * Created by mvlasetc on 12.08.2015.
 */
public class JuniorEmployee extends Employee{
    public JuniorEmployee (String name, int age, int seniority, int hours)
    {
        super(name, age, seniority, hours);
    }

    public double getPayment()
    {
        double maxSalary = 1000;

        if ( getSeniority()<1)
        {
            maxSalary= maxSalary*0.5;
            if (getHours()>1000)
            {
                maxSalary=maxSalary+200;
            }
        }
        return maxSalary;

    }
}
