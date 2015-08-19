package com.saucelabs.Tests.DBTests;

import com.google.common.base.Utf8;
import com.saucelabs.AnyPage;
import com.saucelabs.MapPage;
import com.saucelabs.ProblemPage;
import com.saucelabs.ResourcesPage;
import com.saucelabs.Tests.DAO.CreateNewUserDAO;
import com.saucelabs.Tests.DAO.DeleteUserDAO;
import com.saucelabs.Tests.DAO.UserInfoDAO;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import utility.Constant;
import utility.ExcelUtils;

import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;


/**
 * Created by Olya on 11/3/14.
 */
public class CreateUserTest extends SingleWebdriver{
    ResourcesPage resourcesPage;
    AnyPage anyPage;
    UserInfoDAO userInfoDB;
    DeleteUserDAO deleteUserDAO;

    @DataProvider(name = "SimpleUser", parallel = false)
    public static Object[][] data() throws Exception{
        return ExcelUtils.getTableArray(Constant.Path_SimpleUserCreateData + Constant.File_SimpleUserCreateData, "Sheet1");
    }

    @Test(sequential = true, dataProvider = "SimpleUser", groups = {"createUser"})
    public void userRegistrationDBCheck(String UserName, String UserSurname, String UserEmail, String UserPassword, String UserRoleId, String UserRole) throws Exception {
        checkDriver();
        driver.get(Constant.URLlocal);
        resourcesPage = new ResourcesPage(driver);
        anyPage = new AnyPage(driver);
        userInfoDB = new UserInfoDAO();
        deleteUserDAO = new DeleteUserDAO();

        anyPage.register(UserName, UserSurname, UserEmail, UserPassword);

        Map result = userInfoDB.getInfo("root", "root","jdbc:mysql://localhost:3306/enviromap",UserEmail);
        Map<String, String> ExpectedUserData = new HashMap<String, String>();
        ExpectedUserData.put("Name", UserName);
        ExpectedUserData.put("Surname", UserSurname);
        ExpectedUserData.put("Password", userInfoDB.hmacSha1(UserPassword,Constant.HashKey));
        ExpectedUserData.put("UserRoles_Id", UserRoleId);

        Assert.assertEquals(result, ExpectedUserData);
    }

    @Test(sequential = true, dataProvider = "SimpleUser", dependsOnMethods = {"userRegistrationDBCheck"}, groups = {"createUser"})
    public void userCookiesCheck(String UserName, String UserSurname, String UserEmail, String UserPassword, String UserRoleId, String UserRole) throws Exception {

        Map<String, String> ExpectedUserData = new HashMap<String, String>();
        ExpectedUserData.put("Name", UserName);
        ExpectedUserData.put("Surname", UserSurname);
        ExpectedUserData.put("Email", UserEmail);
        ExpectedUserData.put("UserRole", UserRole);
        Map result = anyPage.getCookiesName();

        Assert.assertEquals(result,ExpectedUserData);
    }

    @Test(sequential = true, dataProvider = "SimpleUser", dependsOnMethods = {"userCookiesCheck"}, groups = {"createUser"})
    public void userNameCheckAfterLogin(String UserName, String UserSurname, String UserEmail, String UserPassword, String UserRoleId, String UserRole) throws Exception {

        String name = anyPage.checkUsernameInRightCorner();
        Assert.assertEquals((UserName + " " + UserSurname).toUpperCase(), name);
    }

    @Test(sequential = true, dataProvider = "SimpleUser", dependsOnMethods = {"userNameCheckAfterLogin"}, groups = {"createUser"})
    public void userAddNewResourceAvailability(String UserName, String UserSurname, String UserEmail, String UserPassword, String UserRoleId, String UserRole) throws Exception {

        Assert.assertEquals(resourcesPage.existResource("ДОДАТИ НОВИЙ РЕСУРС"), null);
    }

    @Test(sequential = true, dataProvider = "SimpleUser", dependsOnMethods = {"userAddNewResourceAvailability"}, groups = {"createUser"})
    public void userNewsAvailability(String UserName, String UserSurname, String UserEmail, String UserPassword, String UserRoleId, String UserRole) throws Exception {

        Assert.assertEquals(anyPage.checkNewsAvailability(),false);
    }

    @Test(sequential = true, dataProvider = "SimpleUser", dependsOnMethods = {"userNewsAvailability"}, groups = {"createUser"})
    public void changePassword(String UserName, String UserSurname, String UserEmail, String UserPassword, String UserRoleId, String UserRole) throws Exception {

        anyPage.changePassword(UserPassword,"testpassword");
        Map result = userInfoDB.getInfo("root", "root","jdbc:mysql://localhost:3306/enviromap",UserEmail);
        Map<String, String> ExpectedUserData = new HashMap<String, String>();
        ExpectedUserData.put("Name", UserName);
        ExpectedUserData.put("Surname", UserSurname);
        ExpectedUserData.put("Password", userInfoDB.hmacSha1("testpassword",Constant.HashKey));
        ExpectedUserData.put("UserRoles_Id", UserRoleId);

        Assert.assertEquals(result, ExpectedUserData);
        anyPage.logOut();
    }

    @Test(sequential = true, dataProvider = "SimpleUser", dependsOnMethods = {"changePassword"}, groups = {"createUser"})
    public void deleteUser(String UserName, String UserSurname, String UserEmail, String UserPassword, String UserRoleId, String UserRole) throws Exception {

        deleteUserDAO.deleteUser(UserEmail);
        Map result = userInfoDB.getInfo("root", "root","jdbc:mysql://localhost:3306/enviromap",UserEmail);

        Assert.assertTrue(result.isEmpty());
    }
}

