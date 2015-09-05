package com.saucelabs.kv004.crossBrowser;

import com.saucelabs.common.SauceOnDemandAuthentication;
import com.saucelabs.common.SauceOnDemandSessionIdProvider;
import com.saucelabs.pages.AdminPage;
import com.saucelabs.pages.AnyPage;
import com.saucelabs.pages.ProblemPage;
import com.saucelabs.testng.SauceOnDemandAuthenticationProvider;
import com.saucelabs.utility.Constant;
import com.saucelabs.utility.ExcelUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by acidroed on 3.09.2015.
 */
public class CrossBrowserSmokeTest implements SauceOnDemandSessionIdProvider, SauceOnDemandAuthenticationProvider {
        /**
         * Constructs a {@link SauceOnDemandAuthentication} instance using the supplied user name/access key.  To use the authentication
         * supplied by environment variables or from an external file, use the no-arg {@link SauceOnDemandAuthentication} constructor.
         */
        public SauceOnDemandAuthentication authentication = new SauceOnDemandAuthentication("acidroed", "92323898-2a02-4168-bb72-c39330136cc6");

    /**
         * ThreadLocal variable which contains the  {@link WebDriver} instance which is used to perform browser interactions with.
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
         * Constructs a new {@link RemoteWebDriver} instance which is configured to use the capabilities defined by the browser,
         * version and os parameters, and which is configured to run against ondemand.saucelabs.com, using
         * the username and access key populated by the {@link #authentication} instance.
         *
         * @param browser Represents the browser to be used as part of the test run.
         * @param version Represents the version of the browser to be used as part of the test run.
         * @param os Represents the operating system to be used as part of the test run.
         * @return
         * @throws MalformedURLException if an error occurs parsing the url
         */
        private WebDriver createDriver(String browser, String version, String os) throws MalformedURLException {

            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability(CapabilityType.BROWSER_NAME, browser);
            if (version != null) {
                capabilities.setCapability(CapabilityType.VERSION, version);
            }
            capabilities.setCapability(CapabilityType.PLATFORM, os);
            capabilities.setCapability("name", "EcomapSmokeTest");
            webDriver.set(new RemoteWebDriver(
                    new URL("http://" + authentication.getUsername() + ":" + authentication.getAccessKey() + "@ondemand.saucelabs.com:80/wd/hub"),
                    capabilities));
            sessionId.set(((RemoteWebDriver) getWebDriver()).getSessionId().toString());
            return webDriver.get();
        }

        /**
         *
         * @param browser Represents the browser to be used as part of the test run.
         * @param version Represents the version of the browser to be used as part of the test run.
         * @param os Represents the operating system to be used as part of the test run.
         * @throws Exception if an error occurs during the running of the test
         */

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
            List<String>    imageURLs       = null;
            List<String>    imageComments   = null;


            WebDriver driver = createDriver(browser, version, os);

            driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
            driver.get("http://acidroed.pagekite.me/#/map");
            driver.manage().window().maximize();

            AnyPage anyPage     = new AnyPage(driver);
            ProblemPage problemPage = new ProblemPage(driver);


            anyPage.register(newUserFirstName, newUserLastName, newUserEmail, newUserPassword);
            anyPage.addProblemToVisibleCenter(latitude, longitude, problemTitle, problemType, problemDescription, problemSolution,
                    imageURLs, imageComments);
            anyPage.logOut();

            anyPage.logIn(adminEmail, adminPassword);

            try {
                Thread.sleep(5000);
            } catch (Exception e) {
                e.printStackTrace();
            }



            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }

            problemPage.clickAtProblemByCoordinateVisible(latitude, longitude);
            Assert.assertEquals(problemPage.getProblemTitle(), problemTitle);


            anyPage.logOut();

            driver.quit();




        }

        /**
         * @return the {@link WebDriver} for the current thread
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
         * @return the {@link SauceOnDemandAuthentication} instance containing the Sauce username/access key
         */
        @Override
        public SauceOnDemandAuthentication getAuthentication() {
            return authentication;
        }
}
