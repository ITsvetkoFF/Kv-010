package test.vlasenko;

import com.softserve.edu.vlasenko.Worker;
import org.testng.AssertJUnit;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


public class JuniorEmployeeTest {
    Worker test;

    Worker worker;

    @BeforeTest
    public void init() {
        worker = new JuniorEmployeeStub();
    }

    @Test
    public void testGetPayment() throws Exception {
        AssertJUnit.assertEquals(worker.getPayment(), 1000);
    }
}
