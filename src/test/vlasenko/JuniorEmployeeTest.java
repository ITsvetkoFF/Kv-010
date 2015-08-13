package test.vlasenko;

import com.softserve.edu.vlasenko.Worker;
import org.testng.annotations.BeforeTest;


public class JuniorEmployeeTest {
    Worker test;

    @BeforeTest
    public void init() {
        test = new JuniorEmployeeStub("Nael", 23, 1, 1222);

    }
}

