package test.shevchenko;

import com.softserve.edu.shevchenko.JuniorEmployee;
import com.softserve.edu.shevchenko.Worker;
import org.testng.AssertJUnit;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class JuniorEmployeeTest {

    Worker worker;

    @BeforeTest
    public void init(){
        worker = new JuniorEmployeeStub();
    }

    @Test
    public void testGetPayment() throws Exception {
        AssertJUnit.assertEquals(worker.getPayment(), 3500.0);
    }


}