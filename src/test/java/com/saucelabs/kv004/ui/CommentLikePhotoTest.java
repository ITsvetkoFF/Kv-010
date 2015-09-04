package com.saucelabs.kv004.ui;

import com.saucelabs.pages.AdminPage;
import com.saucelabs.pages.ProblemPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import com.saucelabs.utility.Constant;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by Evgen on 01.09.15.
 */
public class CommentLikePhotoTest {

    ProblemPage problemPage;
    AdminPage adminPage;
    private double latitude = 50.082585;
    private double longitude = 30.398938;
    private List<String> addedComments = Arrays.asList("Comment 1", "Comment 2", "Comment 3");
    private String problemNameTest = "VeryNewProblem";
    private String problemTypeTest = "—Ï≥ÚÚ∫Á‚‡ÎË˘‡";
    private String problemDescriptionTest = "Some trash on streets";
    private String problemProposeTest = "Kick the trash out";
    private String workingDir = System.getProperty("user.dir");
    private List<String> imageUrls = Arrays.asList(workingDir + "\\resources\\images\\svalka.jpg");
    private List<String> images = Arrays.asList(workingDir + "\\resources\\images\\bomb.jpg");
    private List<String> imageComments = Arrays.asList("comment1");
    private WebDriver driver;
    private String newUserFirstName = "testFirstName";
    private String newUserLastName = "testLastName";
    private String newUserEmail = "test195g@test.com";
    private String newUserPassword = "test123";


    @BeforeSuite
    public void setUp() throws Exception {
        driver = new FirefoxDriver();
        driver.get(Constant.URLlocal);
        driver.manage().window().maximize();
        problemPage = new ProblemPage(driver);
        adminPage = new AdminPage(driver);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }


    @Test
    public void addCommentsLikesPhotos() throws InterruptedException, IOException {

        problemPage.register(newUserFirstName, newUserLastName, newUserEmail, newUserPassword);
        Assert.assertEquals(problemPage.getLoggedInUserName().toUpperCase(),
                (newUserFirstName + " " + newUserLastName).toUpperCase());

        problemPage.addProblemToVisibleCenter(latitude, longitude, problemNameTest, problemTypeTest, problemDescriptionTest, problemProposeTest, imageUrls, imageComments);
        problemPage.clickAtProblemByCoordinateVisible(latitude, longitude);
        problemPage.addVoteToProblem();
        Assert.assertEquals(problemPage.getVote(), "1");

        problemPage.addComments(latitude, longitude, addedComments);
        int filledComments = problemPage.getComments().size();
        List<String> foundComments = problemPage.getComments();
        for (String comment : this.addedComments) {
            Assert.assertTrue(comment.trim().equals(foundComments.remove(0).trim()));
        }

        driver.navigate().refresh();
        problemPage.logOut();

        problemPage.logIn(Constant.Username, Constant.Password);
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
        }

        problemPage.clickAtProblemByCoordinateVisible(latitude, longitude);
        int amountBeforeAdding = problemPage.getComments().size();
        problemPage.addComments(latitude, longitude, addedComments);
        int amountAfterAdding = problemPage.getComments().size();
        Assert.assertEquals(amountAfterAdding, amountBeforeAdding + filledComments);

        problemPage.addVoteToProblem();
        Assert.assertEquals(problemPage.getVote(), "1");

        problemPage.clickAtProblemByCoordinateVisible(latitude, longitude);
        int expectedImgs = problemPage.getNumberOfPhotos() + images.size();
        problemPage.addNewPhotos(images, latitude, longitude);
        int actualImgs = problemPage.getNumberOfPhotos();
        Assert.assertEquals(actualImgs, expectedImgs);

        problemPage.deleteComments(latitude, longitude);
        int amountAfterDeleting = problemPage.getComments().size();
        Assert.assertEquals(amountAfterDeleting, 0);


        adminPage.deleteAllPhotos(latitude, longitude);
        Assert.assertTrue(problemPage.getNumberOfPhotos() == 0);

    }

    @AfterSuite
    public void turnDown() {
        this.driver.quit();
    }

}
