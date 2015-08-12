package com.softserve.edu.vlasenko;

/**
 * Created by mvlasetc on 12.08.2015.
 */
public class SeniorEmployee extends Employee{



    public SeniorEmployee (String name, int age, int seniority, int hours)
    {
        super(name, age, seniority, hours);
    }

    public double getPayment()
    {
        double maxSalary = 3000;

        if ( getSeniority()<3)
        {
            maxSalary= maxSalary*0.5;
            if (getHours()>3000)
            {
                maxSalary=maxSalary+500;
            }
        }
        return maxSalary;

    }
}
