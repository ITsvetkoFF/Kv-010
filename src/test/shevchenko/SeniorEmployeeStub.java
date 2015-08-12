package test.shevchenko;

import com.softserve.edu.shevchenko.Employee;
import com.softserve.edu.shevchenko.Worker;

/**
 * Created by vshevchetc on 11.08.2015.
 */
public class SeniorEmployeeStub extends Employee implements Worker {


    public SeniorEmployeeStub() {
        super("Vadym", 52, 26, 789);
    }

    @Override
    public double getPayment() {
        int start = 2000;
        int iteration = 150;
        return start + iteration * getSeniority();
    }
}
