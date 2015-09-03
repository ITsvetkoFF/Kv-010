package com.saucelabs.kv004.ui;

import com.saucelabs.pages.AnyPage;
import com.saucelabs.pages.StatisticPage;
import org.junit.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;
import java.util.concurrent.TimeUnit;

/**
 * Created by yioteh on 27.08.15.
 * Refactoring stako on 02.09.2015.
 */
public class StatisticsTest{

    WebDriver driver;
    private StatisticPage statisticPage;
    private AnyPage anyPage;
    private int actualNumber;
    private int expectedNumber;

    @BeforeSuite
    public void setUp() throws Exception {
        driver = new FirefoxDriver();
        statisticPage = new StatisticPage(driver);
        anyPage = new AnyPage(driver);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.get("http://localhost:8090/#/statistic");
    }

    @Test
    public void checkAddLikesTest(){
        expectedNumber = statisticPage.getLikesNumberFirstPopProblem();
        statisticPage.likeFirstPopProblemAndBackToStatisticPage();
        actualNumber = statisticPage.getLikesNumberFirstPopProblem();
        Assert.assertEquals(expectedNumber, actualNumber - 1);
    }

    @Test(dependsOnMethods = {"checkAddLikesTest"})
    public void checkAddSeverityTest(){
        statisticPage.baseURL();
        expectedNumber = statisticPage.getSeverityNumberFirstProblem();
        anyPage.logIn("admin@.com", "admin");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        statisticPage.baseURL();
        statisticPage.upSeverityInFirstSeverityProblemAndBackToStatisticPage();
        actualNumber = statisticPage.getSeverityNumberFirstProblem();
        anyPage.logOut();
        Assert.assertEquals(expectedNumber, actualNumber - 2);
    }

    @Test(dependsOnMethods = {"checkAddSeverityTest"})
    public void checkAddCommentsTest(){
        statisticPage.baseURL();
        anyPage.register("FirstNameUser6", "SecondNameUser6", "lord6@top.com", "userPassword");
        statisticPage.baseURL();
        statisticPage.addCommentToFirstPopProblemAndBackToStatisticPage("Cool comment!");
        statisticPage.addCommentToFirstPopProblemAndBackToStatisticPage("Old School!");
        statisticPage.addCommentToFirstSeverityProblemAndBackToStatisticPage("Don't worry.");
        statisticPage.baseURL();
        int[] a = statisticPage.getFirstAndSecondCommentNumber();
        anyPage.logOut();
        Assert.assertTrue(a[0] >= a[1]);
    }

    @AfterSuite
    public void tearDown() throws Exception {
        statisticPage.driverQuit();
    }

}

