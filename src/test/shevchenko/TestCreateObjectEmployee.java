package test.shevchenko;

import com.softserve.edu.shevchenko.JuniorEmployee;
import com.softserve.edu.shevchenko.MyExceptionIlligalArgument;
import com.softserve.edu.shevchenko.SeniorEmployee;
import org.testng.annotations.Test;

public class TestCreateObjectEmployee {

    @Test(expectedExceptions = MyExceptionIlligalArgument.class)
    public void createEmployeeJuniorNegativeParameters1() {
        new JuniorEmployee("Anny", 2, 20, 148);
    }

    @Test(expectedExceptions = MyExceptionIlligalArgument.class)
    public void createEmployeeSeniorNegativeParameters1() {
        new SeniorEmployee("Anny", 2, 20, 148);
    }

    @Test(expectedExceptions = MyExceptionIlligalArgument.class)
    public void createEmployeeJuniorNegativeParameters2() {
        new JuniorEmployee("Anny", 23, 10, 160);
    }

    @Test(expectedExceptions = MyExceptionIlligalArgument.class)
    public void createEmployeeSeniorNegativeParameters2() {
        new SeniorEmployee("Anny", 23, 10, 160);
    }

    @Test
    public void createEmployeeJuniorPositiveParameters() {
        new JuniorEmployee("Anny", 30, 10, 148);
    }

    @Test
    public void createEmployeeSeniorPositiveParameters() {
        new SeniorEmployee("Anny", 30, 10, 148);
    }

}
