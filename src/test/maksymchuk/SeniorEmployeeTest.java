package test.maksymchuk;



import com.softserve.edu.maksymchuk.Worker;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * @author Denys Maxymchuk
 */
public class SeniorEmployeeTest {

    Worker e;

    @BeforeClass
    public void init(){
        e = new SeniorEmployeeStab();

    }
    @Test
    public void testGetPayment() throws Exception {
        Assert.assertTrue(Double.compare(e.getPayment(), 47.) == 0);
    }
}