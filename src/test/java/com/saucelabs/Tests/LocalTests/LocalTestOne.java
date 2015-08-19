package com.saucelabs.Tests.LocalTests;

import com.saucelabs.AdminPage;
import com.saucelabs.AnyPage;
import com.saucelabs.ProblemPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
* Created by Yermek on 30.10.2014.
 */
public class LocalTestOne {
    @DataProvider(name = "sampleTestData", parallel = false)
    public static Object[][] testDataExample() {
        return new Object[][]{
                new Object[]{
                        "51.96",
                        "31.96",
                        "problemTitle",
                        "Загрози біорізноманіттю",
                        "problemDescription",
                        "problemSolution",
                        "http://i.imgur.com/HHXCVbs.jpg" + "\n" + "http://i.imgur.com/1K6AdCH.jpg",
                        //"",
                        "imageComment1" + "\n" + "imageComment2",
                        //"",
                        "admin@.com",
                        "admin",
                        "testFirstName",
                        "testLastName",
                        "test7@test.com",
                        "test",
                        "Comment1"
                }
        };
    }

    @Test(dataProvider = "sampleTestData")
    public void sampleAll(String latitudeString, String longitudeString, String problemTitle,
                          String problemType, String problemDescription, String problemSolution,
                          String imageURLsString, String imageCommentsString,
                          String adminEmail, String adminPassword,
                          String newUserFirstName, String newUserLastName,
                          String newUserEmail, String newUserPassword,
                          String userCommentsString) throws IOException {
        double          latitude        = Double.parseDouble(latitudeString);
        double          longitude       = Double.parseDouble(longitudeString);
        List<String>    imageURLs       = Arrays.asList(imageURLsString.split("\n"));
        List<String>    imageComments   = Arrays.asList(imageCommentsString.split("\n"));
        List<String>    userComments    = Arrays.asList(userCommentsString.split("\n"));
        List<String>    receivedURLs;
        List<String>    receivedComments;
        int             id;

        WebDriver driver = new FirefoxDriver();
        driver.get("http://localhost:8090");
        //driver.get("http://176.36.11.25");
        driver.manage().window().maximize();

        AnyPage anyPage     = new AnyPage(driver);
        AdminPage adminPage   = new AdminPage(driver);
        ProblemPage problemPage = new ProblemPage(driver);

        anyPage.addProblemToVisibleCenter(latitude, longitude, problemTitle, problemType, problemDescription, problemSolution,
                imageURLs, imageComments);
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
        }
        adminPage.logIn(adminEmail, adminPassword);
        try {
            Thread.sleep(2000);
        } catch (Exception e) {
        }
        Assert.assertTrue(adminPage.checkProblemIsUnderModeration(problemTitle));
        adminPage.approveProblem(problemTitle);
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
        }
        /*
        adminPage.logOut();
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
        }
        */
        driver.navigate().refresh();
        id = problemPage.getProblemId(latitude, longitude);
        /*
        problemPage.openProblemById(id);
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
        }
        Assert.assertEquals(problemPage.getProblemTitle(), problemTitle);
        Assert.assertEquals(problemPage.getProblemType(), problemType);
        Assert.assertEquals(problemPage.getProblemDescription(), problemDescription);
        Assert.assertEquals(problemPage.getProblemPropose(), problemSolution);
        receivedURLs = problemPage.getAllImagesURLs();
        for(int i = 0; i < receivedURLs.size(); i++) {
            Assert.assertTrue(ImageDistanceCalculator.isImagesSimilar(receivedURLs.get(i), imageURLs.get(i)));
        }
        receivedComments = problemPage.getImagesComments();
        for(int i = 0; i < receivedComments.size(); i++){
            Assert.assertTrue(receivedComments.get(i).equals(imageComments.get(i)));
        }
        try {
            Thread.sleep(2000);
        } catch (Exception e) {
        }
        //problemPage.register(newUserFirstName, newUserLastName, newUserEmail, newUserPassword);
        problemPage.logIn(newUserEmail, newUserPassword);
        try {
            Thread.sleep(2000);
        } catch (Exception e) {
        }
        problemPage.addComments(latitude, longitude, userComments);
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
        }
        problemPage.logOut();

        adminPage.logIn(adminEmail, adminPassword);
        */
        try {
            Thread.sleep(2000);
        } catch (Exception e) {
        }
        //problemPage.deleteComments(latitude, longitude);
        problemPage.openProblemById(id);
        //adminPage.pressDeleteProblemButton();
        adminPage.logOut();

        driver.quit();
    }
}
