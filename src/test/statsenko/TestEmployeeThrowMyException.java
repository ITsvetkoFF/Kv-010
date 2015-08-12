package test.statsenko;

import com.softserve.edu.statsenko.JuniorEmployee;
import com.softserve.edu.statsenko.MyException;
import org.testng.annotations.Test;

/**
 * Created by stako on 11.08.2015.
 */
public class TestEmployeeThrowMyException {

    @Test(expectedExceptions = MyException.class)
    public void testEmployeeThrowMyException(){
        new JuniorEmployee("Adam", 22, 23, 160);
    }
}
