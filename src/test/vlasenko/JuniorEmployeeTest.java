package test.vlasenko;

import com.softserve.edu.vlasenko.Worker;
import org.testng.annotations.BeforeTest;


public class JuniorEmployeeTest {
    Worker test;

    @BeforeTest
    public void init() {
        test = new JuniorEmployeeStub("MyMi", 23, 15, 1222);

    }
}

