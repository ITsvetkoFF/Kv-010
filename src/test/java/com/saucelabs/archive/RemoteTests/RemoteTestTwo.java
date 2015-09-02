package com.saucelabs.archive.RemoteTests;

import com.saucelabs.pages.AdminPage;
import com.saucelabs.pages.AnyPage;
import com.saucelabs.pages.ProblemPage;
import com.saucelabs.common.SauceOnDemandAuthentication;
import com.saucelabs.common.SauceOnDemandSessionIdProvider;
import com.saucelabs.testng.SauceOnDemandAuthenticationProvider;
import com.saucelabs.utility.ImageDistanceCalculator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.saucelabs.utility.Constant;
import com.saucelabs.utility.ExcelUtils;

import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by Yermek on 03.11.2014.
 */
public class RemoteTestTwo implements SauceOnDemandSessionIdProvider, SauceOnDemandAuthenticationProvider {
    /**
     * Constructs a {@link com.saucelabs.common.SauceOnDemandAuthentication} instance using the supplied user name/access key.  To use the authentication
     * supplied by environment variables or from an external file, use the no-arg {@link com.saucelabs.common.SauceOnDemandAuthentication} constructor.
     */
    public SauceOnDemandAuthentication authentication = new SauceOnDemandAuthentication("yermek", "156a52e4-c0c6-4605-98b0-f55e8f87b574");
    //public SauceOnDemandAuthentication authentication = new SauceOnDemandAuthentication("atqc", "f8581664-c7c0-485b-82af-d388c860d03b");
    //public SauceOnDemandAuthentication authentication = new SauceOnDemandAuthentication("tsvetkoff", "7db17c4f-d114-4de0-9127-69a9a858d3c7");

    /**
     * ThreadLocal variable which contains the  {@link org.openqa.selenium.WebDriver} instance which is used to perform browser interactions with.
     */
    private ThreadLocal<WebDriver> webDriver = new ThreadLocal<WebDriver>();

    /**
     * ThreadLocal variable which contains the Sauce Job Id.
     */
    private ThreadLocal<String> sessionId = new ThreadLocal<String>();

    /**
     * DataProvider that explicitly sets the browser combinations to be used.
     *
     * @param testMethod
     * @return
     */
    @DataProvider(name = "xlsRemoteTestData", parallel = false)
    public static Object[][] data(Method testMethod) throws Exception{
        return ExcelUtils.getTableArray(Constant.Path_RemoteTestData + Constant.File_RemoteTestData, "Sheet1");
    }

    /**
     * /**
     * Constructs a new {@link org.openqa.selenium.remote.RemoteWebDriver} instance which is configured to use the capabilities defined by the browser,
     * version and os parameters, and which is configured to run against ondemand.saucelabs.com, using
     * the username and access key populated by the {@link #authentication} instance.
     *
     * @param browser Represents the browser to be used as part of the test run.
     * @param version Represents the version of the browser to be used as part of the test run.
     * @param os Represents the operating system to be used as part of the test run.
     * @return
     * @throws java.net.MalformedURLException if an error occurs parsing the url
     */
    private WebDriver createDriver(String browser, String version, String os) throws MalformedURLException {

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(CapabilityType.BROWSER_NAME, browser);
        if (version != null) {
            capabilities.setCapability(CapabilityType.VERSION, version);
        }
        capabilities.setCapability(CapabilityType.PLATFORM, os);
        capabilities.setCapability("name", "Ecomap Add Problem, Comment Test");
        webDriver.set(new RemoteWebDriver(
                new URL("http://" + authentication.getUsername() + ":" + authentication.getAccessKey() + "@ondemand.saucelabs.com:80/wd/hub"),
                capabilities));
        sessionId.set(((RemoteWebDriver) getWebDriver()).getSessionId().toString());
        return webDriver.get();
    }

    /**
     * Runs a simple test verifying the title of the amazon.com homepage.
     *
     * @param browser Represents the browser to be used as part of the test run.
     * @param version Represents the version of the browser to be used as part of the test run.
     * @param os Represents the operating system to be used as part of the test run.
     * @throws Exception if an error occurs during the running of the test
     */
    //@Parameters( { "path" } )
    @Test(dataProvider = "xlsRemoteTestData")
    public void sampleAll(String browser, String version, String os,
                          String latitudeString, String longitudeString, String problemTitle,
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

        WebDriver driver = createDriver(browser, version, os);

        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

        driver.get("http://176.36.11.25/#/map");
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
        anyPage.logIn(newUserEmail, newUserPassword);
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

    /**
     * @return the {@link org.openqa.selenium.WebDriver} for the current thread
     */
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
