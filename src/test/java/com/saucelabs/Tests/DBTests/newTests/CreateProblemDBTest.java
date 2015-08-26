package com.saucelabs.Tests.DBTests.newTests;

import com.saucelabs.AdminPage;
import com.saucelabs.AnyPage;
import com.saucelabs.ProblemPage;
import com.saucelabs.Tests.DAO.AddProblemDAO;
import com.saucelabs.Tests.DBTests.SingleWebdriver;
import com.saucelabs.Tests.DBTests.dao.*;
import com.saucelabs.Tests.DBTests.entities.Photos;
import com.saucelabs.Tests.DBTests.entities.PhotosId;
import com.saucelabs.Tests.DBTests.entities.Problems;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;
import utility.Constant;
import utility.SingletonWebDriver;

import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by acidroed on 22.08.2015.
 */
public class CreateProblemDBTest extends SingleWebdriver{

    AnyPage anyPage;
    ProblemPage problemPage;
    ProblemsDAO problemsDAO;
    //WebDriver driver = SingletonWebDriver.getInstance();
    Problems actualProblem;

    public double latitude = 50.1;
    public double longitude = 30.1;
    public String problemNameTest = "problem1";
    public String problemTypeTest = "Загрози біорізноманіттю";
    public String problemDescriptionTest = "description1";
    public String problemProposeTest = "propose1";
//    public List<String> imagePath = Arrays.asList(new File("resources" + File.separator + "images" + File.separator + "bomb.jpg").getAbsolutePath(),
//                                                    new File("resources" + File.separator + "images" + File.separator +  "svalka.jpg").getAbsolutePath());
    public List<String> imagePath = Arrays.asList("C:\\Users\\Public\\Pictures\\Sample Pictures\\desert.jpg",
            "C:\\Users\\Public\\Pictures\\Sample Pictures\\koala.jpg");
    public List<String> imageComments = Arrays.asList("imageComment1", "imageComment2");
    public List<String> problemComments = Arrays.asList("problemComment1");

    //@Test(sequential = true, dependsOnMethods = {"userRegistrationDBCheck"}, groups = {"DBTests"})
    //@Test(sequential = true, dependsOnGroups = {"CreateUser"}, groups = {"DBTests"})
//    @Test
//    public void addProblem() throws SQLException, ClassNotFoundException {
//        driver.get(Constant.URLlocal);
//        //driver.navigate().refresh();
//        System.out.println("111111111111111111111");
//        anyPage = new AnyPage(driver);
//        System.out.println("2222222222222222222222");
//        problemPage = new ProblemPage(driver);
//        anyPage.logIn("admin@.com", "admin");
//        System.out.println("333333333333333333333333");
//        anyPage.addProblemToVisibleCenter(latitude, longitude, problemNameTest, problemTypeTest,
//                problemDescriptionTest, problemProposeTest, imagePath, imageComments);
//        System.out.println("4444444444444444444444444");
//        driver.navigate().refresh();
//        System.out.println("5555555555555555555555555");
//        anyPage.clickAtProblemByCoordinateVisible(latitude, longitude);
//        System.out.println("66666666666666666666666666");
//        String problemTitle = problemPage.getProblemTitle();
//        System.out.println("7777777777777777777777777777");
//        problemPage.closeProblem();
//        System.out.println("88888888888888888888888888888");
//        Assert.assertEquals(problemTitle, problemNameTest);
//        actualProblem = problemsDAO.findProblemByTitle(problemNameTest);
//
//        Assert.assertEquals(actualProblem.getLatitude(), latitude);
//        Assert.assertEquals(actualProblem.getLongtitude(), longitude);
//        Assert.assertEquals(actualProblem.getTitle(), problemNameTest);
//        Assert.assertEquals(actualProblem.getContent(), problemDescriptionTest);
//        Assert.assertEquals(actualProblem.getProposal(), problemProposeTest);
//        Assert.assertEquals(actualProblem.getLatitude(), latitude);
//    }

