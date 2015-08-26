package com.saucelabs.Tests.DBTests;

import com.saucelabs.AnyPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import utility.Constant;

import java.util.concurrent.TimeUnit;

/**
 * Created by ykadytc on 19.11.2014.
 */
public class SingleWebdriver {
   public static WebDriver driver;

    @BeforeSuite
    public static void setUp() {
        System.out.println("BeforeSuite driver create.");
        if (driver == null) {
            driver = new FirefoxDriver();
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            driver.get(Constant.URLlocal);
            AnyPage anypage = new AnyPage(driver);
            anypage.getFirstResourceTitleFromMenu();
        }
    }

    public static void checkDriver() {
        if (driver == null) {
            driver = new FirefoxDriver();
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        }
    }

    @AfterSuite
    public static void tearDown() {
        System.out.println("AfterSuite driver quit.");
        if (driver != null) {
            driver.quit();
        }
    }
}