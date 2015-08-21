package com.saucelabs.Tests.DBTests.newTests;

import com.saucelabs.AnyPage;
import com.saucelabs.ResourcesPage;
import com.saucelabs.Tests.DAO.DeleteUserDAO;
import com.saucelabs.Tests.DAO.UserInfoDAO;
import com.saucelabs.Tests.DBTests.SingleWebdriver;
import com.saucelabs.Tests.DBTests.dao.UserRolesDAO;
import com.saucelabs.Tests.DBTests.dao.UsersDAO;
import com.saucelabs.Tests.DBTests.entities.Users;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import utility.Constant;
import utility.HashUtil;
import utility.SingletonWebDriver;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by acidroed on 21.08.2015.
 */
public class CreateUserDBTest {

    AnyPage anyPage;
    UsersDAO userInfoDB = new UsersDAO();;
    WebDriver driver = SingletonWebDriver.getDriver();

    @Test(sequential = true, dataProvider = "SimpleUser", groups = {"createUser"})
    public void userRegistrationDBCheck(String userName, String userSurname, String userEmail, String userPassword, String userRoleId, String userRole) throws Exception {
        driver.get(Constant.URLlocal);
        anyPage = new AnyPage(driver);
        UserRolesDAO userRolesDAO = new UserRolesDAO();

        anyPage.register(userName, userSurname, userEmail, userPassword);

        Users actualUser = userInfoDB.findUserByEmail(userEmail);

        Assert.assertEquals(actualUser.getName(), userName);
        Assert.assertEquals(actualUser.getSurname(), userSurname);
        Assert.assertEquals(actualUser.getPassword(), HashUtil.hmacSha1(userPassword, Constant.HashKey));
        Assert.assertEquals(actualUser.getUserRoles(), userRolesDAO.findUserRolesByRole(userRole));
    }


    @Test(sequential = true, dataProvider = "SimpleUser", dependsOnMethods = {"userRegistrationDBCheck"}, groups = {"createUser"})
    public void deleteUser(String userName, String userSurname, String userEmail, String userPassword, String userRoleId, String userRole) throws Exception {

        userInfoDB.deleteUserByEmail(userEmail);
        Users actualUser = userInfoDB.findUserByEmail(userEmail);
        Assert.assertTrue(actualUser == null);
    }
}
