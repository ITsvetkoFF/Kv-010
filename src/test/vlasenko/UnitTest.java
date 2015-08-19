package test.vlasenko;

import com.softserve.edu.vlasenko.JuniorEmployee;
import com.softserve.edu.vlasenko.MyException;
import com.softserve.edu.vlasenko.SeniorEmployee;
import org.testng.annotations.Test;

/**
 * Created by mvlasetc on 12.08.2015.
 */
public class UnitTest {



    @Test(expectedExceptions = MyException.class)
    public void createEmployeeJuniorNegativeParameters1() {
        new JuniorEmployee("MyMan", 3, 15, 1000);
    }

    @Test(expectedExceptions = MyException.class)
    public void createEmployeeSeniorNegativeParameters1() {
        new SeniorEmployee("MurMan", 3, 15, 1000);
    }

    @Test(expectedExceptions = MyException.class)
    public void createEmployeeJuniorNegativeParameters2() {
        new JuniorEmployee("MiMaN", 3, 15, 1000);
    }

    @Test(expectedExceptions = MyException.class)
    public void createEmployeeSeniorNegativeParameters2() {
        new SeniorEmployee("MyMan", 3, 15, 1000);
    }

    @Test
    public void createEmployeeJuniorPositiveParameters() {
        new JuniorEmployee("MemOn", 3, 15, 1000);
    }

    @Test
    public void createEmployeeSeniorPositiveParameters() {
        new SeniorEmployee("MyrMan", 3, 15, 1000);
    }

}