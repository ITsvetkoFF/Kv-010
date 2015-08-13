package test.vlasenko;


import com.softserve.edu.vlasenko.Employee;
import com.softserve.edu.vlasenko.Worker;

public class JuniorEmployeeStub extends Employee implements Worker {


    public JuniorEmployeeStub(String mimi, int i, int i1, int i2) {
        super("Mariya", 23, 2, 4000);
    }

    @Override
    public double getPayment() {
        return 0;
    }


}