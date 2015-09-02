package com.saucelabs.kv004.ui;

import com.saucelabs.pages.AdminPage;
import com.saucelabs.pages.ProblemPage;
import com.saucelabs.utility.ImageDistanceCalculator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by vadym on 28.08.15.
 */
public class ProblemTest {

    private WebDriver driver;
    private ProblemPage problemPage;
    private AdminPage adminPage;

    private String adminLogin = "admin@.com";
    private String adminPassword = "admin";

    private String userEmail = "problemtestecomap@gmail.com";
    private String userPassword = "qwerty";

    private double latitude = 50.1514;
    private double longitude = 30.0214;

    @DataProvider(name = "dataProblem", parallel = false)
    public static Object[][] testDataProblem() {
        return new Object[][]{
                new Object[]{
                        "ProblemTest",
                        "Загрози біорізноманіттю",
                        "DescriptionTest",
                        "ProblemSolutionTest",
                        "http://i.imgur.com/ovdN1Sc.jpg",
                        "ImageCommentTest",
                }
        };
    }

    @DataProvider(name = "dataEditProblem", parallel = false)
    public static Object[][] testEditDataProblem() {
        return new Object[][]{
                new Object[]{
                        "ProblemTestEdited",
                        "DescriptionTestEdited",
                        "ProblemSolutionTestEdited",
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
    public void turnDown() {
        this.driver.quit();
    }

    @Test(dataProvider = "dataProblem")
    public void createProblemByUser(String problemNameTest, String problemTypeTest,
                                    String problemDescriptionTest, String problemProposeTest,
                                    String imageUrls, String imageComments) {

        String userName = "UserTest";
        String userLastName = "ProblemTest";

        List<String> imageURLsTest = Arrays.asList(imageUrls.split("\n"));
        List<String> imageCommentsTest = Arrays.asList(imageComments.split("\n"));

        problemPage.logIn(userEmail, userPassword);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        if (!problemPage.getLoggedInUserName().equalsIgnoreCase(userName + " " + userLastName)) {
//            driver.navigate().refresh();
            registerUserAndLogIn(userName, userLastName, userEmail, userPassword);
        }

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        problemPage.addProblemToVisibleCenter(latitude, longitude, problemNameTest, problemTypeTest,
                problemDescriptionTest, problemProposeTest, imageURLsTest, imageCommentsTest);
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        problemPage.logOut();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @Test(dependsOnMethods = {"createProblemByUser"}, dataProvider = "dataProblem")
    public void viewProblemByAdmin(String problemNameTest, String problemTypeTest,
                                   String problemDescriptionTest, String problemProposeTest,
                                   String imageUrls, String imageComments) throws IOException {
        List<String> imageURLsTest = Arrays.asList(imageUrls.split("\n"));
        List<String> imageCommentsTest = Arrays.asList(imageComments.split("\n"));

        problemPage.logIn(adminLogin, adminPassword);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        problemPage.clickAtProblemByCoordinateVisible(latitude, longitude);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        Assert.assertTrue(problemPage.getProblemTitle().equals(problemNameTest));
        Assert.assertTrue(problemPage.getProblemType().equals(problemTypeTest));
        Assert.assertTrue(problemPage.getProblemPropose().equals(problemProposeTest));
        Assert.assertTrue(problemPage.getProblemDescription().equals(problemDescriptionTest));

        // Check problem image
        List<String> receivedUrls = problemPage.getAllImagesURLs();
        for (int i = 0; i < receivedUrls.size(); i++) {
            Assert.assertTrue(ImageDistanceCalculator.isImagesSimilar(receivedUrls.get(i), imageURLsTest.get(i)));
        }

        // Check problem comment
        List<String> receivedComments = problemPage.getImagesComments();
        for (int i = 0; i < receivedComments.size(); i++) {
            Assert.assertTrue(receivedComments.get(i).equals(imageCommentsTest.get(i)));
        }
        problemPage.clickZoomOut();
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
    }

    @Test(dependsOnMethods = {"viewProblemByAdmin"}, dataProvider = "dataEditProblem")
    public void editProblemByAdmin(String problemNameTest,
                                   String problemDescriptionTest, String problemProposeTest) {
        problemPage.clickAtProblemByCoordinateVisible(latitude, longitude);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        problemPage.editProblemTitle(problemNameTest);
        problemPage.editProblemDescription(problemDescriptionTest);
        problemPage.editProblemPropose(problemProposeTest);

        Assert.assertTrue(problemPage.getProblemTitle().equals(problemNameTest));
        Assert.assertTrue(problemPage.getProblemPropose().equals(problemProposeTest));
        Assert.assertTrue(problemPage.getProblemDescription().equals(problemDescriptionTest));

        problemPage.logOut();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @Test(dependsOnMethods = {"editProblemByAdmin"}, dataProvider = "dataEditProblem")
    public void viewProblemByAnonymous(String problemNameTest,
                                       String problemDescriptionTest, String problemProposeTest) {
        problemPage.clickAtProblemByCoordinateVisible(latitude, longitude);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        Assert.assertTrue(problemPage.getProblemTitle().equals(problemNameTest));
        Assert.assertTrue(problemPage.getProblemPropose().equals(problemProposeTest));
        Assert.assertTrue(problemPage.getProblemDescription().equals(problemDescriptionTest));

        problemPage.clickZoomOut();
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
    }

    @Test(dependsOnMethods = {"viewProblemByAnonymous"}, dataProvider = "dataEditProblem")
    public void viewProblemByUser(String problemNameTest,
                                  String problemDescriptionTest, String problemProposeTest) {
        problemPage.logIn(userEmail, userPassword);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        problemPage.clickAtProblemByCoordinateVisible(latitude, longitude);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        Assert.assertTrue(problemPage.getProblemTitle().equals(problemNameTest));
        Assert.assertTrue(problemPage.getProblemPropose().equals(problemProposeTest));
        Assert.assertTrue(problemPage.getProblemDescription().equals(problemDescriptionTest));

        problemPage.clickZoomOut();
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
    }

    @Test(dependsOnMethods = {"viewProblemByUser"})
    public void editProblemByUser() {

    }

    @Test(dependsOnMethods = {"editProblemByUser"})
    public void deleteProblemByAdmin() {
        adminPage.logIn(adminLogin, adminPassword);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        adminPage.clickAtProblemByCoordinateVisible(latitude, longitude);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        adminPage.pressDeleteProblemButton();
    }


    @Test(dataProvider = "dataProblem", dependsOnMethods = {"deleteProblemByAdmin"})
    public void createProblemByAdmin(String problemNameTest, String problemTypeTest,
                                     String problemDescriptionTest, String problemProposeTest,
                                     String imageUrls, String imageComments) {

        List<String> imageURLsTest = Arrays.asList(imageUrls.split("\n"));
        List<String> imageCommentsTest = Arrays.asList(imageComments.split("\n"));

        problemPage.logIn(adminLogin, adminPassword);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        // BE CAREFUL, HERE I AM CHANGING THE COORDINATES FROM DATA PROVIDER!
        problemPage.addProblemToVisibleCenter(latitude + 0.4, longitude + 0.4, problemNameTest, problemTypeTest,
                problemDescriptionTest, problemProposeTest, imageURLsTest, imageCommentsTest);
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        problemPage.clickZoomOut();
    }

    @Test(dependsOnMethods = {"createProblemByAdmin"})
    public void deleteAdminProblemByAdmin() {
        // BE CAREFUL, HERE I AM CHANGING THE COORDINATES FROM DATA PROVIDER!
        adminPage.clickAtProblemByCoordinateVisible(latitude + 0.4, longitude + 0.4);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        adminPage.pressDeleteProblemButton();
    }

    private void registerUserAndLogIn(String first_name, String last_name, String email, String password) {
        problemPage.register(first_name, last_name, email, password);
    }

}
