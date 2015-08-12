package test.deorditsa;

import com.softserve.edu.deorditsa.Employee;
import org.testng.Assert;
import org.testng.annotations.Test;
import test.deorditsa.stubs.JuniorEmployeeWithSalaryLessThan1500Stub;
import test.deorditsa.stubs.JuniorEmployeeWithSalaryMoreThan1500Stub;
import test.deorditsa.stubs.SeniorEmployeeWithSalaryLessThan4000Stub;
import test.deorditsa.stubs.SeniorEmployeeWithSalaryMoreThan4000Stub;

/**
 * Created by acidroed on 12.08.2015.
 */
public class EmployeeGetPaymentTest {

    @Test
    public void testGetPaymentForJuniorWithSalaryLessThan1500() {
        Employee emp = new JuniorEmployeeWithSalaryLessThan1500Stub();
        Assert.assertEquals(emp.getPayment(), 550.0);
    }

    @Test
    public void testGetPaymentForJuniorWithSalaryMoreThan1500() {
        Employee emp = new JuniorEmployeeWithSalaryMoreThan1500Stub();
        Assert.assertEquals(emp.getPayment(), 1500.0);
    }

    @Test
    public void testGetPaymentForSeniorWithSalaryLessThan4000() {
        Employee emp = new SeniorEmployeeWithSalaryLessThan4000Stub();
        Assert.assertEquals(emp.getPayment(), 3500.0);
    }

    @Test
    public void testGetPaymentForSeniorWithSalaryMoreThan4000() {
        Employee emp = new SeniorEmployeeWithSalaryMoreThan4000Stub();
        Assert.assertEquals(emp.getPayment(), 4000.0);
    }
}
