package test.statsenko;

import org.junit.Assert;
import org.testng.annotations.Test;

/**
 * Created by stako on 11.08.2015.
 */
public class TestSeniorEmployeeGetPayment{

    @Test
    public void testGetPayment(){
        StubSeniorEmployee iStubSeniorEmployee = new StubSeniorEmployee();
        double actualPayment = 6.5 * iStubSeniorEmployee.getSeniority() * iStubSeniorEmployee.getHours();
        double expectedPayment = 22880;
        Assert.assertEquals(expectedPayment, actualPayment, 0);
    }
}
