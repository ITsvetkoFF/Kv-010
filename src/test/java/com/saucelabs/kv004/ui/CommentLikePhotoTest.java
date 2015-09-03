package com.saucelabs.kv004.ui;

import com.saucelabs.pages.AdminPage;
import com.saucelabs.pages.ProblemPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by Evgen on 01.09.15.
 */
public class CommentLikePhotoTest {

    public static double latitude = 50.064585;
    public static double longitude = 30.398938;
    public List<String> addedComments = Arrays.asList("Comment 1", "Comment 2", "Comment 3");
    public String problemNameTest = "VeryNewProblem";
    public String problemTypeTest = "—Ï≥ÚÚ∫Á‚‡ÎË˘‡";
    public String problemDescriptionTest = "Some trash on streets";
    public String problemProposeTest = "Kick the trash out";
    public List<String> imageUrls = Arrays.asList("https://www.eco-innovera.eu/lw_resource/datapool/_items/item_5/headerimage.jpg");
    public List<String> images = Arrays.asList("http://files.usmre.com/4138/Asheville%20NC%20Eco%20Homes.jpg");
    public List<String> imageComments = Arrays.asList("comment1");
    private WebDriver driver;
    ProblemPage problemPage;
    AdminPage adminPage;

    @DataProvider(name = "sampleTestData", parallel = false)
    public static Object[][] testDataExample() {
        return new Object[][]{
                new Object[]{
                        "admin@.com",
                        "admin",
                        "testFirstName",
                        "testLastName",
                        "test223g@test.com",
                        "test123"
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



    @Test(dataProvider = "sampleTestData")
    public void addCommentsLikesPhotos(String adminEmail, String adminPassword,
                                       String newUserFirstName, String newUserLastName,
                                       String newUserEmail, String newUserPassword) throws InterruptedException, IOException {

        problemPage.register(newUserFirstName, newUserLastName, newUserEmail, newUserPassword);
        Assert.assertEquals(problemPage.getLoggedInUserName().toUpperCase(),
                (newUserFirstName + " " + newUserLastName).toUpperCase());

        problemPage.addProblemToVisibleCenter(latitude, longitude, problemNameTest, problemTypeTest, problemDescriptionTest, problemProposeTest, imageUrls, imageComments);
        problemPage.clickAtProblemByCoordinateVisible(latitude, longitude);
        problemPage.addVoteToProblem();
        Assert.assertEquals(problemPage.getVote(), "1");

        driver.navigate().refresh();
        problemPage.logOut();

        problemPage.logIn(adminEmail, adminPassword);
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
        }

        problemPage.addComments(latitude, longitude, addedComments);
        int amountAfterAdding = problemPage.getComments().size();
        List<String> foundComments = problemPage.getComments();
        for (String comment : addedComments) {
            Assert.assertTrue(comment.trim().equals(foundComments.remove(0).trim()));
        }

        problemPage.addVoteToProblem();
        Assert.assertEquals(problemPage.getVote(), "1");

        problemPage.clickAtProblemByCoordinateVisible(latitude, longitude);
        int expectedImgs = adminPage.getNumberOfPhotos() + images.size();
        problemPage.addNewPhotos(images, latitude, longitude);
        int actualImgs = adminPage.getNumberOfPhotos();
        Assert.assertEquals(actualImgs, expectedImgs);

        problemPage.deleteComments(latitude, longitude);
        int amountAfterDeleting = problemPage.getComments().size();
        Assert.assertTrue(amountAfterDeleting == amountAfterAdding - addedComments.size());


        adminPage.deleteAllPhotos(latitude, longitude);
        Assert.assertTrue(adminPage.getNumberOfPhotos() == 0);

    }

    @AfterSuite
    public void turnDown() {
        this.driver.quit();
    }

}
