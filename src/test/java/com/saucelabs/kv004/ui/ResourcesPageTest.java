package com.saucelabs.kv004.ui;

import com.saucelabs.pages.ResourcesPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.saucelabs.utility.Constant;
import com.saucelabs.utility.ExcelUtils;

import java.util.concurrent.TimeUnit;

import static java.sql.DriverManager.getConnection;

/**
 * Created by Olya on 10/31/14.
 */
public class ResourcesPageTest {
    static WebDriver driver = new FirefoxDriver();
    static ResourcesPage resourcesPage = new ResourcesPage(driver);

    @BeforeSuite
    public static void beforeTest() throws Exception {
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.get(Constant.URLlocal);
        resourcesPage.logIn("admin@.com", "admin");
    }

    @DataProvider(name = "testData", parallel = false)
    public static Object[][] data() throws Exception {
        return ExcelUtils.getTableArray(Constant.Path_TestData + Constant.File_TestDataLocal, "Sheet1");
    }

    @Test(dataProvider = "testData")
    public void createResource(String UserName, String Password, String ResourceTitle, String ResourceAlias, String ResourceBody, String PlaceToSave, String TextToAdd) throws Exception {

        resourcesPage.createResource(ResourceTitle, ResourceAlias, ResourceBody, PlaceToSave);
        Assert.assertEquals(resourcesPage.existResource(ResourceTitle), PlaceToSave);
    }

    @Test(dataProvider = "testData", dependsOnMethods = {"createResource"})
    public void editResource(String UserName, String Password, String ResourceTitle, String ResourceAlias, String ResourceBody, String PlaceToSave, String TextToAdd) throws Exception {

        resourcesPage.editResource(ResourceTitle, TextToAdd);
        Assert.assertEquals(resourcesPage.existResource(ResourceTitle + TextToAdd), PlaceToSave);
    }

    @Test(dataProvider = "testData", dependsOnMethods = {"editResource"})
    public void deleteResource(String UserName, String Password, String ResourceTitle, String ResourceAlias, String ResourceBody, String PlaceToSave, String TextToAdd) throws Exception {
        resourcesPage.deleteResource(ResourceTitle + TextToAdd);
        Assert.assertEquals(resourcesPage.existResource(ResourceTitle + TextToAdd), "");
    }

    @AfterSuite
    public static void afterTest() throws Exception {
        resourcesPage.logOut();
        driver.close();
    }
}
