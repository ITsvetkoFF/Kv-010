package com.saucelabs.kv004.ui;

import com.saucelabs.pages.AdminPage;
import com.saucelabs.pages.AnyPage;
import com.saucelabs.pages.ProblemPage;
import com.saucelabs.utility.Constant;
import com.saucelabs.utility.SingletonWebDriver;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

/**
 * Created by vika on 02.09.15.
 * First test class adds problem as anon
 * Second test class approve problem as admin.
 *
 */
public class AddAnonProblemTest {

    AnyPage anyPage;
    ProblemPage problemPage;
    AdminPage adminPage;
    WebDriver driver = SingletonWebDriver.getInstance();

    //One @DataProvider for both test classes
    @DataProvider(name = "TestData", parallel = false)
    public static Object[][] testDataExample() {
        return new Object[][]{
                new Object[]{
                        "admin@.com",
                        "admin",
                        "test problem name",
                        "test problem type",
                        "test problem description",
                        "test problem propose",
                        Arrays.asList("1 test image comment", "2 test image comment"),
                        Arrays.asList(new File("resources" + File.separator + "images"
                                        + File.separator + "bomb.jpg").getAbsolutePath(),
                                new File("resources" + File.separator + "images"
                                        + File.separator + "svalka.jpg").getAbsolutePath()),
                        50.2,
                        30.0
                }
        };
    }


    @Test(dataProvider = "TestData")
    public void createProblem(String adminEmail, String adminPassword, String problemNameTest, String problemTypeTest,
                              String problemDescriptionTest, String problemProposeTest, List<String> imageComments,
                              List<String>imagePath,double latitude, double longitude)
            throws SQLException, ClassNotFoundException {

        driver.get(Constant.URLlocal);
        anyPage = new AnyPage(driver);

        problemPage = new ProblemPage(driver);

        anyPage.addProblemToVisibleCenter(latitude, longitude, problemNameTest, problemTypeTest,
                problemDescriptionTest, problemProposeTest, imagePath, imageComments);

        driver.navigate().refresh();
    }


    @Test(dataProvider = "TestData", sequential = true, dependsOnMethods = {"createProblem"})
    public void adminAddProblem(String adminEmail, String adminPassword, String problemNameTest, String problemTypeTest,
                                String problemDescriptionTest, String problemProposeTest, List<String> imageComments,
                                List<String>imagePath,double latitude, double longitude) throws IOException {

        anyPage.logIn(adminEmail, adminPassword);

        try {
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        }

        adminPage = new AdminPage(driver);
        adminPage.approveProblem(problemNameTest);

        driver.close();
    }
}

