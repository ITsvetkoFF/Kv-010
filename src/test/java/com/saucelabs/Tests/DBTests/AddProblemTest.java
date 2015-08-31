package com.saucelabs.Tests.DBTests;

import com.saucelabs.AnyPage;
import com.saucelabs.ProblemPage;
import com.saucelabs.Tests.DAO.AddProblemDAO;
import org.json.JSONException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by Roma on 21.10.2014.
 */
public class AddProblemTest extends SingleWebdriver{
    AnyPage anyPage;
    ProblemPage problemPage;
    AddProblemDAO addProblemDAO;

    public double latitude = 50.1;
    public double longitude = 30.1;
    public String problemNameTest = "problem1";
    public String problemTypeTest = "Загрози біорізноманіттю";
    public String problemDescriptionTest = "description1";
    public String problemProposeTest = "propose1";
    //public List<String> imageUrls = Arrays.asList("http://i.imgur.com/HHXCVbs.jpg", "http://i.imgur.com/1K6AdCH.jpg");
    public List<String> imagePath = Arrays.asList("C:\\Users\\Public\\Pictures\\Sample Pictures\\desert.jpg",
                                                  "C:\\Users\\Public\\Pictures\\Sample Pictures\\koala.jpg");
    public List<String> imageComments = Arrays.asList("imageComment1", "imageComment2");
    public List<String> problemComments = Arrays.asList("problemComment1");

    //@Test
    public void addProblem() throws SQLException, ClassNotFoundException {
        checkDriver();
        driver.get("http://localhost:8090/#/map");
        anyPage = new AnyPage(driver);
        problemPage = new ProblemPage(driver);
//        addProblemDAO = new AddProblemDAO();
        anyPage.logIn("admin@.com", "admin");

        anyPage.addProblemToVisibleCenter(latitude, longitude, problemNameTest, problemTypeTest,
                problemDescriptionTest, problemProposeTest, imagePath, imageComments);
        driver.navigate().refresh();
        anyPage.clickAtProblemByCoordinateVisible(latitude, longitude);
        String problemTitle = problemPage.getProblemTitle();
        problemPage.closeProblem();

        Assert.assertEquals(problemTitle, problemNameTest);
    }

    @Test(dependsOnMethods = "addProblem")
    public void checkSelectedProblemsValuesInDB() throws SQLException, ClassNotFoundException {

        String problemId = Integer.toString(problemPage.getProblemId(latitude, longitude));
        String problemTitle = problemNameTest;
        String problemContent = problemDescriptionTest;
        String problemTypes_Id = "5";
        String problemProposal = problemProposeTest;
        String problemImageComment1 = imageComments.get(0);
        String problemImageComment2 = imageComments.get(1);
        List<String> values = Arrays.asList(problemId, problemTitle, problemContent, problemTypes_Id,
                                            problemProposal, problemImageComment1, problemImageComment2);

        Map<String, String> problems = addProblemDAO.getProblemsById(Integer.parseInt(problemId));

        String problemIdInDB = problems.get("Id");
        String problemTitleDB = problems.get("Title");
        String problemContentDB = problems.get("Content");
        String problemTypes_IdDB = problems.get("ProblemTypes_Id");
        String problemProposalDB = problems.get("Proposal");

        List<Map<String, String>> photos = addProblemDAO.getPhotosByProblemsId(Integer.parseInt(problemId));

        String problemImageComment1DB = photos.get(0).get("Description");
        String problemImageComment2DB = photos.get(1).get("Description");
        List<String> valuesDB = Arrays.asList(problemIdInDB, problemTitleDB, problemContentDB,
                problemTypes_IdDB, problemProposalDB, problemImageComment1DB, problemImageComment2DB);

        Assert.assertEquals(values, valuesDB);
    }

    @Test(dependsOnMethods = "addProblem")
    public void commentsEqualsInDB() throws SQLException, JSONException, ClassNotFoundException {

        problemPage.addComments(latitude, longitude, problemComments);
        int problemId = problemPage.getProblemId(latitude, longitude);
        problemPage.closeProblem();

        Assert.assertEquals(problemComments, addProblemDAO.getCommentsFromDB(problemId));
    }

    @Test(dependsOnMethods = "addProblem")
    public void voteEqualsInDB() throws SQLException, ClassNotFoundException {

        int problemId = problemPage.getProblemId(latitude, longitude);
        problemPage.addVoteToProblem();
        driver.navigate().refresh();

        String vote = problemPage.getVote();
        System.out.println("UI vote => " + vote);
        String voteDB = addProblemDAO.getProblemsById(problemId).get("Votes");
        System.out.println("DB vote => " + voteDB);
        problemPage.deleteOpenedProblem();

        Assert.assertEquals(vote, voteDB);
        problemPage.logOut();
    }
}
