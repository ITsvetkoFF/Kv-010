package com.saucelabs.Tests.OldTests;

import com.saucelabs.ProblemPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by onikistc on 23.10.2014.
 */
public class zOldProblemCommentsTest {
    public static double latitude = 50.164585;
    public static double longitude = 30.358658;
    public List<String> addedComments = Arrays.asList("Comment 1", "Comment 2", "Comment 3");

    @Test
    public void addComment() throws InterruptedException, IOException {
        WebDriver driver = new FirefoxDriver();
        ProblemPage problemPage = new ProblemPage(driver);

        driver.get("http://localhost:8090/#/map");
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        problemPage.logIn("admin@.com", "admin");
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
        }

        problemPage.addComments(latitude, longitude, addedComments);
        int amountAfterAdding = problemPage.getComments().size();
        List<String> foundComments = problemPage.getComments();
        for(String comment : addedComments) {
            Assert.assertTrue(comment.trim().equals(foundComments.remove(0).trim()));
        }
        problemPage.deleteComments(latitude, longitude);
//        driver.navigate().refresh();
        int amountAfterDeleting = problemPage.getComments().size();
        Assert.assertTrue(amountAfterDeleting == amountAfterAdding - addedComments.size());

        driver.quit();
    }
}
