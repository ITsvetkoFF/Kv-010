package com.saucelabs.Tests.DemoTests;

import com.saucelabs.ResourcesPage;
import com.saucelabs.Tests.DAO.BaseDAO;
import com.saucelabs.Tests.DAO.ResourcesDAO;
import com.saucelabs.common.SauceOnDemandAuthentication;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utility.Constant;
import utility.ExcelUtils;

import java.net.MalformedURLException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static java.sql.DriverManager.getConnection;

/**
 * Created by Olya on 10/31/14.
 */
public class ResourcesPageTestLocal{
    static WebDriver driver = new FirefoxDriver();
    static ResourcesPage resourcesPage = new ResourcesPage(driver);

    @BeforeSuite
    public static void beforeTest() throws Exception{
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.get(Constant.URLlocal);
        resourcesPage.logIn("admin@.com", "admin");
    }

    @DataProvider(name = "testData", parallel = false)
    public static Object[][] data() throws Exception{
        return ExcelUtils.getTableArray(Constant.Path_TestData + Constant.File_TestDataLocal, "Sheet1");
    }

    /*private WebDriver createDriver(String browser, String version, String os) throws MalformedURLException {

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(CapabilityType.BROWSER_NAME, browser);
        if (version != null) {
            capabilities.setCapability(CapabilityType.VERSION, version);
        }
        capabilities.setCapability(CapabilityType.PLATFORM, os);
        capabilities.setCapability("name", "Resource Sample Test");
        webDriver.set(new RemoteWebDriver(
                new URL("http://" + authentication.getUsername() + ":" + authentication.getAccessKey() + "@ondemand.saucelabs.com:80/wd/hub"),capabilities));
        sessionId.set(((RemoteWebDriver) getWebDriver()).getSessionId().toString());
        return webDriver.get();
    }*/

    @Test(dataProvider = "testData")
    public void createResource(String UserName, String Password, String ResourceTitle, String ResourceAlias, String ResourceBody, String PlaceToSave, String TextToAdd) throws Exception{

        resourcesPage.createResource(ResourceTitle, ResourceAlias, ResourceBody, PlaceToSave);
        Assert.assertEquals(resourcesPage.existResource(ResourceTitle), PlaceToSave);
    }

    @Test(dataProvider = "testData", dependsOnMethods = {"createResource"})
    public void editResource(String UserName, String Password, String ResourceTitle, String ResourceAlias, String ResourceBody, String PlaceToSave, String TextToAdd) throws Exception{

        resourcesPage.editResource(ResourceTitle, TextToAdd);
        Assert.assertEquals(resourcesPage.existResource(ResourceTitle + TextToAdd), PlaceToSave);
    }

    @Test(dataProvider = "testData", dependsOnMethods = {"editResource"})
    public void deleteResource(String UserName, String Password, String ResourceTitle, String ResourceAlias, String ResourceBody, String PlaceToSave, String TextToAdd) throws Exception{
        resourcesPage.deleteResource(ResourceTitle+TextToAdd);
        Assert.assertEquals(resourcesPage.existResource(ResourceTitle+TextToAdd),null);
    }

    @AfterSuite
    public static void afterTest() throws Exception{
        resourcesPage.logOut();
        driver.quit();
    }
}
