package test.verdiev;

import org.testng.Assert;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

/**
 * Created by yioteh on 11.08.15.
 */
public class JuniorEmployeeTest2 {

    @Test
    public void testGetPayment() throws Exception {
        JuniorEmployeeStub jStab = new JuniorEmployeeStub();
        double expected = 1;
        double actual = jStab.getHours()/(jStab.getAge()*1.7);
        Assert.assertEquals(expected, actual);
    }
}