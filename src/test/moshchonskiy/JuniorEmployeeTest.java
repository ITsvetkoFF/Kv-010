package test.moshchonskiy;

import com.softserve.edu.moshchonskiy.JuniorEmployee;
import com.softserve.edu.moshchonskiy.MyException;
import com.softserve.edu.moshchonskiy.stubs.StubJuniorEmployee;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

/**
 * Created by Evgen on 8/12/2015.
 */
public class JuniorEmployeeTest {

    @Test
    public void testGetPayment() throws Exception {

        StubJuniorEmployee juniorStub = new StubJuniorEmployee();

        JuniorEmployee junior = new JuniorEmployee("Kolya", 24, 8, 200 );

        double actualResult = junior.getPayment();

        double expected = ((juniorStub.getHours() * juniorStub.getJunPerHour()) * (juniorStub.getSeniority() + 100)) / 100;

        assertEquals(actualResult, expected, 000.1 );

    }



    @Test(expectedExceptions = MyException.class)

    public void constructorWithException( ) {

        JuniorEmployee jun = new JuniorEmployee("Sasha", 16, 3, 180);

    }



}