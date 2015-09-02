package com.saucelabs.archive.RemoteTests;

import com.saucelabs.pages.ResourcesPage;
import com.saucelabs.common.SauceOnDemandAuthentication;
import com.saucelabs.common.SauceOnDemandSessionIdProvider;
import com.saucelabs.testng.SauceOnDemandAuthenticationProvider;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import com.saucelabs.utility.Constant;
import com.saucelabs.utility.ExcelUtils;

/**
 * Created by Olya on 10/26/14.
 */

public class ResourcesPageTest implements SauceOnDemandSessionIdProvider, SauceOnDemandAuthenticationProvider {

    //static WebDriver driver = new FirefoxDriver();
    //static ResourcesPage resourcesPage = new ResourcesPage(driver);

    /**
     * Constructs a {@link com.saucelabs.common.SauceOnDemandAuthentication} instance using the supplied user name/access key.  To use the authentication
     * supplied by environment variables or from an external file, use the no-arg {@link com.saucelabs.common.SauceOnDemandAuthentication} constructor.
     */
    public SauceOnDemandAuthentication authentication = new SauceOnDemandAuthentication("yermek", "156a52e4-c0c6-4605-98b0-f55e8f87b574");
    //public SauceOnDemandAuthentication authentication = new SauceOnDemandAuthentication("tsvetkoff", "7db17c4f-d114-4de0-9127-69a9a858d3c7");

    /**
     * ThreadLocal variable which contains the  {@link org.openqa.selenium.WebDriver} instance which is used to perform browser interactions with.
     */
    private ThreadLocal<WebDriver> webDriver = new ThreadLocal<WebDriver>();

    /**
     * ThreadLocal variable which contains the Sauce Job Id.
     */
    private ThreadLocal<String> sessionId = new ThreadLocal<String>();


    /*@BeforeSuite
    public static void beforeTest() throws Exception{
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.get(Constant.URL);
        resourcesPage.logIn("admin@.com", "admin");
    }*/

    @DataProvider(name = "testData", parallel = true)
    public static Object[][] data() throws Exception{
        return ExcelUtils.getTableArray(Constant.Path_TestData + Constant.File_TestData, "Sheet1");
    }

    private WebDriver createDriver(String browser, String version, String os) throws MalformedURLException {

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
    }

    /*@Test(dataProvider = "testData")
    public void createResource(String browser, String version, String os, String UserName, String Password, String ResourceTitle, String ResourceAlias, String ResourceBody, String PlaceToSave, String TextToAdd) throws Exception{

        resourcesPage.createResource(ResourceTitle, ResourceAlias, ResourceBody, PlaceToSave);
        Assert.assertEquals(resourcesPage.existResource(ResourceTitle), PlaceToSave);
    }*/

    @Test(dataProvider = "testData")
    public void resourceTest(String browser, String version, String os, String UserName, String Password, String ResourceTitle, String ResourceAlias, String ResourceBody, String PlaceToSave, String TextToAdd) throws Exception{
        WebDriver driver = createDriver(browser, version, os);
        //driver.get("http://176.36.11.25/#/map");
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.get(Constant.URL);
        ResourcesPage resourcesPage = new ResourcesPage(driver);

        resourcesPage.logIn(UserName, Password);

        resourcesPage.createResource(ResourceTitle, ResourceAlias, ResourceBody, PlaceToSave);
        Assert.assertEquals(resourcesPage.existResource(ResourceTitle), PlaceToSave);

        resourcesPage.editResource(ResourceTitle, TextToAdd);
        Assert.assertEquals(resourcesPage.existResource(ResourceTitle + TextToAdd), PlaceToSave);

        resourcesPage.deleteResource(ResourceTitle+TextToAdd);
        Assert.assertEquals(resourcesPage.existResource(ResourceTitle+TextToAdd),null);
        resourcesPage.logOut();
        driver.quit();
    }

    /*@Test(dataProvider = "testData", dependsOnMethods = {"createResource"})
    public void editResource(String UserName, String Password, String ResourceTitle, String ResourceAlias, String ResourceBody, String PlaceToSave, String TextToAdd) throws Exception{

        resourcesPage.editResource(ResourceTitle, TextToAdd);
        Assert.assertEquals(resourcesPage.existResource(ResourceTitle + TextToAdd), PlaceToSave);
    }

    @Test(dataProvider = "testData", dependsOnMethods = {"editResource"})
    public void deleteResource(String UserName, String Password, String ResourceTitle, String ResourceAlias, String ResourceBody, String PlaceToSave, String TextToAdd) throws Exception{
        resourcesPage.deleteResource(ResourceTitle+TextToAdd);
        Assert.assertEquals(resourcesPage.existResource(ResourceTitle+TextToAdd),null);
        resourcesPage.logOut();
        driver.quit();
    }*/

    /*@AfterSuite
    public static void afterTest() throws Exception{
        resourcesPage.logOut();
        driver.quit();
    }*/

    public WebDriver getWebDriver() {
        System.out.println("WebDriver" + webDriver.get());
        return webDriver.get();
    }

    /**
     *
     * @return the Sauce Job id for the current thread
     */
    public String getSessionId() {
        return sessionId.get();
    }

    /**
     *
     * @return the {@link com.saucelabs.common.SauceOnDemandAuthentication} instance containing the Sauce username/access key
     */
    @Override
    public SauceOnDemandAuthentication getAuthentication() {
        return authentication;
    }
}

