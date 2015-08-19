package com.saucelabs;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Yermek on 02.11.2014.
 */
public class ClickCheck {
    @Test
    public void clickAtVisibleMapCenter() {
        double latitude = 50.2;
        double longitude = 30.2;
        String problemNameTest = "problemNameTest";
        String problemTypeTest = "Загрози біорізноманіттю";
        String problemDescriptionTest = "problemDescriptionTest";
        String problemProposeTest = "problemProposeTest";
        List<String> imageUrls = Arrays.asList("http://i.imgur.com/HHXCVbs.jpg", "http://i.imgur.com/1K6AdCH.jpg");
        List<String> imageComments = Arrays.asList("comment1", "comment2");
        WebDriver driver = new FirefoxDriver();
        AnyPage anyPage = new AnyPage(driver);

        driver.get("http://localhost:8090/#/map");
        //driver.get("http://176.36.11.25/#/map");

        anyPage.addProblemToVisibleCenter(latitude, longitude,
                problemNameTest, problemTypeTest, problemDescriptionTest, problemProposeTest, imageUrls, imageComments);

        Assert.assertTrue(true);
        driver.quit();
    }
}
