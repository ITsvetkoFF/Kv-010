package com.saucelabs.Tests.DBTests.dataBaseTests;

import com.saucelabs.AdminPage;
import com.saucelabs.AnyPage;
import com.saucelabs.ProblemPage;
import com.saucelabs.Tests.DBTests.dataBaseTests.dao.*;
import com.saucelabs.Tests.DBTests.dataBaseTests.entities.Photos;
import com.saucelabs.Tests.DBTests.dataBaseTests.entities.Problems;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import utility.Constant;
import utility.SingletonWebDriver;
import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * Class creates, edits, deletes Problem and checks that changes are represented in DataBase,
 * Created by acidroed on 22.08.2015.
 */
public class CreateProblemDBTest {

    AnyPage anyPage;
    ProblemPage problemPage;
    ProblemsDAO problemsDAO = new ProblemsDAO();
    WebDriver driver = SingletonWebDriver.getInstance();
    Problems actualProblem = new Problems();
    UsersDAO usersDAO = new UsersDAO();

    public double latitude = 50.1;
    public double longitude = 30.1;
    public String problemNameTest = "problem1";
    public String editedProblemNameTest = "editedProblem1";
    public String problemTypeTest = "Загрози біорізноманіттю";
    public String problemDescriptionTest = "description1";
    public String editedProblemDescriptionTest = "editedDescription1";
    public String problemProposeTest = "propose1";
    public String editedProblemProposeTest = "editedPropose1";
    public String addProblemActivityTypeName = "addProblem";
    public List<String> imagePath = Arrays.asList(new File("resources" + File.separator + "images" + File.separator + "bomb.jpg").getAbsolutePath(),
                                                    new File("resources" + File.separator + "images" + File.separator +  "svalka.jpg").getAbsolutePath());
    public List<String> imageComments = Arrays.asList("imageComment1", "imageComment2");


    @Test(dependsOnGroups = {"CreateUser"}, groups = {"CreateProblem"})
    public void addProblem() throws SQLException, ClassNotFoundException {
        driver.get(Constant.URLlocal);
        anyPage = new AnyPage(driver);
        problemPage = new ProblemPage(driver);
        anyPage.addProblemToVisibleCenter(latitude, longitude, problemNameTest, problemTypeTest,
                problemDescriptionTest, problemProposeTest, imagePath, imageComments);
        driver.navigate().refresh();
        anyPage.clickAtProblemByCoordinateVisible(latitude, longitude);
        String problemTitle = problemPage.getProblemTitle();
        problemPage.closeProblem();
        Assert.assertEquals(problemTitle, problemNameTest);
        actualProblem = problemsDAO.findProblemByTitle(problemNameTest);

        Assert.assertEquals(actualProblem.getLatitude(), latitude, Constant.DeltaAccuracyForCoordinates);
        Assert.assertEquals(actualProblem.getLongtitude(), longitude, Constant.DeltaAccuracyForCoordinates);
        Assert.assertEquals(actualProblem.getTitle(), problemNameTest);
        Assert.assertEquals(actualProblem.getContent(), problemDescriptionTest);
        Assert.assertEquals(actualProblem.getProposal(), problemProposeTest);
    }


    @Test(sequential = true, dependsOnMethods = {"addProblem"}, groups = {"CreateProblem"})
    public void checkProblemTypeAfterAddProblem(){

        ProblemsTypesDAO problemsTypesDAO = new ProblemsTypesDAO();
        Assert.assertEquals(actualProblem.getProblemTypes().getId(), problemsTypesDAO.findByProblemTypeName(problemTypeTest).getId());
    }

    @Test(sequential = true, dependsOnMethods = {"checkProblemTypeAfterAddProblem"}, groups = {"CreateProblem"})
    public void checkActivityAfterAddProblem(){
        ActivitiesDAO activitiesDAO = new ActivitiesDAO();
        int userID = usersDAO.findUserByEmail(Constant.SimpleUserUsername).getId();
        String activityTypeName = activitiesDAO.findByUserIdAndProblemID(userID, actualProblem.getId()).getActivityTypes().getName();

        Assert.assertEquals(activityTypeName, addProblemActivityTypeName);
    }


    @Test(sequential = true, dependsOnMethods = {"checkActivityAfterAddProblem"}, groups = {"CreateProblem"})
    public void checkAddedPhotoAfterAddProblem(){
        PhotosDAO photosDAO = new PhotosDAO();
        List<Photos> photosList = photosDAO.findByProblemID(actualProblem.getId());
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

    @Test(sequential = true, dependsOnMethods = {"checkAddedPhotoAfterAddProblem"}, groups = {"CreateProblem"})
    public void checkEditionProblem() {
        anyPage.logOut();
        driver.manage().window().maximize();
        AdminPage adminPage = new AdminPage(driver);
        adminPage.logIn(Constant.Username, Constant.Password);
        ProblemPage problemPage = new ProblemPage(driver);
        int actualProblemId = actualProblem.getId();
        problemPage.openProblemById(actualProblemId);
        Byte newSeverity = 5;
        problemPage.editProblemSeverity(newSeverity);
        problemPage.editProblemTitle(editedProblemNameTest);
        problemPage.editProblemDescription(editedProblemDescriptionTest);
        problemPage.editProblemPropose(editedProblemProposeTest);
        problemPage.pressSaveButton();
        actualProblem = problemsDAO.findById(actualProblemId);

        Assert.assertEquals(actualProblem.getTitle(), editedProblemNameTest);
        Assert.assertEquals(actualProblem.getContent(), editedProblemDescriptionTest);
        Assert.assertEquals(actualProblem.getProposal(), editedProblemProposeTest);
        Assert.assertEquals(actualProblem.getSeverity(), newSeverity);
    }

    @Test(sequential = true, dependsOnGroups = {"CreateProblem"}, groups = {"DeleteProblem"})
    public void deleteProblemUI() {
        AdminPage adminPage = new AdminPage(driver);
        System.out.println("Groups delete, Test deleteProblemUI. ");
        ProblemPage problemPage = new ProblemPage(driver);
        problemPage.openProblemById(actualProblem.getId());
        adminPage.pressDeleteProblemButton();
        System.out.println("Problem deleted. ");
    }


    @Test(sequential = true, dependsOnMethods = {"deleteProblemUI"}, groups = {"DeleteProblem"})
    public void checkProblemIsAbsentAfterDelete() {
        Assert.assertTrue(problemsDAO.findProblemByTitle(problemNameTest) == null);
    }


    @Test(sequential = true, dependsOnMethods = {"checkProblemIsAbsentAfterDelete"}, groups = {"DeleteProblem"})
    public void checkPhotoIsAbsentAfterDeleteProblem(){
        PhotosDAO photosDAO = new PhotosDAO();
        List<Photos> photosList = photosDAO.findByProblemID(actualProblem.getId());
         Assert.assertTrue(photosList.isEmpty());
    }

}
