package com.saucelabs.Tests.LocalTests;

import com.saucelabs.AnyPage;
import com.saucelabs.Tests.DAO.DeleteUserDAO;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import com.saucelabs.Tests.DBTests.dataBaseTests.dao.UsersDAO;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * Created by yioteh on 27.08.15.
 */
public class RegistrationLoginLogoutTest {

    UsersDAO userInfoDB = new UsersDAO();

    @DataProvider(name = "sampleTestData", parallel = false)
    public static Object[][] testDataExample() {
        return new Object[][]{
                new Object[]{
                        "admin@.com",
                        "admin",
                        "testRagFirstName",
                        "testGerLastName",
                        "test12131g@test.com",
                        "test123"
                }
        };
    }

    @Test(dataProvider = "sampleTestData")
    public void sampleAll(String adminEmail, String adminPassword,
                          String newUserFirstName, String newUserLastName,
                          String newUserEmail, String newUserPassword) throws IOException {

        WebDriver driver = new FirefoxDriver();
        driver.get("http://localhost:8090");
        //driver.get("http://176.36.11.25");
        AnyPage anyPage = new AnyPage(driver);
        anyPage.register(newUserFirstName, newUserLastName, newUserEmail, newUserPassword);
        anyPage.logOut();
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        anyPage.logIn(adminEmail, adminPassword);
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Assert.assertEquals(anyPage.getLoggedInUserName().toUpperCase(),
                ("admin").toUpperCase());
        anyPage.logOut();
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        anyPage.logIn(newUserEmail, newUserPassword);
        try {
            Thread.sleep(2000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Assert.assertEquals(anyPage.getLoggedInUserName().toUpperCase(),
                (newUserFirstName + " " + newUserLastName).toUpperCase());
        anyPage.logOut();
        driver.close();
    }
}