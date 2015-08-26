package com.saucelabs.Tests.LocalTests;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.saucelabs.AnyPage;
import com.saucelabs.ProblemPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utility.Constant;
import utility.ExcelUtils;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * Created by Yermek on 27.11.2014.
 */
public class SmokeTest {

    @DataProvider(name = "xlsSmokeTestData", parallel = false)
    public static Object[][] data() throws Exception{
        return ExcelUtils.getTableArray(Constant.Path_SmokeTestData + Constant.File_SmokeTestData, "Sheet1");
    }
    @Test(dataProvider = "xlsSmokeTestData", timeOut = 60000)
    public void sampleWithTimeOut(String adminEmail, String adminPassword, String adminUsername,
                          String problemId, String problemTitle) throws IOException {

        //HtmlUnitDriver driver = new HtmlUnitDriver(BrowserVersion.FIREFOX_24);
        //driver.setJavascriptEnabled(true);

        WebDriver driver = new FirefoxDriver();
        //driver.get("http://localhost:8090");
        driver.get("http://176.36.11.25");
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

        AnyPage anyPage     = new AnyPage(driver);
        ProblemPage problemPage = new ProblemPage(driver);

        Assert.assertTrue(anyPage.getFirstResourceTitleFromMenu().equals(anyPage.getFirstResourceTitleFromOpenedResource()));

        anyPage.logIn(adminEmail, adminPassword);
        Assert.assertEquals(anyPage.getLoggedInUserName().toUpperCase(), adminUsername.toUpperCase());
        anyPage.logOut();

        problemPage.openProblemById(Integer.parseInt(problemId));
        Assert.assertEquals(problemPage.getProblemTitle(), problemTitle);

        driver.quit();
    }
}