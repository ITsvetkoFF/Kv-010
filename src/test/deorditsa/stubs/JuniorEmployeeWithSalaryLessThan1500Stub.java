package test.deorditsa.stubs;

import com.softserve.edu.deorditsa.Employee;

/**
 * Created by acidroed on 12.08.2015.
 */
public class JuniorEmployeeWithSalaryLessThan1500Stub extends Employee {

    public JuniorEmployeeWithSalaryLessThan1500Stub() {
        //With this parametrs method getPayment will return 550.0
        super("Ivan", 22, 2, 1000);
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
