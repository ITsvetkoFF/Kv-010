package test.vlasenko;

import com.softserve.edu.deorditsa.Worker;
import com.softserve.edu.vlasenko.Employee;

/**
 * Created by mvlasetc on 12.08.2015.
 */
public class SeniorEmployeeStub extends Employee implements Worker{

    public SeniorEmployeeStub(String name, int age, int seniority, int hours)
    {
        super("Mary", 25, 3, 800);
    }

    @Override
    public double getPayment() {
        return 0;

    }
}