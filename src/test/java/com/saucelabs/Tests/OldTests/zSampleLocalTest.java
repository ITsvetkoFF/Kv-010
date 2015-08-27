package com.saucelabs.Tests.OldTests;

import com.saucelabs.AnyPage;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by ykadytc on 21.10.2014.
 */
public class zSampleLocalTest {
    @Test
    public void igor() {
        WebDriver driver = new FirefoxDriver();

        driver.get("http://localhost:8090");
       /* JavascriptExecutor js = null;
        if (driver instanceof JavascriptExecutor) {
            js = (JavascriptExecutor) driver;
        }
        ;
        js.executeScript("navigator.geolocation.getCurrentPosition = function(success) { success({coords: {latitude: 50.649460, longitude: 30.731506}}); }");
        */AnyPage anyPage = new AnyPage(driver);
        // Load the page in the browser
        Assert.assertTrue(anyPage.getFirstResourceTitleFromMenu().equals(anyPage.getFirstResourceTitleFromOpenedResource()));
        driver.quit();
    }
}
