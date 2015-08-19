package com.saucelabs.Tests.LocalTests;

/**
 * Created by ykadytc on 20.10.2014.
 */

import com.saucelabs.AdminPage;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class AdminTest {
    //@Test
    public void adminLogin() {
            WebDriver driver = new FirefoxDriver();

            driver.get("http://localhost:8090");
            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
            JavascriptExecutor js = null;
            if (driver instanceof JavascriptExecutor) {
                js = (JavascriptExecutor) driver;
            }
            ;
            js.executeScript("navigator.geolocation.getCurrentPosition = function(success) { success({coords: {latitude: 50.649460, longitude: 30.731506}}); }");
            AdminPage adminPage = new AdminPage(driver);
            adminPage.logIn("admin@.com", "admin");

            Assert.assertTrue(1 + 1 == 2);
            driver.quit();
        }
    @Test
    public void adminGetProblems() {
            int count;
            String[] problemNames;
            List<WebElement> problems;
            WebDriver driver = new FirefoxDriver();

            driver.get("http://localhost:8090");
            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
            JavascriptExecutor js = null;
            if (driver instanceof JavascriptExecutor) {
                js = (JavascriptExecutor) driver;
            }
            ;
            js.executeScript("navigator.geolocation.getCurrentPosition = function(success) { success({coords: {latitude: 50.649460, longitude: 30.731506}}); }");
            AdminPage adminPage = new AdminPage(driver);
            adminPage.logIn("admin@.com", "admin");
            problems = adminPage.getAllProblemsForModeration();
            for (WebElement problem: problems) {
                problem.click();
                Assert.assertTrue(adminPage.checkProblemIsUnderModeration(problem.getText()));
                Assert.assertEquals(problem.getText(), adminPage.getProblemTitle());
                //adminPage.approveProblem(problem.getText());
            }
            adminPage.logOut();
            driver.quit();
        }
    }