    @Test
    public void addProblem() throws SQLException, ClassNotFoundException {
        checkDriver();
        driver.get("http://localhost:8090/#/map");
        anyPage = new AnyPage(driver);
        problemPage = new ProblemPage(driver);
        //addProblemDAO = new AddProblemDAO();
        anyPage.logIn("admin@.com", "admin");

        anyPage.addProblemToVisibleCenter(latitude, longitude, problemNameTest, problemTypeTest,
                problemDescriptionTest, problemProposeTest, imagePath, imageComments);
        driver.navigate().refresh();
        anyPage.clickAtProblemByCoordinateVisible(latitude, longitude);
        String problemTitle = problemPage.getProblemTitle();
        problemPage.closeProblem();

        Assert.assertEquals(problemTitle, problemNameTest);
    }

    @Test(sequential = true, dependsOnMethods = {"addProblem"}, groups = {"DBTests"})
    public void checkProblemTypeAfterAddProblem(){
        ProblemsTypesDAO problemsTypesDAO = new ProblemsTypesDAO();
        Assert.assertEquals(actualProblem.getProblemTypes().getId(), problemsTypesDAO.findByProblemTypeName(problemTypeTest).getId());
    }

    @Test(sequential = true, dependsOnMethods = {"checkProblemTypeAfterAddProblem"}, groups = {"DBTests"})
    public void checkActivityAfterAddProblem(){
        ActivitiesDAO activitiesDAO = new ActivitiesDAO();
        UsersDAO usersDAO = new UsersDAO();
        int userID = usersDAO.findUserByEmail(Constant.SimpleUserUsername).getId();
        String activityTypeName = activitiesDAO.findByUserIdAndProblemID(userID, actualProblem.getId()).getActivityTypes().getName();

        Assert.assertEquals(activityTypeName, Constant.AddProblemActivityTypeName);
    }


    @Test(sequential = true, dependsOnMethods = {"checkActivityAfterAddProblem"}, groups = {"DBTests"})
    public void checkAddedPhotoAfterAddProblem(){
        UsersDAO usersDAO = new UsersDAO();
        int userID = usersDAO.findUserByEmail(Constant.SimpleUserUsername).getId();
        PhotosDAO photosDAO = new PhotosDAO();
        List<Photos> photosList = photosDAO.findByUserIdAndProblemID(userID, actualProblem.getId());
        List<String> actualPhotoDescription = new ArrayList<String>();

        for (Photos p : photosList) {
            actualPhotoDescription.add(p.getDescription());
        }

        Assert.assertTrue(compareTwoStringList(actualPhotoDescription, imageComments));
    }

    /*
     * Method for compare two List<String>. It doesn't matter what sequence of elements is.
     * If first list has the same set of String, method will return true.
    */
    public boolean compareTwoStringList (List<String> list1, List<String> list2) {
        boolean checkPhotos = false;
        if (list1.size() != list2.size()) {
            return false;
        }
        for (String str1 : list1) {
            for (String str2 : list2) {
                if (str1.equals(str2)) {
                    checkPhotos = true;
                    break;
                }
                checkPhotos = false;
            }
        }
        return checkPhotos;

    }


    @AfterTest
    public void deleteProblemUI() {
        anyPage.logOut();
        driver.manage().window().maximize();
        AdminPage adminPage = new AdminPage(driver);
        adminPage.logIn(Constant.Username, Constant.Password);
        System.out.println("Groups delete, Test deleteProblemUI. ");
        ProblemPage problemPage = new ProblemPage(driver);
        List<String> receivedURL;
        String receivedId;
        problemPage.openProblemById(actualProblem.getId());
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
        }
        adminPage.pressDeleteProblemButton();
        System.out.println("Problem deleted. ");
//        try {
//            Thread.sleep(1000);
//        } catch (Exception e) {
//        }
//        problemPage.openProblemById(actualProblem.getId());
//        receivedURL = Arrays.asList(driver.getCurrentUrl().split("/"));
//        receivedId = receivedURL.get(receivedURL.size() - 1);
//        System.out.println("Last value in URL is " + receivedId);
//        if (receivedId.matches("\\d+")) {
//            Assert.assertNotEquals(Integer.parseInt(receivedId), actualProblem.getId());
//        }
//        else {
//            Assert.assertNotEquals(0, actualProblem.getId());
//        }
    }

}
