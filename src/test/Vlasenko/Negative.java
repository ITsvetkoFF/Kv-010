package test.vlasenko;


import com.softserve.edu.vlasenko.JuniorEmployee;
import com.softserve.edu.vlasenko.SeniorEmployee;
import org.testng.annotations.Test;


public class Negative  {
    @Test (expectedExceptions = com.softserve.edu.vlasenko.MyException.class)
    public void testNegotiv1 () { new  JuniorEmployee("Susana", 24,25, 2000); }

    @Test (expectedExceptions = com.softserve.edu.vlasenko.MyException.class)
    public void testNegotiv2 ()  {
        new   SeniorEmployee("Susana", 24, 25, 2000);
    }

}