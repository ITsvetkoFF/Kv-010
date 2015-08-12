package test.shevchenko;

import com.softserve.edu.shevchenko.Employee;
import com.softserve.edu.shevchenko.Worker;

public class JuniorEmployeeStub extends Employee implements Worker {


    public JuniorEmployeeStub() {
        super("Vanya", 44, 15, 789);
    }

    @Override
    public double getPayment() {
        int start = 500;
        int iteration = 200;
        return start + iteration * getSeniority();
    }


}
