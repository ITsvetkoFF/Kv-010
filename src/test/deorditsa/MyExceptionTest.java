package test.deorditsa;

import com.softserve.edu.deorditsa.Employee;
import com.softserve.edu.deorditsa.JuniorEmployee;
import com.softserve.edu.deorditsa.MyException;
import com.softserve.edu.deorditsa.SeniorEmployee;
import org.testng.annotations.Test;

/**
 * Created by acidroed on 12.08.2015.
 */
public class MyExceptionTest {

    @Test(expectedExceptions = MyException.class)
         public void testMyExceptionWhenIncorrectJuniorAge() {
        Employee em = new JuniorEmployee("Ivan", 18, 0, 1000);
    }

    @Test(expectedExceptions = MyException.class)
    public void testMyExceptionWhenIncorrectJuniorSeniority() {
        Employee em = new JuniorEmployee("Ivan", 20, 3, 1000);
    }

    @Test(expectedExceptions = MyException.class)
    public void testMyExceptionWhenIncorrectSeniorAge() {
        Employee em = new SeniorEmployee("Ivan", 81, 20, 10000);
    }

    @Test(expectedExceptions = MyException.class)
    public void testMyExceptionWhenIncorrectSeniorSeniority() {
        Employee em = new SeniorEmployee("Ivan", 50, 40, 10000);
    }
}
