package com.saucelabs.kv004.ui;

import com.saucelabs.pages.AdminPage;
import com.saucelabs.pages.ProblemPage;
import com.saucelabs.utility.Constant;
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

    private String userEmail = "problemtestecomap@gmail.com";
    private String userPassword = "qwerty";

    private double latitude = 50.453201;
    private double longitude = 30.328796;

    @DataProvider(name = "dataProblem", parallel = false)
    public static Object[][] testDataProblem() {
        return new Object[][]{
                new Object[]{
                        "ProblemTest",
                        "Загрози біорізноманіттю",
                        "DescriptionTest",
                        "ProblemSolutionTest",
                        "http://i.imgur.com/ovdN1Sc.jpg", //can add more images just separate '/n' them like "image1 + '/n' + image2"
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
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        if (!problemPage.getLoggedInUserName().equalsIgnoreCase(userName + " " + userLastName)) {
            driver.navigate().refresh();
            registerUserAndLogIn(userName, userLastName, userEmail, userPassword);
        }

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        problemPage.addProblemToVisibleCenter(latitude, longitude, problemNameTest, problemTypeTest,
                problemDescriptionTest, problemProposeTest, imageURLsTest, imageCommentsTest);
        driver.navigate().refresh();
        driver.manage().timeouts().implicitlyWait(70, TimeUnit.SECONDS);
        problemPage.logOut();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @Test(dependsOnMethods = {"createProblemByUser"}, dataProvider = "dataProblem")
    public void viewProblemByAdmin(String problemNameTest, String problemTypeTest,
                                   String problemDescriptionTest, String problemProposeTest,
                                   String imageUrls, String imageComments) throws IOException, InterruptedException {
        List<String> imageURLsTest = Arrays.asList(imageUrls.split("\n"));
        List<String> imageCommentsTest = Arrays.asList(imageComments.split("\n"));

        // LogIn by admin
        problemPage.logIn(Constant.Username, Constant.Password);
        problemPage.clickAtProblemByCoordinateVisible(latitude, longitude);

        Assert.assertTrue(problemPage.getProblemType().equals(problemTypeTest));
        System.out.println(problemPage.getProblemType());
        Assert.assertTrue(problemPage.getProblemPropose().equals(problemProposeTest));
        Assert.assertTrue(problemPage.getProblemDescription().equals(problemDescriptionTest));
        Assert.assertTrue(problemPage.getProblemTitle().equals(problemNameTest));

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
    }

    @Test(dependsOnMethods = {"viewProblemByAdmin"}, dataProvider = "dataEditProblem")
    public void editProblemByAdmin(String problemNameTest,
                                   String problemDescriptionTest, String problemProposeTest) {
        problemPage.clickAtProblemByCoordinateVisible(latitude, longitude);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        problemPage.editProblemTitle(problemNameTest);
        problemPage.pressSaveButton();
        problemPage.editProblemDescription(problemDescriptionTest);
        problemPage.pressSaveButton();
        problemPage.editProblemPropose(problemProposeTest);
        problemPage.pressSaveButton();

        Assert.assertTrue(problemPage.getProblemTitle().equals(problemNameTest));
        Assert.assertTrue(problemPage.getProblemPropose().equals(problemProposeTest));
        Assert.assertTrue(problemPage.getProblemDescription().equals(problemDescriptionTest));

        problemPage.logOut();
    }

    @Test(dependsOnMethods = {"editProblemByAdmin"}, dataProvider = "dataEditProblem")
    public void viewProblemByAnonymous(String problemNameTest,
                                       String problemDescriptionTest, String problemProposeTest) {
        problemPage.clickAtProblemByCoordinateVisible(latitude, longitude);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        Assert.assertTrue(problemPage.getProblemTitle().equals(problemNameTest));
        Assert.assertTrue(problemPage.getProblemPropose().equals(problemProposeTest));
        Assert.assertTrue(problemPage.getProblemDescription().equals(problemDescriptionTest));

        driver.navigate().refresh();
    }

    @Test(dependsOnMethods = {"viewProblemByAnonymous"}, dataProvider = "dataEditProblem")
    public void viewProblemByUser(String problemNameTest,
                                  String problemDescriptionTest, String problemProposeTest) {
        problemPage.logIn(userEmail, userPassword);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        problemPage.clickAtProblemByCoordinateVisible(latitude, longitude);

        Assert.assertTrue(problemPage.getProblemTitle().equals(problemNameTest));
        Assert.assertTrue(problemPage.getProblemPropose().equals(problemProposeTest));
        Assert.assertTrue(problemPage.getProblemDescription().equals(problemDescriptionTest));

        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        problemPage.logOut();
        driver.navigate().refresh();
    }

    @Test(dependsOnMethods = {"viewProblemByUser"})
    public void editProblemByUser() {

    }

    @Test(dependsOnMethods = {"editProblemByUser"})
    public void deleteProblemByAdmin() {
        // LogIn by admin
        adminPage.logIn(Constant.Username, Constant.Password);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        adminPage.clickAtProblemByCoordinateVisible(latitude, longitude);
        adminPage.pressDeleteProblemButton();
    }


    @Test(dataProvider = "dataProblem", dependsOnMethods = {"deleteProblemByAdmin"})
    public void createProblemByAdmin(String problemNameTest, String problemTypeTest,
                                     String problemDescriptionTest, String problemProposeTest,
                                     String imageUrls, String imageComments) {

        List<String> imageURLsTest = Arrays.asList(imageUrls.split("\n"));
        List<String> imageCommentsTest = Arrays.asList(imageComments.split("\n"));

        problemPage.addProblemToVisibleCenter(latitude, longitude, problemNameTest, problemTypeTest,
                problemDescriptionTest, problemProposeTest, imageURLsTest, imageCommentsTest);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.navigate().refresh();
    }

    @Test(dependsOnMethods = {"createProblemByAdmin"})
    public void deleteAdminProblemByAdmin() {
        adminPage.clickAtProblemByCoordinateVisible(latitude, longitude);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        adminPage.pressDeleteProblemButton();
        driver.navigate().refresh();
        adminPage.logOut();
    }

    private void registerUserAndLogIn(String first_name, String last_name, String email, String password) {
        problemPage.register(first_name, last_name, email, password);
    }

}
