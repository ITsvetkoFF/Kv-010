package test.maksymchuk;

import com.softserve.edu.maksymchuk.JuniorEmployee;
import com.softserve.edu.maksymchuk.MyException;
import com.softserve.edu.maksymchuk.SeniorEmployee;
import org.testng.annotations.Test;

/**
* @author Denys Maxymchuk
        */
public class TestEmployeeCreate {
    @Test(expectedExceptions = MyException.class)
    public void testJuniorEmployeeConstructor(){
        new JuniorEmployee("a",1,1,1);
    }

    @Test(expectedExceptions = MyException.class)
    public void testSeniorEmployeeConstructor(){
        new SeniorEmployee("a",1,1,1);
    }
}
