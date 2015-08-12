package test.maksymchuk;


import com.softserve.edu.maksymchuk.Worker;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


/**
 * @author Denys Maxymchuk
 */
public class JuniorEmployeeTest {

    Worker em;
    @BeforeClass
    public void init() {
        em = new JuniorEmployeeStab();
    }

    @Test
    public void testGetPayment() throws Exception {
        Assert.assertTrue(Double.compare(em.getPayment(), 1.4) == 0);
    }

}