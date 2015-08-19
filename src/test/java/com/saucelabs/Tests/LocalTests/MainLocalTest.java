package com.saucelabs.Tests.LocalTests;

import com.saucelabs.AdminPage;
import com.saucelabs.AnyPage;
import com.saucelabs.ProblemPage;
import utility.ImageDistanceCalculator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

/**
 * Created by ykadytc on 30.10.2014.
 */
public class MainLocalTest {

    @DataProvider(name = "testDataExample", parallel = true)
    public static Object[][] testDataExample(Method testMethod) {
        return new Object[][]{
                new Object[]{
                        "50.5",                                                                   // latitude
                        "30.5",                                                                   // longitude
                        "problemTitle",                                                           // problemTitle
                        "problemDescription",                                                     // problemDescription
                        "problemSolution",                                                        // problemSolution
                        "Проблеми лісів",                                                         // problemType
                        "", //http://i.imgur.com/HHXCVbs.jpg" +"\n"+ "http://i.imgur.com/1K6AdCH.jpg", // URLs
                        "", //"imageComment1" + "\n" + "imageComment2",                                 // comments
                        "admin@.com",                                                             // admin email
                        "admin",                                                                  // admin password
                        "testFirstName",                                                          // new user first name
                        "testLastName",                                                           // new user last name
                        "test@test.com",                                                          // new user email
                        "test",                                                                   // new user password
                        "comment1"                                                                // user's comment
                }

        };
    }

    @Test(dataProvider = "testDataExample", groups = "mainLocalTests")
    public void problemTests(String latitudeString, String longitudeString,
                             String problemTitle, String problemDescription, String problemSolution, String problemType,
                             String imageURLsString, String imageCommentsString,
                             String adminEmail, String adminPassword,
                             String newUserFirstName, String newUserLastName,
                             String newUserEmail, String newUserPassword,
                             String userComment) throws IOException {
        double       latitude      = Double.parseDouble(latitudeString);
        double       longitude     = Double.parseDouble(longitudeString);
        List<String> imageURLs     = Arrays.asList(imageURLsString.split("\n"));
        List<String> imageComments = Arrays.asList(imageCommentsString.split("\n"));

        WebDriver driver;
        driver = new FirefoxDriver();
        System.out.println("Browser opened");
        driver.get("http://localhost:8090/#/map");
        driver.manage().window().maximize();

        AnyPage anyPage     = new AnyPage(driver);
        AdminPage adminPage   = new AdminPage(driver);
        ProblemPage problemPage = new ProblemPage(driver);

        List<String> gettedURLs;
        List<String> gettedComments;



        anyPage.addProblemToVisibleCenter(latitude, longitude, problemTitle, problemType, problemDescription,
                problemSolution, imageURLs, imageComments);
        anyPage.logIn(adminEmail, adminPassword);

        Assert.assertTrue(adminPage.checkProblemIsUnderModeration(problemTitle));

        adminPage.approveProblem(problemTitle);
        adminPage.logOut();

        problemPage.clickAtProblemByCoordinateVisible(latitude, longitude);

        Assert.assertTrue(problemPage.getProblemTitle().equals(problemTitle));
        Assert.assertTrue(problemPage.getProblemType().equals(problemType));
        Assert.assertTrue(problemPage.getProblemDescription().equals(problemDescription));
        Assert.assertTrue(problemPage.getProblemPropose().equals(problemSolution));

        gettedURLs = problemPage.getAllImagesURLs();
        for(int i = 0; i < gettedURLs.size(); i++) {
            Assert.assertTrue(ImageDistanceCalculator.isImagesSimilar(gettedURLs.get(i), imageURLs.get(i)));
        }
        gettedComments = problemPage.getImagesComments();
        for(int i = 0; i < gettedComments.size(); i++){
            Assert.assertTrue(gettedComments.get(i).equals(imageComments.get(i)));
        }

        anyPage.register(newUserFirstName, newUserLastName, newUserEmail, newUserPassword);

//        problemPage.addComment(latitude, longitude, userComment);
        problemPage.logOut();

//        problemPage.logIn(adminEmail, adminPassword);
//        problemPage.deleteComment(latitude, longitude, userComment);

        driver.quit();
        System.out.println("Browser closed");
    }
}
