package test.statsenko;

import org.junit.Assert;
import org.testng.annotations.Test;

/**
 * Created by stako on 11.08.2015.
 */
public class TestJuniorEmployeeGetPayment {

    @Test
    public void testGetPayment(){
        StubJuniorEmployee iJuniorEmployee = new StubJuniorEmployee();
        double actualPayment = 6.5 * iJuniorEmployee.getSeniority() * iJuniorEmployee.getHours();
        double expectedPayment = 22880;
        Assert.assertEquals(expectedPayment, actualPayment, 0);
    }
}
