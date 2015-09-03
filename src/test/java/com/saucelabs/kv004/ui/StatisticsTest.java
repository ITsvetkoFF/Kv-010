package com.saucelabs.kv004.ui;

import com.googlecode.javacv.FrameRecorder;
import com.saucelabs.pages.AnyPage;
import com.saucelabs.utility.Constant;
import com.saucelabs.pages.StatisticPage;
import org.testng.Assert;
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

    private WebDriver driver;
    private StatisticPage statisticPage;
    private AnyPage anyPage;
    private int actualNumber;
    private int expectedNumber;

    private String newUserFirstName = "FirstNameUser5";
    private String newUserSecondName = "SecondNameUser5";
    private String newUserEmailName = "lord62@top.com";
    private String newUserPasswordName = "password";

    @BeforeSuite
    public void setUp() throws FrameRecorder.Exception {
        driver = new FirefoxDriver();
        statisticPage = new StatisticPage(driver);
        anyPage = new AnyPage(driver);
        driver.manage().window().maximize();
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
        anyPage.logIn(Constant.Username, Constant.Password);
        statisticPage.waitSecond(1);
        statisticPage.baseURL();
        statisticPage.upSeverityInFirstSeverityProblemAndBackToStatisticPage();
        actualNumber = statisticPage.getSeverityNumberFirstProblem();
        anyPage.logOut();
        Assert.assertEquals(expectedNumber, actualNumber - 2);
    }

    @Test(dependsOnMethods = {"checkAddSeverityTest"})
    public void checkAddCommentsTest(){
        statisticPage.baseURL();
        anyPage.register(newUserFirstName, newUserSecondName, newUserEmailName, newUserPasswordName);
        statisticPage.baseURL();
        statisticPage.addCommentToFirstPopProblemAndBackToStatisticPage("Cool comment!");
        expectedNumber = statisticPage.getCommentNumberFromFirstDiscussedProblem();
        statisticPage.addCommentToFirstPopProblemAndBackToStatisticPage("Old School!");
        actualNumber = statisticPage.getCommentNumberFromFirstDiscussedProblem();
        statisticPage.baseURL();
        anyPage.logOut();
        Assert.assertEquals(expectedNumber, actualNumber - 1);
    }

    @AfterSuite
    public void tearDown() throws Exception {
        statisticPage.driverQuit();
    }


}

