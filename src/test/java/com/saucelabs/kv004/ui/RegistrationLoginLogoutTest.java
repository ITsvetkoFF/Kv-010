package com.saucelabs.kv004.ui;

import com.saucelabs.pages.AnyPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

/**
 * Created by yioteh on 27.08.15.
 */
public class RegistrationLoginLogoutTest {

    @DataProvider(name = "sampleTestData", parallel = false)
    public static Object[][] testDataExample() {
        return new Object[][]{
                new Object[]{
                        "admin@.com",
                        "admin",
                        "testRagFirstName",
                        "testGerLastName",
                        "test12131e@test.com",
                        "test123",
                        "test234"
                }
        };
    }

    @Test(dataProvider = "sampleTestData")
    public void sampleAll(String adminEmail, String adminPassword,
                          String newUserFirstName, String newUserLastName,
                          String newUserEmail, String newUserPassword, String newUserPasswordChange) throws IOException {

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
        anyPage.changePassword(newUserPassword, newUserPasswordChange);

        Assert.assertEquals(anyPage.getLoggedInUserName().toUpperCase(),
                (newUserFirstName + " " + newUserLastName).toUpperCase());
        anyPage.logOut();
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        anyPage.logIn(newUserEmail, newUserPasswordChange);
        try {
            Thread.sleep(2000);
        } catch (Exception e) {
            e.printStackTrace();
        }

        anyPage.logOut();
        driver.close();
    }
}