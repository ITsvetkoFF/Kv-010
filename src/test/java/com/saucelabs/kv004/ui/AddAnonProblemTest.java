package com.saucelabs.kv004.ui;

import com.saucelabs.pages.AdminPage;
import com.saucelabs.pages.AnyPage;
import com.saucelabs.pages.ProblemPage;
import com.saucelabs.utility.Constant;
import com.saucelabs.utility.SingletonWebDriver;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

/**
 * Created by vika on 02.09.15.
 *
 * In first test class anon adds 2 problems.
 * In second test class admin loges in and approve problem.
 * In third test class admin delete problem.
 */
public class AddAnonProblemTest {

    //Used in more than one test class
    AnyPage anyPage;
    ProblemPage problemPage;
    AdminPage adminPage;
    WebDriver driver = SingletonWebDriver.getInstance();

    String problemName1 = new String("the 1st problem name");
    String problemName2 = new String("thr 2nd problem name");

    //DataProvider for createProblem() test class
    //Latitude and longitude must be different each time. So if test failed, try to change them.
    @DataProvider(name = "TestData", parallel = false)
    public static Object[][] testDataExample() {
        return new Object[][]{
                new Object[]{
                        "problem type",
                        "problem description",
                        "problem propose",
                        46.4,
                        33.7
                }
        };
    }


    @Test(dataProvider = "TestData")
    public void createProblem(String problemTypeTest, String problemDescriptionTest, String problemProposeTest,
                              double latitude, double longitude)
                              throws SQLException, ClassNotFoundException {

        driver.get(Constant.URLlocal);

        anyPage     = new AnyPage(driver);
        problemPage = new ProblemPage(driver);
        anyPage.addProblemToVisibleCenter(latitude, longitude, problemName1, problemTypeTest,
                                          problemDescriptionTest, problemProposeTest, null, null);

        //problemPage = new ProblemPage(driver);
        anyPage.addProblemToVisibleCenter(latitude + 0.1, longitude - 0.1, problemName2, problemTypeTest,
                                          problemDescriptionTest, problemProposeTest, null, null);

        driver.navigate().refresh();
    }


    @Test(sequential = true, dependsOnMethods = {"createProblem"})
    public void adminAddProblem() throws IOException {

        anyPage.logIn(Constant.Username, Constant.Password);
        Assert.assertEquals(anyPage.getLoggedInUserName().toUpperCase(),
                ("admin").toUpperCase());

        adminPage = new AdminPage(driver);
        adminPage.approveProblem(problemName1);

        try {
            Thread.sleep(5000);
        } catch (Exception e) {
            e.printStackTrace();
        }

        int id = problemPage.getProblemId(46.4, 33.7);

        try {
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        }

        problemPage.openProblemById(id);
        Assert.assertEquals(problemPage.getProblemTitle(), problemName1);
    }

    @Test(sequential = true, dependsOnMethods = {"adminAddProblem"})
    public void adminDeleteProblem() throws IOException {

        adminPage = new AdminPage(driver);
        driver.navigate().refresh();
        adminPage.pressDeleteProblemButton();

        driver.close();
    }
}