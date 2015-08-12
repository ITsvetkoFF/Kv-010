package test.statsenko;

import com.softserve.edu.statsenko.MyException;
import org.junit.Assert;
import org.testng.annotations.Test;

/**
 * Created by stako on 11.08.2015.
 */
public class TestEmployeeThrowMyException {

    @Test(expectedExceptions = MyException.class, timeOut = 10000)
    public void testEmployeeThrowMyException(){
        StubEmployee employee = new StubEmployee();
        Assert.assertNotNull(employee);
    }
}
