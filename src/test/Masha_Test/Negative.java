package test.Masha_Test;


import com.softserve.edu.JuniorEmployee;
import com.softserve.edu.MyException;
import com.softserve.edu.SeniorEmployee;
import org.testng.annotations.Test;


public class Negative  {
    @Test (expectedExceptions = com.softserve.edu.MyException.class)
    public void testNegotiv1 () { new  JuniorEmployee("Susana", 24,25, 2000); }

    @Test (expectedExceptions = com.softserve.edu.MyException.class)
    public void testNegotiv2 ()  {
        new   SeniorEmployee("Susana", 24, 25, 2000);
    }

}