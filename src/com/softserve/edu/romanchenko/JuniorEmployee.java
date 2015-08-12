package com.softserve.edu.romanchenko;

/**
 * Created by vika on 12.08.15.
 */
import com.softserve.edu.romanchenko.Employee;

public class JuniorEmployee extends Employee {
    public JuniorEmployee(String eName, int eAge, int eSeniority, int eHours) {
        super(eName, eAge, eSeniority, eHours);
    }

    public double getPayment() {
        return this.getAge() <= 20?(double)(this.getHours() * 12L):(this.getAge() <= 25?(double)(this.getHours() * 9L):(this.getAge() <= 30?(double)(this.getHours() * 7L):(double)(this.getHours() * 5L)));
    }
}
