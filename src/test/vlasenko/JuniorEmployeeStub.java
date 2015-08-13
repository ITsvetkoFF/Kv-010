package test.vlasenko;


import com.softserve.edu.vlasenko.Employee;
import com.softserve.edu.vlasenko.Worker;

public class JuniorEmployeeStub extends Employee implements Worker {


    public JuniorEmployeeStub() {
        super("Mariya", 23, 2, 4000);
    }

    @Override
    public double getPayment() {
        return 0;
    }


}