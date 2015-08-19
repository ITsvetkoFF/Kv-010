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
import utility.Constant;
import utility.ExcelUtils;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Yermek on 03.11.2014.
 */
public class LocalTestThree {
    @DataProvider(name = "xlsLocalTestData", parallel = false)
    public static Object[][] data() throws Exception{
        return ExcelUtils.getTableArray(Constant.Path_LocalTestData + Constant.File_LocalTestData, "Sheet1");
    }
    @Test(dataProvider = "xlsLocalTestData")
    public void sampleAll(String latitudeString, String longitudeString, String problemTitle,
                          String problemType, String problemDescription, String problemSolution,
                          String imageURLsString, String imageCommentsString,
                          String adminEmail, String adminPassword,
                          String newUserFirstName, String newUserLastName,
                          String newUserEmail, String newUserPassword,
                          String userCommentsString) throws IOException {
        double          latitude        = Double.parseDouble(latitudeString);
        double          longitude       = Double.parseDouble(longitudeString);
        List<String> imageURLs       = Arrays.asList(imageURLsString.split("\n"));
        List<String>    imageComments   = Arrays.asList(imageCommentsString.split("\n"));
        List<String>    userComments    = Arrays.asList(userCommentsString.split("\n"));
        List<String>    receivedURLs;
        List<String>    receivedComments;
        String          afterDate       = "31 жовт. 2014";
        String          beforeDate      = "01 лист. 2014";


        WebDriver driver = new FirefoxDriver();
        driver.get("http://localhost:8090");
        //driver.get("http://176.36.11.25");
        driver.manage().window().maximize();

        AnyPage anyPage     = new AnyPage(driver);
        AdminPage adminPage   = new AdminPage(driver);
        ProblemPage problemPage = new ProblemPage(driver);

        adminPage.logIn(adminEmail, adminPassword);

        anyPage.addProblemToVisibleCenter(latitude, longitude,
                problemTitle, problemType, problemDescription, problemSolution,
                imageURLs, imageComments);
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
        }
//            adminPage.logIn(adminEmail, adminPassword);
//            Assert.assertTrue(adminPage.checkProblemIsUnderModeration(problemTitle));
//            adminPage.approveProblem(problemTitle);
//            try {
//                Thread.sleep(1000);
//            } catch (Exception e) {
//            }        adminPage.logOut();
//            try {
//                Thread.sleep(1000);
//            } catch (Exception e) {
//            }
        adminPage.logOut();
        try {
            Thread.sleep(2000);
        } catch (Exception e) {
        }
        anyPage.register(newUserFirstName, newUserLastName, newUserEmail, newUserPassword);
        Assert.assertEquals(anyPage.getLoggedInUserName().toUpperCase(),
                (newUserFirstName + " " + newUserLastName).toUpperCase());
        problemPage.clickAtProblemByCoordinateVisible(latitude, longitude);
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

        problemPage.addComments(latitude, longitude, userComments);
        problemPage.logOut();

        try {
            Thread.sleep(2000);
        } catch (Exception e) {
        }

        adminPage.logIn(adminEmail, adminPassword);
        try {
            Thread.sleep(2000);
        } catch (Exception e) {
        }
        problemPage.deleteComments(latitude, longitude);
        adminPage.logOut();

        driver.quit();
    }
}
