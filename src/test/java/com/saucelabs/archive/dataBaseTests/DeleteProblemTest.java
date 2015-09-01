package com.saucelabs.archive.dataBaseTests;

import com.saucelabs.pages.AdminPage;
import com.saucelabs.pages.AnyPage;
import com.saucelabs.pages.ProblemPage;
import com.saucelabs.archive.dataBaseTests.dao_old.DeleteProblemDAO;
import org.testng.Assert;
import org.testng.annotations.*;
import com.saucelabs.utility.Constant;
import com.saucelabs.utility.FileSystem;
import com.saucelabs.utility.ImageDistanceCalculator;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Created by Yermek on 05.11.2014.
 */
public class DeleteProblemTest extends SingleWebdriver{
    double                         latitude;
    double                         longitude;
    String                         problemTitle;
    String                         problemType;
    String                         problemDescription;
    String                         problemSolution;
    List<String>                   imageURLs;
    List<String>                   imageComments;
    String                         adminEmail;
    String                         adminPassword;
    int                            problemId;
    ArrayList<Map<String, String>> photos;

    public DeleteProblemTest(String latitudeString, String longitudeString,
                             String problemTitle, String problemType, String problemDescription, String problemSolution,
                             String imageURLsString, String imageCommentsString, String adminEmail, String adminPassword
                            ) {
        this.latitude           = Double.parseDouble(latitudeString);
        this.longitude          = Double.parseDouble(longitudeString);
        this.problemTitle       = problemTitle;
        this.problemType        = problemType;
        this.problemDescription = problemDescription;
        this.problemSolution    = problemSolution;
        this.imageURLs          = Arrays.asList(imageURLsString.split("\n"));
        this.imageComments      = Arrays.asList(imageCommentsString.split("\n"));
        this.adminEmail         = adminEmail;
        this.adminPassword      = adminPassword;
    }

    @Test(sequential = true, groups = {"delete"}, singleThreaded = true)
    public void addProblemUI() throws IOException {
        checkDriver();
        driver.get(Constant.URLlocal);
        driver.manage().window().maximize();
        AdminPage adminPage = new AdminPage(driver);
        adminPage.logIn(Constant.Username, Constant.Password);
        System.out.println("Groups delete, Test addProblemUI. ");
        AnyPage anyPage     = new AnyPage(driver);
        ProblemPage problemPage = new ProblemPage(driver);
        List<String> receivedURLs;
        List<String> receivedComments;

        try {
            Thread.sleep(1000);
        } catch (Exception e) {
        }
        anyPage.addProblemToVisibleCenter(latitude, longitude,
                problemTitle, problemType, problemDescription, problemSolution,
                imageURLs, imageComments);
        System.out.println("Problem added. ");
        driver.navigate().refresh();
        try {
            Thread.sleep(2000);
        } catch (Exception e) {
        }
        System.out.println("Get problem ID. ");
        problemId = problemPage.getProblemId(latitude, longitude);
        System.out.println("Problem ID = " + problemId);
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
            Assert.assertEquals(receivedComments.get(i), imageComments.get(i));
        }
    }

    @Test(sequential = true, groups = {"delete"}, singleThreaded = true, dependsOnMethods = {"addProblemUI"})
    public void getPhotosDB() throws SQLException, ClassNotFoundException {
        System.out.println("Groups delete, Test getPhotosDB. ");
        FileSystem fs = new FileSystem(Constant.Path_ImagesLocalFolder);
        DeleteProblemDAO dao = new DeleteProblemDAO();
        photos = dao.getPhotosByProblemId(problemId);

        int j = 0;
        for (int i = 0; i < photos.size(); i++) {
            if (fs.isImageFilePresentInFolder(photos.get(i).get("Link"))) {
                j++;
                System.out.println(photos.get(i).get("Link"));
            }
        }

        Assert.assertEquals(j, imageURLs.size());
    }

    @Test(sequential = true, groups = {"delete"}, singleThreaded = true, dependsOnMethods = {"getPhotosDB"})
    public void deleteProblemUI() {
        System.out.println("Groups delete, Test deleteProblemUI. ");
        AdminPage adminPage   = new AdminPage(driver);
        ProblemPage problemPage = new ProblemPage(driver);
        List<String> receivedURL;
        String receivedId;

        try {
            Thread.sleep(1000);
        } catch (Exception e) {
        }
        adminPage.pressDeleteProblemButton();
        System.out.println("Problem deleted. ");
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
        }
        problemPage.openProblemById(problemId);
        receivedURL = Arrays.asList(driver.getCurrentUrl().split("/"));
        receivedId = receivedURL.get(receivedURL.size() - 1);
        System.out.println("Last value in URL is " + receivedId);
        if (receivedId.matches("\\d+")) {
            Assert.assertNotEquals(Integer.parseInt(receivedId), problemId);
        }
        else {
            Assert.assertNotEquals(0, problemId);
        }
    }

    @Test(sequential = true, groups = {"delete"}, singleThreaded = true, dependsOnMethods = {"deleteProblemUI"})
    public void checkProblemDB() throws SQLException, ClassNotFoundException {
        System.out.println("Groups delete, Test checkProblemDB. ");
        DeleteProblemDAO dao = new DeleteProblemDAO();
        Map<String, String> problem = dao.getProblemById(problemId);
        Assert.assertEquals(problem.size(), 0);
    }

    @Test(sequential = true, groups = {"delete"}, singleThreaded = true, dependsOnMethods = {"deleteProblemUI"})
    public void checkActivitiesDB() throws SQLException, ClassNotFoundException {
        System.out.println("Groups delete, Test checkActivitiesDB. ");
        DeleteProblemDAO dao = new DeleteProblemDAO();
        ArrayList<Map<String, String>> activities = dao.getActivitiesByProblemId(problemId);
        Assert.assertEquals(activities.size(), 0);
    }

    @Test(sequential = true, groups = {"delete"}, singleThreaded = true, dependsOnMethods = {"deleteProblemUI"})
    public void checkPhotosDB() throws SQLException, ClassNotFoundException {
        System.out.println("Groups delete, Test checkPhotosDB. ");
        FileSystem fs = new FileSystem(Constant.Path_ImagesLocalFolder);
        DeleteProblemDAO dao = new DeleteProblemDAO();
        ArrayList<Map<String, String>> photosDB = dao.getPhotosByProblemId(problemId);
        Assert.assertEquals(photosDB.size(), 0);

        int j = 0;
        for (int i = 0; i < photos.size(); i++) {
            if (fs.isImageFilePresentInFolder(photos.get(i).get("Link"))) {
                j++;
                System.out.println(photos.get(i).get("Link"));
            }
        }
        AdminPage adminPage = new AdminPage(driver);
        adminPage.logOut();
        Assert.assertEquals(j, 0);
    }
}