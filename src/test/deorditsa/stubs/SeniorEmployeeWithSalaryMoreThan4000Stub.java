package test.deorditsa.stubs;

import com.softserve.edu.deorditsa.Employee;

/**
 * Created by acidroed on 12.08.2015.
 */
public class SeniorEmployeeWithSalaryMoreThan4000Stub extends Employee {

    public SeniorEmployeeWithSalaryMoreThan4000Stub() {
        //With this parametrs method getPayment will return 4000.0
        super("Ivan", 32, 10, 10000);
    }

    @Override
    public double getPayment() {
        double salary = 2500 + (getSeniority() * getHours() / 40);
        if (salary >= 4000) {
            salary = 4000.00;
        }
        return salary;
    }
}
