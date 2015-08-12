package test.deorditsa.stubs;

import com.softserve.edu.deorditsa.Employee;

/**
 * Created by acidroed on 12.08.2015.
 */
public class JuniorEmployeeWithSalaryMoreThan1500Stub extends Employee {

    public JuniorEmployeeWithSalaryMoreThan1500Stub() {
        //With this parametrs method getPayment will return 1500.0
        super("Ivan", 32, 4, 10000);
    }

    @Override
    public double getPayment() {
        double salary = 500 + (super.getSeniority() * super.getHours() / 40);
        if (salary >= 1500) {
            salary = 1500.00;
        }
        return salary;
    }
}
