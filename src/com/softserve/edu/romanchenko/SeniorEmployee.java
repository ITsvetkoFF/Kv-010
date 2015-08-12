package com.softserve.edu;

import com.softserve.edu.Employee;

public class SeniorEmployee extends Employee {
    public SeniorEmployee(String eName, int eAge, int eSeniority, int eHours) {
        super(eName, eAge, eSeniority, eHours);
    }

    public double getPayment() {
        return (double)(this.getHours() * (long)this.getAge());
    }
}

