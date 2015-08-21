package com.saucelabs.Tests.DBTests.newTests;

import com.saucelabs.AnyPage;
import com.saucelabs.ResourcesPage;
import com.saucelabs.Tests.DAO.DeleteUserDAO;
import com.saucelabs.Tests.DAO.UserInfoDAO;
import com.saucelabs.Tests.DBTests.SingleWebdriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import utility.Constant;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by acidroed on 21.08.2015.
 */
public class CreateUserDBTest extends SingleWebdriver {

    ResourcesPage resourcesPage;
    AnyPage anyPage;
    UserInfoDAO userInfoDB;
    DeleteUserDAO deleteUserDAO;

    @Test(sequential = true, dataProvider = "SimpleUser", groups = {"createUser"})
    public void userRegistrationDBCheck(String UserName, String UserSurname, String UserEmail, String UserPassword, String UserRoleId, String UserRole) throws Exception {
        checkDriver();
        driver.get(Constant.URLlocal);
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
}
