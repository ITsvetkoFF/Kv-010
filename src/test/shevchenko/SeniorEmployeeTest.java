package test.shevchenko;

import com.softserve.edu.shevchenko.JuniorEmployee;
import com.softserve.edu.shevchenko.SeniorEmployee;
import com.softserve.edu.shevchenko.Worker;
import org.testng.AssertJUnit;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class SeniorEmployeeTest {

    Worker worker;

    @BeforeTest
    public void init(){
        worker = new SeniorEmployeeStub();
    }

    @Test
    public void testGetPayment() throws Exception {
        AssertJUnit.assertEquals(worker.getPayment(), 5750.0);
    }
}