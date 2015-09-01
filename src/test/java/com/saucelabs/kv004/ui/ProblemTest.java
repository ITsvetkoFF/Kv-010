package com.saucelabs.kv004.ui;

import com.saucelabs.pages.AdminPage;
import com.saucelabs.pages.ProblemPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by vadym on 28.08.15.
 */
public class ProblemTest {

    private WebDriver driver;
    ProblemPage problemPage;
    AdminPage adminPage;

    @DataProvider(name = "dataCreateProblem", parallel = false)
    public static Object[][] testDataExample() {
        return new Object[][]{
                new Object[]{
                        "58.46",
                        "47.34",
                        "ProblemTest",
                        "Загрози біорізноманіттю",
                        "DescriptionTest",
                        "ProblemSolutionTest",
                        "http://i.imgur.com/ovdN1Sc.jpg",
                        "ImageCommentTest"
                }
        };
    }

    @BeforeSuite
    public void setUp() throws Exception {
        driver = new FirefoxDriver();
        driver.get("http://localhost:8090/#/map");
        driver.manage().window().maximize();
        problemPage = new ProblemPage(driver);
        adminPage = new AdminPage(driver);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @AfterSuite
    public  void turnDown() {
//        this.driver.quit();
    }

    @Test(dataProvider = "dataCreateProblem")
    public void createProblemByUser(String latitude, String longitude,
                                    String problemNameTest, String problemTypeTest,
                                    String problemDescriptionTest, String problemProposeTest,
                                    String imageUrls, String imageComments){

        double          latitudeTest        = Double.parseDouble(latitude);
        double          longitudeTest       = Double.parseDouble(longitude);
        List<String>    imageURLsTest       = Arrays.asList(imageUrls.split("\n"));
        List<String>    imageCommentsTest   = Arrays.asList(imageComments.split("\n"));

        problemPage.logIn("problemtestecomap@gmail.com", "qwerty");
        if(!problemPage.getLoggedInUserName().equals("USERTEST PROBLEMTEST")) {
            driver.navigate().refresh();
            registerUserAndLogIn("UserTest", "ProblemTest", "problemtestecomap@gmail.com", "qwerty");
        }

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        problemPage.addProblemToVisibleCenter(latitudeTest, longitudeTest, problemNameTest, problemTypeTest,
                problemDescriptionTest, problemProposeTest, imageURLsTest, imageCommentsTest);
    }

    private void registerUserAndLogIn(String first_name, String last_name, String email, String password) {
        problemPage.register(first_name, last_name, email, password);
    }

}
