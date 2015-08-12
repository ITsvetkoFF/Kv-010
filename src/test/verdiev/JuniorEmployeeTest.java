package test.verdiev;

import org.testng.Assert;
import org.testng.annotations.Test;

public class JuniorEmployeeTest {

    @Test
    public void testGetPayment() throws Exception {
        JuniorEmployeeStub jStab = new JuniorEmployeeStub();
        double expected = 1;
        double actual = jStab.getHours()/(jStab.getAge()*1.7);
        Assert.assertEquals(expected, actual);
    }
}