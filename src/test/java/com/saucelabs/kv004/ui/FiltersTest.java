package com.saucelabs.kv004.ui;

import com.saucelabs.pages.AnyPage;
import com.saucelabs.pages.MapPage;
import com.saucelabs.pages.ProblemPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

/**
 * Created by yioteh on 27.08.15.
 */
public class FiltersTest {

    WebDriver driver;
    AnyPage anyPage;
    MapPage mapPage;
    ProblemPage problemPage;

    public String afterDate = "14 серп. 2015";
    public String beforeDate = "14 серп. 2016";
    public static int typeNumber = 1;
    public static double latitude = 51.29;
    public static double longitude = 31.29;

    @BeforeSuite
    public void setUp() {

        this.driver = new FirefoxDriver();
        this.anyPage = new AnyPage(driver);
        this.mapPage = new MapPage(driver);
        this.problemPage = new ProblemPage(driver);
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.get("http://localhost:8090/#/map");
        driver.manage().window().maximize();
    }

    /**
     * Test filters on left sidebar. Create problem and change it properties while observing it's visibility to filters.
     *
     * @throws Exception
     */
    @Test
    public void checkFilters() throws Exception {
        String imageURLsString = "";
        String imageCommentsString = "";
        anyPage.logIn("admin@.com", "admin");
        anyPage.addProblemToVisibleCenter(latitude, longitude, "Filers test problem", "Проблеми лісів", "Some test to description", "Don't bother about it", Arrays.asList(imageURLsString.split("\n")), Arrays.asList(imageCommentsString.split("\n")));
        driver.navigate().refresh();
        mapPage.openFiltersBoard();
        mapPage.setAfterDate(afterDate);
        mapPage.setBeforeDate(beforeDate);
        mapPage.selectUserProblems();
        mapPage.selectOnlyOneFilter(typeNumber);
        mapPage.selectClosedProblems(); //state became unchecked
        mapPage.clickAtProblemByCoordinateVisible(latitude, longitude);

        Assert.assertEquals(mapPage.getFilterTitle(typeNumber), problemPage.getProblemType());
        problemPage.editProblemSolved();
        problemPage.pressSaveButton();
        problemPage.closeProblem();

        driver.navigate().refresh();

        mapPage.openFiltersBoard();
        mapPage.setAfterDate(afterDate);
        mapPage.setBeforeDate(beforeDate);
        mapPage.selectUserProblems();
        mapPage.selectNewProblems(); //state became unchecked
        mapPage.clickAtProblemByCoordinateVisible(latitude, longitude);
    }

    @AfterSuite
    public void tearDown(){
        problemPage.deleteOpenedProblem();
        driver.close();
    }
}
