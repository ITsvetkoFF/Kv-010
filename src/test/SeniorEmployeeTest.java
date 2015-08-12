package test;

import com.softserve.edu.moshchonskiy.MyException;
import com.softserve.edu.moshchonskiy.SeniorEmployee;
import com.softserve.edu.moshchonskiy.stubs.StubSeniorEmployee;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

/**
 * Created by Evgen on 8/12/2015.
 */
public class SeniorEmployeeTest {

    @Test
    public void testGetPayment() throws Exception {

        StubSeniorEmployee seniorStub = new StubSeniorEmployee();

        SeniorEmployee junior = new SeniorEmployee("Kolya", 24, 4, 250 );

        double actualResult = junior.getPayment();

        double expected = ((seniorStub.getHours() * seniorStub.getSeniorPerHour()) * (seniorStub.getSeniority() + 100)) / 100;

        assertEquals(actualResult, expected, 000.1 );

    }

    @Test(expectedExceptions = MyException.class)

    public void constructorWithException( ) {

        SeniorEmployee sen = new SeniorEmployee("Sasha", 23, 1, 180);

    }
}