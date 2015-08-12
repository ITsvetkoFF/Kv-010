package test;

import org.junit.Assert;
import org.testng.annotations.Test;

/**
 * Created by stako on 11.08.2015.
 */
public class TestSeniorEmployeeGetPaymentNegative {

    @Test
    public void getPaymentTest(){
        StubSeniorEmployeeNegative iStubSeniorEmployee = new StubSeniorEmployeeNegative();
        double actualPayment = 6.5 * iStubSeniorEmployee.getSeniority() * iStubSeniorEmployee.getHours();
        double expectedPayment = 2280;
        Assert.assertEquals(expectedPayment, actualPayment, 0);
    }
}
