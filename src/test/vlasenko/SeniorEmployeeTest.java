package test.vlasenko;

import com.softserve.edu.vlasenko.Worker;
import org.testng.annotations.BeforeTest;


public class SeniorEmployeeTest {
    Worker test;

    @BeforeTest
    public void init() {
        test = new SeniorEmployeeStub("Nael", 23, 1, 1222);

    }
}
