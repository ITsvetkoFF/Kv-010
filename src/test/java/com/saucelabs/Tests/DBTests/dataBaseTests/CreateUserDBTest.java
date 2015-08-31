package com.saucelabs.Tests.DBTests.dataBaseTests;

import com.saucelabs.AnyPage;
import com.saucelabs.Tests.DBTests.dataBaseTests.dao.UsersDAO;
import com.saucelabs.Tests.DBTests.dataBaseTests.entities.Users;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utility.Constant;
import utility.ExcelUtils;
import utility.HashUtil;
import utility.SingletonWebDriver;


/**
 * Created by acidroed on 21.08.2015.
 */
public class CreateUserDBTest {

    AnyPage anyPage;
    UsersDAO userInfoDB = new UsersDAO();
    WebDriver driver = SingletonWebDriver.getInstance();

    @DataProvider(name = "SimpleUser", parallel = false)
    public static Object[][] data() throws Exception{
        System.out.println("In dataProvider");
        return ExcelUtils.getTableArray(Constant.Path_SimpleUserCreateData + Constant.File_SimpleUserCreateData, "Sheet1");
    }

    @Test(dataProvider = "SimpleUser", groups = {"CreateUser"})
    public void userRegistrationDBCheck(String userName, String userSurname, String userEmail, String userPassword, String userRoleId, String userRole) throws Exception {
        Users actualUser;
        driver.get(Constant.URLlocal);
        anyPage = new AnyPage(driver);
        anyPage.register(userName, userSurname, userEmail, userPassword);
        actualUser = userInfoDB.findUserByEmail(userEmail);

        Assert.assertEquals(actualUser.getName(), userName);
        Assert.assertEquals(actualUser.getSurname(), userSurname);
        Assert.assertEquals(actualUser.getPassword(), HashUtil.hmacSha1(userPassword, Constant.HashKey));
        Assert.assertEquals(actualUser.getUserRoles().getRole(), userRole);
    }



    @Test(sequential = true, dataProvider = "SimpleUser", dependsOnGroups = {"DeleteProblem"})
    public void deleteUser(String userName, String userSurname, String userEmail, String userPassword, String userRoleId, String userRole) throws Exception {
        userInfoDB.deleteUserByEmail(userEmail);
        Users actualUser = userInfoDB.findUserByEmail(userEmail);

        Assert.assertTrue(actualUser == null);
    }
}
